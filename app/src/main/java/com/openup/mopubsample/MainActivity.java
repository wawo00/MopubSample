package com.openup.mopubsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.applovin.sdk.AppLovinSdk;
import com.mopub.common.MoPub;
import com.mopub.common.SdkConfiguration;
import com.mopub.common.SdkInitializationListener;
import com.mopub.mobileads.MintegralAdapterConfiguration;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubView;

import static com.mopub.common.logging.MoPubLog.LogLevel.DEBUG;
import static com.mopub.common.logging.MoPubLog.LogLevel.INFO;

public class MainActivity extends AppCompatActivity implements MoPubView.BannerAdListener, SdkInitializationListener {
    public final static String TAG = "Roy_mopub";
    private static String AD_BANNER_UNIT="8bf4e3fcff1e467f87f2ef163e3d2fe5";
    MoPubView mMoPubView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMoPubView = findViewById(R.id.adview);
        mMoPubView.setAdSize(MoPubView.MoPubAdSize.HEIGHT_50);
        mMoPubView.setAdUnitId(AD_BANNER_UNIT);
        final SdkConfiguration.Builder configBuilder = new SdkConfiguration.Builder(
                AD_BANNER_UNIT);
        if (BuildConfig.DEBUG) {
            configBuilder.withLogLevel(DEBUG);
        } else {
            configBuilder.withLogLevel(INFO);
        }

        configBuilder.withAdditionalNetwork(MintegralAdapterConfiguration.class.getName());
        MintegralAdapterConfiguration.setAge(25);
        MoPub.initializeSdk(this,configBuilder.build(),this);
        AppLovinSdk.initializeSdk(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMoPubView.destroy();
    }

    @Override
    public void onBannerLoaded(MoPubView banner) {
        Log.i(TAG, "onBannerLoaded: ");
    }

    @Override
    public void onBannerFailed(MoPubView banner, MoPubErrorCode errorCode) {
        Log.i(TAG, "onBannerFailed: " + errorCode);
    }

    @Override
    public void onBannerClicked(MoPubView banner) {
        Log.i(TAG, "onBannerClicked: ");
    }

    @Override
    public void onBannerExpanded(MoPubView banner) {
        Log.i(TAG, "onBannerExpanded: ");

    }

    @Override
    public void onBannerCollapsed(MoPubView banner) {
        Log.i(TAG, "onBannerCollapsed: ");

    }

    @Override
    public void onInitializationFinished() {
        Log.i(TAG, "onInitializationFinished: ");

        mMoPubView.loadAd();
        mMoPubView.setBannerAdListener(this);
        mMoPubView.setAutorefreshEnabled(false);//设置为true会自动刷新走loaded 回调
    }

    public void showVideo(View view) {
        startActivity(new Intent(this,VideoActivity.class));
    }

    public void showInter(View view) {
        startActivity(new Intent(this,InterActivity.class));
    }
}
