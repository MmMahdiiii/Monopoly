package Cell.bankOwned;

import player.*;

public class Bank extends BankOwned{
    public Bank(int loc) {
        super(loc);
    }
    @Override
    public boolean invest(Player player){
        player.payment(player.money / 2);
        player.giveBonus(Bonus.hasFundinBank);
        return true;
    }
}
