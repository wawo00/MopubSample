package com.openup.mopubsample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mopub.common.MoPub;
import com.mopub.common.MoPubReward;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubRewardedVideoListener;
import com.mopub.mobileads.MoPubRewardedVideos;

import java.util.Set;

public class VideoActivity extends AppCompatActivity implements MoPubRewardedVideoListener {
    public final static String TAG = "Roy_mopub";
    private static final String AD_VIDEO_UNIT = "740ec0f038704657a8e7bb124730abee";
    // 重新请求次数
    private int retryLoad = 0;

    //最大请求次数
    private int maxRetryLoad=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        MoPub.onCreate(this);
        MoPubRewardedVideos.loadRewardedVideo(AD_VIDEO_UNIT);
        MoPubRewardedVideos.setRewardedVideoListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        MoPub.onStart(this);
    }

    @Override
    public void onRestart() {
        super.onRestart();
        MoPub.onRestart(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MoPub.onPause(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        MoPub.onStop(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        MoPub.onResume(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MoPub.onDestroy(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MoPub.onBackPressed(this);
    }

    @Override
    public void onRewardedVideoLoadSuccess(@NonNull String adUnitId) {
        Log.i(TAG, "onRewardedVideoLoadSuccess: ");
    }

    @Override
    public void onRewardedVideoLoadFailure(@NonNull String adUnitId, @NonNull MoPubErrorCode errorCode) {
        Log.i(TAG, "onRewardedVideoLoadFailure: " + errorCode);
        if (retryLoad<maxRetryLoad){
            MoPubRewardedVideos.loadRewardedVideo(AD_VIDEO_UNIT);
            retryLoad++;
        }
    }

    @Override
    public void onRewardedVideoStarted(@NonNull String adUnitId) {
        Log.i(TAG, "onRewardedVideoStarted: ");
    }

    @Override
    public void onRewardedVideoPlaybackError(@NonNull String adUnitId, @NonNull MoPubErrorCode errorCode) {
        Log.i(TAG, "onRewardedVideoPlaybackError: " + errorCode);
    }

    @Override
    public void onRewardedVideoClicked(@NonNull String adUnitId) {
        Log.i(TAG, "onRewardedVideoClicked: ");
    }

    @Override
    public void onRewardedVideoClosed(@NonNull String adUnitId) {
        Log.i(TAG, "onRewardedVideoClosed: ");
        MoPubRewardedVideos.loadRewardedVideo(AD_VIDEO_UNIT);
    }

    @Override
    public void onRewardedVideoCompleted(@NonNull Set<String> adUnitIds, @NonNull MoPubReward reward) {
        Log.i(TAG, "onRewardedVideoCompleted: reward " + reward);
    }

    public void showReward(View view) {
        if (MoPubRewardedVideos.hasRewardedVideo(AD_VIDEO_UNIT)) {
            MoPubRewardedVideos.showRewardedVideo(AD_VIDEO_UNIT);
        } else {
            Log.i(TAG, "no ads to show: ");
        }
    }
}
