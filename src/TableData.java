import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;


public class TableData {
    private String first, second, third, fourth, fifth, sixth, seventh, eighth, ninth;
    private int one, two, three, four, five, six, seven, eight, nine;

    public TableData(String first, String second, String third, String fourth, String fifth, String sixth, String seventh, String eighth, String ninth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.fifth = fifth;
        this.sixth = sixth;
        this.seventh = seventh;
        this.eighth = eighth;
        this.ninth = ninth;
    }

    public TableData(int one, int two, int three, int four, int five, int six, int seven, int eight, int nine){
        this.one = one;
        this.two = two;
        this.three = three;
        this.four = four;
        this.five = five;
        this.six = six;
        this.seven = seven;
        this.eight = eight;
        this.nine = nine;
    }

    public ImageView getFirst() {
        ImageView img = new ImageView(new Image(first));
        img.setFitWidth(20); img.setFitHeight(20);
        img.setTranslateX(-5);
        return img;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public ImageView getSecond() {
        ImageView img = new ImageView(new Image(second));
        img.setFitWidth(20); img.setFitHeight(20);
        img.setTranslateX(-5);
        return img;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public ImageView getThird() {
        ImageView img = new ImageView(new Image(third));
        img.setFitWidth(20); img.setFitHeight(20);
        img.setTranslateX(-5);
        return img;
    }

    public void setThird(String third) {
        this.third = third;
    }

    public ImageView getFourth() {
        ImageView img = new ImageView(new Image(fourth));
        img.setFitWidth(20); img.setFitHeight(20);
        img.setTranslateX(-5);
        return img;
    }

    public void setFourth(String fourth) {
        this.fourth = fourth;
    }

    public ImageView getFifth() {
        ImageView img = new ImageView(new Image(fifth));
        img.setFitWidth(20); img.setFitHeight(20);
        img.setTranslateX(-5);
        return img;
    }

    public void setFifth(String fifth) {
        this.fifth = fifth;
    }

    public ImageView getSixth() {
        ImageView img = new ImageView(new Image(sixth));
        img.setFitWidth(20); img.setFitHeight(20);
        img.setTranslateX(-5);
        return img;
    }

    public void setSixth(String sixth) {
        this.sixth = sixth;
    }

    public ImageView getSeventh() {
        ImageView img = new ImageView(new Image(seventh));
        img.setFitWidth(20); img.setFitHeight(20);
        img.setTranslateX(-5);
        return img;
    }

    public void setSeventh(String seventh) {
        this.seventh = seventh;
    }

    public ImageView getEighth() {
        ImageView img = new ImageView(new Image(eighth));
        img.setFitWidth(20); img.setFitHeight(20);
        img.setTranslateX(-5);
        return img;
    }

    public void setEighth(String eighth) {
        this.eighth = eighth;
    }

    public ImageView getNinth() {
        ImageView img = new ImageView(new Image(ninth));
        img.setFitWidth(20); img.setFitHeight(20);
        img.setTranslateX(-5);
        return img;
    }

    public void setNinth(String ninth) {
        this.ninth = ninth;
    }

    public String getOne() {
        return switch (one) {
            case 1 -> "S";
            case -1 -> "n";
            default -> "~";
        };
    }

    public void setOne(int one) {
        this.one = one;
    }

    public String getTwo() {
        return switch (two) {
            case 1 -> "S";
            case -1 -> "n";
            default -> "~";
        };
    }

    public void setTwo(int two) {
        this.two = two;
    }

    public String getThree() {
        return switch (three) {
            case 1 -> "S";
            case -1 -> "n";
            default -> "~";
        };
    }

    public void setThree(int three) {
        this.three = three;
    }

    public String getFour() {
        return switch (four) {
            case 1 -> "S";
            case -1 -> "n";
            default -> "~";
        };
    }

    public void setFour(int four) {
        this.four = four;
    }

    public String getFive() {
        return switch (five) {
            case 1 -> "S";
            case -1 -> "n";
            default -> "~";
        };
    }

    public void setFive(int five) {
        this.five = five;
    }

    public String getSix() {
        return switch (six) {
            case 1 -> "S";
            case -1 -> "n";
            default -> "~";
        };
    }

    public void setSix(int six) {
        this.six = six;
    }

    public String getSeven() {
        return switch (seven) {
            case 1 -> "S";
            case -1 -> "n";
            default -> "~";
        };
    }

    public void setSeven(int seven) {
        this.seven = seven;
    }

    public String getEight() {
        return switch (eight) {
            case 1 -> "S";
            case -1 -> "n";
            default -> "~";
        };
    }

    public void setEight(int eight) {
        this.eight = eight;
    }

    public String getNine() {
        return switch (nine) {
            case 1 -> "S";
            case -1 -> "n";
            default -> "~";
        };
    }

    public void setNine(int nine) {
        this.nine = nine;
    }
}
