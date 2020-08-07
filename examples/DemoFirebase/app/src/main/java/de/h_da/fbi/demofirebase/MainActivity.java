package de.h_da.fbi.demofirebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.core.OrderBy;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private final String COLLECTION_NAME_USERS = "users";
    private final String COLLECTION_NAME_MESSAGES = "messages";
    final String PREF_KEY_USER = "userId";
    final String PREF_KEY_NICKNAME = "nickname";
    private String nickname;
    private String userId;
    private List<User> allUsers = new ArrayList<>();
    FirebaseFirestore db;

    //ui-elements
    EditText txtMessage, txtNickname;
    RecyclerView rvMessages;
    Button btnSend, btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        //Layout für die Anordnung der Elemente
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvMessages.setLayoutManager(layoutManager);

        db = FirebaseFirestore.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth != null) {
            auth.signInAnonymously().addOnFailureListener(ex -> {
                Toast.makeText(getApplicationContext(), "no connection to database " + ex.getMessage(), Toast.LENGTH_LONG).show();
            }).addOnSuccessListener(result -> {
                iniUser();
                listenToChanges();
            });
        }


    }

    private void listenToChanges(){
        db.collection(COLLECTION_NAME_MESSAGES).addSnapshotListener((snapshot, e) -> {
            if (e != null) {
                Log.w(TAG, "Listen failed.", e);
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
        if (allUsers.stream().filter(o -> o.getNickname().equals(nickname)).findFirst().isPresent()) {
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
            }).addOnFailureListener(ex -> {
                        Toast.makeText(getApplicationContext(), "Error getting data!!!" + ex.getMessage(), Toast.LENGTH_LONG).show();
                    }
            );
        } else {
            txtMessage.setVisibility(View.VISIBLE);
            btnSend.setVisibility(View.VISIBLE);
        }
    }


}
