package pollfish.com.sampleproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.pollfish.constants.Position;
import com.pollfish.interfaces.PollfishClosedListener;
import com.pollfish.interfaces.PollfishOpenedListener;
import com.pollfish.interfaces.PollfishSurveyCompletedListener;
import com.pollfish.interfaces.PollfishSurveyNotAvailableListener;
import com.pollfish.interfaces.PollfishSurveyReceivedListener;
import com.pollfish.interfaces.PollfishUserNotEligibleListener;
import com.pollfish.main.PollFish;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity implements
		PollfishSurveyCompletedListener, PollfishOpenedListener,
		PollfishClosedListener, PollfishSurveyReceivedListener,
		PollfishSurveyNotAvailableListener, PollfishUserNotEligibleListener {

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

		Button incentivize_act_Btn = (Button) findViewById(R.id.incent_act_btn);
		incentivize_act_Btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						IncentivizeActivity.class);

				startActivity(intent);

			}
		});
	}

	@Override
	public void onResume() {
		super.onResume();

		/**
		 * Start the library. Set the api key. Choose position to place the
		 * indicator (Position.TOP_LEFT, Position.BOTTOM_LEFT,MIDDLE_LEFT,
		 * Position.TOP_RIGHT, Position.BOTTOM_RIGHT, MIDDLE_RIGHT). Set padding
		 * for the indicator position from bottom/middle or top according to
		 * position, 0 if default.
		 * 
		 * @param act
		 *            - Your current activity
		 * @param apiKey
		 *            - The api Key for your app (register at www.pollfish.com)
		 * @param p
		 *            - The position to place the pollfish indicator
		 * @param indPadding
		 *            - padding between the indicator and the selected position
		 *            in the screen - 0 for default
		 * 
		 *            e.g PollFish.init(this,
		 *            "2ad6e857-2995-4668-ab95-39e068faa558",
		 *            Position.BOTTOM_RIGHT,5);
		 */

		PollFish.init(this, "2ad6e857-2995-4668-ab95-39e068faa558",
				Position.BOTTOM_RIGHT, 65);

		Map<String, String> map = new HashMap<String,String>();
		
		map.put("FacebookID", "123423423ewdfdsf2w2e3");
		map.put("TwitterID", "10sdfwerqewqwcwew11");

		PollFish.setAttributesMap(map);

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
	public void onPollfishSurveyReceived(boolean playfulSurvey, int surveyPrice) {
		Log.d("Pollfish", "onPollfishSurveyReceived(" + playfulSurvey + " , " + surveyPrice + ")");
	}

	@Override
	public void onPollfishSurveyCompleted(boolean playfulSurvey, int surveyPrice) {
		Log.d("Pollfish", "onPollfishSurveyCompleted(" + playfulSurvey + " , " + surveyPrice + ")");
	}
	
}
