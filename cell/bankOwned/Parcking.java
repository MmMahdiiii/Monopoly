package cell.bankOwned;

import player.Player;

public class Parcking extends BankOwned {
    public Parcking(int loc) {
        super(loc);
    }
    @Override
    public boolean toDo(Player player){
        return true;
    }
}
