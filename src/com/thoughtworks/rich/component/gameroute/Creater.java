package com.thoughtworks.rich.component.gameroute;

import com.thoughtworks.rich.component.land.Land;
import com.thoughtworks.rich.component.land.Mine;
import com.thoughtworks.rich.component.land.Space;

import static com.thoughtworks.rich.component.land.Land.createLand;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-2-27
 * Time: 上午10:08
 * To change this template use File | Settings | File Templates.
 */
public class Creater {
    public static Element getCompleteMap(){
        Element element = new Element();
        Land land;
        land = createLand(Land.Type.START);
        element.addLand(land);
        for(int i=0;i<13;i++){
            land = createLand(Land.Type.SPACE);
            ((Space)land).setValue(200);
            element.addLand(land);
        }
        land = createLand(Land.Type.HOSPITAL);
        element.addLand(land);
        for(int i=0;i<13;i++){
            land = createLand(Land.Type.SPACE);
            ((Space)land).setValue(200);
            element.addLand(land);
        }
        land = createLand(Land.Type.TOOL);
        element.addLand(land);
        for(int i=0;i<6;i++){
            land = createLand(Land.Type.SPACE);
            ((Space)land).setValue(500);
            element.addLand(land);
        }
        land = createLand(Land.Type.GIFT);
        element.addLand(land);
        for(int i=0;i<13;i++){
            land = createLand(Land.Type.SPACE);
            ((Space)land).setValue(300);
            element.addLand(land);
        }
        land = createLand(Land.Type.PRISON);
        element.addLand(land);
        for(int i=0;i<13;i++){
            land = createLand(Land.Type.SPACE);
            ((Space)land).setValue(300);
            element.addLand(land);
        }
        land = createLand(Land.Type.MAGIC);
        element.addLand(land);
        land = createLand(Land.Type.MINE);
        ((Mine)land).setPoint(60);
        element.addLand(land);
        land = createLand(Land.Type.MINE);
        ((Mine)land).setPoint(80);
        element.addLand(land);
        land = createLand(Land.Type.MINE);
        ((Mine)land).setPoint(40);
        element.addLand(land);
        land = createLand(Land.Type.MINE);
        ((Mine)land).setPoint(100);
        element.addLand(land);
        land = createLand(Land.Type.MINE);
        ((Mine)land).setPoint(80);
        element.addLand(land);
        land = createLand(Land.Type.MINE);
        ((Mine)land).setPoint(20);
        element.addLand(land);

        return element;
    }

    public static Element getToolLandMap(){
        Element element = new Element();
        Land land;
        for (int i=0;i<70;i++){
            land = createLand(Land.Type.TOOL);
            element.addLand(land);
        }
        return element;
    }

    public static Element getGiftLandMap(){
        Element element = new Element();
        Land land;
        for (int i=0;i<70;i++){
            land = createLand(Land.Type.GIFT);
            element.addLand(land);
        }
        return element;
    }
}
