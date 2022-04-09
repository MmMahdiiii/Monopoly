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
        if (player.bonuses.contains(Bonus.escapeFromDungeon)) {
            System.out.println("you had a chance to skip prison =)");
            player.bonuses.remove(Bonus.escapeFromDungeon);
            return true;
        }
        prisoners.add(player);
        player.moveTo(12);
        System.out.println("Welcome to the dungeon!");
        return true;
    }

    public static boolean putOutPrison(Player player) {
        if (prisoners.contains(player)) {
            System.out.println("Enjoy from your freedom =)");
            prisoners.remove(player);
            return true;
        }
        System.out.println("You are not in dungeon!!!");
        return false;
    }
}
