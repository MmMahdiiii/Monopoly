import player.Player;

import java.util.Scanner;

import static player.Player.allPlayers;

public class Main {
    public static void main(String[] args) {
        int timeLimit = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println(scanner.next());
        System.out.println("Write 'create_game' to create a game.");
        while (true) {
            if (scanner.next().equals("create_game")) {
                break;
            } else {
                System.out.println("no game created.");
            }
        }
        System.out.println("Enter game's time limit with 'Time x min' format which x is your desired time.");
        while (true) {
            try {
                if (!scanner.next().equals("Time"))
                    throw new RuntimeException();
                if (scanner.hasNextInt())
                    timeLimit = scanner.nextInt();
                else
                    throw new RuntimeException();
                if (scanner.next().equals("min"))
                    break;
                else throw new RuntimeException();
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
                    else break;
                } else allPlayers.add(new player.Player(temp));
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
    }
}
