package com.pollfish.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.pollfish.Pollfish;
import com.pollfish.builder.Params;
import com.pollfish.builder.Position;
import com.pollfish.builder.UserProperties;
import com.pollfish.callback.PollfishClosedListener;
import com.pollfish.callback.PollfishOpenedListener;
import com.pollfish.callback.PollfishSurveyCompletedListener;
import com.pollfish.callback.PollfishSurveyNotAvailableListener;
import com.pollfish.callback.PollfishSurveyReceivedListener;
import com.pollfish.callback.PollfishUserNotEligibleListener;
import com.pollfish.callback.PollfishUserRejectedSurveyListener;
import com.pollfish.callback.SurveyInfo;

public class MainActivity extends Activity implements
        PollfishSurveyCompletedListener,
        PollfishOpenedListener,
        PollfishClosedListener,
        PollfishSurveyReceivedListener,
        PollfishSurveyNotAvailableListener,
        PollfishUserNotEligibleListener,
        PollfishUserRejectedSurveyListener {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button showBtn = findViewById(R.id.show_btn);

        // Show Pollfish if available anywhere within the activity LifeCycle
        showBtn.setOnClickListener(v -> Pollfish.show());

        Button hideBtn = findViewById(R.id.hide_btn);

        // Hide Pollfish if available anywhere within the activity LifeCycle
        hideBtn.setOnClickListener(v -> Pollfish.hide());

        Button incentivize_act_Btn = findViewById(R.id.rewarded_survey_act_btn);
        incentivize_act_Btn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,
                    RewardedSurveyActivity.class);

            startActivity(intent);
        });

        Button offerwall_act_Btn = findViewById(R.id.offerwall_act_btn);
        offerwall_act_Btn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,
                    OfferwallActivity.class);

            startActivity(intent);
        });

        initPollfish();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (!Pollfish.isPollfishPresent()) {
            initPollfish();
        }
    }

    private void initPollfish() {
        UserProperties userProperties = new UserProperties.Builder()
                .gender(UserProperties.Gender.MALE)
                .yearOfBirth(1984)
                .maritalStatus(UserProperties.MaritalStatus.SINGLE)
                .parentalStatus(UserProperties.ParentalStatus.ZERO)
                .educationLevel(UserProperties.EducationLevel.UNIVERSITY)
                .employmentStatus(UserProperties.EmploymentStatus.EMPLOYED_FOR_WAGES)
                .career(UserProperties.Career.TELECOMMUNICATIONS)
                .race(UserProperties.Race.WHITE)
                .income(UserProperties.Income.MIDDLE_I)
                .customAttribute("MY_PARAM", "MY_VALUE")
                .build();

        Params params = new Params.Builder("YOUR_API_KEY")
                .indicatorPadding(50)
                .indicatorPosition(Position.MIDDLE_RIGHT)
                .releaseMode(false)
                .userProperties(userProperties)
                .build();

        Pollfish.initWith(this, params);
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
    }

    @Override
    public void onUserNotEligible() {
        Log.d(TAG, getString(R.string.user_not_eligible));
    }

    @Override
    public void onUserRejectedSurvey() {
        Log.d(TAG, getString(R.string.user_rejected_survey));
    }

    @Override
    public void onPollfishSurveyCompleted(SurveyInfo surveyInfo) {
        Log.d(TAG, "onPollfishSurveyCompleted with CPA: " + surveyInfo.getSurveyCPA()
                + " and SurveyClass: " + surveyInfo.getSurveyClass()
                + " and LOI: " + surveyInfo.getSurveyLOI()
                + " and IR: " + surveyInfo.getSurveyIR());
    }

    @Override
    public void onPollfishSurveyReceived(SurveyInfo surveyInfo) {
        Log.d(TAG, "onPollfishSurveyReceived with CPA: " + surveyInfo.getSurveyCPA()
                + " and SurveyClass: " + surveyInfo.getSurveyClass()
                + " and LOI: " + surveyInfo.getSurveyLOI()
                + " and IR: " + surveyInfo.getSurveyIR());

    }
}
