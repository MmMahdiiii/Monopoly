package Cell.BankOwned;

import Player.Player;

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
                return true;
            }
        }
        return false;
    }
}
