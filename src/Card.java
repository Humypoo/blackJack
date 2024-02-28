public class Card {
    int number;
    int value;
    int suit;
    String numName;
    String suitName;
    public Card(int number, int suit){
        this.number = number;
        this.suit = suit;
        setStrings();
    }

    public int getNumber() {
        return number;
    }

    public void setValue(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int getSuit() {
        return suit;
    }

    private void setStrings(){
        if (number == 1){
            numName = "A";
        } else if (number == 2){
            numName = "2";
        } else if (number == 3){
            numName = "3";
        } else if (number == 4){
            numName = "4";
        } else if (number == 5){
            numName = "5";
        } else if (number == 6){
            numName = "6";
        } else if (number == 7){
            numName = "7";
        } else if (number == 8){
            numName = "8";
        } else if (number == 9){
            numName = "9";
        } else if (number == 10){
            numName = "10";
        } else if (number == 11){
            numName = "J";
        } else if (number == 12){
            numName = "Q";
        } else if (number == 13){
            numName = "K";
        }

        if (suit == 0){
            suitName = "♥";
        } else if (suit == 1) {
            suitName = "♣";
        } else if (suit == 2) {
            suitName = "♦";
        } else if (suit == 3) {
            suitName = "♠";
        }
    }

    public String toString(){
        return numName + suitName;
    }
}
