package app.sosocom.smallstep.ui.launch

import android.os.Bundle
import app.sosocom.smallstep.R
import app.sosocom.smallstep.base.BaseActivity
import app.sosocom.smallstep.databinding.ActivityLaunchBinding

class LaunchActivity : BaseActivity<ActivityLaunchBinding>() {
    override fun getLayoutResId() = R.layout.activity_launch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
    }
}