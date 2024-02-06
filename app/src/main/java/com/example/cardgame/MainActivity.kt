package com.example.cardgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity(), Animation.AnimationListener {

    lateinit var animHide : Animation
    lateinit var animShow : Animation
    lateinit var ib :ImageButton
    lateinit var label : TextView
    lateinit var animList :Animation.AnimationListener

    var nowState = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ib = findViewById(R.id.imageButton)
        label = findViewById(R.id.textView)
        animHide = AnimationUtils.loadAnimation(this,R.anim.changecard)
        animHide.setAnimationListener(this)
        animShow = AnimationUtils.loadAnimation(this,R.anim.showcard)
        animShow.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {

                ChangeCard(nowState)
            }

            override fun onAnimationRepeat(animation: Animation?) {}

        })

    }

    fun ChangeCard(stage:Int){
        if(nowState >=4) nowState =0
        if(stage == 1){
            nowState++
            ib.setImageResource(R.drawable.card_2)
            ib.startAnimation(animShow)
        }
        else if(stage ==3){
            nowState++
            ib.setImageResource(R.drawable.card_1)
            ib.startAnimation(animShow)
        }
        else if(stage ==0 ||stage == 2){
            nowState++
            ib.startAnimation(animHide)

        }


        label.text = nowState.toString()
    }


    public fun ImageButtonClick(view : View){
       ChangeCard(nowState)

    }

    override fun onAnimationStart(animation: Animation?) {

    }

    override fun onAnimationEnd(animation: Animation?) {
       ChangeCard(nowState)
    }

    override fun onAnimationRepeat(animation: Animation?) {

    }
}