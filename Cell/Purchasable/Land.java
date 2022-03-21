package Cell.Purchasable;

import Player.Player;

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
    public boolean build(Player player) {
        if (buildNumCheck(player)) {
            if (this.buildings == -1) {
                System.out.println("Sorry you can't build anymore.");
                return false;
            }
            if (this.buildings < 4) {
                if (player.payment(150)) {
                    this.buildings++;
                    System.out.println("The building has been built.");
                    return true;
                } else {
                    return false;
                }
            }
            if (this.buildings == 4) {
                if (player.payment(100)) {
                    this.buildings = -1;
                    System.out.println("Buildings are converted to Hotel.");
                    return true;
                } else {
                    return false;
                }
            }
        }
            System.out.println("You can't build duo to others lands limit.");
            return false;

    }
}
