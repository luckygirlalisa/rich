package com.thoughtworks.rich.component.gameroute;

import com.thoughtworks.rich.component.land.Land;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-2-23
 * Time: 上午9:52
 * To change this template use File | Settings | File Templates.
 */
public class Element {
    ArrayList<Land> lands = new ArrayList<Land>();

    public Element() {
    }

    public void addLand(Land land){
        land.setLocation(getSize());
        lands.add(land);
    }

    public int getSize() {
        return lands.size();
    }

    public Land getLand(int i) {
        return lands.get(i);
    }

    @Override
    public String toString() {
        Object[] landArr = lands.toArray();
        StringBuilder stringBuffer = new StringBuilder();
        for (Object aLandArr : landArr) {
            stringBuffer.append(((Land) aLandArr).getMark());
        }
        return stringBuffer.toString();
    }
}
