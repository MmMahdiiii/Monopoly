package cell.BankOwned;

import Player.Player;


public class Tax extends BankOwned {
    public Tax(int loc) {
        super(loc);
    }
    public boolean takeTax(Player player) {
        return player.payment(player.money * 10 / 100);
    }
}
