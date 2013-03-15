package com.thoughtworks.rich;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-2-23
 * Time: 下午4:05
 * To change this template use File | Settings | File Templates.
 */
public class LandTool extends Land {
    void showWelcome(){
        InputOutput.outputStringLine("欢迎光临道具屋， 请选择您所需要的道具：\n" +
                "道具        编号    价值（点数)\n" +
                "路障          1         50\n" +
                "机器娃娃      2         30\n" +
                "炸弹          3         50");
    }


    @Override
    public void action(Player player) {

        showWelcome();
        while (true){
            if(player.pointsLessThanCheapestTool()){
                InputOutput.outputStringLine("您的点数不足以购买道具，自动退出。");
                return;
            }
            InputOutput.outputString(">");
            String string = InputOutput.inputString();
            if(string.equals("f")){
                return;
            } else {
                buyToolsWithString(string,player);
            }
        }
    }

    public void buyToolsWithString(String string,Player player){
        Tool tool = null;
        if(string.equals("1")){
            tool = Tool.createTool(Tool.Type.roadblock);
        } else if (string.equals("2")){
            tool = Tool.createTool(Tool.Type.robot);
        } else if (string.equals("3")){
            tool = Tool.createTool(Tool.Type.bomb);
        }

        if(tool == null){
            return;
        }

        if(player.hasEnoughPointsFor(tool)){
            player.buyTools(tool);
            InputOutput.outputStringLine("购买" + tool.getName() + "成功！");
        } else {
            InputOutput.outputStringLine("您当前剩余的点数为"
                    + player.getPoint() + "，不足以购买"
                    + tool.getName() +"道具。");
        }
    }

}
