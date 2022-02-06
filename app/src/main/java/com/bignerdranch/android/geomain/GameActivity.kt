package com.bignerdranch.android.geomain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.bignerdranch.android.geomain.storage.AppPreferences
import com.bignerdranch.android.geomain.view.TetrisView

class GameActivity : AppCompatActivity() {

    var tvHighScore: TextView? = null
    var tvCurrentScore: TextView? = null
    var appPreferences: AppPreferences? = null

    private lateinit var tetrisView: TetrisView
    private val appModel: AppModel = AppModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        appPreferences = AppPreferences(this)

        appModel.setPreferences(appPreferences)

        val btnRestart = findViewById<Button>(R.id.btn_restart)
        tvHighScore = findViewById(R.id.tv_high_score)
        tvCurrentScore = findViewById(R.id.tv_current_score)

        tetrisView = findViewById(R.id.view_tetris)
        tetrisView.setActivity(this)
        tetrisView.setModel(appModel)
        tetrisView.setOnTouchListener(this::onTetrisViewTouch)
        btnRestart.setOnClickListener(this::btnRestartClick)

        updateHighScore()
        updateCurrentScore()
    }

    private fun btnRestartClick(view: View){
        appModel.restartGame()
    }

    private fun updateHighScore(){
        tvHighScore?.text = "${appPreferences?.getHighScore()}"
    }

    private fun updateCurrentScore(){
        tvCurrentScore?.text = "0"
    }

    private fun onTetrisViewTouch(view: View, event: MotionEvent):Boolean{
        if(appModel.isGameOver() || appModel.isGameAwaitingStart()){
            appModel.startGame()
            tetrisView.setGameCommandWithDelay(AppModel.Motions.DOWN)
        } else if (appModel.isGameActive()){
            when(resolveTouchDirection(view, event)){
                0 -> moveTetromino(AppModel.Motions.Left)
                1 -> moveTetromino(AppModel.Motions.ROTATE)
                2 -> moveTetromino(AppModel.Motions.DOWN)
                3 -> moveTetromino(AppModel.Motions.RIGHT)
            }
        }
        return true
    }

    private fun resolveTouchDirection(view: View, event: MotionEvent):Int{
        val x = event.x / view.width
        val y = event.y / view.height
        val direction: Int
        direction = if(y > x){
            if(x > 1 - y) 2 else 0
        } else{
            if(x > 1 - y) 3 else 1
        }
        return direction
    }

    private fun moveTetromino(montion: AppModel.Motions){
        if(appModel.isGameActive()){
            tetrisView.setGameCommand(montion)
        }
    }

    private fun updateHightScore(){
        tvHighScore?.text = "${appPreferences?.getHighScore()}"
    }

    private fun updateCurrectScore(){
        tvCurrentScore?.text = "0"
    }

}