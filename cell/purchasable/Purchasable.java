package cell.purchasable;

import player.Player;

public abstract class Purchasable extends cell.Cell {
    public double value;
    public Player owner=null;
    public Color color;

    public Purchasable(int location, double value, Color color) {
        super(location);
        this.value = value;
        this.color = color;
    }

}
