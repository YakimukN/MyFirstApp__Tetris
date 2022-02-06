package com.bignerdranch.android.geomain

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.bignerdranch.android.geomain.storage.AppPreferences
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    var tvHighScore: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val btnExit = findViewById<Button>(R.id.btn_exit)
        val btnNewGame = findViewById<Button>(R.id.btn_new_game)
        val btnResetScore = findViewById<Button>(R.id.btn_reset_score)
        tvHighScore = findViewById(R.id.tv_high_score)

        btnNewGame.setOnClickListener(this:: onBtnNewGameClick)
        btnResetScore.setOnClickListener(this::onBtnResetScore)
        btnExit.setOnClickListener(this::onBtnExitClick)


//        btnExit.setOnClickListener(this::handleExitEvent)

    }

    private fun onBtnNewGameClick(view: View){
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }

    private fun onBtnResetScore(view: View){
        val preferences = AppPreferences(this)
        preferences.clearHighScore()
        Snackbar.make(view,"Score successfully reset", Snackbar.LENGTH_SHORT).show()
        tvHighScore?.text = "High score: ${preferences.getHighScore()}"
    }

    private fun onBtnExitClick(view: View){
        System.exit(0)
    }

//    fun handleExitEvent(view: View){
//        finish()
//    }
}






















//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//
//class MainActivity : AppCompatActivity() {
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//
//    }
//
//}
