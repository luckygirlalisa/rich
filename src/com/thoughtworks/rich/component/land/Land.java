package com.thoughtworks.rich.component.land;

import com.thoughtworks.rich.component.gameTool.Tool;
import com.thoughtworks.rich.component.Player;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-2-23
 * Time: 上午10:13
 * To change this template use File | Settings | File Templates.
 */
public class Land {


    enum NewType{
        ERR("","*"),
        START("起点","S"),
        SPACE("空地","0"),
        TOOL("道具屋","T"),
        GIFT("礼品屋","G"),
        MAGIC("魔法屋","M"),
        HOSPITAL("医院","H"),
        PRISON("监狱","P"),
        MINE("矿地","$");

        private String name;
        private String mark;

        NewType(String name, String mark) {
            this.name=name;
            this.mark=mark;
        }
    }
    public enum Type {ERR, START, SPACE, TOOL, GIFT, MAGIC, HOSPITAL, PRISON, MINE}
    static final String[] NAME_ARRAY = {"","起点","空地","道具屋","礼品屋","魔法屋","医院","监狱","矿地"};
    static final String[] MARK_ARRAY = {"*","S","0","T","G","M","H","P","$"};

    int location;
    Type type;
    String name;
    String mark;


    public Land() {
    }
    public Land(String name) {
        setName(name);
    }

    public Land(Type type) {
        setType(type);
    }

    public static Land createLand(Type type) {

        Land land = null;
        switch (type){
            case START:
                land = new Start();
                break;
            case SPACE:
                land = new Space();
                break;
            case TOOL:
                land = new Tool();
                break;
            case GIFT:
                land = new Gift();
                break;
            case MAGIC:
                land = new Magic();
                break;
            case HOSPITAL:
                land = new Hospital();
                break;
            case PRISON:
                land = new Prison();
                break;
            case MINE:
                land = new Mine();
                break;
            default:
                land = new Land();
                break;
        }
        land.setType(type);
        return land;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
        setName(getNameFromType(type));
        setMark(getMarkFromType(type));
    }

    public static int getTypeIndex(Type type){
        Type[] types = Type.values();
        for(int i=0;i<types.length;i++){
            if(type == types[i]){
                return i;
            }
        }
        return 0;
    }
    public static String getNameFromType(Type type){
        int index = getTypeIndex(type);
        return NAME_ARRAY[index];
    }

    public static String getMarkFromType(Type type){
        int index = getTypeIndex(type);
        return MARK_ARRAY[index];
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public com.thoughtworks.rich.component.gameTool.Tool getTool() {
        return tool;
    }

    public void setTool(com.thoughtworks.rich.component.gameTool.Tool tool) {
        tool.setLocation(getLocation());
        this.tool = tool;
    }

    public void action(Player player) {

    }

    public String getOwnerMark() {
        return " ";
    }

    public void clearTool() {
        tool = null;
    }
}
