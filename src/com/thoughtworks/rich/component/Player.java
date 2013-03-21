package com.thoughtworks.rich.component;

import com.thoughtworks.rich.command.InputOutput;
import com.thoughtworks.rich.component.gameroute.Element;
import com.thoughtworks.rich.component.land.Land;
import com.thoughtworks.rich.component.land.Space;
import com.thoughtworks.rich.component.gameTool.Tool;

import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-2-23
 * Time: 下午4:52
 * To change this template use File | Settings | File Templates.
 */
public class Player {
    enum NewDisableState{
        NORMAL("正常"),
        HOSPITAL("在住院"),
        PRISON("在监狱");

        private String name;

        NewDisableState(String name){
            this.name = name;
        }
    }

    enum NewGodState {
        NONE("无"),
        MASCOT("福神");

        private String name;

        NewGodState (String name){
            this.name = name;
        }
    }


    public enum DisableState {normal,hospital,prison}
    public enum GodState {none,mascot}

    public static final String[] NAME_DISABLE_STATE = {"正常","在住院","在监狱"};
    public static final String[] NAME_GOD_STATE = {"无","福神"};
    public static final int DAY_WITH_MASCOT = 5;

    protected int id;
    protected String name;
    protected int money;
    protected int point;
    public LinkedList<Land> lands = new LinkedList<Land>();
    protected LinkedList<Tool> tools = new LinkedList<Tool>();
    protected int location;
    protected DisableState disableState = DisableState.normal;
    protected GodState godState = GodState.none;
    protected Element element;
    private String mark;
    private int disableCount;
    private int godCount;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Player(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void addLand(Land land){
         lands.add(land);
    }

    public int getLandNum(){
        return lands.size();
    }

    public void addTool(Tool tool){
        tools.add(tool);
    }

    public int getToolNum(){
        return tools.size();
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public DisableState getDisableState() {
        return disableState;
    }

    public void setDisableState(DisableState disableState) {
        this.disableState = disableState;
    }

    public GodState getGodState() {
        return godState;
    }

    public void setGodState(GodState godState) {
        this.godState = godState;
    }

    public String getNameOfDisableState() {
        DisableState[] disableStates = DisableState.values();
        for(int i =0;i<disableStates.length;i++){
            if(disableStates[i] == disableState){
                return  NAME_DISABLE_STATE[i];
            }
        }
        return null;
    }

    public String getNameOfGodState() {
        GodState[] godStates = GodState.values();
        for(int i =0;i<godStates.length;i++){
            if(godStates[i] == godState){
                return  NAME_GOD_STATE[i];
            }
        }
        return null;
    }

    public void move(int walkTimes) {
        location+=walkTimes;
        if(element != null){
            location%= element.getSize();
        }
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public void buyLand(Land land) {
        money -= ((Space)land).getValue();
        addLand(land);
        ((Space)land).setOwner(this);
    }

    public void payForTravelling(Space space) {
        if(!willPayForTravelling(space)){
            return;
        }
        int TravellingExpense = space.getTravellingExpense();
        money -= TravellingExpense;
        Player landSpaceOwner = space.getOwner();
        landSpaceOwner.setMoney(landSpaceOwner.getMoney()+TravellingExpense);

        InputOutput.outputStringLine("支付给" + landSpaceOwner.getName()
                + "过路费" + TravellingExpense + "元");
    }

    public boolean willPayForTravelling(Space space) {
        if(getGodState() == GodState.mascot){
            InputOutput.outputStringLine("福神附体，免过路费。");
            return false;
        }
        Player player = space.getOwner();
        if(player.getDisableState() != DisableState.normal){
            InputOutput.outputStringLine(player.getName() + player.getNameOfDisableState() + "，免过路费。");
            return false;
        }
        return true;
    }


    public void update(Space space) {
        money -= space.getValue();
        space.setLevel(space.getLevel()+1);
    }

    public Boolean hasEnoughPointsFor(Tool tool) {
        return point>=tool.getPoint();
    }

    public Boolean toolsNumberReachLimit() {
        return getToolNum()<=9;
    }

    public void buyTools(Tool tool) {

        point -= tool.getPoint();
        addTool(tool);
    }

    public Boolean pointsLessThanCheapestTool() {
        return point<30;
    }

    public void giftLandWithMoneySelected() {
        money += 2000;
    }

    public void giftLandWithPointSelected() {
        point += 200;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void showProperty() {
        InputOutput.outputStringLine("资金："+ getMoney() + "元");
        InputOutput.outputStringLine("点数："+ getPoint() + "点");
        InputOutput.outputString("地产：空地" + getLandNumWithLvl(0) + "处；");
        InputOutput.outputString("茅屋" + getLandNumWithLvl(1) + "处；");
        InputOutput.outputString("洋房" + getLandNumWithLvl(2) + "处；");
        InputOutput.outputStringLine("摩天楼" + getLandNumWithLvl(3) + "处；");
        InputOutput.outputString("道具：路障" + getToolNumWithType(Tool.Type.ROADBLOCK) + "个；");
        InputOutput.outputString("炸弹"+ getToolNumWithType(Tool.Type.bomb) + "个；");
        InputOutput.outputStringLine("机器娃娃" + getToolNumWithType(Tool.Type.ROBOT) + "个；");



    }

    public int getLandNumWithLvl(int level) {
        int count = 0;
        for (int i=0;i<getLandNum();i++){
            if(level == ((Space)lands.get(i)).getLevel()){
                count++;
            }
        }
        return count;
    }

    public int getToolNumWithType(Tool.Type type) {
        int count = 0;
        for (int i=0;i<getToolNum();i++){
            if(type == (tools.get(i)).getType()){
                count++;
            }
        }
        return count;
    }

    public boolean sellLand(Land land) {
        if(lands.remove(land)){
            ((Space)land).setOwner(null);
            setMoney(getMoney() + 2 * ((Space) land).getCurrentValue());
            return true;
        }
        return false;
    }

    public boolean sellLandByLoction(int loction) {
        Land land = getLand(loction);
        return land != null && sellLand(land);
    }

    private Land getLand(int loction) {
        for(Land land : lands){
            if(land.getLocation() == loction){
                return land;
            }
        }
        return null;
    }

    public boolean removeTool(Tool.Type type){
        Tool tool;
        for(int i=0;i<getToolNum();i++){
            tool = tools.get(i);
            if(tool.getType() == type){
                tools.remove(tool);
                return true;
            }
        }
        return false;
    }

    public boolean sellTool(Tool.Type type) {
        if(removeTool(type)){
            setPoint(getPoint() + Tool.createTool(type).getPoint());
            return true;
        }
        return false;
    }

    public void setDisableCount(int disableCount) {
        this.disableCount = disableCount;
    }

    public int getDisableCount() {
        return disableCount;
    }


    public int getGodCount() {
        return godCount;
    }

    public void setGodCount(int godCount) {
        this.godCount = godCount;
    }

    public void decreaseDisableCount(){
        if(disableCount > 0){
            disableCount--;
        }
    }

    public void decreaseGodCount(){
        if(godCount > 0){
            godCount--;
        }

    }

    public void beInHospital(int day,int locationHospital){
        InputOutput.outputStringLine("住院" + day + "天。");
        setLocation(locationHospital);
        setDisableState(DisableState.hospital);
        setDisableCount(day);
    }

    public void beInPrison(int day,int locationPrison){
        InputOutput.outputStringLine("拘留" + day + "天。");
        setLocation(locationPrison);
        setDisableState(DisableState.prison);
        setDisableCount(day);
    }

    public void beWithMascot() {
        InputOutput.outputStringLine("福神俯身" + DAY_WITH_MASCOT + "天。");
        setGodState(GodState.mascot);
        setGodCount(DAY_WITH_MASCOT );
    }

    public void passOver() {
        resetDisableAndGodState();
        decreaseDisableCount();
        decreaseGodCount();

    }

    private void resetDisableAndGodState() {
        if(getDisableState() != DisableState.normal && disableCount == 0){
            InputOutput.outputStringLine(getName() + "恢复自由身。");
            setDisableState(DisableState.normal);
        }
        if(getGodState() != GodState.none && godCount == 0){
            InputOutput.outputStringLine(getNameOfGodState() + "离开。");
            setGodState(GodState.none);
        }
    }
}
