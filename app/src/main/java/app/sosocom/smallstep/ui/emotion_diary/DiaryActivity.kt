package app.sosocom.smallstep.ui.emotion_diary

import android.os.Bundle
import androidx.activity.viewModels
import app.sosocom.smallstep.R
import app.sosocom.smallstep.base.BaseActivity
import app.sosocom.smallstep.databinding.ActivityDiaryBinding
import app.sosocom.smallstep.model.Diary
import app.sosocom.smallstep.util.ExtraConstants
import app.sosocom.smallstep.util.Log
import app.sosocom.smallstep.util.LogTag
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiaryActivity : BaseActivity<ActivityDiaryBinding>(R.layout.activity_diary) {
    private val viewModel by viewModels<DiaryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.vm = viewModel

        loadData()
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