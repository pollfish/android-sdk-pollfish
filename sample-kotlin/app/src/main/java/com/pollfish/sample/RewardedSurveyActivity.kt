package com.pollfish.sample

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.pollfish.Pollfish
import com.pollfish.builder.Params
import com.pollfish.callback.*
import com.pollfish.sample.databinding.ActivityRewardedSurveyBinding

class RewardedSurveyActivity : AppCompatActivity(),
    PollfishSurveyCompletedListener,
    PollfishSurveyReceivedListener,
    PollfishOpenedListener,
    PollfishClosedListener,
    PollfishSurveyNotAvailableListener,
    PollfishUserNotEligibleListener,
    PollfishUserRejectedSurveyListener {

    private lateinit var binding: ActivityRewardedSurveyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRewardedSurveyBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.coinsBtn.setOnClickListener {
            Pollfish.show()
        }

        binding.activityBackBtn.setOnClickListener {
            finish()
        }

        initPollfish()
    }

    override fun onResume() {
        super.onResume()

        if (!Pollfish.isPollfishPresent()) {
            initPollfish()
            binding.logText.setText(R.string.logging)
            binding.coinsBtn.visibility = View.INVISIBLE
        } else {
            binding.coinsBtn.visibility = View.VISIBLE
            binding.logText.setText(R.string.survey_received)
        }
    }

    private fun initPollfish() {
        val params = Params.Builder("YOUR_API_KEY")
            .rewardMode(true)
            .build()

        Pollfish.initWith(this, params)
    }

    override fun onPollfishSurveyCompleted(surveyInfo: SurveyInfo) {
        binding.coinsBtn.visibility = View.INVISIBLE

        // in a real world scenario you should wait here for verification from s2s callback prior rewarding your users
        binding.logText.setText(R.string.survey_completed)
    }

    override fun onPollfishSurveyReceived(surveyInfo: SurveyInfo?) {
        binding.coinsBtn.visibility = View.VISIBLE
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
        binding.coinsBtn.visibility = View.INVISIBLE
        binding.logText.setText(R.string.user_not_eligible)
    }

    override fun onUserRejectedSurvey() {
        binding.coinsBtn.visibility = View.INVISIBLE
        binding.logText.setText(R.string.user_rejected_survey)
    }

    companion object {
        const val TAG = "RewardedSurveyActivity"
    }

}