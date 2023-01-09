package app.sosocom.smallstep.presentation.ui.diary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import app.sosocom.smallstep.domain.model.Diary
import app.sosocom.smallstep.domain.usecase.DiaryDeleteUseCase
import app.sosocom.smallstep.presentation.util.ExtraConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(
    private val diaryDeleteUseCase: DiaryDeleteUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _diary = savedStateHandle.getLiveData<Diary>(ExtraConstants.EXTRA_DIARY)
    val diary: LiveData<Diary> = _diary

    fun setDiary(diary: Diary) {
        _diary.value = diary
    }

    suspend fun deleteDiary(diary: Diary) = diaryDeleteUseCase(diary)
}