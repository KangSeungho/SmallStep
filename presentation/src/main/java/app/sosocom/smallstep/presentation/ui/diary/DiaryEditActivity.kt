package app.sosocom.smallstep.presentation.ui.diary

import android.os.Bundle
import androidx.activity.viewModels
import app.sosocom.smallstep.presentation.base.BaseActivity
import app.sosocom.smallstep.domain.model.Diary
import app.sosocom.smallstep.presentation.R
import app.sosocom.smallstep.presentation.databinding.ActivityDiaryEditBinding
import app.sosocom.smallstep.presentation.util.ExtraConstants
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class DiaryEditActivity : BaseActivity<ActivityDiaryEditBinding>(R.layout.activity_diary_edit) {
    private val viewModel by viewModels<DiaryEditViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.vm = viewModel

        loadData()
        initUI()
    }

    private fun loadData() {
        viewModel.baseDate = intent.getSerializableExtra(ExtraConstants.EXTRA_DATE) as Calendar
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

    private fun initUI() {
        // 등록/수정 버튼 클릭
        binding.btnSaveDiary.setOnClickListener {
            val title = viewModel.title.value
            val content = viewModel.content.value

            if(title == null) {
                toast(R.string.diary_input_title_empty)
                return@setOnClickListener
            }

            if(content == null) {
                toast(R.string.diary_input_content_empty)
                return@setOnClickListener
            }

            viewModel.saveDiary(title, content)
            viewModel.resetAll()
        }
    }
}