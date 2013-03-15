package com.thoughtworks.rich;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-2-23
 * Time: 上午10:13
 * To change this template use File | Settings | File Templates.
 */
public class Land {



    enum Type {err,start,space,tool,gift,magic,hospital,prison,mine}
    static final String[] NAME_ARRAY = {"","起点","空地","道具屋","礼品屋","魔法屋","医院","监狱","矿地"};
    static final String[] MARK_ARRAY = {"*","S","0","T","G","M","H","P","$"};

    int location;
    Type type;
    String name;
    String mark;
    Tool tool;

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
            case start:
                land = new LandStart();
                break;
            case space:
                land = new LandSpace();
                break;
            case tool:
                land = new LandTool();
                break;
            case gift:
                land = new LandGift();
                break;
            case magic:
                land = new LandMagic();
                break;
            case hospital:
                land = new LandHospital();
                break;
            case prison:
                land = new LandPrison();
                break;
            case mine:
                land = new LandMine();
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

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
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
