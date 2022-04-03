import cell.bankOwned.Prison;
import player.PlayGround;
import player.Player;

import java.util.Collections;
import java.util.Scanner;

import player.*;
import cell.*;


public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int timeLimit = 0;
        System.out.println("Write 'create_game' to create a game.");
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
                String temp = scanner.nextLine();
                if (!temp.startsWith("Time"))
                    throw new RuntimeException();
                timeLimit = Integer.parseInt(temp.substring(5, temp.indexOf("min") - 1));
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
            if (Player.allPlayers.size() < 5) {
                String temp = scanner.nextLine();
                if (temp.equals("start_game")) {
                    if (Player.allPlayers.size() < 2)
                        System.out.println("Players size is too low, please add more players then write start game.");
                    else
                        break;
                } else {
                    boolean flag = true;
                    for (Player player : Player.allPlayers) {
                        if (player.name.equals(temp)) {
                            System.out.println("this name already exist, use another name.");
                            flag = false;
                        }
                    }
                    if (flag)
                        Player.allPlayers.add(new player.Player(temp));
                }
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
        System.out.println("Roll your turn dice.    Notice:if some dices are drawn,the players who joined sooner ,play sooner.");
        for (int i = 0; i < Player.allPlayers.size(); i++) {
            try {
                System.out.println("Enter " + Player.allPlayers.get(i).name + "'s priority turn dice:");
                int temp = scanner.nextInt();
                if (temp < 1 || temp > 6)
                    throw new Exception();
                Player.allPlayers.get(i).priorityDice = temp;
            } catch (Exception e) {
                System.out.println("pls enter a valid number!");
                scanner.nextLine();
                i--;
            }
        }
        for (int i = 0; i < Player.allPlayers.size() - 1; i++)
            for (int j = 0; j < Player.allPlayers.size() - i - 1; j++) {
                if (Player.allPlayers.get(j).priorityDice < Player.allPlayers.get(j + 1).priorityDice) {
                    Collections.swap(Player.allPlayers, j, j + 1);
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
        long startTime = System.nanoTime();
        long endTime = System.nanoTime();
        for (int i = 0; Player.allPlayers.size() > 1 && (endTime - startTime) / 60000000000L < timeLimit; i++) {
            if (i == Player.allPlayers.size()) {
                i = 0;
                round++;
                System.out.println("round : " + round);
            }
            Player p = Player.allPlayers.get(i);
            System.out.println("turn " + p.name);
            System.out.println(p.name + " left money : " + p.money + "$");
            if (Prison.prisoners.contains(p)) {
                prisonersTurn(p);
                continue;
            }
            System.out.println("roll your dice");
            int dice;
            while (true) {
                try {
                    dice = scanner.nextInt();
                    if (dice < 1 || dice > 6)
                        throw new Exception();
                    if (dice == 6) {
                        System.out.println("Nice! you can roll dice again!");
                        while (true) {
                            try {
                                int temp = scanner.nextInt();
                                if (temp > 6 || temp < 0) {
                                    throw new Exception();
                                } else {
                                    dice += temp;
                                    break;
                                }
                            } catch (Exception e) {
                                System.out.println("pls enter a valid number!");
                            }
                        }
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("pls enter a valid number!");
                    scanner.nextLine();
                }
            }
            if (dice == 12) {
                Prison.putInPrison(p);
                continue;
            }
            try {
                p.location += dice;
                if (p.location > 23)
                    p.location -= 24;
                int loc = p.location;
                System.out.println("You can command your method then enter \"Quit\" for end your tasks.");
                scanner.nextLine();
                String method = scanner.nextLine();
                while (!method.equalsIgnoreCase("Quit")) {
                    try {
                        if (method.startsWith("build"))
                            PlayGround.getMap().cells[p.location].build(p);
                        else if (method.startsWith("sell"))
                            p.sell(Integer.parseInt(method.substring(5)));
                        else if (method.startsWith("invest"))
                            PlayGround.getMap().cells[loc].invest(p);
                        else if (method.startsWith("fly"))
                            PlayGround.getMap().cells[loc].fly(p, Integer.parseInt(method.substring(4)) - 1);
                        else if (method.startsWith("swap wealth")) {
                            Player.swap_wealth(method.split("\s")[2], method.split("\s")[3]);
                        } else if (method.equalsIgnoreCase("buy"))
                            p.buy(PlayGround.getMap().cells[p.location]);
                        else if (method.equalsIgnoreCase("index"))
                            System.out.println(p.location + 1);
                        else if (method.equalsIgnoreCase("property")) {
                            p.property();
                        } else if (method.equalsIgnoreCase("time"))
                            System.out.printf("remaining time= %d min\n", timeLimit - (System.nanoTime() - startTime) / 60000000000L);
                        else if (method.equalsIgnoreCase("rank")) {
                            System.out.println(p.name + "'s rank is " + p.ranking());
                        } else System.out.println("Wrong method format.");

                    } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                        System.out.println("Wrong method format.");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("this index is out of map.");
                    }
                    method = scanner.nextLine();
                }
                PlayGround.getMap().cells[loc].toDo(p);
            } catch (Lose lose) {
                System.out.println(lose.getMessage());
                System.out.println(p.name + " Lost!");
                Player.allPlayers.remove(p);
                Player.losers.add(p);
            }
            endTime = System.nanoTime();
            if (i == Player.allPlayers.size() - 1) {
                System.out.println("you can swap players' wealth by command \"swap_wealth (first_player)(second_player)\"\n" +
                        "or enter anything else to skip");
                if (scanner.next().startsWith("swap_wealth")) {
                    Player.swap_wealth(scanner.next(), scanner.next());
                }
            }
        }
        if (Player.allPlayers.size() == 1) {
            System.out.println(ANSI_GREEN + Player.allPlayers.get(0).name + ", YOU WON." + ANSI_RESET);
            for (Player player : Player.losers) {
                System.out.println(ANSI_RED + player.name + ", YOU LOST." + ANSI_RESET);
            }
        } else {
            try {
                Player temp = Player.allPlayers.get(0);
                for (Player player : Player.allPlayers) {
                    if (player.ranking() < temp.ranking())
                        temp = player;
                    if (player.ranking() == temp.ranking() && !player.equals(Player.allPlayers.get(0)))
                        throw new Exception();
                }
                System.out.println(temp.name + ANSI_GREEN + ", YOU WON." + ANSI_RESET);
                for (Player player : Player.allPlayers) {
                    if (!player.equals(temp))
                        System.out.println(player.name + ANSI_RED + ", YOU LOST." + ANSI_RESET);
                }
                for (Player player : Player.losers) {
                    System.out.println(player.name + ANSI_RED + ", YOU LOST." + ANSI_RESET);
                }
            } catch (Exception e) {
                System.out.println(ANSI_RED + "We have no Winner,duo to same wealth." + ANSI_RESET);
                for (Player player : Player.allPlayers) {
                    System.out.println(player.name + ANSI_RED + ",YOU LOST." + ANSI_RESET);
                }
                for (Player player : Player.losers) {
                    System.out.println(player.name + ANSI_RED + ",YOU LOST." + ANSI_RESET);
                }
            }


        }
    }

    private static void prisonersTurn(Player player) {
        System.out.println("do you prefer to pay 50 for your freedom or try your luck?");
        System.out.println("enter \"free\" to pay");
        System.out.println("enter \"chance\" to try your luck");
        Outer:
        while (true) {
            String s = scanner.next();
            try {
                if (!s.equalsIgnoreCase("free") && !s.equalsIgnoreCase("chance"))
                    throw new Exception();
                if (s.equalsIgnoreCase("chance")) {
                    while (true) {
                        try {
                            System.out.println("roll your dice");
                            int dice = scanner.nextInt();
                            if (dice < 1 || dice > 6)
                                throw new Exception();
                            if (dice == 1) {
                                Prison.putOutPrison(player);
                            }
                            break Outer;
                        } catch (Exception e) {
                            System.out.println("pls enter a valid number!");
                        }
                    }
                } else {
                    if (PlayGround.getMap().cells[12].free(player))
                        break;
                }

            } catch (Exception e) {
                System.out.println("Unexpected command: " + s);
            }
        }
    }

}