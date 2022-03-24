package cell.bankOwned;

import player.*;

import java.util.ArrayList;

public class Prison extends BankOwned {

    public static ArrayList<Player> prisoners = new ArrayList<Player>();

    public Prison(int loc) {
        super(loc);
    }

    @Override
    public boolean free(Player player) {
        if (prisoners.contains(player)) {
            if (player.payment(50.0)) {
                System.out.println("Enjoy from your freedom =)");
                prisoners.remove(player);
                return true;
            }
        }
        System.out.println("You are not in dungeon!!!");
        return false;
    }
    public static boolean putInPrison(Player player) {
        if (prisoners.contains(player))
            return false;
        prisoners.add(player);
        return true;
    }
}
