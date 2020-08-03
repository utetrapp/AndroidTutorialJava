package de.h_da.fbi.demorest;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class RequestWorker extends Worker {
    public final static String INPUT_KEY_ADDRESS = "address";
    public final static String OUTPUT_KEY_RESPONSE = "response";
    public final static int MAX_RESPONSE_SIZE = 5000;

    public RequestWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {
        //Context applicationContext = getApplicationContext();
        try {
            Thread.sleep(1000);//simulate long connection
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String address = getInputData().getString(INPUT_KEY_ADDRESS);
        String response = MainActivity.getResponse(address);
        //maximum to be returned is 10240
        response = response.substring(0, Math.min(MAX_RESPONSE_SIZE, response.length() - 1));
        Data outputData = new Data.Builder()
                .putString(OUTPUT_KEY_RESPONSE, response)
                .build();
        if (response.startsWith("problem"))
            return Result.retry();
        else if (response.startsWith("error"))
            return Result.failure(outputData);
        else
            return Result.success(outputData);
    }
}
