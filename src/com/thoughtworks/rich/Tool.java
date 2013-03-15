package com.thoughtworks.rich;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-2-23
 * Time: 下午5:00
 * To change this template use File | Settings | File Templates.
 */
public class Tool {


    enum Type{roadblock, robot, bomb}

    public static final String[] NAME_ARRAY = {"路障","机器娃娃","炸弹"};
    public static final String[] MARK_ARRAY = {"#","","@"};
    private static final int range = 10;

    protected Type type;
    protected int point;
    protected int location;

    public Type getType() {
        return type;
		
    }

    public void setType(Type type) {
        this.type = type;
    }

    protected int getPoint(){
        return point;
    }

    public static Tool createTool(Type type) {
        Tool tool = null;
        switch (type){
            case roadblock:
                tool = new ToolRoadblock();
                break;
            case robot:
                tool = new ToolRobot();
                break;
            case  bomb:
                tool = new ToolBomb();
                break;
            default:
                tool = new Tool();
                break;
        }
        tool.setType(type);
        return tool;
    }

    public String getName(){
        Type[] types = Type.values();
        for(int i=0;i<types.length;i++){
            if(type == types[i]){
                return NAME_ARRAY[i];
            }
        }
        return null;
    }

    public String getMark() {
        Type[] types = Type.values();
        for(int i=0;i<types.length;i++){
            if(type == types[i]){
                return MARK_ARRAY[i];
            }
        }
        return null;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getRange() {
        return range;
    }
}
