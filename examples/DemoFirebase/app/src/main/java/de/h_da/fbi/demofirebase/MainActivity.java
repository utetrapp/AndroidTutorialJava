package de.h_da.fbi.demofirebase;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    final String PREF_KEY_USER = "userId";
    final String PREF_KEY_NICKNAME = "nickname";
    private final String TAG = "MainActivity";
    private final String COLLECTION_NAME_USERS = "users";
    private final String COLLECTION_NAME_MESSAGES = "messages";
    FirebaseFirestore db;
    //ui-elements
    EditText txtMessage, txtNickname;
    RecyclerView rvMessages;
    Button btnSend, btnSignIn, btnConnect;
    private View mainLayout;
    private String nickname;
    private String userId;
    private List<User> allUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLayout = findViewById(R.id.mainLayout);
        txtMessage = findViewById(R.id.txtMessage);
        txtNickname = findViewById(R.id.txtNickname);
        rvMessages = findViewById(R.id.rvMessages);
        btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(view -> sendMessage());
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(view -> signIn());
        txtNickname.setVisibility(View.GONE);
        txtMessage.setVisibility(View.GONE);
        btnSend.setVisibility(View.GONE);
        btnSignIn.setVisibility(View.GONE);
        btnConnect = findViewById(R.id.btnConnect);
        btnConnect.setOnClickListener(view -> connectToFirebase());
        //Layout für die Anordnung der Elemente
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvMessages.setLayoutManager(layoutManager);
    }

    private void connectToFirebase() {
        db = FirebaseFirestore.getInstance();
        //@see https://firebase.google.com/docs/auth/android/anonymous-auth
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInAnonymously()//not for production!!!
                .addOnFailureListener(ex -> Toast.makeText(getApplicationContext(), "no connection to database " + ex.getMessage(), Toast.LENGTH_LONG).show())
                .addOnSuccessListener(result -> {
                    Toast.makeText(getApplicationContext(), "sign in succeeded", Toast.LENGTH_LONG).show();
                    btnConnect.setVisibility(View.GONE);
                    iniUser();
                    listenToChanges();
                });
    }

    private void listenToChanges() {
        //@see https://firebase.google.com/docs/firestore/query-data/listen
        db.collection(COLLECTION_NAME_MESSAGES).addSnapshotListener((snapshot, e) -> {
            if (e != null) {
                Toast.makeText(getApplicationContext(), "listen to messages failed", Toast.LENGTH_LONG).show();
                return;
            }

            if (snapshot != null) {
                loadMessages();
            }
        });
    }

    private void sendMessage() {
        if (db != null && !userId.isEmpty()) {
            db.collection(COLLECTION_NAME_MESSAGES).add(new Message(txtMessage.getText().toString().trim(), userId, nickname));
            txtMessage.setText("");
        }
    }

    private void loadMessages() {
        if (db != null)
            db.collection(COLLECTION_NAME_MESSAGES).orderBy("timestamp", Query.Direction.ASCENDING).get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            List<Message> messages = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                messages.add(document.toObject(Message.class));
                            }
                            rvMessages.setAdapter(new MessageAdapter(MainActivity.this, messages, userId));
                            rvMessages.scrollToPosition(messages.size() - 1);
                        } else {
                            Log.e(TAG, "Error getting messages: ", task.getException());
                            Toast.makeText(getApplicationContext(), "error reading messages ", Toast.LENGTH_LONG).show();
                        }
                    });
    }

    private void signIn() {
        nickname = txtNickname.getText().toString().trim();
        if (nickname.isEmpty()) {
            txtNickname.setError("nickname is mandatory");
            return;
        }
        if (allUsers == null) {
            txtNickname.setError("unable to fetch existing users, please try again later");
            return;
        }
        if (allUsers.stream().anyMatch(o -> o.getNickname().equals(nickname))) {
            txtNickname.setError("nickname exists");
            return;
        }
        db.collection(COLLECTION_NAME_USERS).add(new User(nickname)).addOnSuccessListener(documentReference -> {
            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
            userId = documentReference.getId();
            editor.putString(PREF_KEY_USER, userId);
            editor.putString(PREF_KEY_NICKNAME, nickname);
            editor.apply();
            txtNickname.setVisibility(View.GONE);
            btnSignIn.setVisibility(View.GONE);
            txtMessage.setVisibility(View.VISIBLE);
            btnSend.setVisibility(View.VISIBLE);
        })
                .addOnFailureListener(e -> {
                    Log.w(TAG, "Error signing in", e);
                    txtNickname.setError("sign in failed, try again later");
                });
    }

    private void iniUser() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());//so für alle Activities verfügbar, falls nur hier gebraucht: this.getPreferences(Context.MODE_PRIVATE);
        userId = sharedPref.getString(PREF_KEY_USER, "");
        nickname = sharedPref.getString(PREF_KEY_NICKNAME, "");
        if (userId.isEmpty() || nickname.isEmpty()) {
            db.collection("users").get().addOnSuccessListener(documentSnapshots -> {
                if (documentSnapshots.isEmpty()) {
                    Log.d(TAG, "no users so far");
                } else {
                    allUsers = documentSnapshots.toObjects(User.class);
                }
                txtNickname.setVisibility(View.VISIBLE);
                btnSignIn.setVisibility(View.VISIBLE);
            }).addOnFailureListener(ex -> Toast.makeText(getApplicationContext(), "Error getting data!!!" + ex.getMessage(), Toast.LENGTH_LONG).show()
            );
        } else {
            txtMessage.setVisibility(View.VISIBLE);
            btnSend.setVisibility(View.VISIBLE);
        }
    }
}
