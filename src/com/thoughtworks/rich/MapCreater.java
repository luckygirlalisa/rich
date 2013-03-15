package com.thoughtworks.rich;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-2-27
 * Time: 上午10:08
 * To change this template use File | Settings | File Templates.
 */
public class MapCreater {
    public static Map getCompleteMap(){
        Map map = new Map();
        Land land;
        land = Land.createLand(Land.Type.start);
        map.addLand(land);
        for(int i=0;i<13;i++){
            land = Land.createLand(Land.Type.space);
            ((LandSpace)land).setValue(200);
            map.addLand(land);
        }
        land = Land.createLand(Land.Type.hospital);
        map.addLand(land);
        for(int i=0;i<13;i++){
            land = Land.createLand(Land.Type.space);
            ((LandSpace)land).setValue(200);
            map.addLand(land);
        }
        land = Land.createLand(Land.Type.tool);
        map.addLand(land);
        for(int i=0;i<6;i++){
            land = Land.createLand(Land.Type.space);
            ((LandSpace)land).setValue(500);
            map.addLand(land);
        }
        land = Land.createLand(Land.Type.gift);
        map.addLand(land);
        for(int i=0;i<13;i++){
            land = Land.createLand(Land.Type.space);
            ((LandSpace)land).setValue(300);
            map.addLand(land);
        }
        land = Land.createLand(Land.Type.prison);
        map.addLand(land);
        for(int i=0;i<13;i++){
            land = Land.createLand(Land.Type.space);
            ((LandSpace)land).setValue(300);
            map.addLand(land);
        }
        land = Land.createLand(Land.Type.magic);
        map.addLand(land);
        land = Land.createLand(Land.Type.mine);
        ((LandMine)land).setPoint(60);
        map.addLand(land);
        land = Land.createLand(Land.Type.mine);
        ((LandMine)land).setPoint(80);
        map.addLand(land);
        land = Land.createLand(Land.Type.mine);
        ((LandMine)land).setPoint(40);
        map.addLand(land);
        land = Land.createLand(Land.Type.mine);
        ((LandMine)land).setPoint(100);
        map.addLand(land);
        land = Land.createLand(Land.Type.mine);
        ((LandMine)land).setPoint(80);
        map.addLand(land);
        land = Land.createLand(Land.Type.mine);
        ((LandMine)land).setPoint(20);
        map.addLand(land);

        return map;
    }

    public static Map getToolLandMap(){
        Map map = new Map();
        Land land;
        for (int i=0;i<70;i++){
            land = Land.createLand(Land.Type.tool);
            map.addLand(land);
        }
        return map;
    }

    public static Map getGiftLandMap(){
        Map map = new Map();
        Land land;
        for (int i=0;i<70;i++){
            land = Land.createLand(Land.Type.gift);
            map.addLand(land);
        }
        return map;
    }
}
