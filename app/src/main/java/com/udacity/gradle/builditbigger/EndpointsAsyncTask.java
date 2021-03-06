package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;
import java.util.Objects;

public final class EndpointsAsyncTask extends AsyncTask<OnJokeListener, Void, String> {

    private static MyApi myApiService = null;
    private OnJokeListener jokeListener;

    @Override
    protected String doInBackground(OnJokeListener... params) {
        if (myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(new NetHttpTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }

        jokeListener = params[0];

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            Log.e("Log AsyncTask", Objects.requireNonNull(e.getMessage()));
            return "";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        jokeListener.onJokeLoaded(result);
    }
}