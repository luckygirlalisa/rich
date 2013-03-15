package com.thoughtworks.rich;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-2-23
 * Time: 下午4:06
 * To change this template use File | Settings | File Templates.
 */
public class LandPrison extends Land {
    public static final int DAY_IN_PRISON = 2;

    @Override
    public void action(Player player) {
        InputOutput.outputStringLine(player.getName() + "进入监狱");
        player.beInPrison(DAY_IN_PRISON,getLocation());
    }
}
