import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

   @Override
    public void start(Stage stage) throws Exception {
       BorderPane root = new BorderPane();
       TableView<TableData> table = createSetupForPlayer();
       TableView<TableData> tableStart = createTable();
       GridPane newSceneGrid = new GridPane();
       Button reset = new Button("Reset");
       reset.setOnAction(e -> {
           for (int i = 0; i < field.length; i++)
               for (int j = 0; j < field[0].length; j++)
                   field[i][j] = 0;

           shipPos = new int[9][2];
           ships = new ArrayList<>();
           table.setItems(getDataForSetup());
           table.refresh();
       });
       Button newScene = new Button("Start");
       newScene.setOnAction(actionEvent -> {
           if ((count1 == 2) && (count2 == 2) && (count3 == 1)) {
               BorderPane root1 = new BorderPane();
               root1.setCenter(tableStart);
               Scene scene2 = new Scene(root1, 200, 250);
               stage.setScene(scene2);
               stage.show();
           } else System.out.println("Incomplete board!");
       });
       newSceneGrid.addRow(0, newScene, reset);
       root.setCenter(table);
       root.setBottom(newSceneGrid);
       Scene scene = new Scene(root, 200, 265);
       stage.setScene(scene);
       stage.show();
    }

    static int[][] field =  {{0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}};
    static ArrayList<Ship> ships = new ArrayList<>();
    static int[][] shipPos = new int[9][2];
    static int count1, count2, count3;

    private static void addToField(int row, int col){
        //if you can place your ship
        if (field[row][col] == -1) {
            System.out.println("You can't place your ship here!");
            return;
        }
        else if (field[row][col] == 1){
            System.out.println("You already have ship here!");
            return;
        }
        //create ship
        if (((row > 2) && (field[row-3][col] == 1) && (field[row-2][col] == 1) && (field[row-1][col] == 1)) ||
                ((row < 6) && (field[row+3][col] == 1) && (field[row+2][col] == 1) && (field[row+1][col] == 1)) ||
                ((col > 2) && (field[row][col-3] == 1) && (field[row][col-2] == 1) && (field[row][col-1] == 1)) ||
                ((col < 6) && (field[row][col+3] == 1) && (field[row][col+2] == 1) && (field[row][col+1] == 1))) {
            System.out.println("You can't have bigger ship");
            return;
        }
        else if ((((row > 1) && (field[row-2][col] == 1) && (field[row-1][col] == 1)) && count3 > 0) ||
                (((row < 7) && (field[row+2][col] == 1) && (field[row+1][col] == 1)) && count3 > 0)) {
            System.out.println("You can't have more of this type!");
            return;
        }
        else if (((((col < 7) && (field[row][col+2] == 1) && (field[row][col+1] == 1)) && count3 > 0)) ||
                (((col > 1) && (field[row][col-2] == 1) && (field[row][col-1] == 1)) && count3 > 0)){
            System.out.println("You can't have more of this type!");
            return;
        }
        else if (((row > 0) && (field[row-1][col] == 1) && count2 > 1) ||
                ((row < 8) && (field[row+1][col] == 1) && count2 > 1) ||
                ((col > 0) && (field[row][col-1] == 1) && count2 > 1) ||
                ((col < 8) && (field[row][col+1] == 1) && count2 > 1)){
            System.out.println("You can't have more of this type!");
            return;
        }

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
            field[row][col+2] = -1;
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
            if (field[row][col-1] == 1){
                field[row][col+1] = 0;
                field[row][col-2] = 0;
            }
            if (field[row-1][col] == 1) field[row-2][col] = 0;
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
            if (field[row][col+1] == 1)
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

        setup();
        //col
        if ((col < 6) && (field[row][col+3] == 1)) field[row][col+1] = field[row][col+1]==1?1:-1;
        if ((col < 6) && (row > 2) && ((field[row-2][col+3] == 1) || (field[row-1][col+3] == 1))) field[row][col+1] = field[row][col+1]==1?1:-1;
        if ((col < 6) && (row < 7) && ((field[row+2][col+3] == 1) || (field[row+1][col+3] == 1))) field[row][col+1] = field[row][col+1]==1?1:-1;

        if ((col < 5) && (field[row][col+4] == 1)) field[row][col+2] = field[row][col+2]==1?1:-1;
        if ((col < 5) && (row > 2) && ((field[row-2][col+4] == 1) || (field[row-1][col+4] == 1))) field[row][col+2] = field[row][col+2]==1?1:-1;
        if ((col < 5) && (row < 7) && ((field[row+1][col+4] == 1) || (field[row+2][col+4] == 1))) field[row][col+2] = field[row][col+2]==1?1:-1;

        if ((col > 2) && (field[row][col-3] == 1)) field[row][col-1] = field[row][col-1]==1?1:-1;
        if ((col > 2) && (row > 2) && ((field[row-2][col-3] == 1) || (field[row-1][col-3] == 1))) field[row][col-1] = field[row][col-1]==1?1:-1;
        if ((col > 2) && (row < 7) && ((field[row+2][col-3] == 1) || (field[row+1][col-3] == 1))) field[row][col-1] = field[row][col-1]==1?1:-1;

        if ((col > 3) && (field[row][col-4] == 1)) field[row][col-2] = field[row][col-2]==1?1:-1;
        if ((col > 3) && (row > 2) && ((field[row-2][col-4] == 1) || (field[row-1][col-4] == 1))) field[row][col-2] = field[row][col-2]==1?1:-1;
        if ((col > 3) && (row < 7) && ((field[row+2][col-4] == 1) || (field[row+1][col-4] == 1))) field[row][col-2] = field[row][col-2]==1?1:-1;

        //rows
        if ((row < 6) && (field[row+3][col] == 1)) field[row+1][col] = field[row+1][col]==1?1:-1;
        if ((row < 6) && (col > 2) && ((field[row+3][col-2] == 1) || (field[row+3][col-1] == 1))) field[row+1][col] = field[row+1][col]==1?1:-1;
        if ((row < 6) && (col < 7) && ((field[row+3][col+1] == 1) || (field[row+3][col+2] == 1))) field[row+1][col] = field[row+1][col]==1?1:-1;

        if ((row < 5) && (field[row+4][col] == 1)) field[row+2][col] = field[row+2][col]==1?1:-1;
        if ((row < 5) && (col > 2) && ((field[row+4][col-2] == 1) || (field[row+4][col-1] == 1))) field[row+2][col] = field[row+2][col]==1?1:-1;
        if ((row < 5) && (col < 7) && ((field[row+4][col+1] == 1) || (field[row+4][col+2] == 1))) field[row+2][col] = field[row+2][col]==1?1:-1;

        if ((row > 2) && (field[row-3][col] == 1)) field[row-1][col] = field[row-1][col]==1?1:-1;
        if ((row > 2) && (col > 2) && ((field[row-3][col-2] == 1) || (field[row-3][col-1] == 1))) field[row-1][col] = field[row-1][col]==1?1:-1;
        if ((row > 2) && (col < 7) && ((field[row-3][col+1] == 1) || (field[row-3][col+2] == 1))) field[row-1][col] = field[row-1][col]==1?1:-1;

        if ((row > 3) && (field[row-4][col] == 1)) field[row-2][col] = field[row-2][col]==1?1:-1;
        if ((row > 3) && (col > 2) && ((field[row-4][col-2] == 1) || (field[row-4][col-1] == 1))) field[row-2][col] = field[row-2][col]==1?1:-1;
        if ((row > 3) && (col < 7) && ((field[row-4][col+1] == 1) || (field[row-4][col+2] == 1))) field[row-2][col] = field[row-2][col]==1?1:-1;
    }

    private static void scanField(){
        for (int i = 0; i < shipPos.length; i++){
            if ((i < shipPos.length - 1) && (shipPos[i][0] == shipPos[i + 1][0])) {
                if ((i < shipPos.length - 2) && (shipPos[i][0] == shipPos[i + 1][0]) && (shipPos[i][0] == shipPos[i + 2][0])) {
                    if ((shipPos[i][1] + 1 == shipPos[i + 1][1]) && (shipPos[i][1] + 1 == shipPos[i + 2][1] + 2)) {
                        count3 -= -1; //453
                        i -= -2;
                    } else if ((shipPos[i][1] + 1 == shipPos[i + 1][1]) && (shipPos[i][1] + 1 == shipPos[i + 2][1] - 1)) {
                        count3 -= -1; //345
                        i -= -2;
                    } else if ((shipPos[i][1] == shipPos[i + 1][1] + 1) && (shipPos[i][1] == shipPos[i + 2][1] + 2)) {
                        count3 -= -1; //543
                        i -= -2;
                    } else if ((shipPos[i][1] == shipPos[i + 1][1] + 1) && (shipPos[i][1] + 1 == shipPos[i + 2][1])) {
                        count3 -= -1; //435
                        i -= -2;
                    } else if ((shipPos[i][1] + 1 == shipPos[i + 1][1]) || (shipPos[i][1] == shipPos[i + 1][1] + 1)) {
                        count2 -= -1;
                        i++;
                    } else if (!((shipPos[i][0] == 0) && (shipPos[i][1] == 0)))
                        count1 -= -1;
                } else if ((shipPos[i][1] + 1 == shipPos[i + 1][1]) || (shipPos[i][1] == shipPos[i + 1][1] + 1)) {
                    count2 -= -1;
                    i++;
                }
            } else if ((i < shipPos.length - 1) && (shipPos[i][1] == shipPos[i + 1][1])) {
                if ((i < shipPos.length - 2) && (shipPos[i][1] == shipPos[i + 1][1]) && (shipPos[i][1] == shipPos[i + 2][1])) {
                    if ((shipPos[i][0] + 1 == shipPos[i + 1][0]) && (shipPos[i][0] + 1 == shipPos[i + 2][0] + 2)) {
                        count3 -= -1; //453
                        i -= -2;
                    } else if ((shipPos[i][0] + 1 == shipPos[i + 1][0]) && (shipPos[i][0] + 1 == shipPos[i + 2][0] - 1)) {
                        count3 -= -1; //345
                        i -= -2;
                    } else if ((shipPos[i][0] == shipPos[i + 1][0] + 1) && (shipPos[i][0] == shipPos[i + 2][0] + 2)) {
                        count3 -= -1; //543
                        i -= -2;
                    } else if ((shipPos[i][0] == shipPos[i + 1][0] + 1) && (shipPos[i][0] + 1 == shipPos[i + 2][0])) {
                        count3 -= -1; //435
                        i -= -2;
                    } else if ((shipPos[i][0] + 1 == shipPos[i + 1][0]) || (shipPos[i][0] == shipPos[i + 1][0] + 1)) {
                        count2 -= -1;
                        i++;
                    } else if (!((shipPos[i][0] == 0) && (shipPos[i][1] == 0)))
                        count1 -= -1;
                } else if ((shipPos[i][0] + 1 == shipPos[i + 1][0]) || (shipPos[i][0] == shipPos[i + 1][0] + 1)) {
                    count2 -= -1;
                    i++;
                } else if (!((shipPos[i][0] == 0) && (shipPos[i][1] == 0)))
                    count1 -= -1;
            } else if (!((shipPos[i][0] == 0) && (shipPos[i][1] == 0)))
                count1 -= -1;
        }
    }

    private static void setup(){
        int i = 0;
        for (Ship ship : ships){
            field[ship.getRow()][ship.getCol()] = 1;
            shipPos[i++] = new int[] {ship.getRow(), ship.getCol()};
        }
        count1 = 0; count2 = 0; count3 = 0;
        scanField();
    }

    private ObservableList<TableData> getDataForSetup(){
        ObservableList<TableData> data = FXCollections.observableArrayList();
        data.add(new TableData(field[0][0], field[0][1], field[0][2], field[0][3], field[0][4], field[0][5], field[0][6], field[0][7], field[0][8]));
        data.add(new TableData(field[1][0], field[1][1], field[1][2], field[1][3], field[1][4], field[1][5], field[1][6], field[1][7], field[1][8]));
        data.add(new TableData(field[2][0], field[2][1], field[2][2], field[2][3], field[2][4], field[2][5], field[2][6], field[2][7], field[2][8]));
        data.add(new TableData(field[3][0], field[3][1], field[3][2], field[3][3], field[3][4], field[3][5], field[3][6], field[3][7], field[3][8]));
        data.add(new TableData(field[4][0], field[4][1], field[4][2], field[4][3], field[4][4], field[4][5], field[4][6], field[4][7], field[4][8]));
        data.add(new TableData(field[5][0], field[5][1], field[5][2], field[5][3], field[5][4], field[5][5], field[5][6], field[5][7], field[5][8]));
        data.add(new TableData(field[6][0], field[6][1], field[6][2], field[6][3], field[6][4], field[6][5], field[6][6], field[6][7], field[6][8]));
        data.add(new TableData(field[7][0], field[7][1], field[7][2], field[7][3], field[7][4], field[7][5], field[7][6], field[7][7], field[7][8]));
        data.add(new TableData(field[8][0], field[8][1], field[8][2], field[8][3], field[8][4], field[8][5], field[8][6], field[8][7], field[8][8]));
        return data;
    }

    private ObservableList<TableData> getData(){
       ObservableList<TableData> data = FXCollections.observableArrayList();
       data.add(new TableData("X", "X", "X", "X", "X", "X", "X", "X", "X"));
       data.add(new TableData("X", "X", "X", "X", "X", "X", "X", "X", "X"));
       data.add(new TableData("X", "X", "X", "X", "X", "X", "X", "X", "X"));
       data.add(new TableData("X", "X", "X", "X", "X", "X", "X", "X", "X"));
       data.add(new TableData("X", "X", "X", "X", "X", "X", "X", "X", "X"));
       data.add(new TableData("X", "X", "X", "X", "X", "X", "X", "X", "X"));
       data.add(new TableData("X", "X", "X", "X", "X", "X", "X", "X", "X"));
       data.add(new TableData("X", "X", "X", "X", "X", "X", "X", "X", "X"));
       data.add(new TableData("X", "X", "X", "X", "X", "X", "X", "X", "X"));
       return data;
    }

    private TableView<TableData> createTable(){
        TableView<TableData> table = new TableView<>();
        table.setEditable(false);
        TextField text = new TextField();
        BorderPane root = new BorderPane();
        root.setTop(new Label("Game"));
        root.setBottom(text);

        TableColumn<TableData, String> firstCol = createCol("1", "first");
        TableColumn<TableData, String> secondCol = createCol("2", "second");
        TableColumn<TableData, String> thirdCol = createCol("3", "third");
        TableColumn<TableData, String> fourthCol = createCol("4", "fourth");
        TableColumn<TableData, String> fifthCol = createCol("5", "fifth");
        TableColumn<TableData, String> sixthCol = createCol("6", "sixth");
        TableColumn<TableData, String> seventhCol = createCol("7", "seventh");
        TableColumn<TableData, String> eightCol = createCol("8", "eighth");
        TableColumn<TableData, String> ninthCol = createCol("9", "ninth");


        table.setItems(getData());
        table.getColumns().addAll(firstCol, secondCol, thirdCol, fourthCol, fifthCol, sixthCol, seventhCol, eightCol, ninthCol);
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.getSelectionModel().setCellSelectionEnabled(true);
        table.setOnMouseClicked(mouseEvent -> {
            TablePosition tablePosition = table.getSelectionModel().getSelectedCells().get(0);
            int row = tablePosition.getRow();
            int col = tablePosition.getColumn();
            TableData td = table.getItems().get(table.getSelectionModel().getSelectedIndex());
            switch (col) {
                case 0 -> {
                    if (field[row][0] == 1)
                        td.setFirst("O");
                    else
                        td.setFirst("~");
                }
                case 1 -> {
                    if (field[row][1] == 1)
                        td.setSecond("O");
                    else
                        td.setSecond("~");
                }
                case 2 -> {
                    if (field[row][2] == 1)
                        td.setThird("O");
                    else
                        td.setThird("~");
                }
                case 3 -> {
                    if (field[row][3] == 1)
                        td.setFourth("O");
                    else
                        td.setFourth("~");
                }
                case 4 -> {
                    if (field[row][4] == 1)
                        td.setFifth("O");
                    else
                        td.setFifth("~");
                }
                case 5 -> {
                    if (field[row][5] == 1)
                        td.setSixth("O");
                    else
                        td.setSixth("~");
                }
                case 6 -> {
                    if (field[row][6] == 1)
                        td.setSeventh("O");
                    else
                        td.setSeventh("~");
                }
                case 7 -> {
                    if (field[row][7] == 1)
                        td.setEighth("O");
                    else
                        td.setEighth("~");
                }
                case 8 -> {
                    if (field[row][8] == 1)
                        td.setNinth("O");
                    else
                        td.setNinth("~");
                }
            }
            table.refresh();
        });
        return table;
    }

    private TableColumn<TableData, String> createCol(String text, String where){
        TableColumn<TableData, String> column = new TableColumn<>(text);
        column.setCellValueFactory(new PropertyValueFactory<>(where));
        column.setPrefWidth(20);
        column.setEditable(false);
        return column;
    }

    private TableView<TableData> createSetupForPlayer(){
        TableView<TableData> table = new TableView<>();
        table.setEditable(false);

        TableColumn<TableData, String> firstCol = createCol("1", "one");
        TableColumn<TableData, String> secondCol = createCol("2", "two");
        TableColumn<TableData, String> thirdCol = createCol("3", "three");
        TableColumn<TableData, String> fourthCol = createCol("4", "four");
        TableColumn<TableData, String> fifthCol = createCol("5", "five");
        TableColumn<TableData, String> sixthCol = createCol("6", "six");
        TableColumn<TableData, String> seventhCol = createCol("7", "seven");
        TableColumn<TableData, String> eightCol = createCol("8", "eight");
        TableColumn<TableData, String> ninthCol = createCol("9", "nine");

        table.setItems(getDataForSetup());
        table.getColumns().addAll(firstCol, secondCol, thirdCol, fourthCol, fifthCol, sixthCol, seventhCol, eightCol, ninthCol);
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.getSelectionModel().setCellSelectionEnabled(true);
        table.setOnMouseClicked(mouseEvent -> {
            TablePosition tablePosition = table.getSelectionModel().getSelectedCells().get(0);
            int row = tablePosition.getRow();
            int col = tablePosition.getColumn();
            addToField(row, col);

            table.setItems(getDataForSetup());
            table.refresh();
        });
       return table;
    }

    public static void main(String[] args) {
       launch(args);
    }

}
