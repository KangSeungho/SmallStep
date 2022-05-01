package app.sosocom.smallstep.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import app.sosocom.smallstep.R
import app.sosocom.smallstep.base.BaseActivity
import app.sosocom.smallstep.databinding.ActivityMainBinding
import app.sosocom.smallstep.ui.emotion_diary.EmotionDiaryActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getLayoutResId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun onClickTodo(view: View) {

    }

    fun onClickHappyPoint(view: View) {

    }

    fun onClickGoodAndNew(view: View) {

    }

    fun onClickEmotionDiary(view: View) {
        val intent = Intent(this, EmotionDiaryActivity::class.java)
        startActivity(intent)
    }
}