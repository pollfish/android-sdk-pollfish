package com.pollfish.sample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pollfish.Pollfish;
import com.pollfish.builder.Params;
import com.pollfish.callback.PollfishClosedListener;
import com.pollfish.callback.PollfishOpenedListener;
import com.pollfish.callback.PollfishSurveyCompletedListener;
import com.pollfish.callback.PollfishSurveyNotAvailableListener;
import com.pollfish.callback.PollfishSurveyReceivedListener;
import com.pollfish.callback.PollfishUserNotEligibleListener;
import com.pollfish.callback.PollfishUserRejectedSurveyListener;
import com.pollfish.callback.SurveyInfo;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends Activity implements
        PollfishSurveyCompletedListener,
        PollfishOpenedListener,
        PollfishClosedListener,
        PollfishSurveyReceivedListener,
        PollfishSurveyNotAvailableListener,
        PollfishUserNotEligibleListener,
        PollfishUserRejectedSurveyListener {

    private static final String TAG = "MainActivity";

    private Button coinsBtn;
    private TextView loggingTxt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        loggingTxt = findViewById(R.id.logText);
        coinsBtn = findViewById(R.id.coins_btn);

        coinsBtn.setOnClickListener(v -> Pollfish.show());
        coinsBtn.setVisibility(View.GONE);

        initPollfish();
    }

    private void initPollfish() {
        Params params = new Params.Builder("YOUR_API_KEY")
                .rewardMode(true)
                .build();

        Pollfish.initWith(this, params);
    }

    @Override
    public void onPollfishSurveyCompleted(@NotNull SurveyInfo surveyInfo) {
        coinsBtn.setVisibility(View.GONE);

        // in a real world scenario you should wait here for verification from s2s callback prior rewarding your users
        loggingTxt.setText(getString(R.string.survey_completed, surveyInfo.getRewardValue() == null ? 200 : surveyInfo.getRewardValue()));
    }

    @Override
    public void onPollfishSurveyReceived(SurveyInfo surveyInfo) {
        coinsBtn.setVisibility(View.VISIBLE);
        coinsBtn.setText(getString(R.string.win_coins, surveyInfo != null && surveyInfo.getRewardValue() != null ? surveyInfo.getRewardValue() : 200));
        loggingTxt.setText(R.string.survey_received);
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
        coinsBtn.setVisibility(View.GONE);
        loggingTxt.setText(R.string.user_not_eligible);
    }

    @Override
    public void onUserRejectedSurvey() {
        coinsBtn.setVisibility(View.GONE);
        loggingTxt.setText(R.string.user_rejected_survey);
    }

}
