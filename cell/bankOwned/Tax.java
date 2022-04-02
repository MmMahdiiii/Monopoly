package cell.bankOwned;
import player.Player;


public class Tax extends BankOwned {
    public Tax(int loc) {
        super(loc);
    }
    @Override
    public boolean toDo(Player player ){
        System.out.println("the tax you should pay is " + player.money * 10/ 100 + "$");
        return player.payment(player.money * 10/ 100);
    }
}
