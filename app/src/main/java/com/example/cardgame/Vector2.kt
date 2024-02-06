package com.example.cardgame

public class Vector2 {
    public var x :Int = 0
    public var y :Int = 0
    public var stage : Int =0
    public constructor(_x:Int, _y:Int){
        x= _x
        y=_y
    }
    public constructor(_x:Int, _y:Int, _stage:Int){
        x= _x
        y=_y
        stage=_stage
    }
}