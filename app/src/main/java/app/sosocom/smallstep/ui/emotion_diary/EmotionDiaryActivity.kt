package app.sosocom.smallstep.ui.emotion_diary

import android.os.Bundle
import app.sosocom.smallstep.R
import app.sosocom.smallstep.base.BaseActivity
import app.sosocom.smallstep.databinding.ActivityEmotionDiaryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmotionDiaryActivity : BaseActivity<ActivityEmotionDiaryBinding>() {
    override fun getLayoutResId() = R.layout.activity_emotion_diary

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}