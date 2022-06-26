package app.sosocom.smallstep.presentation.ui.todo

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import app.sosocom.smallstep.domain.model.DailyTodoBundle
import app.sosocom.smallstep.domain.model.Todo
import app.sosocom.smallstep.presentation.R
import app.sosocom.smallstep.presentation.base.BaseActivity
import app.sosocom.smallstep.presentation.databinding.ActivityTodoListBinding
import app.sosocom.smallstep.presentation.ui.todo.adapter.TodoAdapter
import app.sosocom.smallstep.presentation.util.ExtraConstants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime

@AndroidEntryPoint
class TodoListActivity : BaseActivity<ActivityTodoListBinding>(R.layout.activity_todo_list) {
    private val viewModel by viewModels<TodoViewModel>()

    private val adapter = TodoAdapter()

    private val editDialog by lazy { TodoEditDialog(this) }

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
        val dailyTodoBundle = intent.getParcelableExtra<DailyTodoBundle>(ExtraConstants.EXTRA_DAILY_TODO_BUNDLE)
        if(dailyTodoBundle == null) {
            finish()
            return
        }

        // 할일 목록이 없다면 자동으로 작성 화면 표시
        if(dailyTodoBundle.todoList.isEmpty()) {
            editDialog.show()
        }

        viewModel.setDailyTodoBundle(dailyTodoBundle)
    }

    private fun initActionBar() {
        binding.actionBar.run {
            // 뒤로가기
            btnBack.setOnClickListener { onBackPressed() }

            // 제목
            title.text = getString(R.string.todo_title)
        }
    }

    private fun initUI() {
        // 리스트 세팅
        binding.listTodo.adapter = adapter

        // 아이템 클릭
        adapter.setOnItemClickListener { todo ->
            editDialog.todo = todo
            editDialog.show()
        }

        // 아이템 체크
        adapter.setOnCheckedListener { todo, isChecked ->
            lifecycleScope.launch {
                todo.isComplete = isChecked
                viewModel.insertTodo(todo)
            }
        }

        // 아이템 추가 버튼
        binding.btnAddTodo.setOnClickListener {
            editDialog.show()
        }

        // 작성 화면 상태에 따라 표시
        editDialog.setOnShowListener { binding.btnAddTodo.visibility = View.GONE }
        editDialog.setOnDismissListener { binding.btnAddTodo.visibility = View.VISIBLE }

        editDialog.setOnSaveListener { todo, content ->
            when(todo) {
                // 추가
                null -> {
                    val createTodo = Todo(
                        content = content,
                        isComplete = false,
                        baseDate = LocalDate.now(),
                        createdAt = LocalDateTime.now()
                    )

                    lifecycleScope.launch { viewModel.insertTodo(createTodo) }
                    adapter.submitList(adapter.currentList.toMutableList().apply { add(createTodo) })
                }

                // 변경
                else -> {
                    val position = adapter.currentList.indexOf(todo)
                    todo.content = content

                    lifecycleScope.launch { viewModel.insertTodo(todo) }
                    adapter.notifyItemChanged(position)
                }
            }
        }
    }

    private fun initObserver() {
        viewModel.dailyTodoBundle.observe(this) {
            adapter.submitList(it.todoList)
        }
    }
}