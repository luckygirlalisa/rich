package com.thoughtworks.rich;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-2-23
 * Time: 上午9:52
 * To change this template use File | Settings | File Templates.
 */
public class Map {
    ArrayList<Land> lands = new ArrayList<Land>();

    public Map() {
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
