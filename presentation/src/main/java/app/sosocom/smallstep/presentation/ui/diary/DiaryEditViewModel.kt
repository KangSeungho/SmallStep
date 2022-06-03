package app.sosocom.smallstep.presentation.ui.diary

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiaryEditViewModel @Inject constructor() : ViewModel() {
    val title = MutableLiveData<String>()       // 제목
    val content = MutableLiveData<String>()     // 내용

    var isRegister = false                      // 등록 여부
}