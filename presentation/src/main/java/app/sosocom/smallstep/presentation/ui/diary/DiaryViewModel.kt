package app.sosocom.smallstep.presentation.ui.diary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.sosocom.smallstep.domain.model.Diary
import app.sosocom.smallstep.domain.usecase.DiaryDeleteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(
    private val diaryDeleteUseCase: DiaryDeleteUseCase
): ViewModel() {
    private val _diary = MutableLiveData<Diary>()
    val diary: LiveData<Diary> = _diary

    fun setDiary(diary: Diary) {
        _diary.value = diary
    }

    suspend fun deleteDiary(diary: Diary) = diaryDeleteUseCase(diary)
}