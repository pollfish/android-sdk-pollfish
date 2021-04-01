package com.pollfish.sample

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pollfish.Pollfish
import com.pollfish.builder.Params
import com.pollfish.callback.*
import com.pollfish.sample.databinding.ActivityOfferwallBinding

class OfferwallActivity : AppCompatActivity(),
    PollfishSurveyCompletedListener,
    PollfishSurveyNotAvailableListener,
    PollfishOpenedListener,
    PollfishClosedListener,
    PollfishUserNotEligibleListener,
    PollfishSurveyReceivedListener {

    private lateinit var binding: ActivityOfferwallBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOfferwallBinding.inflate(layoutInflater)

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
            binding.coinsBtn.setText(R.string.offerwall)
            binding.logText.setText(R.string.offerwall_available)
        }
    }

    private fun initPollfish() {
        val params = Params.Builder("YOUR_API_KEY")
            .rewardMode(true)
            .offerwallMode(true)
            .build()

        Pollfish.initWith(this, params)
    }

    override fun onPollfishSurveyCompleted(surveyInfo: SurveyInfo) {
        binding.logText.setText(R.string.survey_offerwall_completed)
        Toast.makeText(this, getString(R.string.survey_offerwall_completed), Toast.LENGTH_LONG)
            .show()
    }

    override fun onPollfishSurveyNotAvailable() {
        Log.d(TAG, getString(R.string.survey_not_available))
        binding.logText.setText(R.string.survey_not_available)
    }

    override fun onPollfishOpened() {
        Log.d(TAG, getString(R.string.on_pollfish_opened))
    }

    override fun onPollfishClosed() {
        Log.d(TAG, getString(R.string.on_pollfish_closed))
    }

    override fun onUserNotEligible() {
        Log.d(TAG, getString(R.string.user_not_eligible))
        binding.logText.setText(R.string.user_not_eligible)
        Toast.makeText(this, getString(R.string.user_not_eligible), Toast.LENGTH_LONG).show()
    }

    override fun onPollfishSurveyReceived(surveyInfo: SurveyInfo?) {
        binding.coinsBtn.visibility = View.VISIBLE
        binding.coinsBtn.setText(R.string.offerwall)
        binding.logText.setText(R.string.offerwall_available)
    }

    companion object {
        const val TAG = "OfferwallActivity"
    }

}