package com.pollfish.sample

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.pollfish.Pollfish
import com.pollfish.builder.Params
import com.pollfish.callback.*
import com.pollfish.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),
    PollfishSurveyCompletedListener,
    PollfishSurveyNotAvailableListener,
    PollfishOpenedListener,
    PollfishClosedListener,
    PollfishUserNotEligibleListener,
    PollfishUserRejectedSurveyListener,
    PollfishSurveyReceivedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.coinsBtn.setOnClickListener {
            Pollfish.show()
        }

        initPollfish()
    }

    private fun initPollfish() {
        val params = Params.Builder("YOUR_API_KEY")
            .rewardMode(true)
            .build()

        Pollfish.initWith(this, params)
    }

    override fun onPollfishSurveyCompleted(surveyInfo: SurveyInfo) {
        binding.coinsBtn.visibility = View.GONE

        // in a real world scenario you should wait here for verification from s2s callback prior rewarding your users
        binding.logText.text = getString(R.string.survey_completed, surveyInfo.rewardValue ?: 200)
    }

    override fun onPollfishSurveyReceived(surveyInfo: SurveyInfo?) {
        binding.coinsBtn.visibility = View.VISIBLE
        binding.coinsBtn.text = getString(R.string.win_coins, surveyInfo?.rewardValue ?: 200)
        binding.logText.setText(R.string.survey_received)
    }

    override fun onPollfishOpened() {
        Log.d(TAG, getString(R.string.on_pollfish_opened))
    }

    override fun onPollfishClosed() {
        Log.d(TAG, getString(R.string.on_pollfish_closed))
    }

    override fun onPollfishSurveyNotAvailable() {
        Log.d(TAG, getString(R.string.survey_not_available))
        binding.logText.setText(R.string.survey_not_available)
    }

    override fun onUserNotEligible() {
        Log.d(TAG, getString(R.string.user_not_eligible))
        binding.coinsBtn.visibility = View.GONE
        binding.logText.setText(R.string.user_not_eligible)
    }

    override fun onUserRejectedSurvey() {
        binding.coinsBtn.visibility = View.GONE
        binding.logText.setText(R.string.user_rejected_survey)
    }

    companion object {
        const val TAG = "MainActivity"
    }
}