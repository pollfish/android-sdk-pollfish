package com.pollfish.sample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.pollfish.Pollfish;
import com.pollfish.builder.Params;
import com.pollfish.callback.PollfishClosedListener;
import com.pollfish.callback.PollfishOpenedListener;
import com.pollfish.callback.PollfishSurveyCompletedListener;
import com.pollfish.callback.PollfishSurveyNotAvailableListener;
import com.pollfish.callback.PollfishSurveyReceivedListener;
import com.pollfish.callback.PollfishUserNotEligibleListener;
import com.pollfish.callback.SurveyInfo;

import org.jetbrains.annotations.NotNull;

public class OfferwallActivity extends Activity implements
        PollfishSurveyCompletedListener,
        PollfishSurveyReceivedListener,
        PollfishOpenedListener,
        PollfishClosedListener,
        PollfishSurveyNotAvailableListener,
        PollfishUserNotEligibleListener {

    private static final String TAG = "OfferwallActivity";

    private Button coinsBtn;
    private TextView loggingTxt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_offerwall);

        loggingTxt = findViewById(R.id.logText);
        coinsBtn = findViewById(R.id.coins_btn);

        coinsBtn.setOnClickListener(v -> Pollfish.show());
        coinsBtn.setVisibility(View.INVISIBLE);

        Button activityBackBtn = findViewById(R.id.activity_back_btn);
        activityBackBtn.setOnClickListener(v -> finish());

        initPollfish();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (!Pollfish.isPollfishPresent()) {
            initPollfish();
            loggingTxt.setText(R.string.logging);
            coinsBtn.setVisibility(View.INVISIBLE);
        } else {
            coinsBtn.setVisibility(View.VISIBLE);
            coinsBtn.setText(R.string.offerwall);
            loggingTxt.setText(R.string.offerwall_available);
        }
    }

    private void initPollfish() {
        Params params = new Params.Builder("YOUR_API_KEY")
                .rewardMode(true)
                .offerwallMode(true)
                .build();

        Pollfish.initWith(this, params);
    }

    @Override
    public void onPollfishSurveyCompleted(@NotNull SurveyInfo surveyInfo) {
        loggingTxt.setText(R.string.survey_offerwall_completed);
        Toast.makeText(this, getString(R.string.survey_offerwall_completed), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPollfishSurveyReceived(SurveyInfo surveyInfo) {
        coinsBtn.setVisibility(View.VISIBLE);
        coinsBtn.setText(R.string.offerwall);
        loggingTxt.setText(R.string.offerwall_available);
    }

    @Override
    public void onPollfishClosed() {
        Log.d(TAG, getString(R.string.on_pollfish_closed));
    }

    @Override
    public void onPollfishOpened() {
        Log.d(TAG, getString(R.string.on_pollfish_opened));
    }

    @Override
    public void onPollfishSurveyNotAvailable() {
        Log.d(TAG, getString(R.string.survey_not_available));
        loggingTxt.setText(R.string.survey_not_available);
    }

    @Override
    public void onUserNotEligible() {
        Log.d(TAG, getString(R.string.user_not_eligible));
        loggingTxt.setText(R.string.user_not_eligible);
        Toast.makeText(this, getString(R.string.user_not_eligible), Toast.LENGTH_LONG).show();
    }

}
