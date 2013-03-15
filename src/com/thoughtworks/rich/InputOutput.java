package com.thoughtworks.rich;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-2-27
 * Time: 上午9:56
 * To change this template use File | Settings | File Templates.
 */
public class InputOutput {
    public static final String[] COMMANDS = {"roll","block","bomb",
               "robot","sell","selltool","query","help","quit"} ;

    public static Scanner sc = new Scanner(System.in);
    public static String inputString() {
        String string = sc.next();
        string = string.toLowerCase();
        return string;
    }

    public static int inputInt() {
        int num;
        try{
            num = Integer.parseInt(inputString());
        }catch (NumberFormatException e) {
            outputStringLine("输入错误，请重新输入。");
            return inputInt();
        }
        return num;
    }

    public static void outputStringLine(String string) {
        System.out.println(string);
    }

    public static void outputString(String string) {
        System.out.print(string);
    }

    public static String inputYorNChoice(){
        String string = inputString();
        while (!checkStringIsYorN(string)){
            outputStringLine("输入错误，请输入整数。");
            string = inputString();
        }
        return string;
    }

    public static boolean checkStringIsYorN(String string){
        string = string.toLowerCase();
        return  (string.equals("y") || string.equals("n"));
    }

    public static String inputCommand(){
        outputString(">");
        String string = inputString();
        for(String command : COMMANDS){
            if(string.equals(command)){
                return string;
            }
        }
        outputStringLine("命令“" + string + "”不存在，" +
                "请重新输入。输入help查看帮助");
        return inputCommand();
    }
}
