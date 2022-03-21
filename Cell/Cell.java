package Cell;

import Cell.BankOwned.Airport;
import Cell.Purchasable.Land;
import Player.Player;

import javax.print.attribute.PrintRequestAttribute;

public abstract class Cell {
    public int location;

    public Cell(int location) {
        this.location = location;
    }

    public boolean build(Player player) {
        return itsNoAvailable();
    }

    public boolean fly() {
        return itsNoAvailable();
    }

    public boolean free(){
        return itsNoAvailable();
    }

    public abstract boolean invest();

    public boolean itsNoAvailable() {
        System.out.println("This action is not available!");
        return false;
    }
}
