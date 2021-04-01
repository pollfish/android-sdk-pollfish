package com.pollfish.sample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.pollfish.Pollfish
import com.pollfish.builder.Params
import com.pollfish.builder.Position
import com.pollfish.builder.UserProperties
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

        // Show Pollfish if available anywhere within the activity LifeCycle
        binding.showBtn.setOnClickListener {
            Pollfish.show()
        }

        // Hide Pollfish if available anywhere within the activity LifeCycle
        binding.hideBtn.setOnClickListener {
            Pollfish.hide()
        }

        binding.rewardedSurveyActBtn.setOnClickListener {
            startActivity(Intent(this, RewardedSurveyActivity::class.java))
        }

        binding.offerwallActBtn.setOnClickListener {
            startActivity(Intent(this, OfferwallActivity::class.java))
        }

        initPollfish()
    }

    override fun onResume() {
        super.onResume()

        if (!Pollfish.isPollfishPresent()) {
            initPollfish()
        }
    }

    private fun initPollfish() {
        val userProperties = UserProperties.Builder()
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
            .build()

        val params = Params.Builder("YOUR_API_KEY")
            .indicatorPadding(50)
            .indicatorPosition(Position.MIDDLE_RIGHT)
            .releaseMode(false)
            .userProperties(userProperties)
            .build()

        Pollfish.initWith(this, params)
    }

    override fun onPollfishSurveyCompleted(surveyInfo: SurveyInfo) {
        Log.d(TAG, """
            onPollfishSurveyReceived with CPA: ${surveyInfo.surveyCPA}
            and surveyClass: ${surveyInfo.surveyClass}
            and LOI: ${surveyInfo.surveyLOI}
            and IR: ${surveyInfo.surveyIR}
        """.trimIndent())
    }

    override fun onPollfishSurveyNotAvailable() {
        Log.d(TAG, "onPollfishSurveyNotAvailable()")
    }

    override fun onPollfishOpened() {
        Log.d(TAG, "onPollfishOpened()")
    }

    override fun onPollfishClosed() {
        Log.d(TAG, "onPollfishClosed()")
    }

    override fun onUserNotEligible() {
        Log.d(TAG, "onUserNotEligible()")
    }

    override fun onUserRejectedSurvey() {
        Log.d(TAG, "onUserRejectedSurvey()")
    }

    override fun onPollfishSurveyReceived(surveyInfo: SurveyInfo?) {
        Log.d(TAG, """
            onPollfishSurveyReceived with CPA: ${surveyInfo?.surveyCPA}
            and surveyClass: ${surveyInfo?.surveyClass}
            and LOI: ${surveyInfo?.surveyLOI}
            and IR: ${surveyInfo?.surveyIR}
        """.trimIndent())
    }

    companion object {
        const val TAG = "Pollfish"
    }
}