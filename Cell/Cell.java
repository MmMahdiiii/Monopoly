package Cell;

import Cell.Purchasable.Land;
import Player.Player;

public abstract class Cell {
    public int location;

    public Cell(int location) {
        this.location = location;
    }
    public abstract void build(Player player);
}
