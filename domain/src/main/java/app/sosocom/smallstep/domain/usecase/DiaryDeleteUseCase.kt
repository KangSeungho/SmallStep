package app.sosocom.smallstep.domain.usecase

import app.sosocom.smallstep.domain.model.Diary
import app.sosocom.smallstep.domain.repository.DiaryRepository
import javax.inject.Inject

class DiaryDeleteUseCase @Inject constructor(
    private val repository: DiaryRepository
) {
    suspend operator fun invoke(diary: Diary) = repository.deleteDiary(diary)
}