package com.prodege.sample

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.prodege.Prodege
import com.prodege.builder.InitOptions
import com.prodege.listener.ProdegeEventListener
import com.prodege.listener.ProdegeException
import com.prodege.listener.ProdegeInitListener
import com.prodege.listener.ProdegeReward
import com.prodege.listener.ProdegeRewardListener
import com.prodege.listener.ProdegeRewardedInfo
import com.prodege.listener.ProdegeRewardedLoadListener
import com.prodege.listener.ProdegeShowListener
import com.prodege.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),
    ProdegeInitListener,
    ProdegeShowListener,
    ProdegeRewardedLoadListener,
    ProdegeEventListener,
    ProdegeRewardListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.coinsBtn.setOnClickListener {
            onShowClick()
        }
        binding.coinsBtn.visibility = View.GONE

        val initOptions: InitOptions = InitOptions.Builder().testMode(true).build()

        if (!Prodege.isInitialized()) {
            Prodege.setRewardListener(this)
            Prodege.setEventListener(this)
            Prodege.initialize(this, getString(R.string.prodege_api_key), this, initOptions)
        }
    }

    private fun loadRewardedAd() {
        Prodege.loadRewardedAd(getString(R.string.prodege_rewarded_placement_id), this, null)
    }

    private fun onShowClick() {
        Prodege.showPlacement(getString(R.string.prodege_rewarded_placement_id), this, null)
    }

    override fun onError(exception: ProdegeException) {
        Log.e(TAG, getString(R.string.on_prodege_sdk_init_error, exception.message))
    }

    override fun onSuccess() {
        Log.d(TAG, getString(R.string.on_prodege_sdk_init_success))
        loadRewardedAd()
    }

    override fun onClosed(placementId: String) {
        Log.d(TAG, getString(R.string.on_placement_closed, placementId))
        if (!Prodege.isPlacementLoaded(placementId)) {
            loadRewardedAd()
        }
    }

    override fun onOpened(placementId: String) {
        Log.d(TAG, getString(R.string.on_placement_opened, placementId))
    }

    override fun onShowFailed(placementId: String, exception: ProdegeException) {
        Log.e(TAG, getString(R.string.on_placement_failed_to_show, placementId, exception.message))
    }

    override fun onRewardedLoadFailed(placementId: String, exception: ProdegeException) {
        Log.e(TAG, getString(R.string.on_rewarded_placement_load_failed, placementId, exception.message))
    }

    override fun onRewardedLoaded(placementId: String, info: ProdegeRewardedInfo) {
        Log.d(TAG, getString(R.string.on_rewarded_placement_loaded, placementId))
        binding.coinsBtn.visibility = View.VISIBLE
        binding.coinsBtn.text = getString(
            R.string.win_coins,
            info.points,
            info.currency
        )
    }

    override fun onRewardReceived(reward: ProdegeReward) {
        val message = getString(
            R.string.on_prodege_reward_received,
            reward.points,
            reward.currency
        )
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        Log.d(TAG, message)
    }

    override fun onClick(placementId: String) {
        Log.d(TAG, getString(R.string.on_prodege_placement_click, placementId))
    }

    override fun onComplete(placementId: String) {
        binding.coinsBtn.visibility = View.GONE
        Log.d(TAG, getString(R.string.on_prodege_placement_completed, placementId))
    }

    override fun onStart(placementId: String) {
        Log.d(TAG, getString(R.string.on_prodege_placement_started, placementId))
    }

    override fun onUserNotEligible(placementId: String) {
        binding.coinsBtn.visibility = View.GONE
        Log.d(TAG, getString(R.string.on_user_not_eligible, placementId))
    }

    override fun onUserReject(placementId: String) {
        binding.coinsBtn.visibility = View.GONE
        Log.d(TAG, getString(R.string.on_user_reject, placementId))
    }

    companion object {
        const val TAG = "MainActivity"
    }
}