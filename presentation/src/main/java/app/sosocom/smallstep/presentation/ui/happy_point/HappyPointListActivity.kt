package app.sosocom.smallstep.presentation.ui.happy_point

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import app.sosocom.smallstep.domain.model.DailyHappyPointBundle
import app.sosocom.smallstep.presentation.R
import app.sosocom.smallstep.presentation.base.BaseActivity
import app.sosocom.smallstep.presentation.databinding.ActivityHappyPointListBinding
import app.sosocom.smallstep.presentation.ui.happy_point.adapter.HappyPointAdapter
import app.sosocom.smallstep.presentation.util.ExtraConstants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDate

@AndroidEntryPoint
class HappyPointListActivity : BaseActivity<ActivityHappyPointListBinding>(R.layout.activity_happy_point_list) {
    private val viewModel by viewModels<HappyPointListViewModel>()

    private val adapter = HappyPointAdapter()

    private val editDialog by lazy {
        HappyPointEditDialog(
            context = this,
            baseDate = viewModel.dailyHappyPointBundle.value?.baseDate ?: LocalDate.now()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.vm = viewModel

        loadData()
        initActionBar()
        initUI()
        initObserver()
    }

    private fun loadData() {
        // 데이터 리스트
        val dailyHappyPointBundle = intent.getParcelableExtra<DailyHappyPointBundle>(ExtraConstants.EXTRA_DAILY_HAPPY_POINT_BUNDLE)
        if(dailyHappyPointBundle == null) {
            finish()
            return
        }

        viewModel.setDailyHappyPointBundle(dailyHappyPointBundle)
    }

    private fun initActionBar() {
        binding.actionBar.run {
            // 뒤로가기
            btnBack.setOnClickListener { onBackPressed() }

            // 제목
            title.text = getString(R.string.happy_point_title)
        }
    }

    private fun initUI() {
        // 리스트 세팅
        binding.listHappyPoint.adapter = adapter

        // 아이템 추가 버튼
        binding.btnAddHappyPoint.setOnClickListener {
            editDialog.show()
        }

        // 작성 완료
        editDialog.setOnDoneListener { happyPoint ->
            lifecycleScope.launch {
                viewModel.insertHappyPoint(happyPoint)
            }
        }
    }

    private fun initObserver() {
        viewModel.dailyHappyPointBundle.observe(this) {
            adapter.submitList(it.happyPointList)
        }
    }
}