package app.sosocom.smallstep.presentation.ui.happy_point

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.sosocom.smallstep.domain.model.DailyHappyPointBundle
import app.sosocom.smallstep.domain.model.HappyPoint
import app.sosocom.smallstep.domain.usecase.HappyPointInsertUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HappyPointListViewModel @Inject constructor(
    private val insertUseCase: HappyPointInsertUseCase
): ViewModel() {
    private val _dailyHappyPointBundle = MutableLiveData<DailyHappyPointBundle>()
    val dailyHappyPointBundle: LiveData<DailyHappyPointBundle> get() = _dailyHappyPointBundle

    fun setDailyHappyPointBundle(bundle: DailyHappyPointBundle) {
        _dailyHappyPointBundle.value = bundle
    }

    suspend fun insertHappyPoint(happyPoint: HappyPoint) = insertUseCase(happyPoint)
}