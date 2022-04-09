package cell.bankOwned;

import player.Bonus;
import player.Player;


public class Tax extends BankOwned {
    public Tax(int loc) {
        super(loc);
    }

    @Override
    public boolean toDo(Player player) {
        if (player.bonuses.contains(Bonus.escapeFromTax)) {
            System.out.println("you had a chance to skip prison =)");
            player.bonuses.remove(Bonus.escapeFromTax);
            return true;
        }
        System.out.println("the tax you should pay is " + player.money * 10 / 100 + "$");
        return player.payment(player.money * 0.1);
    }
}
