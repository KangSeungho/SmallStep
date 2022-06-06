package app.sosocom.smallstep.domain.usecase

import app.sosocom.smallstep.domain.repository.DiaryRepository
import javax.inject.Inject

class DiaryQueryUseCase @Inject constructor(
    private val repository: DiaryRepository
) {
    suspend operator fun invoke() = repository.getAllDiary()
}