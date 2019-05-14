package pollfish.com.sampleproject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.pollfish.classes.SurveyInfo;
import com.pollfish.interfaces.PollfishClosedListener;
import com.pollfish.interfaces.PollfishCompletedSurveyListener;
import com.pollfish.interfaces.PollfishOpenedListener;
import com.pollfish.interfaces.PollfishReceivedSurveyListener;
import com.pollfish.interfaces.PollfishSurveyNotAvailableListener;
import com.pollfish.interfaces.PollfishUserNotEligibleListener;
import com.pollfish.main.PollFish;

public class OfferwallActivity extends Activity implements
        PollfishCompletedSurveyListener, PollfishReceivedSurveyListener, PollfishOpenedListener,
        PollfishClosedListener,
        PollfishSurveyNotAvailableListener, PollfishUserNotEligibleListener {


    private Button coinsBtn;
    private TextView loggingTxt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_offerwall);

        loggingTxt = (TextView) findViewById(R.id.logText);
        coinsBtn = (Button) findViewById(R.id.coins_btn);

        coinsBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                PollFish.show();

            }
        });
        coinsBtn.setVisibility(View.INVISIBLE);

        Button activityBackBtn = (Button) findViewById(R.id.activity_back_btn);
        activityBackBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onPollfishClosed() {
        Log.d("Pollfish", "onPollfishClosed");
    }

    @Override
    public void onPollfishOpened() {
        Log.d("Pollfish", "onPollfishOpened");

    }

    @Override
    public void onPollfishSurveyNotAvailable() {
        Log.d("Pollfish", "onPollfishSurveyNotAvailable");

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loggingTxt.setText(getString(R.string.not_available));
            }
        });
    }

    @Override
    public void onUserNotEligible() {
        Log.d("Pollfish", "onUserNotEligible");

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loggingTxt.setText(getString(R.string.user_not_eligible));
            }
        });

        Toast.makeText(this, getString(R.string.user_not_eligible),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d("Pollfish", "onResume() ");

        PollFish.ParamsBuilder paramsBuilder = new PollFish.ParamsBuilder("2ad6e857-2995-4668-ab95-39e068faa558")
                .rewardMode(true)
                .offerWallMode(true)
                .build();

        PollFish.initWith(this, paramsBuilder);

        loggingTxt.setText(getString(R.string.logging));
        coinsBtn.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onPollfishSurveyCompleted(SurveyInfo surveyInfo) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loggingTxt.setText(getString(R.string.survey_offerwall_completed));
            }
        });

        Toast.makeText(this, getString(R.string.survey_offerwall_completed),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPollfishSurveyReceived(SurveyInfo surveyInfo) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                coinsBtn.setVisibility(View.VISIBLE);
                coinsBtn.setText(getString(R.string.offerwall));
                loggingTxt.setText(getString(R.string.offerwall_available));
            }
        });
    }
}
