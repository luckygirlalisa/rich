package com.thoughtworks.rich;

import com.thoughtworks.rich.command.Game;
import com.thoughtworks.rich.component.*;
import com.thoughtworks.rich.component.gameroute.Creater;
import com.thoughtworks.rich.component.gameroute.Element;
import com.thoughtworks.rich.component.land.*;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-2-23
 * Time: 上午9:38
 * To change this template use File | Settings | File Templates.
 */
public class RichTest {

    @Test
    public void created_map_with_lands(){
        Element element = new Element();
        element.addLand(new Land());
        element.addLand(new Land());
        element.addLand(new Land());
        assertThat(element.getSize(), is(3));
    }

    @Test
    public void can_get_land_in_map(){
        Element element = new Element();
        element.addLand(new Land());

        Land land = new Land();
        element.addLand(land);

        element.addLand(new Land());

        assertThat(element.getLand(1), is(land));
    }

    @Test
    public void land_has_name(){
        Land land = new Land("test_land");
        assertThat(land.getName(),is("test_land"));
    }

    @Test
    public void land_has_type(){
        Land land = new Land(Land.Type.SPACE);
        assertThat(land.getType(), is(Land.Type.SPACE));
    }

    @Test
    public void land_has_mark(){
        Land land = new Land();
        land.setMark("S");
        assertThat(land.getMark(), is("S"));
    }

    @Test
    public void create_different_land(){
        Land land;
        land = Land.createLand(Land.Type.START);
        assertThat(land.getName(),is("起点"));
        land = Land.createLand(Land.Type.SPACE);
        assertThat(land.getName(),is("空地"));
        land = Land.createLand(Land.Type.TOOL);
        assertThat(land.getName(),is("道具屋"));
        land = Land.createLand(Land.Type.GIFT);
        assertThat(land.getName(),is("礼品屋"));
        land = Land.createLand(Land.Type.MAGIC);
        assertThat(land.getName(),is("魔法屋"));
        land = Land.createLand(Land.Type.HOSPITAL);
        assertThat(land.getName(),is("医院"));
        land = Land.createLand(Land.Type.PRISON);
        assertThat(land.getName(),is("监狱"));
        land = Land.createLand(Land.Type.MINE);
        assertThat(land.getName(),is("矿地"));
    }

    @Test
    public void different_land_has_different_title(){
        Land land;
        land = Land.createLand(Land.Type.START);
        assertThat(land.getMark(),is("S"));
        land = Land.createLand(Land.Type.SPACE);
        assertThat(land.getMark(),is("0"));
        land = Land.createLand(Land.Type.TOOL);
        assertThat(land.getMark(),is("T"));
        land = Land.createLand(Land.Type.GIFT);
        assertThat(land.getMark(),is("G"));
        land = Land.createLand(Land.Type.MAGIC);
        assertThat(land.getMark(),is("M"));
        land = Land.createLand(Land.Type.HOSPITAL);
        assertThat(land.getMark(),is("H"));
        land = Land.createLand(Land.Type.PRISON);
        assertThat(land.getMark(),is("P"));
        land = Land.createLand(Land.Type.MINE);
        assertThat(land.getMark(),is("$"));
    }


    @Test
    public void map_to_string(){
        Element element = new Element();
        element.addLand(Land.createLand(Land.Type.START));
        element.addLand(Land.createLand(Land.Type.SPACE));
        element.addLand(Land.createLand(Land.Type.SPACE));
        element.addLand(Land.createLand(Land.Type.TOOL));
        element.addLand(Land.createLand(Land.Type.MAGIC));

        assertThat(element.toString(), is("S00TM"));
    }

    @Test
    public void land_id(){
        Element element = new Element();
        element.addLand(Land.createLand(Land.Type.START));
        element.addLand(Land.createLand(Land.Type.SPACE));
        element.addLand(Land.createLand(Land.Type.SPACE));
        Land land = Land.createLand(Land.Type.SPACE);
        element.addLand(land);

        assertThat(land.getLocation(), is(3));
    }

    @Test
    public void space_has_value(){
        Space land= (Space) Land.createLand(Land.Type.SPACE);
        land.setValue(1000);
        assertThat(land.getValue(), is(1000));
    }

    @Test
    public void space_has_level(){
        Space land= (Space) Land.createLand(Land.Type.SPACE);
        land.setLevel(1);
        assertThat(land.getLevel(), is(1));
    }

    @Test
    public void different_current_value_with_current_level(){
        Space land= (Space) Land.createLand(Land.Type.SPACE);
        land.setValue(1000);
        land.setLevel(1);
        assertThat(land.getCurrentValue(), is(2000));
    }

    @Test
    public void mine_has_point(){
        Mine land= (Mine) Land.createLand(Land.Type.MINE);
        land.setPoint(100);
        assertThat(land.getPoint(), is(100));
    }

    @Test
    public void player_has_id_and_name(){
        Player player = new Player(1,"阿土伯");
        assertThat(player.getId(), is(1));
        assertThat(player.getName(),is("阿土伯"));
    }

    @Test
    public void player_has_property(){
        Player player = new Player(1,"阿土伯");
        player.setMoney(1000);
        player.setPoint(250);
        for(int i=0;i<10;i++){
            player.addLand(new Land());
        }
        for(int i=0;i<5;i++){
            player.addTool(new com.thoughtworks.rich.component.gameTool.Tool());
        }

        assertThat(player.getMoney(),is(1000));
        assertThat(player.getPoint(),is(250));
        assertThat(player.getLandNum(),is(10));
        assertThat(player.getToolNum(),is(5));
    }

    @Test
    public void player_has_location(){
        Player player = new Player(1,"阿土伯");
        player.setLocation(42);
        assertThat(player.getLocation(),is(42));
    }

    @Test
    public void player_has_disable_State(){
        Player player = new Player(1,"阿土伯");
        player.setDisableState(Player.DisableState.hospital);
        assertThat(player.getDisableState(),is(Player.DisableState.hospital));
        assertThat(player.getNameOfDisableState(),is("在住院"));
    }

    @Test
    public void player_has_god_State(){
        Player player = new Player(1,"阿土伯");
        player.setGodState(Player.GodState.mascot);
        assertThat(player.getGodState(),is(Player.GodState.mascot));
        assertThat(player.getNameOfGodState(),is("福神"));
    }

    @Test
    public void different_tools(){
        com.thoughtworks.rich.component.gameTool.Tool tool = new com.thoughtworks.rich.component.gameTool.Tool();
        tool.setType(com.thoughtworks.rich.component.gameTool.Tool.Type.bomb);
        assertThat(tool.getType(),is(com.thoughtworks.rich.component.gameTool.Tool.Type.bomb));
        tool.setType(com.thoughtworks.rich.component.gameTool.Tool.Type.ROADBLOCK);
        assertThat(tool.getType(),is(com.thoughtworks.rich.component.gameTool.Tool.Type.ROADBLOCK));
        tool.setType(com.thoughtworks.rich.component.gameTool.Tool.Type.ROBOT);
        assertThat(tool.getType(),is(com.thoughtworks.rich.component.gameTool.Tool.Type.ROBOT));
    }

    @Test
    public void land_with_tools(){
        Land land = Land.createLand(Land.Type.SPACE);
        com.thoughtworks.rich.component.gameTool.Tool tool = new com.thoughtworks.rich.component.gameTool.Tool();
        land.setTool(tool);
        assertThat(land.getTool(),is(tool));
    }

    @Test
    public void the_number_of_players_in_game_can_be_get(){
        Game game = new Game();
        Player player1 = new Player(0,"小王");
        game.addPlayer(player1);
        Player player2 = new Player(1,"小李");
        game.addPlayer(player2);
        assertThat(game.getPlayerNum(),is(2));
    }

    @Test
    public void the_expected_player_in_game_can_be_get(){
        Game game = new Game();
        Player player1 = new Player();
        game.addPlayer(player1);
        Player player2 = new Player();
        game.addPlayer(player2);
        assertThat(game.getPlayer(1),is(player2));
    }

    @Test
    public void the_map_in_the_game_can_be_get(){
        Game game = new Game();
        Element element = new Element();
        game.setElement(element);
        assertThat(game.getElement(),is(element));
    }

    @Test
    public void get_the_times_that_player_can_walk(){
        for(int i=0;i<10;i++){
            int roll = Game.roll();
            assertTrue(1<=roll&&roll<=6);
        }
    }

    @Test
    public void get_the_location_of_player_after_walk(){
        Player player= new Player();
        player.setLocation(0);
        player.move(2);
        assertThat(player.getLocation(),is(2));
    }

    @Test
    public void get_the_correct_location_in_annular_map(){
        Game game = new Game();
        Element element = new Element();
        for(int i=0;i<10;i++){
            element.addLand(new Land());
        }
        game.setElement(element);
        Player player = new Player();
        game.addPlayer(player);
        player.setLocation(8);
        player.move(2);
        assertThat(player.getLocation(),is(0));
    }

    @Test
    public void if_the_current_space_land_belong_to_somebody(){
        Space space = new Space();
        assertThat(space.isBelongToSomeBody(),is(false));
        Player player = new Player();
        space.setOwner(player);
        assertThat(space.isBelongToSomeBody(),is(true));
    }

    @Test
    public void if_the_current_land_belong_to_himself(){
        Space space = new Space();
        Player player = new Player();
        space.setOwner(player);
        assertThat(space.isBelongToHimself(player),is(true));
        Player player1 = new Player();
        space.setOwner(player1);
        assertThat(space.isBelongToHimself(player),is(false));
    }

    @Test
    public void if_the_current_player_can_afford_the_current_space_land(){
        Space space = new Space();
        Player player = new Player();
        space.setValue(100);
        player.setMoney(200);
        assertThat(space.canBeAffordBy(player),is(true));
        player.setMoney(50);
        assertThat(space.canBeAffordBy(player),is(false));
    }

    @Test
    public void the_player_can_buy_the_landSpace_correctly(){
        Space space = new Space();
        space.setValue(100);
        Player player = new Player();
        player.setMoney(200);
        player.buyLand(space);
        assertThat(player.getMoney(), is(100));
    }

    @Test
    public void calculate_the_travelling_expense_correctly(){
        Space space = new Space();
        space.setValue(100);
        space.setLevel(1);
        assertThat(space.getTravellingExpense(),is(100));
        space.setLevel(3);
        assertThat(space.getTravellingExpense(),is(400));
    }

    @Test
    public void player_can_pay_for_travelling_expense_correctly(){
        Space space = new Space();
        Player player = new Player();
        player.setMoney(0);
        space.setOwner(player);
        space.setValue(100);
        space.setLevel(1);
        Player player1 = new Player();
        player1.setMoney(500);
        player1.payForTravelling(space);
        assertThat(player1.getMoney(), is(400));
        assertThat(player.getMoney(),is(100));
        space.setLevel(3);
        player1.setMoney(500);
        player.setMoney(0);
        player1.payForTravelling(space);
        assertThat(player1.getMoney(),is(100));
        assertThat(player.getMoney(),is(400));
    }

    @Test
    public void the_landSpace_can_be_upgrade_correctly(){
        Space space = new Space();
        Player player = new Player();
        space.setLevel(1);
        space.setValue(100);
        player.setMoney(500);
        player.update(space);
        assertThat(space.getLevel(),is(2));
        assertThat(player.getMoney(),is(400));

    }

    @Test
    public void the_points_of_tools_are_correct(){
        com.thoughtworks.rich.component.gameTool.Tool tool = com.thoughtworks.rich.component.gameTool.Tool.createTool(com.thoughtworks.rich.component.gameTool.Tool.Type.ROADBLOCK);
        assertThat(tool.getPoint(),is(50));
        tool = com.thoughtworks.rich.component.gameTool.Tool.createTool(com.thoughtworks.rich.component.gameTool.Tool.Type.ROBOT);
        assertThat(tool.getPoint(),is(30));
        tool = com.thoughtworks.rich.component.gameTool.Tool.createTool(com.thoughtworks.rich.component.gameTool.Tool.Type.bomb);
        assertThat(tool.getPoint(),is(50));
    }

    @Test
    public void the_points_of_current_player_is_enough_for_tools_or_not(){
        Player player =new Player();
        player.setPoint(40);
        com.thoughtworks.rich.component.gameTool.Tool roadblockTool = com.thoughtworks.rich.component.gameTool.Tool.createTool(com.thoughtworks.rich.component.gameTool.Tool.Type.ROADBLOCK);
        assertThat(player.hasEnoughPointsFor(roadblockTool),is(false));
        com.thoughtworks.rich.component.gameTool.Tool robotTool = com.thoughtworks.rich.component.gameTool.Tool.createTool(com.thoughtworks.rich.component.gameTool.Tool.Type.ROBOT);
        assertThat(player.hasEnoughPointsFor(robotTool),is(true));
    }

    @Test
    public void if_the_number_of_player_tools_reach_limit(){
        Player player = new Player();
        for(int i=0;i<9;i++){
            com.thoughtworks.rich.component.gameTool.Tool tool = new com.thoughtworks.rich.component.gameTool.Tool();
            player.addTool(tool);
        }
        assertThat(player.toolsNumberReachLimit(),is(true));
        player = new Player();
        for(int i=0;i<10;i++){
            com.thoughtworks.rich.component.gameTool.Tool tool = new com.thoughtworks.rich.component.gameTool.Tool();
            player.addTool(tool);
        }
        assertThat(player.toolsNumberReachLimit(),is(false));
    }

    @Test
    public void player_can_buy_tools_correctly(){
        Player player = new Player();
        player.setPoint(200);
        for(int i=0;i<2;i++){
            com.thoughtworks.rich.component.gameTool.Tool tool = new com.thoughtworks.rich.component.gameTool.Tool();
            player.addTool(tool);
        }
        com.thoughtworks.rich.component.gameTool.Tool roadblockTool = com.thoughtworks.rich.component.gameTool.Tool.createTool(com.thoughtworks.rich.component.gameTool.Tool.Type.ROADBLOCK);
        player.buyTools(roadblockTool);
        assertThat(player.getPoint(),is(150));
        assertThat(player.getToolNum(),is(3));
    }

    @Test
    public void if_player_points_less_than_the_cheapest_tool(){
        Player player = new Player();
        player.setPoint(10);
        assertThat(player.pointsLessThanCheapestTool(),is(true));
        player.setPoint(100);
        assertThat(player.pointsLessThanCheapestTool(),is(false));
    }

    @Test
    public void the_gift_money_or_point_is_correct(){
        assertThat(Gift.getBonusMoney(),is(2000));
        assertThat(Gift.getBonusPoint(),is(200));
    }
    @Test
    public void player_money_increases_in_giftLand(){
        Player player = new Player();
        player.setMoney(500);
        player.setPoint(50);
        player.giftLandWithMoneySelected();
        assertThat(player.getMoney(),is(2500));
        player.giftLandWithPointSelected();
        assertThat(player.getPoint(),is(250));
    }

    @Test
    public void the_initial_money_can_be_selected_correctly(){
        Game game = new Game();
        game.setInitialMoney(20000);
        assertThat(game.getInitialMoney(),is(20000));
    }

    @Test
    public void the_player_name_is_correct_with_corresponding_number(){
        Game game = new Game();
        assertThat(game.getPlayerNameWithIndex(0),is("钱夫人"));
        assertThat(game.getPlayerNameWithIndex(1),is("阿土伯"));
        assertThat(game.getPlayerNameWithIndex(2),is("孙小美"));
        assertThat(game.getPlayerNameWithIndex(3),is("金贝贝"));
    }

    @Test
    public void get_input_index_array_for_name(){
        Game game = new Game();
        int nameIndex[] = game.getSelectedIndexArray("12");
        assertThat(nameIndex[0],is(0));
        assertThat(nameIndex[1],is(1));
    }

    @Test
    public void add_player_with_input_number(){
        Game game =new Game();
        int [] nameIndex = {0,1};
        game.addPlayerWithIndex(nameIndex);
        assertThat((game.getPlayer(0)).getName(), is("钱夫人"));
    }

    @Test
    public void get_the_current_player(){
        Game game = new Game();
        int [] nameIndex = {3,1};
        game.addPlayerWithIndex(nameIndex);
        assertThat((game.getCurrentPlayer()).getName(),is("金贝贝"));
    }

    @Test
    public void get_next_player(){
        Game game = new Game();
        int [] nameIndex = {3,1,2};
        game.addPlayerWithIndex(nameIndex);
        game.nextPlayer();
        assertThat((game.getCurrentPlayer()).getName(), is("阿土伯"));
        game.nextPlayer();
        game.nextPlayer();
        assertThat((game.getCurrentPlayer()).getName(),is("金贝贝"));
    }

    @Test
    public void player_can_move_correctly_after_roll(){
        Game game = new Game();
        Player player = new Player();
        Element element = new Element();
        for(int i=0; i<10; i++){
            Land land = new Land();
            element.addLand(land);
        }
        game.addPlayer(player);
        game.setElement(element);

        player.setLocation(1);
        game.letPlayerMove(player);
        assertThat((player.getLocation() > 1) && (player.getLocation() <= 7), is(true));

        player.setLocation(8);
        game.letPlayerMove(player);
        assertThat(((player.getLocation() >= 0) && (player.getLocation() <= 4))
                || (player.getLocation() == 9), is(true));
    }

    @Test
    public void player_can_action_correctly_on_mine_land(){
        Land land = Land.createLand(Land.Type.MINE);
        ((Mine)land).setPoint(50);
        Player player = new Player();
        player.setPoint(100);
        land.action(player);
        assertThat(player.getPoint(),is(150));
    }

    @Test
    public void input_and_output_are_correct(){
//        String string = com.thoughtworks.Rich.InputOutput.inputString();
//        int com.thoughtworks.Rich.InputOutput.inputInt();
//        com.thoughtworks.Rich.InputOutput.outputString(string);
//        com.thoughtworks.Rich.InputOutput.outputStringLine(string);

    }

    @Test
    public void add_complete_map(){
        Element element = Creater.getCompleteMap();
        Land land;
        land = element.getLand(0);
        assertThat(land.getType(),is(Land.Type.START));
        for(int i=1;i<=13;i++){
            land = element.getLand(i);
            assertThat(land.getType(),is(Land.Type.SPACE));
            assertThat(((Space)land).getValue(),is(200));
        }
        land = element.getLand(14);
        assertThat(land.getType(),is(Land.Type.HOSPITAL));
        for(int i=15;i<=27;i++){
            land = element.getLand(i);
            assertThat(land.getType(),is(Land.Type.SPACE));
            assertThat(((Space)land).getValue(),is(200));
        }
        land = element.getLand(28);
        assertThat(land.getType(),is(Land.Type.TOOL));
        for(int i=29;i<=34;i++){
            land = element.getLand(i);
            assertThat(land.getType(),is(Land.Type.SPACE));
            assertThat(((Space)land).getValue(),is(500));
        }
        land = element.getLand(35);
        assertThat(land.getType(),is(Land.Type.GIFT));
        for(int i=36;i<=48;i++){
            land = element.getLand(i);
            assertThat(land.getType(),is(Land.Type.SPACE));
            assertThat(((Space)land).getValue(),is(300));
        }
        land = element.getLand(49);
        assertThat(land.getType(),is(Land.Type.PRISON));
        for(int i=50;i<=62;i++){
            land = element.getLand(i);
            assertThat(land.getType(),is(Land.Type.SPACE));
            assertThat(((Space)land).getValue(),is(300));
        }
        land = element.getLand(63);
        assertThat(land.getType(),is(Land.Type.MAGIC));
        for(int i=64;i<=69;i++){
            land = element.getLand(i);
            assertThat(land.getType(),is(Land.Type.MINE));
        }
        assertThat(((Mine) element.getLand(64)).getPoint(),is(60));
        assertThat(((Mine) element.getLand(65)).getPoint(),is(80));
        assertThat(((Mine) element.getLand(66)).getPoint(),is(40));
        assertThat(((Mine) element.getLand(67)).getPoint(),is(100));
        assertThat(((Mine) element.getLand(68)).getPoint(),is(80));
        assertThat(((Mine) element.getLand(69)).getPoint(),is(20));
    }

    @Test
    public void player_mark_can_be_get(){
        Game game = new Game();
        int[] index = {3,2};
        game.addPlayerWithIndex(index);
        assertThat(game.getPlayer(0).getMark(), is("J"));
    }
    @Test
    public void show_map(){
        Game game = new Game();
        int[] index = {3,2};
        game.addPlayerWithIndex(index);
        game.getCurrentPlayer().move(20);
        game.setElement(Creater.getCompleteMap());
        game.showMap();
    }

    @Test
    public void get_initial_money_input(){
        Game game = new Game();
        //game.getInitialMoneyInput();
    }

    @Test
    public void add_player_with_input(){
        Game game = new Game();
//        game.addPlayerWithInput();
    }

    @Test
    public void show_current_player_command(){
        Game game = new Game();
        int[] index = {3,2};
        game.addPlayerWithIndex(index);
        game.showCurrentPlayer();
    }

    @Test
    public void get_command_from_input_and_let_player_act(){
        Game game = new Game();
        int[] index = {3,2};
        game.addPlayerWithIndex(index);
//        game.getCommandInputAndAct();
    }

    @Test
    public void let_players_act_in_turn(){
        Game game = new Game();
        int[] index = {3,2,0};
        game.addPlayerWithIndex(index);
        game.setElement(Creater.getCompleteMap());

//        game.startPlay();
    }

    @Test
    public void show_player_property(){
        Player player = new Player();
        player.setMoney(2100);
        player.setPoint(530);

        Space space;
        for (int i=0;i<3;i++){
            space = new Space();
            space.setLevel(0);
            player.addLand(space);
        }

        for (int i=0;i<5;i++){
            space = new Space();
            space.setLevel(1);
            player.addLand(space);
        }
        for (int i=0;i<6;i++){
            space = new Space();
            space.setLevel(2);
            player.addLand(space);
        }
        for (int i=0;i<2;i++){
            space = new Space();
            space.setLevel(3);
            player.addLand(space);
        }

        com.thoughtworks.rich.component.gameTool.Tool tool;
        for(int i=0;i<4;i++){
            tool= com.thoughtworks.rich.component.gameTool.Tool.createTool(com.thoughtworks.rich.component.gameTool.Tool.Type.ROADBLOCK);
            player.addTool(tool);
        }
        for(int i=0;i<2;i++){
            tool= com.thoughtworks.rich.component.gameTool.Tool.createTool(com.thoughtworks.rich.component.gameTool.Tool.Type.ROBOT);
            player.addTool(tool);
        }
        for(int i=0;i<3;i++){
            tool= com.thoughtworks.rich.component.gameTool.Tool.createTool(com.thoughtworks.rich.component.gameTool.Tool.Type.bomb);
            player.addTool(tool);
        }

        player.showProperty();
    }

    @Test
    public void exit_game(){
        Game game = new Game();
        game.quitGame();
        assertThat(game.isPlaying(), is(false));
    }

    @Test
    public void exit_game_with_input(){
        Game game = new Game();
        int[] index = {3,2};
        game.addPlayerWithIndex(index);
//       game.getCommandInputAndAct();
//       assertThat(game.isPlaying(),is(false));
    }

    @Test
    public void player_sell_land_correctly(){
        Space space = new Space();
        space.setValue(500);
        space.setLevel(2);

        Player player = new Player();
        player.addLand(space);
        player.setMoney(1000);

        player.sellLand(space);
        assertThat(player.getMoney(),is(4000));
        assertThat(player.getLandNum(),is(0));

        space = new Space();
        space.setValue(200);
        space.setLevel(3);
        player.sellLand(space);
        assertThat(player.getMoney(),is(4000));
    }

    @Test
    public void sell_land_with_input(){
        Game game = new Game();
        int[] index = {3,2};
        game.addPlayerWithIndex(index);
        game.setElement(Creater.getCompleteMap());
        game.getPlayer(0).buyLand((Space) game.getElement().getLand(4));
//        game.getCommandInputAndAct();
//        assertThat(game.getPlayer(0).getMoney(),is( ));
//        assertThat(game.getPlayer(0).getLandNum(),is(0));
    }

    @Test
    public void player_sell_tool_correctly(){
        com.thoughtworks.rich.component.gameTool.Tool tool = com.thoughtworks.rich.component.gameTool.Tool.createTool(com.thoughtworks.rich.component.gameTool.Tool.Type.bomb);

        Player player = new Player();
        player.addTool(tool);
        player.setPoint(100);

        player.sellTool(com.thoughtworks.rich.component.gameTool.Tool.Type.bomb);
        assertThat(player.getPoint(),is(150));
        assertThat(player.getToolNum(),is(0));

        player.sellTool(com.thoughtworks.rich.component.gameTool.Tool.Type.ROADBLOCK);
        assertThat(player.getPoint(),is(150));
    }

    @Test
    public void sell_tool_with_input(){
        Game game = new Game();
        int[] index = {3,2};
        game.addPlayerWithIndex(index);
        game.setElement(Creater.getCompleteMap());
        game.getPlayer(0).addTool(com.thoughtworks.rich.component.gameTool.Tool.createTool(com.thoughtworks.rich.component.gameTool.Tool.Type.bomb));
//        game.getCommandInputAndAct();
//        assertThat(game.getPlayer(0).getPoint(),is(50));
//        assertThat(game.getPlayer(0).getLandNum(),is(0));
    }

    @Test
    public void show_help_info(){
//        game.getCommandInputAndAct();
    }

    @Test
    public void player_can_action_correctly_on_space_land(){
        Land land;
        Player player;
        land = Land.createLand(Land.Type.SPACE);
        ((Space)land).setValue(200);
        player = new Player();
        player.setMoney(1000);
        //land.action(player);  //buy SPACE land

        land = Land.createLand(Land.Type.SPACE);
        ((Space)land).setValue(200);
        player = new Player();
        player.setMoney(1000);
        player.addLand(land);
        //land.action(player);      //sell land

        land = Land.createLand(Land.Type.SPACE);
        ((Space)land).setValue(200);
        player = new Player();
        player.setMoney(1000);
        player.addLand(land);
        Player playerPass = new Player();
        //land.action(playerPass);    //pay for travelling
    }

    @Test
    public void can_get_tool_name(){
        com.thoughtworks.rich.component.gameTool.Tool tool = com.thoughtworks.rich.component.gameTool.Tool.createTool(com.thoughtworks.rich.component.gameTool.Tool.Type.bomb);
        assertThat(tool.getName(),is("炸弹"));
    }

    @Test
    public void player_can_action_correctly_on_tool_land(){
        Tool landTool = new Tool();
        Player player = new Player();
        player.setPoint(200);
        //landTool.action(player);
    }

    @Test
    public void player_can_use_road_block(){
        Game game = new Game();
        game.setElement(Creater.getCompleteMap());
        Player player = new Player();
        player.addTool(com.thoughtworks.rich.component.gameTool.Tool.createTool(com.thoughtworks.rich.component.gameTool.Tool.Type.ROADBLOCK));
        game.addPlayer(player);
        player.setLocation(10);
        game.playerUseRoadBlockAndBomb(player, com.thoughtworks.rich.component.gameTool.Tool.Type.ROADBLOCK, 10);
        assertThat(game.getElement().getLand(20).getTool().getType(),is(com.thoughtworks.rich.component.gameTool.Tool.Type.ROADBLOCK));
    }

    @Test
    public void use_road_block_with_input(){
        Game game = new Game();
        int[] index = {3,2};
        game.addPlayerWithIndex(index);
        game.setElement(Creater.getCompleteMap());
        game.getPlayer(0).addTool(com.thoughtworks.rich.component.gameTool.Tool.createTool(com.thoughtworks.rich.component.gameTool.Tool.Type.ROADBLOCK));
//        game.getCommandInputAndAct();
    }

    @Test
    public void find_tool_on_road_in_front_of_player(){
        Game game = new Game();
        Player player = new Player();
        game.addPlayer(player);
        game.setElement(Creater.getCompleteMap());
        com.thoughtworks.rich.component.gameTool.Tool tool = com.thoughtworks.rich.component.gameTool.Tool.createTool(com.thoughtworks.rich.component.gameTool.Tool.Type.ROADBLOCK);
        game.getElement().getLand(7).setTool(tool);
        player.setLocation(2);
        assertThat(game.getLandWithToolOnFrontofPlay(player, 10),is(game.getElement().getLand(7)));
        assertTrue(game.getLandWithToolOnFrontofPlay(player, 4) == null);
    }

    @Test
    public void player_move_and_hit_by_road_block(){
        Game game = new Game();
        Player player = new Player();
        game.addPlayer(player);
        game.setElement(Creater.getCompleteMap());
        game.getElement().getLand(3).setTool(com.thoughtworks.rich.component.gameTool.Tool.createTool(com.thoughtworks.rich.component.gameTool.Tool.Type.ROADBLOCK));
        player.setLocation(2);
        game.letPlayerMove(player);
        assertThat(player.getLocation(),is(3));
    }

    @Test
    public void clear_tools_in_front_of_player(){
        Game game = new Game();
        Player player = new Player();
        game.addPlayer(player);
        game.setElement(Creater.getCompleteMap());
        game.getElement().getLand(4).setTool(com.thoughtworks.rich.component.gameTool.Tool.createTool(com.thoughtworks.rich.component.gameTool.Tool.Type.ROADBLOCK));
        game.getElement().getLand(7).setTool(com.thoughtworks.rich.component.gameTool.Tool.createTool(com.thoughtworks.rich.component.gameTool.Tool.Type.bomb));
        game.getElement().getLand(13).setTool(com.thoughtworks.rich.component.gameTool.Tool.createTool(com.thoughtworks.rich.component.gameTool.Tool.Type.ROADBLOCK));
        player.setLocation(2);
        game.clearToolsOnFrontOfPlayer(player,10);

        assertTrue(game.getElement().getLand(4).getTool() == null);
        assertTrue(game.getElement().getLand(7).getTool() == null);
        assertTrue(game.getElement().getLand(13).getTool() != null);
    }

    @Test
    public void use_robot_with_input(){
        Game game = new Game();
        int[] index = {3,2};
        game.addPlayerWithIndex(index);
        game.setElement(Creater.getCompleteMap());
        game.getPlayer(0).addTool(com.thoughtworks.rich.component.gameTool.Tool.createTool(com.thoughtworks.rich.component.gameTool.Tool.Type.ROADBLOCK));
        game.getElement().getLand(4).setTool(com.thoughtworks.rich.component.gameTool.Tool.createTool(com.thoughtworks.rich.component.gameTool.Tool.Type.ROADBLOCK));
        game.getElement().getLand(7).setTool(com.thoughtworks.rich.component.gameTool.Tool.createTool(com.thoughtworks.rich.component.gameTool.Tool.Type.bomb));
        game.getElement().getLand(13).setTool(com.thoughtworks.rich.component.gameTool.Tool.createTool(com.thoughtworks.rich.component.gameTool.Tool.Type.ROADBLOCK));
//        game.getCommandInputAndAct();
    }

    @Test
    public void player_can_use_bomb(){
        Game game = new Game();
        game.setElement(Creater.getCompleteMap());
        Player player = new Player();
        player.addTool(com.thoughtworks.rich.component.gameTool.Tool.createTool(com.thoughtworks.rich.component.gameTool.Tool.Type.bomb));
        game.addPlayer(player);
        player.setLocation(10);
        game.playerUseRoadBlockAndBomb(player, com.thoughtworks.rich.component.gameTool.Tool.Type.bomb, 10);
        assertThat(game.getElement().getLand(20).getTool().getType(), is(com.thoughtworks.rich.component.gameTool.Tool.Type.bomb));
    }

    @Test
    public void can_get_hospital_location(){
        Game game = new Game();
        game.setElement(Creater.getCompleteMap());
        assertThat(game.getHospitalLocation(),is(14));
    }

    @Test
    public void player_move_and_hit_by_bomb(){
        Game game = new Game();
        Player player = new Player();
        game.addPlayer(player);
        game.setElement(Creater.getCompleteMap());
        game.getElement().getLand(3).setTool(com.thoughtworks.rich.component.gameTool.Tool.createTool(com.thoughtworks.rich.component.gameTool.Tool.Type.bomb));
        player.setLocation(2);
        game.letPlayerMove(player);
        assertThat(player.getLocation(),is(game.getHospitalLocation()));
        assertThat(player.getDisableState(),is(Player.DisableState.hospital));
    }

    @Test
    public void can_count_player_day_in_hospital(){
        Game game = new Game();
        Player player = new Player();
        game.addPlayer(player);
        game.setElement(Creater.getCompleteMap());
        game.playerHitOnBomb(player);
        assertThat(player.getDisableCount(), is(3));
        player.decreaseDisableCount();
        assertThat(player.getDisableCount(),is(2));
        player.decreaseDisableCount();
    }

    @Test
    public void can_not_move_if_player_in_hospital_or_prison(){
        Game game = new Game();
        int[] index = {3,2};
        game.addPlayerWithIndex(index);
        game.getPlayer(0).setDisableState(Player.DisableState.hospital);
        assertThat(game.isCurrentPlayerDisableState(), is(true));
        game.nextPlayer();
        assertThat(game.isCurrentPlayerDisableState(), is(false));
    }

    @Test
    public void can_leave_hospital(){
        Game game = new Game();
        Player player = new Player();
        game.addPlayer(player);
        game.setElement(Creater.getCompleteMap());
        game.playerHitOnBomb(player);
        player.passOver();
        assertThat(player.getDisableCount(), is(2));
        assertThat(player.getDisableState(),is(Player.DisableState.hospital));
        player.passOver();
        player.passOver();
        player.passOver();
        assertThat(player.getDisableCount(),is(0));
        assertThat(player.getDisableState(),is(Player.DisableState.normal));
    }

    @Test
    public void player_can_be_in_prison(){
        Player player = new Player();
        player.setLocation(5);
        player.beInPrison(2,10);
        assertThat(player.getLocation(),is(10));
        assertThat(player.getDisableState(),is(Player.DisableState.prison));
        player.passOver();
        player.passOver();
        player.passOver();
        assertThat(player.getDisableState(),is(Player.DisableState.normal));
    }

    @Test
    public void not_pay_travelling_expense_when_owner_in_hospital_or_prison(){
        Player playerLandOwner = new Player();
        playerLandOwner.setMoney(2000);
        playerLandOwner.beInPrison(2,10);
        Space space = new Space();
        space.setValue(200);
        space.setOwner(playerLandOwner);
        Player player = new Player();
        player.setMoney(1000);
        player.payForTravelling(space);
        assertThat(playerLandOwner.getMoney(), is(2000));
        assertThat(player.getMoney(),is(1000));
    }


        @Test
    public void player_can_be_with_god(){
        Player player = new Player();
        player.beWithMascot();
        assertThat(player.getGodState(), is(Player.GodState.mascot));
        for(int i=0;i<6;i++){
            player.passOver();
        }
        assertThat(player.getGodState(),is(Player.GodState.none));
    }

    @Test
    public void player_will_not_pay_travelling_expense_with_mascot(){
        Player playerLandOwner = new Player();
        playerLandOwner.setMoney(2000);
        Space space = new Space();
        space.setValue(200);
        space.setOwner(playerLandOwner);
        Player playerWithMascot = new Player();
        playerWithMascot.setGodState(Player.GodState.mascot);
        playerWithMascot.setMoney(1000);
        playerWithMascot.payForTravelling(space);
        assertThat(playerLandOwner.getMoney(), is(2000));
        assertThat(playerWithMascot.getMoney(),is(1000));
    }

    @Test
    public void player_can_action_correctly_on_gift_land(){
        Land land = Land.createLand(Land.Type.GIFT);
        Player player = new Player();
        player.setMoney(10000);
        player.setPoint(100);
        player.setGodState(Player.GodState.none);
        //land.action(player);
    }

    public void player_break_up_and_exit_game(){
        Game game = new Game();
        int[] indexs = {1,3,2};
        game.addPlayerWithIndex(indexs);
        Space space = new Space();
        space.setValue(100);
        game.getPlayer(0).buyLand(space);
        game.getPlayer(0).setMoney(0);
        assertThat(game.getPlayerNum(),is(3));
        game.checkPlayerBreakUp();
        assertThat(game.getPlayerNum(),is(2));
        assertTrue(space.getOwner() == null);
    }

    public void game_over_when_one_player_left(){
        Game game = new Game();
        int[] indexs = {1,2};
        game.addPlayerWithIndex(indexs);
        Space space = new Space();
        space.setValue(100);
        game.getPlayer(0).setMoney(0);
        game.checkPlayerBreakUp();
        game.checkIfGameOver();
        assertThat(game.isPlaying(),is(false));
    }
}
