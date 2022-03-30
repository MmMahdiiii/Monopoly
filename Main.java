import cell.bankOwned.Prison;
import player.PlayGround;
import player.Player;

import java.lang.invoke.SerializedLambda;
import java.util.Scanner;

import player.*;
import cell.*;

import static player.Player.allPlayers;

public class Main {
    public static void main(String[] args) {
        int timeLimit = 0;
        System.out.println("Write 'create_game' to create a game.");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.next().equals("create_game")) {
                break;
            } else {
                System.out.println("no game created.");
            }
        }
        System.out.println("Enter game's time limit with 'Time x min' format which x is your desired time.");
        scanner.nextLine();

        while (true) {
            try {
                String  temp=scanner.nextLine();
                if (!temp.startsWith("Time"))
                    throw new RuntimeException();
                timeLimit=Integer.parseInt(temp.substring(5,temp.indexOf("min")-1));
                if (temp.contains("min"))
                    break;
                else
                    throw new RuntimeException();
            } catch (RuntimeException e) {
                System.out.println("Wrong Format,Please write game limit with 'Time x min' format.");
            }
        }
        System.out.println("Enter player's name (at least 2 , at last 4 players) then write 'start_game'.");
        outerLoop:
        while (true) {
            if (allPlayers.size() < 4) {
                String temp = scanner.next();
                if (temp.equals("start_game")) {
                    if (allPlayers.size() < 2)
                        System.out.println("Players size is too low, please add more players then write start game.");
                    else
                        break;
                } else
                    allPlayers.add(new player.Player(temp));
            } else {
                System.out.println("Players capacity is full, Please write 'start_game'.");
                while (true) {
                    if (!scanner.next().equals("start_game"))
                        System.out.println("Please write 'start_game' to start the game.");
                    else {
                        break outerLoop;
                    }
                }
            }
        }
        /*
         * {
         * allplayer... sort
         * 1 5 6 4
         * 6 5 4 1
         * }
         */
        int round = 1;
        System.out.println("round : " + round);
        long startTime=System.nanoTime();
        long endTime=System.nanoTime()+timeLimit* 2L;
        boolean onTime = true;
        for (int i = 0; Player.allPlayers.size() > 1 && endTime-startTime/60000000000L<timeLimit; i++) {
            if (i == Player.allPlayers.size()) {
                i = 0;
                round++;
                System.out.println("round : " + round);
            }
            System.out.println("turn " + allPlayers.get(i).name);
            int dice;
            while (true) {
                try {
                    dice = scanner.nextInt();
                    if (dice < 0 || dice > 6)
                        throw new Exception();
                    if(dice == 6){
                        System.out.println("Nice! you can roll dice again!");
                        dice += scanner.nextInt();
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("pls enter a valid number!");
                }
            }

            Player p = Player.allPlayers.get(i);
            System.out.println(p.name + " left money : " + p.money + "$");
            if(dice == 12){
                Prison.putInPrison(p);
            }
            try {
                p.location += dice;
                if (p.location > 23)
                    p.location -= 24;
                int loc = p.location;
                System.out.println("You can command your method then enter \"Quit\" for end your tasks.");
                String method = scanner.next();
                while (!method.equalsIgnoreCase("Quit")) {
                    if (method.startsWith("build"))
                        PlayGround.getMap().cells[Integer.parseInt(method.substring(6)) - 1].build(p);
                    else if (method.startsWith("sell"))
                        p.sell(Integer.parseInt(method.substring(5)));
                    else if (method.startsWith("fly"))
                        PlayGround.getMap().cells[loc].fly(p, Integer.parseInt(method.substring(4)));
                    else if (method.startsWith("buy"))
                        p.buy(PlayGround.getMap().cells[Integer.parseInt(method.substring(4)) - 1]);
                    else if (method.equalsIgnoreCase("index"))
                        System.out.println(p.location+1);
                    else if (method.equalsIgnoreCase("property")){
                        p.estatePrint();
                    }
                    else if(method.equalsIgnoreCase("time"))
                        System.out.printf("remaining time= %d\n",timeLimit-System.nanoTime()-startTime);
                    else if (method.equalsIgnoreCase("rank")){
                        System.out.println(p.name+"'s rank is "+p.ranking());
                    }
                    else System.out.println("Wrong method format.");


                    method = scanner.next();
                }
                PlayGround.getMap().cells[loc].toDo(p);
            } catch (Lose lose) {
                System.out.println(lose.getMessage());
                System.out.println(p.name + " Lost!");
                Player.allPlayers.remove(p);
                Player.losers.add(p);
            }catch (NumberFormatException e){
                System.out.println("Wrong method format.");
            }
            endTime=System.nanoTime();
        }
        if(Player.allPlayers.size() == 1){
            // mamad win
        }
        else
        {
            // time over
        }
    }
}