package com.prodege.sample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.prodege.Prodege;
import com.prodege.builder.InitOptions;
import com.prodege.listener.ProdegeEventListener;
import com.prodege.listener.ProdegeException;
import com.prodege.listener.ProdegeInitListener;
import com.prodege.listener.ProdegeReward;
import com.prodege.listener.ProdegeRewardListener;
import com.prodege.listener.ProdegeRewardedInfo;
import com.prodege.listener.ProdegeRewardedLoadListener;
import com.prodege.listener.ProdegeShowListener;

public class MainActivity extends Activity implements
        ProdegeInitListener,
        ProdegeShowListener,
        ProdegeRewardedLoadListener,
        ProdegeEventListener,
        ProdegeRewardListener {

    private static final String TAG = "MainActivity";

    private Button coinsBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        coinsBtn = findViewById(R.id.coins_btn);

        coinsBtn.setOnClickListener(v -> onShowClick());
        coinsBtn.setVisibility(View.GONE);

        InitOptions initOptions = new InitOptions.Builder().testMode(true).build();

        if (!Prodege.isInitialized()) {
            Prodege.setRewardListener(this);
            Prodege.setEventListener(this);
            Prodege.initialize(this, getString(R.string.prodege_api_key), this, initOptions);
        }
    }

    private void loadRewardedAd() {
        Prodege.loadRewardedAd(getString(R.string.prodege_rewarded_placement_id), this, null);
    }

    private void onShowClick() {
        Prodege.showPlacement(getString(R.string.prodege_rewarded_placement_id), this, null);
    }

    @Override
    public void onError(@NonNull ProdegeException e) {
        Log.e(TAG, getString(R.string.on_prodege_sdk_init_error, e.getMessage()));
    }

    @Override
    public void onSuccess() {
        Log.d(TAG, getString(R.string.on_prodege_sdk_init_success));

        loadRewardedAd();
    }

    @Override
    public void onClosed(@NonNull String placementId) {
        Log.d(TAG, getString(R.string.on_placement_closed, placementId));

        if (!Prodege.isPlacementLoaded(placementId)) {
            loadRewardedAd();
        }
    }

    @Override
    public void onOpened(@NonNull String placementId) {
        Log.d(TAG, getString(R.string.on_placement_opened, placementId));
    }

    @Override
    public void onShowFailed(@NonNull String placementId, @NonNull ProdegeException e) {
        Log.e(TAG, getString(R.string.on_placement_failed_to_show, placementId, e.getMessage()));
    }

    @Override
    public void onRewardedLoadFailed(@NonNull String placementId, @NonNull ProdegeException e) {
        Log.e(TAG, getString(R.string.on_rewarded_placement_load_failed, placementId, e.getMessage()));
    }

    @Override
    public void onRewardedLoaded(@NonNull String placementId, @NonNull ProdegeRewardedInfo prodegeRewardedInfo) {
        Log.d(TAG, getString(R.string.on_rewarded_placement_loaded, placementId));
        coinsBtn.setVisibility(View.VISIBLE);
        coinsBtn.setText(getString(R.string.win_coins, prodegeRewardedInfo.getPoints(), prodegeRewardedInfo.getCurrency()));
    }

    @Override
    public void onRewardReceived(@NonNull ProdegeReward prodegeReward) {
        String message = getString(R.string.on_prodege_reward_received, prodegeReward.getPoints(), prodegeReward.getCurrency());
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Log.d(TAG, message);
    }

    @Override
    public void onClick(@NonNull String placementId) {
        Log.d(TAG, getString(R.string.on_prodege_placement_click, placementId));
    }

    @Override
    public void onComplete(@NonNull String placementId) {
        coinsBtn.setVisibility(View.GONE);
        Log.d(TAG, getString(R.string.on_prodege_placement_completed, placementId));
    }

    @Override
    public void onStart(@NonNull String placementId) {
        Log.d(TAG, getString(R.string.on_prodege_placement_started, placementId));
    }

    @Override
    public void onUserNotEligible(@NonNull String placementId) {
        coinsBtn.setVisibility(View.GONE);
        Log.d(TAG, getString(R.string.on_user_not_eligible, placementId));
    }

    @Override
    public void onUserReject(@NonNull String placementId) {
        coinsBtn.setVisibility(View.GONE);
        Log.d(TAG, getString(R.string.on_user_reject, placementId));
    }
}
