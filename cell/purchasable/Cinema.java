package cell.purchasable;


import cell.Lose;
import player.PlayGround;
import player.Player;

public class Cinema extends Purchasable{
    public Cinema(int location, double value, Color color) {
        super(location, value, color);
    }
    public int getTicket(){
       int result=25;
       if(cinemaCounter()!=0)
           result*=cinemaCounter()*2;
       return result;
    }
    public int cinemaCounter(){
        int result=0;
        PlayGround map=PlayGround.getMap();
        for(int i=0;i<map.cells.length;i++){
            if(map.cells[i] instanceof Cinema)
                if (((Cinema) map.cells[i]).owner!=null&&((Cinema) map.cells[i]).owner.equals(this.owner))
                    result++;
        }
        return --result;
    }

    @Override
    public boolean toDo(Player player) throws Lose {
        if (owner == null || player.equals(owner))
            return true;
        int cost = getTicket();
        if (!player.canPay(cost))
            throw new Lose();
        while (!player.payment(cost)){
            player.haveToSellSomething(cost);
        }
        this.owner.money+=cost;
        return true;
    }
}
