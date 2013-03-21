package com.thoughtworks.rich;

import com.thoughtworks.rich.command.Game;
import com.thoughtworks.rich.component.gameroute.Creater;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-2-28
 * Time: 下午4:19
 * To change this template use File | Settings | File Templates.
 */
public class Rich {
    public static void main(String[] arg){
        Game game = new Game();
        game.setElement(Creater.getCompleteMap());
        game.startPlay();
    }
}
