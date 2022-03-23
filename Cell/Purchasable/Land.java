package cell.Purchasable;

import Player.*;


public class Land extends Purchasable {
    public int buildings = 0;//-1 means Hotel
    static int numberOfFreeBuildings = 20;

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
        if (numberOfFreeBuildings > 0) {
            if (buildNumCheck(player)) {
                if (this.buildings == -1) {
                    System.out.println("Sorry you can't build anymore.");
                    return false;
                }
                if (this.buildings < 4) {
                    if (player.payment(150)) {
                        this.buildings++;
                        value+=150;
                        numberOfFreeBuildings--;
                        System.out.println("The building has been built.");
                        return true;
                    } else {
                        return false;
                    }
                }
                if (this.buildings == 4) {
                    if (player.payment(100)) {
                        this.buildings = -1;
                        value+=100;
                        numberOfFreeBuildings += 3;
                        System.out.println("Buildings are converted to Hotel.");
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        System.out.println("You can't build duo to others lands limit.");
        return false;
    }
    public int calculateRent(){
        int rent=50;
        if (this.buildings<=4)
            rent+=this.buildings*150;
        if(this.buildings==-1)
            rent=600;
        if(colorMonopoly())
            rent*=2;
        return rent;
    }
    private boolean colorMonopoly(){
        PlayGround map=PlayGround.getMap();
        for(int i=0;i<map.cells.length;i++){
            if(map.cells[i] instanceof Purchasable){
                if (((Purchasable) map.cells[i]).color.equals(this.color))
                if (!((Purchasable) map.cells[i]).owner.equals(this.owner))
                    return false;
            }
        }
        return true;
    }
}
