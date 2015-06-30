package pollfish.com.sampleproject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.pollfish.interfaces.PollfishClosedListener;
import com.pollfish.interfaces.PollfishOpenedListener;
import com.pollfish.interfaces.PollfishSurveyCompletedListener;
import com.pollfish.interfaces.PollfishSurveyNotAvailableListener;
import com.pollfish.interfaces.PollfishSurveyReceivedListener;
import com.pollfish.interfaces.PollfishUserNotEligibleListener;
import com.pollfish.main.PollFish;
import com.pollfish.constants.Position;

public class IncentivizeActivity extends Activity implements
		PollfishSurveyCompletedListener, PollfishOpenedListener,
		PollfishClosedListener, PollfishSurveyReceivedListener,
		PollfishSurveyNotAvailableListener, PollfishUserNotEligibleListener {

	private Button coinsBtn;
	private TextView loggingTxt;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_incentivize);

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

	}

	@Override
	public void onResume() {
		super.onResume();

		Log.d("Pollfish", "onResume() ");
		
		PollFish.customInit(IncentivizeActivity.this, "2ad6e857-2995-4668-ab95-39e068faa558",
				Position.MIDDLE_LEFT, 5);

		PollFish.hide();

		loggingTxt.setText(getString(R.string.logging));

		coinsBtn.setVisibility(View.INVISIBLE);
	}

	@Override
	public void onPollfishSurveyReceived(boolean playfulSurvey, int surveyPrice) {
		Log.d("Pollfish", "onPollfishSurveyReceived(" + playfulSurvey + " , " + surveyPrice + ")");	

		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				coinsBtn.setVisibility(View.VISIBLE);
				loggingTxt.setText("Pollfish Survey Received");
			}
		});
	}

	@Override
	public void onPollfishSurveyCompleted(boolean playfulSurvey, int surveyPrice) {
		Log.d("Pollfish", "onPollfishSurveyCompleted(" + playfulSurvey + " , " + surveyPrice + ")");

		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				coinsBtn.setVisibility(View.INVISIBLE);
				loggingTxt.setText(getString(R.string.survey_completed));
			}
		});
	}

}
