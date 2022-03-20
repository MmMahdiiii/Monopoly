package Cell.Purchasable;

import Player.Player;

import static Player.Bonus.escapeFromTax;

public class Land extends Purchasable {
    public int buildings = 0;//-1 means Hotel

    public Land(int location, double value, Color color) {
        super(location, value, color);
    }

    private boolean buildNumCheck(Player player) {
        for (int i = 0; i < player.estates.size(); i++) {
            if (player.estates.get(i) instanceof Land)
                if (((Land) player.estates.get(i)).buildings < this.buildings)
                    return false;
        }
        return true;
    }

    @Override
    public void build(Player player) {
        if (buildNumCheck(player)) {
            if (this.buildings == -1) {
                System.out.println("Sorry you can't build anymore.");
                return;
            }
            if (this.buildings < 4) {
                if (player.payment(150)) {
                    this.buildings++;
                    System.out.println("The building has been built.");
                } else {
                    System.out.println("Sorry you don't have enough money.");
                }
                return;
            }
            if (this.buildings == 4) {
                if (player.payment(100)) {
                    this.buildings = -1;
                    System.out.println("Buildings are converted to Hotel.");
                } else {
                    System.out.println("Sorry you don't have enough money.");
                }
            }
        } else {
            System.out.println("You can't build duo to others lands limit.");
        }
    }
}
