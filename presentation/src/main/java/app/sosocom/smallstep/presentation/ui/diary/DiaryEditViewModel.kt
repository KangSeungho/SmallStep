package app.sosocom.smallstep.presentation.ui.diary

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.sosocom.smallstep.domain.model.Diary
import app.sosocom.smallstep.domain.usecase.DiaryInsertUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DiaryEditViewModel @Inject constructor(
    private val diaryEditUseCase: DiaryInsertUseCase
) : ViewModel() {
    val title = MutableLiveData<String>()       // 제목
    val content = MutableLiveData<String>()     // 내용
    lateinit var baseDate: LocalDate            // 기준 날짜

    var isRegister = false                      // 등록 여부

    fun saveDiary(title: String, content: String) {
        viewModelScope.launch {
            val diary = Diary(
                title = title,
                content = content,
                baseDate = baseDate,
                createdAt = Calendar.getInstance()
            )

            diaryEditUseCase(diary)
        }
    }
}