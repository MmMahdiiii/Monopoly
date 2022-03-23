package Cell.bankOwned;

public class Road extends BankOwned{
    public Road(int loc) {
        super(loc);
    }
    public int getCharge(){
        return 100;
    }
}
