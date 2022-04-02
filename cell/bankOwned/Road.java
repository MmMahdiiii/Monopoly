package cell.bankOwned;

import cell.*;
import player.Player;

public class Road extends BankOwned{
    public Road(int loc) {
        super(loc);
    }
    public int getCharge(){
        return 100;
    }
    @Override
    public boolean toDo(Player player)throws Lose {
        if(!player.canPay(100))
            throw new Lose("You do not have enough money to pass road :'(");
        while (!player.payment(100)){
            System.out.println("you payed 100$ to pass road");
            player.haveToSellSomething(100);
        }
        return true;
    }
}
