package app.sosocom.smallstep.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import app.sosocom.smallstep.util.Log
import app.sosocom.smallstep.util.LogTag

abstract class BaseActivity<T: ViewDataBinding> : AppCompatActivity() {
    @LayoutRes
    abstract fun getLayoutResId(): Int

    private var _binding: T? = null
    protected val binding: T get() = _binding!!

    // region LifeCycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(LogTag.LIFE_CYCLE, "onCreate ${this::class.java.simpleName}")
        _binding = DataBindingUtil.setContentView(this, getLayoutResId())
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

    // endregion LifeCycle
}