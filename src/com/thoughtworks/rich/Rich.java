package com.thoughtworks.rich;

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
        game.setMap(MapCreater.getCompleteMap());
        game.startPlay();
    }
}
