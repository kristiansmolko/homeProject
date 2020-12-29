import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main{ //extends Application {

   /*@Override
    public void start(Stage stage) throws Exception {

    }*/
   static int[][] field =  {{0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}};
   static ArrayList<Ship> ships = new ArrayList<>();

   private static void addToField(String text){
       String[] temp = text.split("");
       int row = 0;
       int col = (Integer.parseInt(temp[1]))-1;

       if (temp[0].equalsIgnoreCase("A"))
           row = 0;
       else if (temp[0].equalsIgnoreCase("B"))
           row = 1;
       else if (temp[0].equalsIgnoreCase("C"))
           row = 2;
       else if (temp[0].equalsIgnoreCase("D"))
           row = 3;
       else if (temp[0].equalsIgnoreCase("E"))
           row = 4;
       else if (temp[0].equalsIgnoreCase("F"))
           row = 5;
       else if (temp[0].equalsIgnoreCase("G"))
           row = 6;
       else if (temp[0].equalsIgnoreCase("H"))
           row = 7;
       else if (temp[0].equalsIgnoreCase("I"))
           row = 8;

       //if you can place your ship
       if (field[row][col] == -1) {
           System.out.println("You can't place your ship here");
           return;
       }
       //create ship
       field[row][col] = 1;
       ships.add(new Ship(row, col));

       //1st and 2nd row
       if ((row-2 < 0) && (col-1 < 0)){
            if (!(row-1 < 0)) {
                field[row - 1][col + 1] = -1;
                field[row - 1][col + 2] = -1;
                if (field[row-1][col] == 1)
                    field[row+1][col] = 0;
                if (field[row+1][col] == 1){
                    field[row-1][col] = 0;
                    field[row+2][col] = 0;
                }
            }
            field[row+1][col+1] = -1;
            field[row+1][col+2] = -1;
            field[row+2][col] = -1;
            field[row+2][col+1] = -1;
            field[row+2][col+2] = -1;
            if (field[row+1][col] == 1)
                field[row+2][col] = 0;
        }
       else if ((row-2 < 0) && (col-2 < 0)){
            field[row][col+2] = -1;
            field[row+1][col-1] = -1;
            field[row+1][col+1] = -1;
            field[row+1][col+2] = -1;
            field[row+2][col-1] = -1;
            field[row+2][col] = -1;
            field[row+2][col+1] = -1;
            field[row+2][col+2] = -1;
            if (!(row-1 < 0)){
                field[row-1][col-1] = -1;
                field[row-1][col+1] = -1;
                field[row-1][col+2] = -1;
                if (field[row-1][col] == 1)
                    field[row+1][col] = 0;
                else if (field[row+1][col] == 1){
                    field[row-1][col] = 0;
                    field[row+2][col] = 0;
                }
            }
            if (field[row][col-1] == 1)
                field[row][col+1] = 0;
            else if (field[row][col+1] == 1) {
                field[row][col - 1] = 0;
                field[row][col + 2] = 0;
            }
            else if (field[row+1][col] == 1)
                field[row+2][col] = 0;
        }
       else if ((row-2 < 0) && (col < 7)){
            if (!(row-1 < 0)) {
                field[row - 1][col - 2] = -1;
                field[row - 1][col - 1] = -1;
                field[row - 1][col + 1] = -1;
                field[row - 1][col + 2] = -1;
                if (field[row-1][col] == 1)
                    field[row+1][col] = 0;
                else if (field[row+1][col] == 1){
                    field[row-1][col] = 0;
                    field[row+2][col] = 0;
                }
            }
            field[row][col-2] = -1;
            field[row][col+2] = -1;
            field[row+1][col-2] = -1;
            field[row+1][col-1] = -1;
            field[row+1][col+1] = -1;
            field[row+1][col+2] = -1;
            field[row+2][col-2] = -1;
            field[row+2][col-1] = -1;
            field[row+2][col] = -1;
            field[row+2][col+1] = -1;
            field[row+2][col+2] = -1;
           if (field[row][col-1] == 1) {
               field[row][col+1] = 0;
               field[row][col-2] = 0;
           }
           else if (field[row][col+1] == 1) {
               field[row][col-1] = 0;
               field[row][col+2] = 0;
           }
           else if (field[row+1][col] == 1)
               field[row+2][col] = 0;

        }
       else if ((row-2 < 0) && (col+2 > 8)){
            if (!(row-1 < 0)) {
                field[row - 1][col - 2] = -1;
                field[row - 1][col - 1] = -1;
                if (field[row-1][col] == 1)
                    field[row+1][col] = 0;
            }
            field[row][col-2] = -1;
            field[row+1][col-2] = -1;
            field[row+1][col-1] = -1;
            field[row+2][col-2] = -1;
            field[row+2][col-1] = -1;
            field[row+2][col] = -1;
            if (!(col+1 > 8)){
                if (!(row-1 < 0)) field[row-1][col+1] = -1;
                field[row+1][col+1] = -1;
                field[row+2][col+1] = -1;
                if (field[row][col+1] == 1)
                    field[row][col-1] = 0;
                else if (field[row][col-1] == 1){
                    field[row][col+1] = 0;
                    field[row][col-2] = 0;
                }
                if (field[row+1][col] == 1)
                    field[row-1][col] = 0;
            }
            if (field[row][col-1] == 1)
                field[row][col-2] = 0;
            else if (field[row+1][col] == 1)
                field[row+2][col] = 0;
        }

       //last col
       else if ((row < 7) && (col+2 > 8)){
            field[row-2][col-2] = -1;
            field[row-2][col-1] = -1;
            field[row-2][col] = -1;
            field[row-1][col-2] = -1;
            field[row-1][col-1] = -1;
            field[row][col-2] = -1;
            field[row+1][col-2] = -1;
            field[row+1][col-1] = -1;
            field[row+2][col-2] = -1;
            field[row+2][col-1] = -1;
            field[row+2][col] = -1;
            if (!(col+1 > 8)){
                field[row-2][col+1] = -1;
                field[row-1][col+1] = -1;
                field[row+1][col+1] = -1;
                field[row+2][col+1] = -1;
                if (field[row][col-1] == 1){
                    field[row][col+1] = 0;
                    field[row][col-2] = 0;
                }
            }
            if (field[row-1][col] == 1){
                field[row+1][col] = 0;
                field[row-2][col] = 0;
            }
            else if (field[row+1][col] == 1){
                field[row-1][col] = 0;
                field[row+2][col] = 0;
            }
            else if (field[row][col-1] == 1){
                field[row][col-2] = 0;
            }
        }

       //last 2 rows
       else if ((row+2 > 8) && (col+2 >8)){
            field[row-2][col-2] = -1;
            field[row-2][col-1] = -1;
            field[row-2][col] = -1;
            field[row-1][col-2] = -1;
            field[row-1][col-1] = -1;
            field[row][col-2] = -1;
            if (!(row+1 > 8)){
                field[row+1][col-2] = -1;
                field[row+1][col-1] = -1;
                if (field[row+1][col] == 1)
                    field[row-1][col] = 0;
                else if (field[row-1][col] == 1){
                    field[row+1][col] = 0;
                    field[row-2][col] = 0;
                }
            }
            if (!(col+1 > 8)){
                field[row-2][col+1] = -1;
                field[row-1][col+1] = -1;
                if (!(row+1 > 8)) field[row+1][col+1] = -1;
                if (field[row][col+1] == 1)
                    field[row][col-1] = 0;
                if (field[row][col-1] == 1){
                    field[row][col+1] = 0;
                    field[row][col-2] = 0;
                }
            }
            if (field[row-1][col] == 1)
                field[row-2][col] = 0;
            else if (field[row][col-1] == 1)
                field[row][col-2] = 0;
        }
       else if ((row+2 > 8) && (col-2 < 0)){
            field[row-2][col] = -1;
            field[row-2][col+1] = -1;
            field[row-2][col+2] = -1;
            field[row-1][col+1] = -1;
            field[row-1][col+2] = -1;
            field[row][col+2] = -1;
            if (!(row+1 > 8)){
                field[row+1][col+1] = -1;
                field[row+1][col+2] = -1;
                if (field[row+1][col] == 1)
                    field[row-1][col] = 0;
                else if (field[row-1][col] == 1){
                    field[row+1][col] = 0;
                    field[row-2][col] = 0;
                }
            }
            if (!(col-1 < 0)){
                field[row-2][col-1] = -1;
                field[row-1][col-1] = -1;
                if (!(row+1 > 8)) field[row+1][col-1] = -1;
                if (field[row][col-1] == 1){
                    field[row][col+1] = 0;
                }
                if (field[row][col+1] == 1){
                    field[row][col-1] = 0;
                    field[row][col+2] = 0;
                }
            }

            if (field[row][col+1] == 1)
                field[row][col+2] = 0;
            else if (field[row-1][col] == 1)
                field[row-2][col] = 0;
       }
       else if ((row+2 > 8) && (col < 7)){
            field[row-2][col-2] = -1;
            field[row-2][col-1] = -1;
            field[row-2][col] = -1;
            field[row-2][col+1] = -1;
            field[row-2][col+2] = -1;
            field[row-1][col-2] = -1;
            field[row-1][col-1] = -1;
            field[row-1][col+1] = -1;
            field[row-1][col+2] = -1;
            field[row][col-2] = -1;
            field[row][col+2] = -1;
            if (!(row+1 > 8)) field[row+1][col-1] = -1;
            if (!(row+1 > 8)){
                field[row+1][col-2] = -1;
                field[row+1][col-1] = -1;
                field[row+1][col+1] = -1;
                field[row+1][col+2] = -1;
                if (field[row+1][col] == 1)
                    field[row-1][col] = 0;
                if (field[row-1][col] == 1){
                    field[row+1][col] = 0;
                    field[row-2][col] = 0;
                }
            }

            if (field[row][col+1] == 1){
                field[row][col-1] = 0;
                field[row][col+2] = 0;
            }
            else if (field[row][col-1] == 1){
                field[row][col+1] = 0;
                field[row][col-2] = 0;
            }
            else if (field[row-1][col] == 1)
                field[row-2][col] = 0;
        }

       //first col
       else if ((row < 7) && (col-2 < 0)){
            field[row-2][col] = -1;
            field[row-2][col+1] = -1;
            field[row-2][col+2] = -1;
            field[row-1][col+1] = -1;
            field[row-1][col+2] = -1;
            field[row][col+2] = -1;
            field[row+1][col+1] = -1;
            field[row+1][col+2] = -1;
            field[row+2][col] = -1;
            field[row+2][col+1] = -1;
            field[row+2][col+2] = -1;
            if (!(col-1 < 0)){
                field[row-2][col-1] = -1;
                field[row-1][col-1] = -1;
                field[row+1][col-1] = -1;
                field[row+2][col-1] = -1;
                if (field[row][col-1] == 1)
                    field[row][col+1] = 0;
                else if (field[row][col+1] == 1){
                    field[row][col-1] = 0;
                    field[row][col+2] = 0;
                }
            }
            if (field[row-1][col] == 1){
                field[row+1][col] = 0;
                field[row-2][col] = 0;
            }
            else if (field[row+1][col] == 1){
                field[row-1][col] = 0;
                field[row+2][col] = 0;
            }
            else if (field[row][col+1] == 1)
                field[row][col+2] = 0;
        }

       //everything between
       else {
           field[row-2][col-2] = -1;
           field[row-2][col-1] = -1;
           field[row-2][col] = -1;
           field[row-2][col+1] = -1;
           field[row-2][col+2] = -1;
           field[row-1][col-2] = -1;
           field[row-1][col-1] = -1;
           field[row-1][col+1] = -1;
           field[row-1][col+2] = -1;
           field[row][col-2] = -1;
           field[row][col+2] = -1;
           field[row+1][col-2] = -1;
           field[row+1][col-1] = -1;
           field[row+1][col+1] = -1;
           field[row+1][col+2] = -1;
           field[row+2][col-2] = -1;
           field[row+2][col-1] = -1;
           field[row+2][col] = -1;
           field[row+2][col+1] = -1;
           field[row+2][col+2] = -1;
           if (field[row][col+1] == 1){
               field[row][col-1] = 0;
               field[row][col+2] = 0;
           }
           else if (field[row][col-1] == 1){
               field[row][col+1] = 0;
               field[row][col-2] = 0;
           }
           if (field[row+1][col] == 1){
               field[row-1][col] = 0;
               field[row+2][col] = 0;
           }
           else if (field[row-1][col] == 1){
               field[row+1][col] = 0;
               field[row-2][col] = 0;
           }
       }

   }

    private static void printField(){
       setup();
       System.out.println();
       System.out.println("   1  2  3  4  5  6  7  8  9");
       for (int i = 0; i < field.length; i++){
           switch (i) {
               case 0 -> System.out.print("A ");
               case 1 -> System.out.print("B ");
               case 2 -> System.out.print("C ");
               case 3 -> System.out.print("D ");
               case 4 -> System.out.print("E ");
               case 5 -> System.out.print("F ");
               case 6 -> System.out.print("G ");
               case 7 -> System.out.print("H ");
               case 8 -> System.out.print("I ");
           }
           for (int j = 0; j < field[0].length; j++){
               switch (field[i][j]) {
                   case 0 -> System.out.print(" ~ ");
                   case 1 -> System.out.print(" S ");
                   case -1 -> System.out.print(" n ");
               }
           }
           System.out.println();
       }
    }

    private static void whereIsShip(){

       for (int i = 0; i < field.length; i++){
           for (int j = 0; j < field[0].length; j++){
               if (field[i][j] == 1){
                   System.out.print("Ship at: ");
                   switch (i){
                       case 0 -> System.out.print("A ");
                       case 1 -> System.out.print("B ");
                       case 2 -> System.out.print("C ");
                       case 3 -> System.out.print("D ");
                       case 4 -> System.out.print("E ");
                       case 5 -> System.out.print("F ");
                       case 6 -> System.out.print("G ");
                       case 7 -> System.out.print("H ");
                       case 8 -> System.out.print("I ");
                   }
                   System.out.print((j+1));
               }
           }
       }
    }

    private static void setup(){
       for (Ship ship : ships){
           field[ship.getRow()][ship.getCol()] = 1;
       }
    }

    public static void main(String[] args) {
        addToField("f6");
        addToField("f5");
        addToField("f7");
        printField();

    }
}
