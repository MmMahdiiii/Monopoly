package Player;
import Cell.BankOwned.BankOwned;
import Cell.Cell;
import Cell.Purchasable.Purchasable;
import java.util.ArrayList;
public class Player {
        public final String name;
        public int money;
        public int location;
        private ArrayList<Cell.Purchasable.Purchasable> estates;

        Player(String name) {
            this.name = name;
            money = 1500;
            location = 0;
            estates = new ArrayList<Cell.Purchasable.Purchasable>();
        }
        boolean sell(){
            if (!estatePrint())
                return false;

        }
        boolean buy(Cell cell){
            if(cell instanceof BankOwned) {
                System.out.println("You can not buy bank's estates!");
                return false;
            }
            Purchasable item = (Purchasable) cell;
            if (item.owner != null){
                System.out.println("This place is already bought by " + item.owner.name);
                return false;
            }
            payment(item.value);
            item.owner = this;
            return true;
        }
        private boolean payment (double price){
            if(price > money){
                System.out.println("Sorry but you have not enough money!");
                if (estates.size() > 0)
                    System.out.println("You can sell yor estates!");
                else
                    System.out.println("and you do not have any estate to sell!");
                return false;
            }
            money -= price;
            return true;
        }
        int index (){
            return location + 1;
        }
        String property(){

        }
        private boolean estatePrint(){
            if (estates.size() == 0){
                System.out.println("You do not have any estate!");
                return flase;
            }
            for(int i = 0; i < estates.size(); i++){
                System.out.printf("%d- %s (%d)\n", (i + 1), estates.get(i).getClass().getSimpleName(), estates.get(i).location);
            }
            return true;
        }


}


