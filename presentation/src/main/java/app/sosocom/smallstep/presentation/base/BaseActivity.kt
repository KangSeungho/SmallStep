package app.sosocom.smallstep.presentation.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import app.sosocom.smallstep.domain.util.Log
import app.sosocom.smallstep.domain.util.LogTag
import app.sosocom.smallstep.presentation.R

abstract class BaseActivity<T: ViewDataBinding>(@LayoutRes val layoutID: Int) : AppCompatActivity() {

    private var _binding: T? = null
    protected val binding: T get() = _binding!!

    protected val activityContext: Context get() = this

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

    fun pushActivity() {
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    private fun popActivity() {
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    // endregion LifeCycle

    fun toast(@StringRes msgRes: Int, duration: Int = Toast.LENGTH_SHORT) = toast(getString(msgRes), duration)
    fun toast(msg: String, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(this, msg, duration).show()
}