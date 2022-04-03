package player;

import cell.bankOwned.BankOwned;
import cell.Cell;
import cell.purchasable.Purchasable;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class Player {
    public final String name;
    public double money;
    public int location;
    public int priorityDice;
    public ArrayList<cell.purchasable.Purchasable> estates = new ArrayList<cell.purchasable.Purchasable>();
    private ArrayList<Bonus> bonuses = new ArrayList<Bonus>();
    public static ArrayList<Player> allPlayers = new ArrayList<Player>();
    public static ArrayList<Player> losers = new ArrayList<Player>();
    public Double fund = null;

    public void giveBonus(Bonus bonus) {
        bonuses.add(bonus);
    }

    public Player(String name) {
        this.name = name;
        money = 1500;
        location = 0;
        estates = new ArrayList<cell.purchasable.Purchasable>();
        bonuses = new ArrayList<Bonus>();
    }

    public boolean sell(int index) {
//        if (index < 1 || index > 24){
//            System.out.println("your chosen number is invalid pls try again:");
//        }
        Cell tempCell = PlayGround.getMap().cells[index - 1];
        if (!(tempCell instanceof Purchasable)) {
            System.out.println("This item isn't purchasable");
            return false;
        } else {
            if (!this.equals(((Purchasable) tempCell).owner)) {
                System.out.println("This item doesn't belong you.");
                return false;
            }
        }
        money += ((Purchasable) tempCell).value / 2;
        ((Purchasable) tempCell).owner = null;
        estates.remove(tempCell);
        System.out.println("you soled the item");
        return true;
    }

    public boolean buy(Cell cell) {
        if (cell instanceof BankOwned) {
            System.out.println("You can not buy bank's estates!");
            return false;
        }
        Purchasable item = (Purchasable) cell;
        if (item.owner != null) {
            System.out.println("This place is already bought by " + item.owner.name);
            return false;
        }
        payment(item.value);
        item.owner = this;
        estates.add(item);
        System.out.println("you bought the item.");
        return true;
    }

    public boolean payment(double cost) {
        if (cost > money) {
            System.out.println("Sorry but you have not enough money!");
            return false;
        }
        money -= cost;
        return true;
    }

    public boolean moveTo(int destination) {
        if (destination < 0 || destination > 23)
            return false;
        location = destination;
        return true;
    }

    int index() {
        return location + 1;
    }

    public void property() {
        System.out.println("Left money : " + money + "$");
        estatePrint();
    }

    static public boolean swap_wealth(String s1, String s2) {
        if (!containsThisName(s1) || !containsThisName(s2)) {
            System.out.println("This user is broke");
            return false;
        }
        Player player1 = getPlayer(s1);
        Player player2 = getPlayer(s2);
        for (Purchasable p : player1.estates) {
            p.owner = player2;
        }
        for (Purchasable p : player2.estates) {
            p.owner = player1;
        }
        ArrayList<cell.purchasable.Purchasable> temp;
        temp = player1.estates;
        player1.estates = player2.estates;
        player2.estates = temp;
        double tempMoney = player1.money;
        player1.money = player2.money;
        player2.money = tempMoney;
        return true;

    }

    static private boolean containsThisName(String s) {
        return getPlayer(s) != null;
    }

    static private Player getPlayer(String s) {
        for (Player p : allPlayers) {
            if (p.name.equals(s))
                return p;
        }
        return null;
    }

    public boolean estatePrint() {
        if (estates.size() == 0) {
            System.out.println("You do not have any estate!");
            return false;
        }
        for (int i = 0; i < estates.size(); i++) {
            System.out.printf("%d- %s (%d)\n", (i + 1), estates.get(i).getClass().getSimpleName(), estates.get(i).location + 1);
        }
        return true;
    }

    public boolean canPay(double price) {
        if (wholeCredit() < price) {
            return false;
        }
        return true;
    }

    public double wholeCredit() {
        double result = this.money;
        for (Purchasable estate : this.estates) {
            result += estate.value;
        }
        return result;
    }

    public boolean haveToSellSomething(int cost) {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public int ranking() {
        int i = 1;
        for (Player p : allPlayers) {
            if (p.wholeCredit() > this.wholeCredit())
                i++;
        }
        return i;
    }

}



