package app.sosocom.smallstep.base

import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import app.sosocom.smallstep.R
import app.sosocom.smallstep.util.Log
import app.sosocom.smallstep.util.LogTag

abstract class BaseActivity<T: ViewDataBinding>(@LayoutRes val layoutID: Int) : AppCompatActivity() {

    private var _binding: T? = null
    protected val binding: T get() = _binding!!

    // region LifeCycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(LogTag.LIFE_CYCLE, "onCreate ${this::class.java.simpleName}")
        _binding = DataBindingUtil.setContentView(this, layoutID)
    }

    override fun onResume() {
        super.onResume()
        Log.d(LogTag.LIFE_CYCLE, "onResume ${this::class.java.simpleName}")
    }

    override fun onPause() {
        super.onPause()
        Log.d(LogTag.LIFE_CYCLE, "onPause ${this::class.java.simpleName}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LogTag.LIFE_CYCLE, "onDestroy ${this::class.java.simpleName}")
    }

    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
        pushActivity()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        popActivity()
    }

    override fun finish() {
        super.finish()
        popActivity()
    }

    private fun pushActivity() {
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    private fun popActivity() {
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    // endregion LifeCycle
}