package com.thoughtworks.rich.component.land;

import com.thoughtworks.rich.command.InputOutput;
import com.thoughtworks.rich.component.Player;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-2-23
 * Time: 下午4:07
 * To change this template use File | Settings | File Templates.
 */
public class Mine extends Land {
    private int point;

    public void setPoint(int point) {
        this.point = point;
    }

    public int getPoint() {
        return point;
    }


    public void action(Player player) {
        InputOutput.outputStringLine(player.getName() + "获得点数：" + getPoint());
        player.setPoint(player.getPoint()+point);
    }
}
