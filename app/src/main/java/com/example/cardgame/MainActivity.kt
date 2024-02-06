package com.example.cardgame

import android.os.Bundle
import android.os.Debug
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    lateinit var animHide : Animation
    lateinit var animShow : Animation
    lateinit var ib :ImageButton
    lateinit var label : TextView
    lateinit var animList :Animation.AnimationListener
    lateinit var gridlayout : GridLayout

    var list : MutableMap<ImageButton, Vector2> =mutableMapOf()

    val currenciesMutableMap: MutableMap<String, String> =
        mutableMapOf("Гривна" to "Украина", "Доллар" to "США", "Рубль" to "Россия")

    var nowState = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ib = findViewById(R.id.imageButton)
        label = findViewById(R.id.textView)
        gridlayout = findViewById(R.id.gridLayout)


        AddCards(6,6)



    }

    fun AddCards(widthUnit:Int , heightUnit :Int){
        gridlayout.rowCount = heightUnit
        gridlayout.columnCount =widthUnit
        ////////////
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels
        label.text="h " + height + " w " + width
        //https://stackoverflow.com/questions/4743116/get-screen-width-and-height-in-android
        ///////////
        val  procentSize = (100*width/widthUnit)/width

        for (row in 0 until gridlayout.rowCount) {
            for (column in 0 until gridlayout.columnCount) {
                val imba = ImageButton(this)
                imba.setImageResource(R.drawable.card_1)

                val vector : Vector2= Vector2(column,row)

                imba.setOnClickListener(object  : View.OnClickListener{
                    override fun onClick(v: View?) {
                       vector.stage= ChangeCard(imba, vector.stage)
                    }
                })


                list.put(imba, vector)

                Log.d("AddCards", list.toString())
                val params = GridLayout.LayoutParams()
                params.rowSpec = GridLayout.spec(row)
                params.columnSpec = GridLayout.spec(column)
                params.height =width*procentSize/100
                params.width =width*procentSize/100
                label.text="h " +procentSize
                gridlayout.addView(imba,params)
            }
        }
    }

    fun ChangeCard(ib : ImageButton, stage:Int): Int {
        var newState = stage
        animHide = AnimationUtils.loadAnimation(this,R.anim.changecard)
        animHide.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {

                ChangeCard(ib, newState)
            }

            override fun onAnimationRepeat(animation: Animation?) {}

        })
        animShow = AnimationUtils.loadAnimation(this,R.anim.showcard)
        animShow.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {

                ChangeCard(ib, newState)
            }

            override fun onAnimationRepeat(animation: Animation?) {}

        })


        if(newState >=4) newState =0
        if(stage == 1){
            newState++
            ib.setImageResource(R.drawable.card_2)
            ib.startAnimation(animShow)
        }
        else if(stage ==3){
            newState++
            ib.setImageResource(R.drawable.card_1)
            ib.startAnimation(animShow)
        }
        else if(stage ==0 ||stage == 2){
            newState++
            ib.startAnimation(animHide)

        }




        label.text = newState.toString()

        return newState
    }


    public fun ImageButtonClick(view : View){
       ChangeCard(ib,nowState)

    }


}