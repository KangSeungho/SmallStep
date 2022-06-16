package app.sosocom.smallstep.presentation.ui.diary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.sosocom.smallstep.domain.model.Diary
import app.sosocom.smallstep.domain.usecase.DiaryDeleteUseCase
import javax.inject.Inject

class DiaryViewModel @Inject constructor(
    val diaryDeleteUseCase: DiaryDeleteUseCase
): ViewModel() {
    private val _diary = MutableLiveData<Diary>()
    val diary: LiveData<Diary> = _diary

    fun setDiary(diary: Diary) {
        _diary.value = diary
    }
}