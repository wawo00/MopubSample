package com.openup.mopubsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mopub.common.MoPub;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;

public class InterActivity extends AppCompatActivity implements MoPubInterstitial.InterstitialAdListener {
    public final static String TAG = "Roy_mopub";
    private static final String AD_INTER_UNIT = "e80371c8e58749d8bd191283ec033bb8";

    private MoPubInterstitial mMoPubInterstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inter_layout);
        mMoPubInterstitial = new MoPubInterstitial(this, AD_INTER_UNIT);
        mMoPubInterstitial.setInterstitialAdListener(this);
        mMoPubInterstitial.load();
    }

    @Override
    public void onInterstitialLoaded(MoPubInterstitial interstitial) {
        Log.i(TAG, "onInterstitialLoaded: ");
    }

    @Override
    public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
        Log.i(TAG, "onInterstitialFailed: " + errorCode);
    }

    @Override
    public void onInterstitialShown(MoPubInterstitial interstitial) {
        Log.i(TAG, "onInterstitialShown: ");
    }

    @Override
    public void onInterstitialClicked(MoPubInterstitial interstitial) {
        Log.i(TAG, "onInterstitialClicked: ");
    }

    @Override
    public void onInterstitialDismissed(MoPubInterstitial interstitial) {
        Log.i(TAG, "onInterstitialDismissed: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        MoPub.onPause(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MoPub.onStop(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MoPub.onResume(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMoPubInterstitial.destroy();
    }

    public void showInter(View view) {
        if (mMoPubInterstitial.isReady()) {
            mMoPubInterstitial.show();
        } else {
            Toast.makeText(this, " inter is not ready", Toast.LENGTH_SHORT).show();
        }
    }
}
