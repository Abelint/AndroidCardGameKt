package com.example.cardgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton

class MainActivity : AppCompatActivity(), Animation.AnimationListener {

    lateinit var animHide : Animation
    lateinit var animShow : Animation
    lateinit var ib :ImageButton
    lateinit var animList :Animation.AnimationListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ib = findViewById(R.id.imageButton)
        animHide = AnimationUtils.loadAnimation(this,R.anim.changecard)
        animHide.setAnimationListener(this)
        animShow = AnimationUtils.loadAnimation(this,R.anim.showcard)
        animShow.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                ib.setImageResource(R.drawable.card_1)
                ib.startAnimation(animShow)
            }

            override fun onAnimationRepeat(animation: Animation?) {}

        })


    }

    public fun ImageButtonClick(view : View){
        ib.startAnimation(animHide)

    }

    override fun onAnimationStart(animation: Animation?) {

    }

    override fun onAnimationEnd(animation: Animation?) {
        ib.setImageResource(R.drawable.card_2)
        ib.startAnimation(animShow)
    }

    override fun onAnimationRepeat(animation: Animation?) {

    }
}