import java.util.ArrayList;

public class Ship {
    private ArrayList<Ship> list;
    private int row;
    private int col;

    public Ship(int row, int col){
        this.row = row;
        this.col = col;
    }

    public void addToShips(Ship ship){
        list.add(ship);
    }

    public ArrayList<Ship> getList(){
        return list;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
