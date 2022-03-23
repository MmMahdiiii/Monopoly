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
        System.out.println("Enter game's time limit with format 'Time x min' which x is your desired time.");
        while (true){

        }
    }
}
