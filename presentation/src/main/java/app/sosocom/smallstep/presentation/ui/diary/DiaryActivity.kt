package app.sosocom.smallstep.presentation.ui.diary

import android.os.Bundle
import androidx.activity.viewModels
import app.sosocom.smallstep.domain.model.Diary
import app.sosocom.smallstep.domain.util.Log
import app.sosocom.smallstep.domain.util.LogTag
import app.sosocom.smallstep.presentation.R
import app.sosocom.smallstep.presentation.base.BaseActivity
import app.sosocom.smallstep.presentation.databinding.ActivityDiaryBinding
import app.sosocom.smallstep.presentation.util.ExtraConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiaryActivity : BaseActivity<ActivityDiaryBinding>(R.layout.activity_diary) {
    private val viewModel by viewModels<DiaryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.vm = viewModel

        initActionBar()
        loadData()
    }

    private fun initActionBar() {
        binding.actionBar.run {
            btnBack.setOnClickListener { onBackPressed() }

            title.text = getString(R.string.diary_title)
        }
    }

    private fun loadData() {
        val diary = intent.getParcelableExtra<Diary>(ExtraConstants.EXTRA_DIARY)
        if(diary == null) {
            Log.e(LogTag.LIFE_CYCLE, "데이터가 없습니다. key = ${ExtraConstants.EXTRA_DIARY}")
            finish()
            return
        }

        viewModel.setDiary(diary)
    }
}