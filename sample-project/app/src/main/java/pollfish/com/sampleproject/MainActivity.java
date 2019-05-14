package pollfish.com.sampleproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.pollfish.classes.SurveyInfo;
import com.pollfish.constants.Position;
import com.pollfish.constants.UserProperties;
import com.pollfish.interfaces.PollfishClosedListener;
import com.pollfish.interfaces.PollfishCompletedSurveyListener;
import com.pollfish.interfaces.PollfishOpenedListener;
import com.pollfish.interfaces.PollfishReceivedSurveyListener;
import com.pollfish.interfaces.PollfishSurveyNotAvailableListener;
import com.pollfish.interfaces.PollfishUserNotEligibleListener;
import com.pollfish.interfaces.PollfishUserRejectedSurveyListener;
import com.pollfish.main.PollFish;

public class MainActivity extends Activity implements
        PollfishCompletedSurveyListener, PollfishOpenedListener,
        PollfishClosedListener, PollfishReceivedSurveyListener,
        PollfishSurveyNotAvailableListener, PollfishUserNotEligibleListener, PollfishUserRejectedSurveyListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // show Pollfish if available anywhere within the activity LifeCycle

        Button showBtn = (Button) findViewById(R.id.show_btn);
        showBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                PollFish.show();
            }
        });

        // hide Pollfish if available anywhere within the activity LifeCycle

        Button hideBtn = (Button) findViewById(R.id.hide_btn);
        hideBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                PollFish.hide();
            }
        });

        // start activity to see orientation handle

        Button activityBtn = (Button) findViewById(R.id.activity_btn);
        activityBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        OrientationActivity.class);

                startActivity(intent);

            }
        });

        // start activity to see implementation with incentivization

        Button incentivize_act_Btn = (Button) findViewById(R.id.rewarded_survey_act_btn);
        incentivize_act_Btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        RewardedSurveyActivity.class);

                startActivity(intent);

            }
        });

        Button offerwall_act_Btn = (Button) findViewById(R.id.offerwall_act_btn);
        offerwall_act_Btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        OfferwallActivity.class);

                 startActivity(intent);

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

        UserProperties userProperties = new UserProperties()
                      /*included in Demographic Surveys*/
		// Added UserProperties class as a part of the fix for the issue #3
		// by github.com/RahulSDeshpande
                .setGender(UserProperties.Gender.MALE)
                .setYearOfBirth(UserProperties.YearOfBirth._1984)
                .setMaritalStatus(UserProperties.MaritalStatus.SINGLE)
                .setParentalStatus(UserProperties.ParentalStatus.ZERO)
                .setEducation(UserProperties.EducationLevel.UNIVERSITY)
                .setEmployment(UserProperties.EmploymentStatus.EMPLOYED_FOR_WAGES)
                .setCareer(UserProperties.Career.TELECOMMUNICATIONS)
                .setRace(UserProperties.Race.WHITE)
                .setIncome(UserProperties.Income.MIDDLE_I)
                         /*other user attributes*/
                .setEmail("user_email@test.com")
                .setFacebookId("USER_FB")
                .setGoogleId("USER_GOOGLE")
                .setTwitterId("USER_TWITTER")
                .setLinkedInId("USER_LINKEDIN")
                .setPhone("USER_PHONE")
                .setName("USER_NAME")
                .setSurname("USER_SURNAME")
                .setCustomAttributes("MY_PARAM", "MY_VALUE");

        PollFish.ParamsBuilder paramsBuilder = new PollFish.ParamsBuilder("2ad6e857-2995-4668-ab95-39e068faa558")
                .indicatorPadding(50)
                .indicatorPosition(Position.MIDDLE_RIGHT)
                .customMode(false)
                .releaseMode(false)
                .userProperties(userProperties)
                .build();

        PollFish.initWith(this, paramsBuilder);

    }

    @Override
    public void onPollfishClosed() {
        Log.d("Pollfish", "onPollfishClosed()");
    }

    @Override
    public void onPollfishOpened() {
        Log.d("Pollfish", "onPollfishOpened()");
    }

    @Override
    public void onPollfishSurveyNotAvailable() {
        Log.d("Pollfish", "onPollfishSurveyNotAvailable()");

    }

    @Override
    public void onUserNotEligible() {
        Log.d("Pollfish", "onUserNotEligible()");

    }

    @Override
    public void onUserRejectedSurvey() {
        Log.d("Pollfish", "onUserRejectedSurvey()");
    }

    @Override
    public void onPollfishSurveyCompleted(SurveyInfo surveyInfo) {
        Log.d("Pollfish", "onPollfishSurveyCompleted with CPA: " + surveyInfo.getSurveyCPA()
                + " and SurveyClass: " + surveyInfo.getSurveyClass()+ " and LOI: " + surveyInfo.getSurveyLOI()+ " and IR: " + surveyInfo.getSurveyIR());
    }

    @Override
    public void onPollfishSurveyReceived(SurveyInfo surveyInfo) {
        Log.d("Pollfish", "onPollfishSurveyReceived with CPA: " + surveyInfo.getSurveyCPA()
                + " and SurveyClass: " + surveyInfo.getSurveyClass()+ " and LOI: " + surveyInfo.getSurveyLOI()+ " and IR: " + surveyInfo.getSurveyIR());

    }
}
