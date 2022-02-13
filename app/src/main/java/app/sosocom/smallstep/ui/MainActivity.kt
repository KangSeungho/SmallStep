package app.sosocom.smallstep.ui

import android.os.Bundle
import app.sosocom.smallstep.R
import app.sosocom.smallstep.base.BaseActivity
import app.sosocom.smallstep.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getLayoutResId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}