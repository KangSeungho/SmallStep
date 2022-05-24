package app.sosocom.smallstep.ui.emotion_diary

import android.os.Bundle
import app.sosocom.smallstep.R
import app.sosocom.smallstep.base.BaseActivity
import app.sosocom.smallstep.databinding.ActivityEmotionEditBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmotionEditActivity : BaseActivity<ActivityEmotionEditBinding>(R.layout.activity_emotion_edit) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}