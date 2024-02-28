import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack {

    private Deck deck;
    private ArrayList<Card> player;
    private ArrayList<Card> dealer;
    private Scanner kb;
    private int playerValue;
    public BlackJack(){
        deck = new Deck();
        player = new ArrayList<>();
        dealer = new ArrayList<>();
        kb = new Scanner(System.in);
    }
    public static void main(String[] args) {
        BlackJack game = new BlackJack();
        game.run();
    }

    private void run(){
        deck.shuffle();
        dealHands();
        playerTurn();
        dealerTurn();
    }

    private void dealHands() {
        player.add(deck.getCard());
        dealer.add(deck.getCard());
        player.add(deck.getCard());
        dealer.add(deck.getCard());

        System.out.println("Dealer Hand: \t" + "[?]" + " " + dealer.get(1));
        System.out.println("Player Hand: \t" + player.get(0) + " " + player.get(1));
    }

    private void playerTurn(){
        System.out.println();
        boolean turnEnd = false;
        String playerCards;
        while (!turnEnd) {
            System.out.println("[hit/stay]");
            String response = kb.nextLine();
            playerCards = " ";
            if (response.equals("hit")) {
                player.add(deck.getCard());
            } else if (response.equals("stay")) {
                turnEnd = true;
            }
            for(int i = 0; i < player.size(); i++){
                playerCards += " " + player.get(i);
            }
            if (calcBust()){
                turnEnd = true;
            }
            System.out.println("Player Cards:" + playerCards);
            System.out.println();
        }
    }

    private boolean calcBust(){
        playerValue = 0;
        int numOfAces= 0;
        boolean bust = false;
        for(int i = 0; i < player.size();i++){
            Card tempCard = player.get(i);
            if (tempCard.getNumber() == 1){
                numOfAces++;
            }
            playerValue += tempCard.getValue();
        }
        if(playerValue > 21){
            bust = true;
        }
        while(bust == true && numOfAces > 0){
            playerValue -= 10;
            numOfAces --;
            if (playerValue <= 21) {
                bust = false;
            }
        }
        return(bust);
    }

    private int dealerTurn(){
        int dealerValue;
        if(!calcBust()){
            dealerValue = 0;
            for(int i = 0; i < dealer.size();i++){
                Card tempCard = dealer.get(i);
                dealerValue += tempCard.getValue();
            }
            while(dealerValue < playerValue || dealerValue < 21){
                dealer.add(deck.getCard());
                dealerValue = 0;
                for(int i = 0; i < dealer.size();i++){
                    Card tempCard = dealer.get(i);
                    dealerValue += tempCard.getValue();
                }
            }
        }

    }

}
