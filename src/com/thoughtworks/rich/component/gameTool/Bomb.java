package com.thoughtworks.rich.component.gameTool;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-2-25
 * Time: 下午10:50
 * To change this template use File | Settings | File Templates.
 */
public class Bomb extends Tool {
    protected static final int POINT = 50;


    public static final int HOSPITAL_DAY = 3;

    public int getPoint() {
        return POINT;
    }


    public static int getHospitalDay(){
        return HOSPITAL_DAY;
    }

}
