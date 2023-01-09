package app.sosocom.smallstep.presentation.ui.diary

import android.content.Intent
import android.os.Bundle
import android.widget.PopupMenu
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import app.sosocom.smallstep.domain.model.Diary
import app.sosocom.smallstep.domain.util.Log
import app.sosocom.smallstep.domain.util.LogTag
import app.sosocom.smallstep.presentation.R
import app.sosocom.smallstep.presentation.base.BaseActivity
import app.sosocom.smallstep.presentation.base.CustomAlertDialog
import app.sosocom.smallstep.presentation.databinding.ActivityDiaryBinding
import app.sosocom.smallstep.presentation.util.ExtraConstants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DiaryActivity : BaseActivity<ActivityDiaryBinding>(R.layout.activity_diary) {
    private val viewModel by viewModels<DiaryViewModel>()

    private val editResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        when(it.resultCode) {
            RESULT_OK -> {
                val diary = it.data?.getParcelableExtra<Diary>(ExtraConstants.EXTRA_DIARY) ?: return@registerForActivityResult

                viewModel.setDiary(diary)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContent {
//            DiaryScreen()
//        }

        initActionBar()
        loadData()
    }

    private fun initActionBar() {
        binding.actionBar.run {
            // 뒤로가기
            btnBack.setOnClickListener { onBackPressed() }

            // 제목
            title.text = getString(R.string.diary_title)

            // 더보기
            btnEnd.setImageResource(R.drawable.ic_more_vertical)
            btnEnd.setOnClickListener {
                PopupMenu(activityContext, it).run {
                    menuInflater.inflate(R.menu.menu_modify, menu)

                    setOnMenuItemClickListener { item ->
                        when(item.itemId) {
                            // 수정하기
                            R.id.action_modify -> {
                                Intent(activityContext, DiaryEditActivity::class.java).run {
                                    putExtra(ExtraConstants.EXTRA_DIARY, viewModel.diary.value)
                                    putExtra(ExtraConstants.EXTRA_DATE, viewModel.diary.value?.baseDate)
                                    editResult.launch(this)
                                    pushActivity()
                                }
                            }

                            // 삭제하기
                            R.id.action_delete -> {
                                CustomAlertDialog(activityContext).apply {
                                    setMessage(R.string.delete_ask)
                                    setOnClickListener {
                                        lifecycleScope.launch {
                                            val diary = viewModel.diary.value ?: return@launch
                                            viewModel.deleteDiary(diary)

                                            finish()
                                        }
                                    }
                                    isCancel(true)
                                    show()
                                }
                            }
                        }

                        return@setOnMenuItemClickListener false
                    }

                    show()
                }
            }
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