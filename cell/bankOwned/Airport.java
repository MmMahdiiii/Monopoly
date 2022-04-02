package cell.bankOwned;

import player.Player;

public class Airport extends BankOwned {
    public Airport(int loc) {
        super(loc);
    }

    // input should 1 less than
    @Override
    public boolean fly(Player player, int destination) {
        if ((destination == 2 || destination == 10 || destination == 19)
                && destination != this.location) {
            if (player.payment(50.0)) {
                player.moveTo(destination);
                System.out.println("you arrived =)");
                return true;
            }
        }
        System.out.println("you cant fly to " + destination);
        return false;
    }
}
