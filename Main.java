import Player.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int timeLimit=0;
        Scanner scanner=new Scanner(System.in);
        System.out.println(scanner.next());
        System.out.println("Write 'create_game' to create a game");
        while (true){
            if(scanner.next().equals("create_game")) {
                break;
            } else{
                System.out.println("no game created.");
            }
        }
        System.out.println("Enter game's time limit with 'Time x min' format which x is your desired time.");
        while (true){
            try{
                if (!scanner.next().equals("Time"))
                    throw new RuntimeException();
                if (scanner.hasNextInt())
                    timeLimit=scanner.nextInt();
                else
                    throw new RuntimeException();
                if (scanner.next().equals("min"))
                    break;
                else throw new RuntimeException();
            }catch(RuntimeException e){
                System.out.println("Wrong Format,Please write game limit with 'Time x min' format.");
            }
        }
        System.out.println("Enter players name (at least 2 , at last 4 players) then write 'start_game' .");
    }
}
