package de.h_da.fbi.demorest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResponse, textViewArtistName;
    private EditText editTextUrl;
    private ImageView imageViewArtist;
    private ProgressBar busyIndicator;
    private Gson gson;
    private WorkManager workManager;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResponse = findViewById(R.id.textViewResponse);
        editTextUrl = findViewById(R.id.editTextUrl);
        textViewArtistName = findViewById(R.id.textViewArtistName);
        imageViewArtist = findViewById(R.id.imageViewArtist);
        busyIndicator = findViewById(R.id.busyIndicator);
        btnCancel = findViewById(R.id.buttonCancel);
        btnCancel.setVisibility(View.GONE);
        gson = new Gson();
        workManager = WorkManager.getInstance(this.getApplication());
    }


    public void sendStringRequest(View view) {
        //@see https://developer.android.com/training/volley/simple
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://www.google.de";
        if (editTextUrl.getText().length() > 0)
            url = editTextUrl.getText().toString();

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                (response) -> textViewResponse.setText("Response (string): " + response.substring(0, Math.min(response.length() - 1, 500)))

                , (error) -> textViewResponse.setText("That didn't work! " + error.getMessage())

        );

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void sendJsonRequest(View view) {
        //@see https://app.swaggerhub.com/apis/Bandsintown/PublicAPI/3.0.0
        String url = "https://rest.bandsintown.com/artists/paddy%20goes%20to%20holyhead?app_id=510";
        //Zugriff auf localhost 127.0.0.1 ist loopback des Android-Geräts, daher auf ip-Adresse des Rechners zugreifen (ipconfig in cmd eingeben)
        //@see https://developer.android.com/training/volley/request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null,
                        (response) -> {
                            //JSONObject httpResponse = new JSONObject(result);
                            //String httpStatus = httpResponse.getString("status");
                            textViewResponse.setText("Response (json): " + response.toString());
                            try {
                                JSONObject jsonObject = new JSONObject(response.toString());
                                //ggf. JSONArray jsonArray = jsonObject.getJSONArray("Data");
                                // if (jsonArray.length() > 0) {
                                //    artists = Arrays.asList(gson.fromJson(jsonArray.toString(), ArtistData[].class));
                                ArtistData artist = gson.fromJson(jsonObject.toString(), ArtistData.class);
                                if (artist != null) {
                                    Glide.with(this).load(artist.getImageUrl()).fitCenter().into(imageViewArtist);
                                    textViewArtistName.setText(artist.getName());
                                }
                            } catch (JSONException e) {
                                textViewResponse.setText("JSON problem " + e.getMessage());
                            }
                        }
                        , (error) -> textViewResponse.setText("That didn't work! " + error.getMessage())
                );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
    }

    public void sendAsWorkManager(View view) {
        final String address = editTextUrl.getText().toString();
        busyIndicator.setVisibility(View.VISIBLE);
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        Data inputData = new Data.Builder()
                .putString(RequestWorker.INPUT_KEY_ADDRESS, address)
                .build();
        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(RequestWorker.class).setInputData(inputData).setConstraints(constraints).build();
        workManager.getWorkInfoByIdLiveData(request.getId()).observe(this, workInfo -> {
            if (workInfo == null) return;

            if (workInfo.getState().isFinished()) {
                busyIndicator.setVisibility(View.GONE);
                btnCancel.setVisibility(View.GONE);
                btnCancel.setOnClickListener(null);
                Data outputData = workInfo.getOutputData();
                String response = outputData.getString(RequestWorker.OUTPUT_KEY_RESPONSE);
                if (workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                    textViewResponse.setText("response (worker succeeded): " + response);
                } else if (workInfo.getState() == WorkInfo.State.FAILED) {
                    textViewResponse.setText("response (worker failed): " + response);
                }
            } else if (workInfo.getRunAttemptCount() > 0) {
                textViewResponse.setText(String.format("response (worker): number tries: %d", workInfo.getRunAttemptCount()));
            }
        });
        btnCancel.setVisibility(View.VISIBLE);
        btnCancel.setOnClickListener(v -> {
            workManager.cancelWorkById(request.getId());
            btnCancel.setVisibility(View.GONE);
            textViewResponse.setText("cancelled worker");
        });
        workManager.enqueue(request);
    }

    public void sendAsThread(View view) {
        final String address = editTextUrl.getText().toString();
        new Thread(() -> {
            String response = getResponse(address);
            textViewResponse.post(() -> textViewResponse.setText("response (thread): " + response));
        }).start();
    }

    public static String getResponse(String address) {
        try {
            final URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            if (conn.getResponseCode() != 200)
                return String.format("problem - response code: %d", conn.getResponseCode());

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (Exception ex) {
            return "error:" + ex.getMessage();
        }
    }
}