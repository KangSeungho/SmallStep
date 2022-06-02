package app.sosocom.smallstep.presentation.ui.emotion_diary

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.sosocom.smallstep.domain.model.Diary
import app.sosocom.smallstep.domain.usecase.DiaryInsertUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiaryEditViewModel @Inject constructor(
    private val diaryEditUseCase: DiaryInsertUseCase
) : ViewModel() {
    val title = MutableLiveData<String>()       // 제목
    val content = MutableLiveData<String>()     // 내용

    var beforeDiary: Diary? = null

    var isRegister = false                      // 등록 여부

    fun resetAll() {
        this.title.value = ""
        this.content.value = ""
    }

    fun saveDiary(title: String, content: String) {
        viewModelScope.launch {
            val diary = Diary(
                title = title,
                content = content,
                createAt = beforeDiary?.createAt ?: System.currentTimeMillis()
            )

            diaryEditUseCase(diary)
        }
    }
}