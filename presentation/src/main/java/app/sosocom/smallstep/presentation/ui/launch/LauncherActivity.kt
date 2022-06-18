package app.sosocom.smallstep.presentation.ui.launch

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import app.sosocom.smallstep.presentation.base.BaseActivity
import app.sosocom.smallstep.presentation.R
import app.sosocom.smallstep.presentation.databinding.ActivityLauncherBinding
import app.sosocom.smallstep.presentation.ui.main.MainActivity
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LauncherActivity : BaseActivity<ActivityLauncherBinding>(R.layout.activity_launcher) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Glide.with(this)
            .asGif()
            .load(R.drawable.intro)
            .into(binding.launchImage)

        lifecycleScope.launch {
            delay(2500)
            val intent = Intent(this@LauncherActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}