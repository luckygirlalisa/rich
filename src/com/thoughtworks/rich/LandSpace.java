package com.thoughtworks.rich;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-2-23
 * Time: 下午4:03
 * To change this template use File | Settings | File Templates.
 */
public class LandSpace extends Land {
    public static final int HIGHEST_LEVEL = 3;

    private int value;
    private int level;
    private Player owner;

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }



    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCurrentValue() {
        return value*(1+level);
    }

    public Boolean isBelongToSomeBody() {
        return owner!=null;
    }

    public Boolean isBelongToHimself(Player owner) {
        return this.owner == owner;
    }

    public Boolean canBeAffordBy(Player player) {
        return value<=player.getMoney();
    }

    public int getTravellingExpense() {
        return (value/2)*(int)(Math.pow(2,level));
    }

    @Override
    public String getOwnerMark() {
        if(null != getOwner() ){
            return getOwner().getMark().toLowerCase();
        }
        return " ";
    }

    @Override
    public void action(Player player){
        if(!isBelongToSomeBody()){
            if(!canBeAffordBy(player)){
                InputOutput.outputString("资金余额不足，不能买地。");
                return;
            }
            if(!isPlayerWantToBuy()){
                return;
            }
            player.buyLand(this);
        }else if(isBelongToHimself(player)){
            if(isHighestLevel()){
                return;
            }
            if(!isPlayerWantToUpdate()){
                return;
            }
            player.update(this);
        } else {
            player.payForTravelling(this);
        }
    }

    public boolean isHighestLevel() {
        return getLevel() >= HIGHEST_LEVEL;
    }


    public boolean isPlayerWantToUpdate() {
        InputOutput.outputString("是否升级该处地产，" + getValue() +"元（Y/N）?");
        String string = InputOutput.inputYorNChoice();
        return string.equals("y");
    }

    public boolean isPlayerWantToBuy() {
        InputOutput.outputString("是否购买该处空地，" + getValue() +"元（Y/N）?");
        String string = InputOutput.inputYorNChoice();
        return string.equals("y");
    }

    @Override
    public String getMark() {
        return String.valueOf(level);
    }
}
