package app.sosocom.smallstep.presentation.ui.diary

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import app.sosocom.smallstep.presentation.base.BaseActivity
import app.sosocom.smallstep.domain.model.Diary
import app.sosocom.smallstep.presentation.R
import app.sosocom.smallstep.presentation.base.CustomAlertDialog
import app.sosocom.smallstep.presentation.databinding.ActivityDiaryEditBinding
import app.sosocom.smallstep.presentation.util.ExtraConstants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDate

@AndroidEntryPoint
class DiaryEditActivity : BaseActivity<ActivityDiaryEditBinding>(R.layout.activity_diary_edit) {
    private val viewModel by viewModels<DiaryEditViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.vm = viewModel

        initActionBar()
        loadData()
        initUI()
    }

    private fun initActionBar() {
        binding.actionBar.run {
            btnBack.setOnClickListener { onBackPressed() }

            title.text = getString(R.string.diary_title)
        }
    }

    private fun loadData() {
        viewModel.baseDate = intent.getSerializableExtra(ExtraConstants.EXTRA_DATE) as LocalDate
        val diary = intent.getParcelableExtra<Diary>(ExtraConstants.EXTRA_DIARY)

        when(diary) {
            // 등록
            null -> {}

            // 수정
            else -> {
                viewModel.id = diary.id
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

            CustomAlertDialog(this).apply {
                setMessage(R.string.save_ask)
                isCancel(true)
                setOnClickListener {
                    lifecycleScope.launch {
                        val diary = viewModel.saveDiary(title, content)

                        CustomAlertDialog(activityContext).apply {
                            setMessage(R.string.diary_save_success)
                            setOnClickListener {
                                val intent = Intent().apply {
                                    putExtra(ExtraConstants.EXTRA_DIARY, diary)
                                }
                                setResult(RESULT_OK, intent)
                                finish()
                            }
                            show()
                        }
                    }
                }
                show()
            }
        }
    }
}