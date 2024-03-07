import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> deck;

    public Deck(){
        this.deck = new ArrayList<>();
        for(int suit = 0; suit <= 3; suit ++){
            for(int number = 1; number <= 13; number++){
                Card tempCard = new Card(number,suit);
                tempCard.setValue(setInitialValues(number));
                deck.add(tempCard);
            }
        }

    }

    public void shuffle(){
        Collections.shuffle(deck);
    }

    public void newDeck(){
        deck.clear();
        for(int suit = 0; suit <= 3; suit ++){
            for(int number = 1; number <= 13; number++){
                Card tempCard = new Card(number,suit);
                tempCard.setValue(setInitialValues(number));
                deck.add(tempCard);
            }
        }
    }
    public int getSize(){
        return deck.size();
    }

    public Card getCard(){
        if(!deck.isEmpty()) {
            return deck.remove(deck.size() - 1);
        }
        return null;
    }

    private int setInitialValues(int num){
        if (num == 1) {
            return 11;
        } else if (num >=2 && num < 11) {
            return num;
        } else if (num >= 11) {
            return 10;
        } else {
            return 0;
        }
    }

}
