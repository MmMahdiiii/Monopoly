package cell.bankOwned;

import player.*;

public class Chance extends BankOwned {
    public Chance(int loc) {
        super(loc);
    }

    public boolean chanceGame(Player player) {
        int rand = (int) (Math.random() * 7);
        if (rand == 0) {
            player.money += 200.0;
            return true;
        } else if (rand == 1) {
            Prison.putInPrison(player);
            return true;
        } else if (rand == 2) {
            player.money *= 0.9;
            return true;
        } else if (rand == 3) {
            player.moveTo(player.location + 3);
            return true;
        } else if (rand == 4) {
            player.giveBonus(Bonus.escapeFromDungeon);
            return true;
        } else if (rand == 5) {
            player.giveBonus(Bonus.escapeFromTax);
            return true;
        } else if (rand == 6) {
            if (player.payment((Player.allPlayers.size() - 1) * 10)) {
                for (Player i : Player.allPlayers) {
                    if (i != player)
                        i.money += 10.0;
                }
                return true;
            }
            return false;
        }
        return false;
    }
}
