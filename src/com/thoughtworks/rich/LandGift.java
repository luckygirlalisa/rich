package com.thoughtworks.rich;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-2-23
 * Time: 下午4:05
 * To change this template use File | Settings | File Templates.
 */
public class LandGift extends Land {

    private static final int BONUSMONEY = 2000;
    private static final int BONUSPOINT = 200;

    public static int getBonusMoney() {
        return BONUSMONEY;
    }

    public static int getBonusPoint() {
        return BONUSPOINT;
    }

    @Override
    public void action(Player player) {
        showWelcome();
        try{
            int choice = Integer.parseInt(InputOutput.inputString());
            switch (choice){
                case 1:
                    addMoney(player);
                    break;
                case 2:
                    addPoint(player);
                    break;
                case 3:
                    addMascot(player);
                    break;
                default:
                    InputOutput.outputStringLine("输入错误，放弃机会,离开礼品屋。");
                    break;
            }
        } catch (Exception e){
            InputOutput.outputStringLine("输入错误，放弃机会,离开礼品屋。");
        }
    }

    private void addMascot(Player player) {
        player.beWithMascot();
    }

    private void addPoint(Player player) {
        player.setPoint(player.getPoint() + getBonusPoint());
    }

    private void addMoney(Player player) {
        player.setMoney(player.getMoney() + getBonusMoney());
    }

    void showWelcome(){
        InputOutput.outputStringLine(
                "欢迎光临礼品屋，请选择一件您 喜欢的礼品：”\n" +
                "通过输入礼品编号选择礼品：\n" +
                "礼品    编号\n" +
                "奖金      1\n" +
                "点数卡    2\n" +
                "福神      3\n");
    }
}
