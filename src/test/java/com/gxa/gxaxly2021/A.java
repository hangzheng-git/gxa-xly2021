package com.gxa.gxaxly2021;

import net.minidev.json.JSONUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class A extends B {

    public static void main(String[] args) {
        float a = 14.0f;
        long b = 14L;
        System.out.println(a==b);
    }
}
abstract class B {
    public static void doSome(){}
    float da(){
        short b = 1;
        return b;
    }
    public int a = 1 ;
}
interface C{
    public abstract float get(int x);
}
