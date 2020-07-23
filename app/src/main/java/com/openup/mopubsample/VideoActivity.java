package com.openup.mopubsample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.mopub.common.MoPub;
import com.mopub.common.MoPubReward;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;
import com.mopub.mobileads.MoPubRewardedVideoListener;
import com.mopub.mobileads.MoPubRewardedVideos;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VideoActivity extends AppCompatActivity implements MoPubInterstitial.InterstitialAdListener {
    public final static String TAG = "Roy_mopub";
    private static final String AD_VIDEO_UNIT1 = "fefa0938d82e46c39712dc2fe53acc17";
    private static final String AD_VIDEO_UNIT2 = "276a23fbc6014aa79b516595600de907";
    private static final String AD_VIDEO_UNIT3 = "c9038acbeffd4b7987bf8bc5eb43de20";
    private static final String AD_VIDEO_UNIT4 = "d3597e44f97241019d42d427be7b10a0";
    private static final String AD_VIDEO_UNIT5 = "1a9eebf7253f44d9b6cd88f9e800d33f";

    private static final String AD_INTER_UNIT1 = "8be13314ba89422db379595e7f7a9e2c";
    private static final String AD_INTER_UNIT2 = "a7622ff872b54997b141d89403b99361";
    private static final String AD_INTER_UNIT3 = "c720ee3edf894373956f89f012ef87ee";

    private MoPubInterstitial mMoPubInterstitial1,mMoPubInterstitial2,mMoPubInterstitial3;

    // 重新请求次数
    private int retryLoad = 0;

    //最大请求次数
    private int maxRetryLoad = 3;
    List<String> adUnitIds;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        MoPub.onCreate(this);
//        MoPubRewardedVideos.loadRewardedVideo(AD_VIDEO_UNIT);
        adUnitIds = new ArrayList<>();
        adUnitIds.add(AD_VIDEO_UNIT5);
        adUnitIds.add(AD_VIDEO_UNIT1);
        adUnitIds.add(AD_VIDEO_UNIT2);
        adUnitIds.add(AD_VIDEO_UNIT3);
        adUnitIds.add(AD_VIDEO_UNIT4);

//        mMoPubInterstitial1 = new MoPubInterstitial(this, AD_INTER_UNIT1);
//        mMoPubInterstitial2 = new MoPubInterstitial(this, AD_INTER_UNIT2);
//        mMoPubInterstitial3 = new MoPubInterstitial(this, AD_INTER_UNIT3);
//
//        mMoPubInterstitial1.setInterstitialAdListener(this);
//        mMoPubInterstitial1.load();
//
//        mMoPubInterstitial2.setInterstitialAdListener(this);
//        mMoPubInterstitial2.load();
//
//        mMoPubInterstitial3.setInterstitialAdListener(this);
//        mMoPubInterstitial3.load();


        for (String adUnitId:adUnitIds) {
            MoPubRewardedVideos.loadRewardedVideo(adUnitId);
            MoPubRewardedVideos.setRewardedVideoListener(new MyListener(adUnitId));

        }

        HandlerThread handlerThread=new HandlerThread(TAG);
        handlerThread.start();
        handler=new Handler(handlerThread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                while(true){
                    for (String adUnitId:adUnitIds) {
                        try {
                            Thread.sleep(1*1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.i(TAG, adUnitId+"---hasRewardVideo: "+ MoPubRewardedVideos.hasRewardedVideo(adUnitId));
                    }

                }
            }
        };
        handler.sendEmptyMessage(1);



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


    public void showReward(View view) {
//        if (MoPubRewardedVideos.hasRewardedVideo(AD_VIDEO_UNIT)) {
//            MoPubRewardedVideos.showRewardedVideo(AD_VIDEO_UNIT);
//        } else {
//            Log.i(TAG, "no ads to show: ");
//        }
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

    class MyListener implements MoPubRewardedVideoListener {

        private String originalAdUnitId;

        public MyListener(String originalAdUnitId) {
            this.originalAdUnitId = originalAdUnitId;
        }

        @Override
        public void onRewardedVideoLoadSuccess(@NonNull String adUnitId) {
            Log.i(TAG, "onRewardedVideoLoadSuccess: "+adUnitId);

        }

        @Override
        public void onRewardedVideoLoadFailure(@NonNull String adUnitId, @NonNull MoPubErrorCode errorCode) {
            Log.i(TAG, "onRewardedVideoLoadFailure: " + errorCode+" adunitid "+adUnitId);

//            if (retryLoad < maxRetryLoad) {
//                MoPubRewardedVideos.loadRewardedVideo(AD_VIDEO_UNIT);
//                retryLoad++;
//            }
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
//            MoPubRewardedVideos.loadRewardedVideo(AD_VIDEO_UNIT);
        }

        @Override
        public void onRewardedVideoCompleted(@NonNull Set<String> adUnitIds, @NonNull MoPubReward reward) {
            Log.i(TAG, "onRewardedVideoCompleted: reward " + reward);
        }

    }
}
