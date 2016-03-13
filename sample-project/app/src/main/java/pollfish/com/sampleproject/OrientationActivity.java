package pollfish.com.sampleproject;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.pollfish.constants.Position;
import com.pollfish.main.PollFish;

public class OrientationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_orientation);

		// back to main activity

		Button activityBackBtn = (Button) findViewById(R.id.activity_back_btn);
		activityBackBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}

	@Override
	public void onResume() {
		super.onResume();


		PollFish.ParamsBuilder paramsBuilder = new PollFish.ParamsBuilder("2ad6e857-2995-4668-ab95-39e068faa558")
				.indicatorPadding(5)
				.indicatorPosition(Position.BOTTOM_RIGHT)
				.build();

		PollFish.initWith(this, paramsBuilder);

	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);


		PollFish.ParamsBuilder paramsBuilder = new PollFish.ParamsBuilder("2ad6e857-2995-4668-ab95-39e068faa558")
				.indicatorPadding(5)
				.indicatorPosition(Position.BOTTOM_RIGHT)
				.build();

		PollFish.initWith(this, paramsBuilder);
	}
}
