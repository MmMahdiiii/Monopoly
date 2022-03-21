package Player;

import Cell.BankOwned.Parcking;
import Cell.Cell;
import Cell.BankOwned.*;
import Cell.Purchasable.*;
import Cell.Purchasable.Land;

public class PlayGround {
    static PlayGround map=new PlayGround();
    private PlayGround() {

    }

     public Cell[] cells = new Cell[24];

    {
        cells[0] = new Parcking(0);
        cells[1] = new Land(1, 100, Color.green);
        cells[2] = new Airport(2);
        cells[3] = new Cinema(3, 200, Color.red);
        cells[4] = new Road(5);
        cells[5] = new Gift(5);
        cells[6] = new Land(6, 100, Color.yellow);
        cells[7] = new Cinema(7, 200, Color.blue);
        cells[8] = new Land(8, 100, Color.red);
        cells[9] = new Road(9);
        cells[10] = new Airport(10);
        cells[11] = new Land(11, 100, Color.green);
        cells[12] = new Prison(12);
        cells[13] = new Land(13, 100, Color.blue);
        cells[14] = new Cinema(14, 200, Color.green);
        cells[15] = new Road(15);
        cells[16] = new Tax(16);
        cells[17] = new Land(17, 100, Color.red);
        cells[18] = new Land(18, 100, Color.yellow);
        cells[19] = new Airport(19);
        cells[20] = new Bank(20);
        cells[21] = new Cinema(121, 200, Color.yellow);
        cells[22] = new Land(22, 100, Color.blue);
        cells[23] = new Chance(23);
    }

    public static PlayGround getMap() {
        return map;
    }
}
