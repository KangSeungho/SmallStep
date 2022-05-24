package app.sosocom.smallstep.ui.emotion_diary

import android.os.Bundle
import androidx.activity.viewModels
import app.sosocom.smallstep.R
import app.sosocom.smallstep.base.BaseActivity
import app.sosocom.smallstep.databinding.ActivityDiaryEditBinding
import app.sosocom.smallstep.model.Diary
import app.sosocom.smallstep.util.ExtraConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiaryEditActivity : BaseActivity<ActivityDiaryEditBinding>(R.layout.activity_diary_edit) {
    private val viewModel by viewModels<DiaryEditViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.vm = viewModel

        loadData()
    }

    private fun loadData() {
        val diary = intent.getParcelableExtra<Diary>(ExtraConstants.EXTRA_DIARY)

        when(diary) {
            // 등록
            null -> {
                viewModel.isRegister = true
            }

            // 수정
            else -> {
                viewModel.isRegister = false
                viewModel.title.value = diary.title
                viewModel.content.value = diary.content
            }
        }
    }
}