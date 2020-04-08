package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;

import com.example.jokeslibrary.JokeActivity;

import java.util.Objects;

/**
 * A placeholder fragment containing a simple view.
 */
public final class MainActivityFragment extends Fragment implements OnJokeListener {

    private View rootView;
    private Button tellButton;
    private ProgressBar tellProgress;
    private String joke;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_main, container, false);

        tellProgress = rootView.findViewById(R.id.progress_tell);
        tellButton = rootView.findViewById(R.id.btn_tell);
        tellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getJoke();
            }
        });

        return rootView;
    }

    @Override
    public void onJokeLoaded(String joke) {
        startJokeActivity();
        setVisibleProgress(false);
    }

    private void startJokeActivity() {
        Intent intent = new Intent(getActivity(), JokeActivity.class);
        intent.putExtra("KeyJoke", joke);
        startActivity(intent);
    }

    private void getJoke() {
        new EndpointsAsyncTask().execute(this);
        setVisibleProgress(true);
    }

    private void setVisibleProgress(Boolean visible) {
        if (visible) {
            tellProgress.setVisibility(View.VISIBLE);
        } else {
            tellProgress.setVisibility(View.INVISIBLE);
        }
    }
}