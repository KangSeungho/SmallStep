package app.sosocom.smallstep.domain.usecase

import app.sosocom.smallstep.domain.model.Diary
import app.sosocom.smallstep.domain.repository.DiaryRepository
import javax.inject.Inject

class DiaryInsertUseCase @Inject constructor(
    private val diaryRepository: DiaryRepository
) {
    suspend operator fun invoke(diary: Diary) = diaryRepository.insertDiary(diary)
}