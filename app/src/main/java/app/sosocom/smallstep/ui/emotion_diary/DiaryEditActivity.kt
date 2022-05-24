package app.sosocom.smallstep.ui.emotion_diary

import android.os.Bundle
import app.sosocom.smallstep.R
import app.sosocom.smallstep.base.BaseActivity
import app.sosocom.smallstep.databinding.ActivityDiaryEditBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiaryEditActivity : BaseActivity<ActivityDiaryEditBinding>(R.layout.activity_diary_edit) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}