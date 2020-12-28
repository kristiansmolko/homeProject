import javafx.application.Application;
import javafx.stage.Stage;

public class Main{ //extends Application {

   /*@Override
    public void start(Stage stage) throws Exception {

    }*/
   static int[][] field =  {{0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}};

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

       field[row][col] = 1;
        //1st and 2nd row
       if ((row-2 < 0) && (col-1 < 0)){
            if (!(row-1 < 0)) {
                field[row - 1][col] = -1;
                field[row - 1][col + 1] = -1;
                field[row - 1][col + 2] = -1;
            }
            field[row][col+1] = -1;
            field[row][col+2] = -1;
            field[row+1][col] = -1;
            field[row+1][col+1] = -1;
            field[row+1][col+2] = -1;
            field[row+2][col] = -1;
            field[row+2][col+1] = -1;
            field[row+2][col+2] = -1;
        }
        else if ((row-2 < 0) && (col-2 < 0)){
            field[row][col-1] = -1;
            field[row][col+1] = -1;
            field[row][col+2] = -1;
            field[row+1][col-1] = -1;
            field[row+1][col] = -1;
            field[row+1][col+1] = -1;
            field[row+1][col+2] = -1;
            field[row+2][col-1] = -1;
            field[row+2][col] = -1;
            field[row+2][col+1] = -1;
            field[row+2][col+2] = -1;
            if (!(row-1 < 0)){
                field[row-1][col-1] = -1;
                field[row-1][col] = -1;
                field[row-1][col+1] = -1;
                field[row-1][col+2] = -1;
            }
        }
        else if ((row-2 < 0) && (col < 7)){
            if (!(row-1 < 0)) {
                field[row - 1][col - 2] = -1;
                field[row - 1][col - 1] = -1;
                field[row - 1][col] = -1;
                field[row - 1][col + 1] = -1;
                field[row - 1][col + 2] = -1;
            }
            field[row][col-2] = -1;
            field[row][col-1] = -1;
            field[row][col+1] = -1;
            field[row][col+2] = -1;
            field[row+1][col-2] = -1;
            field[row+1][col-1] = -1;
            field[row+1][col] = -1;
            field[row+1][col+1] = -1;
            field[row+1][col+2] = -1;
            field[row+2][col-2] = -1;
            field[row+2][col-1] = -1;
            field[row+2][col] = -1;
            field[row+2][col+1] = -1;
            field[row+2][col+2] = -1;
        }
        else if ((row-2 < 0) && (col+2 > 8)){
            if (!(row-1 < 0)) {
                field[row - 1][col - 2] = -1;
                field[row - 1][col - 1] = -1;
                field[row - 1][col] = -1;
            }
            field[row][col-2] = -1;
            field[row][col-1] = -1;
            field[row+1][col-2] = -1;
            field[row+1][col-1] = -1;
            field[row+1][col] = -1;
            field[row+2][col-2] = -1;
            field[row+2][col-1] = -1;
            field[row+2][col] = -1;
            if (!(col+1 > 8)){
                if (!(row-1 < 0)) field[row-1][col+1] = -1;
                field[row][col+1] = -1;
                field[row+1][col+1] = -1;
                field[row+2][col+1] = -1;
            }
        }

        //last col
        else if ((row < 7) && (col+2 > 8)){
            field[row-2][col-2] = -1;
            field[row-2][col-1] = -1;
            field[row-2][col] = -1;
            field[row-1][col-2] = -1;
            field[row-1][col-1] = -1;
            field[row-1][col] = -1;
            field[row][col-2] = -1;
            field[row][col-1] = -1;
            field[row+1][col-2] = -1;
            field[row+1][col-1] = -1;
            field[row+1][col] = -1;
            field[row+2][col-2] = -1;
            field[row+2][col-1] = -1;
            field[row+2][col] = -1;
            if (!(col+1 > 8)){
                field[row-2][col+1] = -1;
                field[row-1][col+1] = -1;
                field[row][col+1] = -1;
                field[row+1][col+1] = -1;
                field[row+2][col+1] = -1;
            }
        }

        //last 2 rows
        else if ((row+2 > 8) && (col+2 >8)){
            field[row-2][col-2] = -1;
            field[row-2][col-1] = -1;
            field[row-2][col] = -1;
            field[row-1][col-2] = -1;
            field[row-1][col-1] = -1;
            field[row-1][col] = -1;
            field[row][col-2] = -1;
            field[row][col-1] = -1;
            if (!(row+1 > 8)){
                field[row+1][col-2] = -1;
                field[row+1][col-1] = -1;
                field[row+1][col] = -1;
            }
            if (!(col+1 > 8)){
                field[row-2][col+1] = -1;
                field[row-1][col+1] = -1;
                field[row][col+1] = -1;
                if (!(row+1 > 8)) field[row+1][col+1] = -1;
            }
        }
        else if ((row+2 > 8) && (col-2 < 0)){
            field[row-2][col] = -1;
            field[row-2][col+1] = -1;
            field[row-2][col+2] = -1;
            field[row-1][col] = -1;
            field[row-1][col+1] = -1;
            field[row-1][col+2] = -1;
            field[row][col+1] = -1;
            field[row][col+2] = -1;
            if (!(row+1 > 8)){
                field[row+1][col] = -1;
                field[row+1][col+1] = -1;
                field[row+1][col+2] = -1;
            }
            if (!(col-1 < 0)){
                field[row-2][col-1] = -1;
                field[row-1][col-1] = -1;
                field[row][col-1] = -1;
                if (!(row+1 > 8)) field[row+1][col-1] = -1;
            }
       }
        else if ((row+2 > 8) && (col < 7)){
            field[row-2][col-2] = -1;
            field[row-2][col-1] = -1;
            field[row-2][col] = -1;
            field[row-2][col+1] = -1;
            field[row-2][col+2] = -1;
            field[row-1][col-2] = -1;
            field[row-1][col-1] = -1;
            field[row-1][col] = -1;
            field[row-1][col+1] = -1;
            field[row-1][col+2] = -1;
            field[row][col-2] = -1;
            field[row][col-1] = -1;
            field[row][col+1] = -1;
            field[row][col+2] = -1;
            if (!(row+1 > 8)) field[row+1][col-1] = -1;
            if (!(row+1 > 8)){
                field[row+1][col-2] = -1;
                field[row+1][col-1] = -1;
                field[row+1][col] = -1;
                field[row+1][col+1] = -1;
                field[row+1][col+2] = -1;
            }
        }

        //first col
        else if ((row < 7) && (col-2 < 0)){
            field[row-2][col] = -1;
            field[row-2][col+1] = -1;
            field[row-2][col+2] = -1;
            field[row-1][col] = -1;
            field[row-1][col+1] = -1;
            field[row-1][col+2] = -1;
            field[row][col+1] = -1;
            field[row][col+2] = -1;
            field[row+1][col] = -1;
            field[row+1][col+1] = -1;
            field[row+1][col+2] = -1;
            field[row+2][col] = -1;
            field[row+2][col+1] = -1;
            field[row+2][col+2] = -1;
            if (!(col-1 < 0)){
                field[row-2][col-1] = -1;
                field[row-1][col-1] = -1;
                field[row][col-1] = -1;
                field[row+1][col-1] = -1;
                field[row+2][col-1] = -1;
            }
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
           field[row-1][col] = -1;
           field[row-1][col+1] = -1;
           field[row-1][col+2] = -1;
           field[row][col-2] = -1;
           field[row][col-1] = -1;
           field[row][col+1] = -1;
           field[row][col+2] = -1;
           field[row+1][col-2] = -1;
           field[row+1][col-1] = -1;
           field[row+1][col] = -1;
           field[row+1][col+1] = -1;
           field[row+1][col+2] = -1;
           field[row+2][col-2] = -1;
           field[row+2][col-1] = -1;
           field[row+2][col] = -1;
           field[row+2][col+1] = -1;
           field[row+2][col+2] = -1;
       }
   }

    private static void printField(){
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
                switch (field[i][j]){
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

    public static void main(String[] args) {
        addToField("c5");
        printField();

    }
}
