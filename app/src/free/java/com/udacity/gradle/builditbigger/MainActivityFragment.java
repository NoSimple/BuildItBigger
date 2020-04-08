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
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Objects;

/**
 * A placeholder fragment containing a simple view.
 */
public final class MainActivityFragment extends Fragment implements OnJokeListener {

    private View rootView;
    private Button tellButton;
    private ProgressBar tellProgress;
    private AdView adView;
    private InterstitialAd interstitialAd;
    private String joke;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        adView = rootView.findViewById(R.id.ad_view);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);

        interstitialAd = new InterstitialAd(Objects.requireNonNull(getContext()));
        interstitialAd.setAdUnitId(getString(R.string.banner_ad_unit_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                startJokeActivity();
            }
        });

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
        this.joke = joke;
        if (interstitialAd.isLoaded() && interstitialAd != null) {
            interstitialAd.show();
            startJokeActivity();
        } else {
            startJokeActivity();
        }
        setVisibleProgress(false);
    }

    private void startJokeActivity() {
        Intent intent = new Intent(getActivity(), JokeActivity.class);
        intent.putExtra("KeyJoke", joke);
        startActivity(intent);
    }

    private void getJoke(){
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