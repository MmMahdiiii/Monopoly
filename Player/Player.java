package Player;

import Cell.BankOwned.BankOwned;
import Cell.Cell;
import Cell.Purchasable.Purchasable;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    public final String name;
    public int money;
    public int location;
    public ArrayList<Cell.Purchasable.Purchasable> estates=new ArrayList<Cell.Purchasable.Purchasable>();
    private ArrayList<Bonus> bonuses=new ArrayList<Bonus>();

    public void giveBonus(Bonus bonus) {
        bonuses.add(bonus);
    }

    Player(String name) {
        this.name = name;
        money = 1500;
        location = 0;
        estates = new ArrayList<Cell.Purchasable.Purchasable>();
        bonuses = new ArrayList<Bonus>();
    }

    boolean sell() {
        if (!estatePrint())
            return false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("pls enter a index to sell!");
        int index = scanner.nextInt();
        while (index < 1 || index > estates.size()) {
            System.out.println("your chosen number is invalid pls try again:");
            index = scanner.nextInt();
        }
        money += estates.get(index - 1).value / 2;
        estates.get(index - 1).owner = null;
        estates.remove(index - 1);
        return true;
    }

    boolean buy(Cell cell) {
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
        return true;
    }

    public boolean payment(double price) {
        if (price > money) {
            System.out.println("Sorry but you have not enough money!");
            return false;
        }
        money -= price;
        return true;
    }

    int index() {
        return location + 1;
    }

    public void property() {
        System.out.println("Left money : " + money + "$");
        estatePrint();
    }

    private boolean estatePrint() {
        if (estates.size() == 0) {
            System.out.println("You do not have any estate!");
            return false;
        }
        for (int i = 0; i < estates.size(); i++) {
            System.out.printf("%d- %s (%d)\n", (i + 1), estates.get(i).getClass().getSimpleName(), estates.get(i).location);
        }
        return true;
    }
}


