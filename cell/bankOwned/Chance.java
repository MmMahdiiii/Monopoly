package cell.bankOwned;

import cell.Lose;
import player.*;
import cell.Lose;
public class Chance extends BankOwned {
    public Chance(int loc) {
        super(loc);
    }

    @Override
    public boolean toDo(Player player) throws Lose {
        int rand = (int) (Math.random() * 7);
        if (rand == 0) {
            System.out.println("You won 200 dollars =)");
            player.money += 200.0;
            return true;
        } else if (rand == 1) {
            System.out.println("Welcome to dungeon :')");
            Prison.putInPrison(player);
            return true;
        } else if (rand == 2) {
            System.out.println("You have to pay tax =/");
            player.money *= 0.9;
            return true;
        } else if (rand == 3) {
            System.out.println("You moved to 3 steps further ^-^");
            player.moveTo(player.location + 3);
            return true;
        } else if (rand == 4) {
            System.out.println("Now you have a chance to skip the dungeon for 1 time =)");
            player.giveBonus(Bonus.escapeFromDungeon);
            return true;
        } else if (rand == 5) {
            System.out.println("Now you have a chance to skip the tax for 1 time =)");
            player.giveBonus(Bonus.escapeFromTax);
            return true;
        } else if (rand == 6) {
            if(player.canPay((Player.allPlayers.size() - 1) * 10))
                throw new Lose("you do not have enough money to pay 10 dollars to each players");
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
