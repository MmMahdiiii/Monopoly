package Cell.BankOwned;

import Player.*;

import java.util.Random;

public class Chance extends BankOwned {
    public Chance(int loc) {
        super(loc);
    }

    public void chanceGame(Player player) {
        int rand = (int) (Math.random() * 7);
        if (rand == 0) {
            player.money += 200.0;
        } else if (rand == 1) {
            //zendani ash kon in heyvoon ro
        } else if (rand == 2) {
            player.money *= 0.9;
        } else if (rand == 3) {
            // 3 ta khoone bebar jelo in heyvoone zaboon baste ro
        } else if (rand == 4) {
            player.giveBonus(Bonus.escapeFromDungeon);
        } else if (rand == 5) {
            player.giveBonus(Bonus.escapeFromTax);
        } else if (rand == 6) {
            // be har bazikon 10$ bede =)
        }
    }
}
