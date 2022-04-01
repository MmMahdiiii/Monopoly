package cell.bankOwned;

import player.*;

public class Bank extends BankOwned {
    public Bank(int loc) {
        super(loc);
    }

    @Override
    public boolean invest(Player player) {
        if (player.fund != null) {
            System.out.println("You can not invest twice!");
            return false;
        }
        player.fund = player.money / 2;
        player.payment(player.money / 2);
        System.out.println("You invest successfully =)");
        return true;
    }

    @Override
    public boolean toDo(Player player) {
        if (player.fund != null) {
            player.money += player.fund * 2;
            player.fund = null;
        }
        System.out.println("You have got back your fund from the bank!");
        return true;

    }
}
