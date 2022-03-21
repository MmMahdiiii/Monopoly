package Cell.Purchasable;


import Player.PlayGround;

public class Cinema extends Purchasable{
    public Cinema(int location, double value, Color color) {
        super(location, value, color);
    }
    public int getTicket(){
       int result=25;
       result*=cinemaCounter()*2;
       return result;
    }
    public int cinemaCounter(){
        int result=0;
        PlayGround map=PlayGround.getMap();
        for(int i=0;i<map.cells.length;i++){
            if(map.cells[i] instanceof Cinema)
                if (((Cinema) map.cells[i]).owner.equals(this.owner))
                    result++;
        }
        return result;
    }
}
