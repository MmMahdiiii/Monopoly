package cell.bankOwned;
import cell.*;
import player.Player;


public class Tax extends BankOwned {
    public Tax(int loc) {
        super(loc);
    }
    @Override
    public boolean toDo(Player player ){
        return player.payment(player.money * 10/ 100);
    }
}
