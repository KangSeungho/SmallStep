package app.sosocom.smallstep.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import app.sosocom.smallstep.presentation.util.Log
import app.sosocom.smallstep.presentation.util.LogTag

abstract class BaseFragment<T: ViewDataBinding>(@LayoutRes val layoutID: Int) : Fragment() {

    private var _binding: T? = null
    protected val binding: T get() = _binding!!

    // region LifeCycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(LogTag.LIFE_CYCLE, "onCreate ${this::class.java.simpleName}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(LogTag.LIFE_CYCLE, "onCreateView ${this::class.java.simpleName}")
        _binding = DataBindingUtil.inflate(inflater, layoutID, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        Log.d(LogTag.LIFE_CYCLE, "onResume ${this::class.java.simpleName}")
    }

    override fun onPause() {
        super.onPause()
        Log.d(LogTag.LIFE_CYCLE, "onPause ${this::class.java.simpleName}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(LogTag.LIFE_CYCLE, "onDestroyView ${this::class.java.simpleName}")
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LogTag.LIFE_CYCLE, "onDestroy ${this::class.java.simpleName}")
    }

    // endregion LifeCycle
}