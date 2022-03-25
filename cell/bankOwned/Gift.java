package cell.bankOwned;
import player.Player;

public class Gift extends BankOwned{
    public Gift(int loc) {
        super(loc);
    }
    @Override
    public boolean toDo(Player player){
        player.money += 200;
        return true;
    }
}
