package com.thoughtworks.rich.command;

import com.thoughtworks.rich.component.*;
import com.thoughtworks.rich.component.gameroute.Element;
import com.thoughtworks.rich.component.land.Land;
import com.thoughtworks.rich.component.land.Space;
import com.thoughtworks.rich.component.gameTool.Bomb;
import com.thoughtworks.rich.component.gameTool.Tool;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-2-24
 * Time: 下午3:10
 * To change this template use File | Settings | File Templates.
 */
public class Game {
    ArrayList<Player> players = new ArrayList<Player>();
    private Element element;
    private static Random random = new Random();
    private int initialMoney = 10000;
    private int initialPoint = 0;
    private static final String PLAYER_NAME[]={"钱夫人","阿土伯","孙小美","金贝贝"};
    private static final String PLAYER_MARK[]={"Q","A","S","J"};
    public static final int MAX_INITIAL_MONEY = 50000;
    public static final int MIN_INITIAL_MONEY = 1000;
    public static final int MAX_PLAYER_NUM = 4;
    public static final int MIN_PLAYER_NUM = 2;


    private int controlId = 0;
    private boolean playing;
    private int hospitalLocation;

    public void addPlayer(Player player) {
        player.setId(getPlayerNum());
        players.add(player);
        player.setElement(element);
    }

    public Player getPlayer(int playerID) {
        return players.get(playerID);
    }

    public int getPlayerNum() {
        return players.size();
    }

    public void setElement(Element element) {
        this.element = element;
        for (Player player : players) {
            player.setElement(element);
        }

        for(int i=0;i< element.getSize();i++){
            if(element.getLand(i).getType() == Land.Type.HOSPITAL){
                setHospitalLocation(i);
            }
        }
    }

    public Element getElement() {
        return element;
    }

    public int getHospitalLocation() {
        return hospitalLocation;
    }

    public void setHospitalLocation(int hospitalLocation) {
        this.hospitalLocation = hospitalLocation;
    }

    public static int roll() {
        return random.nextInt(5)+1;
    }

    public void setInitialMoney(int initialMoney) {
        this.initialMoney = initialMoney;
    }


    public int getInitialMoney() {
        return initialMoney;
    }

    public String getPlayerNameWithIndex(int selectedIndex) {
        return PLAYER_NAME[selectedIndex];
    }

    public String getPlayerMarkWithIndex(int selectedIndex) {
        return PLAYER_MARK[selectedIndex];
    }

    public int[] getSelectedIndexArray(String selectedNameIndex) {
        char nameCharIndex[] = selectedNameIndex.toCharArray();
        int [] nameIntIndex = new int[nameCharIndex.length];
        try{
            for(int i=0;i<nameCharIndex.length;i++){
                nameIntIndex[i]= Integer.parseInt(String.valueOf(nameCharIndex[i])) - 1;
            }
        } catch (Exception e){
            return null;
        }
        if(isPlayerSelectedIndexArrayOk(nameIntIndex)) {
            return nameIntIndex;
        }
        return null;
    }

    private boolean isPlayerSelectedIndexArrayOk(int[] nameIntIndex) {
        if(nameIntIndex.length <MIN_PLAYER_NUM || nameIntIndex.length >MAX_PLAYER_NUM){
            return false;
        }
        for(int i=0;i<nameIntIndex.length;i++){
            if(nameIntIndex[i] < 0 || nameIntIndex[i] >= PLAYER_NAME.length){
                return false;
            }
            for(int j=i+1;j<nameIntIndex.length;j++){
                if(nameIntIndex[i] == nameIntIndex[j]){
                    return false;
                }
            }
        }
        return true;
    }

    public void addPlayerWithIndex(int[] nameIndex) {
        for(int i=0;i<nameIndex.length;i++){
            Player player = new Player(i, getPlayerNameWithIndex(nameIndex[i]));
            player.setMark(getPlayerMarkWithIndex(nameIndex[i]));
            player.setMoney(initialMoney);
            player.setPoint(initialPoint);
            addPlayer(player);
        }
    }

    public Player getCurrentPlayer() {
        for (Player player : players) {
            if (player.getId() == controlId) {
                return player;
            }
        }
        return null;
    }

    public void nextPlayer() {
        controlId = (controlId + 1) % (players.size());
    }

    public void letPlayerMove(Player player) {
        int rollNum = roll();
        InputOutput.outputStringLine("掷点：" + rollNum);
        Land land = getLandWithToolOnFrontofPlay(player, rollNum);
        if(land == null){
            player.move(rollNum);
            return;
        }
        if(land.getTool().getType() == Tool.Type.ROADBLOCK){
            InputOutput.outputStringLine("遇到路障，停下。");
            int location = land.getLocation();
            land.clearTool();
            player.setLocation(location);
        } else if(land.getTool().getType() == Tool.Type.bomb){
            InputOutput.outputStringLine("遇到炸弹，被炸进医院。");
            land.clearTool();
            playerHitOnBomb(player);
        }
    }

    public void playerHitOnBomb(Player player) {
        player.beInHospital(Bomb.getHospitalDay(), getHospitalLocation());
    }

    public Land getLandWithToolOnFrontofPlay(Player player, int numMove) {
        int location = player.getLocation();
        for (int i=0;i<=numMove;i++){
            int locationMove = (location + i)% getElement().getSize();
            Land land = getElement().getLand(locationMove);
            if(land.getTool() != null){
                return land;
            }
        }
        return null;
    }

    public void letCurrentPlayerMove() {
        letPlayerMove(getCurrentPlayer());
    }

    public void showMap() {
//        String[] stringsMap = getTotalMapStringLine();
        String[] stringsMap = getTotalMapStringCircle();

        for (String aStringsMap : stringsMap) {
            InputOutput.outputStringLine(aStringsMap);
        }
    }

    public String[] getTotalMapStringCircle() {
        String[] stringsMapLine = getTotalMapStringLine();
        int widthString = stringsMapLine.length;
        int lengthString = stringsMapLine[0].length()-1;

        String[] stringsMapCircle = new String[(widthString)*2 + 6];
        int colCircle = lengthString*29/70;
        int rowCircle = (lengthString - colCircle*2)/2;

        StringBuffer stringBuffer;

        //第一个横向
        for(int i=0;i<widthString;i++ ){
            stringBuffer = new StringBuffer();
            for(int j = 0;j<widthString-1;j++){
                stringBuffer.append(" ");
                stringBuffer.append(" ");
            }
            stringBuffer.insert(widthString - 1, stringsMapLine[i].substring(0, colCircle));
            stringsMapCircle[i] = stringBuffer.toString();
        }

        //两个竖向
        for(int i=0;i<rowCircle;i++){
            stringBuffer = new StringBuffer();
            for (String aStringsMapLine : stringsMapLine) {
                stringBuffer.append(aStringsMapLine.charAt(lengthString - i - 1));
            }
            for(int j = 0;j<colCircle-2;j++){
                stringBuffer.append(" ");
            }
            for(int j=0;j<widthString;j++){
                stringBuffer.append(stringsMapLine[widthString-j-1].charAt(i+colCircle));
            }
            stringsMapCircle[widthString+i] = stringBuffer.toString();
        }

        //第二个横向
        for(int i=0;i<widthString;i++ ){
            stringBuffer = new StringBuffer();
            for(int j = 0;j<widthString-1;j++){
                stringBuffer.append(" ");
                stringBuffer.append(" ");
            }
            StringBuffer stringSub = new StringBuffer(
                    stringsMapLine[i].substring(colCircle + rowCircle,
                            2*colCircle + rowCircle));
            stringBuffer.insert(widthString-1,stringSub.reverse());
            stringsMapCircle[2*widthString+rowCircle-i-1] = stringBuffer.toString();
        }
        return stringsMapCircle;
    }

    public String[] getTotalMapStringLine() {
        String[] stringsPlayer = getPlayersAndToolsString();
        String[] stringsTotal = new String[stringsPlayer.length + 2];
        int i;
        for (i=0;i<stringsPlayer.length;i++){
            stringsTotal[i] = stringsPlayer[i];
        }
        stringsTotal[i++] = getLandOwnerString();
        stringsTotal[i] = getElement().toString();
        return stringsTotal;

    }

    public String[] getPlayersAndToolsString(){
        String[] strings = new String[getPlayerNum()];
        StringBuffer stringBuffer;
        Player player;
        for(int i=0;i<getPlayerNum();i++){
            stringBuffer = new StringBuffer();
            for(int j=0;j< getElement().getSize();j++){
                stringBuffer.append(" ");
            }
            player = getPlayer(i);
            stringBuffer.replace(player.getLocation(),player.getLocation(),player.getMark());
            strings[i] = stringBuffer.toString();
        }
        stringBuffer = new StringBuffer(strings[getPlayerNum()-1]);
        for (int i=0;i< getElement().getSize();i++){
            Tool tool = getElement().getLand(i).getTool();
            if(tool != null){
                stringBuffer.replace(i,i,tool.getMark());
            }
        }
        strings[getPlayerNum()-1] = stringBuffer.toString();
        return strings;
    }

    public String getLandOwnerString(){
        StringBuilder stringBuffer = new StringBuilder();
        for(int i=0;i< getElement().getSize();i++){

            stringBuffer.append(getElement().getLand(i).getOwnerMark());
        }
        return stringBuffer.toString();
    }

    public void getInitialMoneyInput() {
        InputOutput.outputString("请输入玩家初始资金（" + MIN_INITIAL_MONEY
                + "-" + MAX_INITIAL_MONEY + "）：");
        int initialMoney= InputOutput.inputInt();
        while (initialMoney < 1000 || initialMoney > 50000 ){
            InputOutput.outputString("初始资金范围是" + MIN_INITIAL_MONEY
                    + "-" + MAX_INITIAL_MONEY + "，请重新输入：");
            initialMoney= InputOutput.inputInt();
        }
        setInitialMoney(initialMoney);
    }

    public void addPlayerWithInput() {
        InputOutput.outputString("请选择2~4位不重复玩家，输入编号即可。" +
                "(1.钱夫人; 2.阿土伯; 3.孙小美; 4.金贝贝):");
        String string = InputOutput.inputString();
        int[] index = getSelectedIndexArray(string);
        while (index == null ){
            InputOutput.outputString("输入错误，请重新输入。");
            string = InputOutput.inputString();
            index = getSelectedIndexArray(string);
        }
        addPlayerWithIndex(index);
    }

    public void showCurrentPlayer() {
        String string = getCurrentPlayer().getName();
        InputOutput.outputString(string);
    }

    public void getCommandInputAndAct() {
        String string;
        while (true){
            string = InputOutput.inputCommand();
            if(string.equals("roll")){
                letCurrentPlayerMove();
                showMap();
                currentLandActionBy(getCurrentPlayer());
                break;
            }else if(string.equals("quit")){
                quitGame();
                break;
            }else if(string.equals("query")){
                getCurrentPlayer().showProperty();
            }else if(string.equals("sell")){
                int loction = InputOutput.inputInt();
                currentPlayerSellLand(loction);
            }else if(string.equals("selltool")){
                String stringTool = InputOutput.inputString();
                currentPlayerSellTool(stringTool);
            }else if(string.equals("help")){
                showHelpInfo();
            } else if(string.equals("block")){
                int offset = InputOutput.inputInt();
                playerUseRoadBlockAndBomb(getCurrentPlayer(), Tool.Type.ROADBLOCK, offset);
                showMap();
            } else if(string.equals("bomb")){
                int offset = InputOutput.inputInt();
                playerUseRoadBlockAndBomb(getCurrentPlayer(), Tool.Type.bomb, offset);
                showMap();
            } else if(string.equals("ROBOT")){
                playerUseRobot(getCurrentPlayer());
                showMap();
            }
        }
    }

    private void currentPlayerSellTool(String stringTool) {
        boolean sellOk = false;
        if(stringTool.equals("block")){
            sellOk = getCurrentPlayer().sellTool(Tool.Type.ROADBLOCK);
        }else if(stringTool.equals("bomb")){
            sellOk = getCurrentPlayer().sellTool(Tool.Type.bomb);
        }else if(stringTool.equals("ROBOT")){
            sellOk = getCurrentPlayer().sellTool(Tool.Type.ROBOT);
        }
        if(sellOk){
            InputOutput.outputStringLine("道具出售成功！");
        }else{
            InputOutput.outputStringLine("道具出售失败，请检查是否拥有该道具。");
        }
    }

    private void currentPlayerSellLand(int loction) {
        boolean sellOk = getCurrentPlayer().sellLandByLoction(loction);
        if(sellOk){
            InputOutput.outputStringLine("土地出售成功！");
            showMap();
        }else{
            InputOutput.outputStringLine("土地出售失败，请检查是否拥有该土地。");
        }
    }

    public boolean isCurrentPlayerDisableState() {
        Player player = getCurrentPlayer();
        Player.DisableState disableState = player.getDisableState();
        if(disableState == Player.DisableState.normal){
            return false;
        }
        InputOutput.outputStringLine(player.getNameOfDisableState()
                + ",还剩" + player.getDisableCount() + "天。");
        return true;
    }


    public void currentLandActionBy(Player player) {
        Land land = getElement().getLand(player.getLocation());
        land.action(player);
    }

    public void startPlay() {
        setPlaying(true);
        getInitialMoneyInput();
        addPlayerWithInput();
        while (isPlaying()){
            showMap();
            showCurrentPlayer();
            currentPlayerPassOver();
            if(canCurrentPlayerMove()){
               getCommandInputAndAct();
            }
            checkPlayerBreakUp();
            nextPlayer();
        }
    }

    public boolean canCurrentPlayerMove() {
        if(!isCurrentPlayerDisableState()){
            return true;
        }
        InputOutput.outputStringLine("轮空。");
        return false;
    }

    public void currentPlayerPassOver() {
        getCurrentPlayer().passOver();
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public void quitGame() {
        setPlaying(false);
    }

    public void showHelpInfo(){
        InputOutput.outputStringLine("命令一览表\n" +
                "roll        掷骰子命令，行走1~6步。步数由随即算法产生。   \n" +
                "block n     玩家拥有路障后，可将路障放置到离当前位置前后10步的距离，" +
                "任一玩家经过路障，都将被拦截。该道具一次有效。n 前后的相对距离，负数表示后方。\n" +
                "bomb n      可将路障放置到离当前位置前后10步的距离，任一玩家j 经过在该位置，" +
                "将被炸伤，送往医院，住院三天。n 前后的相对距离，负数表示后方。\n" +
                "robot       使用该道具，可清扫前方路面上10步以内的其它道具，如炸弹、路障。\n" +
                "sell x      出售自己的房产，x 地图上的绝对位置，即地产的编号。\n" +
                "sellTool x  出售道具，x 道具编号\n" +
                "query       显示自家资产信息   \n" +
                "help        查看命令帮助   \n" +
                "quit        强制退出\n");
    }

    public void playerUseRobot(Player player) {
        if(player.removeTool(Tool.Type.ROBOT)){
            clearToolsOnFrontOfPlayer(player,Tool.createTool(Tool.Type.ROBOT).getRange());
            InputOutput.outputStringLine("机器娃娃。");
        } else {
            InputOutput.outputStringLine("玩家道具中没有路障。");
        }
    }

    public void playerUseRoadBlockAndBomb(Player player, Tool.Type type, int offset) {
        Tool tool = Tool.createTool(type);
        int location = ((player.getLocation() + offset) + getElement().getSize())% getElement().getSize();
        Land land = getElement().getLand(location);
        int range = tool.getRange();
        if(offset < -range || offset > range){
            InputOutput.outputStringLine(tool.getName() + "使用相对距离应该在[-"
                    + range + "," + range + "]。");
            return;
        }
        if(offset == 0){
            InputOutput.outputStringLine(tool.getName() + "不能在原地使用。");
            return;
        }
        if(!canLandSetTool(location)){
            return;
        }
        if(player.removeTool(type)){
            land.setTool(tool);
            InputOutput.outputStringLine("使用" + tool.getName() + "。");
        } else {
            InputOutput.outputStringLine("玩家道具中没有" + tool.getName() + "。");
        }

    }

    public boolean canLandSetTool(int location) {
        Land land = getElement().getLand(location);
        if(land.getTool() != null){
            InputOutput.outputStringLine("该地点已有道具，不能再放置道具。");
            return false;
        }
        if(isTherePlayerOnLand(location)) {
            InputOutput.outputStringLine("该地点有玩家，不能放置道具。");
            return false;
        }
        return true;
    }

    public boolean isTherePlayerOnLand(int location) {
        for (int i=0;i<getPlayerNum();i++){
            if(players.get(i).getLocation() == location){
                return true;
            }
        }
        return false;
    }

    public void clearToolsOnFrontOfPlayer(Player player, int offset) {
        Land land = getLandWithToolOnFrontofPlay(player, offset);
        while (land != null) {
            land.clearTool();
            land = getLandWithToolOnFrontofPlay(player, offset);
        }
    }


    public void checkPlayerBreakUp() {
        for(Player player : players){
            if(player.getMoney() <= 0){
                InputOutput.outputStringLine(player.getName() +  "资金耗尽，已破产！");
                removePlayer(player);
                checkIfGameOver();
            }
        }
    }

    private void removePlayer(Player player) {
        players.remove(player);
        for(Land land : player.lands){
            ((Space)land).setOwner(null);
            ((Space)land).setLevel(0);
        }

        if(player.getId() <= controlId){
            controlId--;
        }

        Player playerOther;
        for(int i=0;i<getPlayerNum();i++){
            playerOther = getPlayer(i);
            playerOther.setId(i);
        }

    }

    public void checkIfGameOver() {
        if(getPlayerNum() == 1){
            InputOutput.outputStringLine("只剩一个玩家，"
                    + getPlayer(0).getName() + "是胜利者！");
            quitGame();
        }
    }
}
