import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack {

    private static Deck deck;
    private ArrayList<Card> player;
    private ArrayList<Card> dealer;
    private int bank;
    private static boolean gameEnd;
    private Scanner kb;
    private int betValue;
    public BlackJack(){
        deck = new Deck();
        player = new ArrayList<>();
        dealer = new ArrayList<>();
        kb = new Scanner(System.in);
        bank = 2000;
        gameEnd = false;
    }
    public static void main(String[] args) {
        BlackJack game = new BlackJack();
        while (!gameEnd) {
            if (deck.getSize() < 4) {
                System.out.println("Deck has run out of cards.");
                System.out.println("Shuffling...");
                System.out.println();
                deck.newDeck();
            } else {
                gameEnd = game.run();
            }
        }
        System.out.println("You have no more coins");
    }

    private boolean run(){
        playerBet();
        deck.shuffle();
        dealHands();
        int playerVal = playerTurn();
        int dealerVal = dealerTurn(playerVal);
        winOrLose(playerVal,dealerVal);
        clearHands();
        if (bank <= 0){
            return true;
        }
        return false;
    }
    //Method to DealHands
    private void dealHands() {
        player.add(deck.getCard());
        dealer.add(deck.getCard());
        player.add(deck.getCard());
        dealer.add(deck.getCard());

        System.out.println("Dealer Hand: \t" + "[?]" + " " + dealer.get(1));
        System.out.println("Player Hand: \t" + player.get(0) + " " + player.get(1));
    }

    private void playerBet(){
        boolean validResponse = false;
        System.out.println("How much coins do you want to bet?");
        System.out.println("Bank: " + bank);
        while (!validResponse){
            betValue = kb.nextInt();
            if (betValue > bank) {
                System.out.println("You don't have that much coins");
                System.out.println("Please choose a valid amount");
            } else {
                validResponse = true;
            }
        }
        kb.nextLine();
        System.out.println();
    }
    private int playerTurn(){
        System.out.println();
        boolean turnEnd = false;
        String playerCards;
        String response = "";
        while (!turnEnd) {
            System.out.println("[hit/stay]");
            response = kb.nextLine();
            playerCards = " ";
            if (response.equals("hit")) {
                player.add(deck.getCard());
            } else if (response.equals("stay")) {
                turnEnd = true;
            }
            for(int i = 0; i < player.size(); i++){
                playerCards += " " + player.get(i);
            }
            if (calcPlayerVal() > 21){
                turnEnd = true;
            }
            System.out.println("Player Hand:" + playerCards);
            System.out.println();
        }
        return calcPlayerVal();
    }

    private int calcPlayerVal(){
        int playerValue = 0;
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
        return(playerValue);
    }

    private int calcDealerVal(){
        int dealerValue = 0;
        int numOfAces= 0;
        boolean bust = false;
        for(int i = 0; i < dealer.size();i++){
            Card tempCard = dealer.get(i);
            if (tempCard.getNumber() == 1){
                numOfAces++;
            }
            dealerValue += tempCard.getValue();
        }
        if(dealerValue > 21){
            bust = true;
        }
        while(bust == true && numOfAces > 0){
            dealerValue -= 10;
            numOfAces --;
            if (dealerValue <= 21) {
                bust = false;
            }
        }
        return(dealerValue);
    }

    private int dealerTurn(int playerVal){
        int dealerValue = calcDealerVal();
        String dealerCards = " " + dealer.get(0) + " " + dealer.get(1);
        System.out.println("Dealer Hand:" + dealerCards);
        if(playerVal < 21){
            while(dealerValue < playerVal && dealerValue < 21){
                dealerCards = " ";
                dealer.add(deck.getCard());
                dealerValue = calcDealerVal();
                for(int i = 0; i < dealer.size(); i++){
                    dealerCards += " " + dealer.get(i);
                }
            }
        }
        System.out.println();
        System.out.println("Dealer Hand:" + dealerCards);
        return dealerValue;
    }

    private void winOrLose(int playerVal, int dealerVal){
        if (playerVal > 21){
            System.out.println("BUST!");
            bank -= betValue;
        } else if (playerVal == dealerVal){
            System.out.println("PUSH!");
        } else if (dealerVal > 21 || dealerVal < playerVal){
            System.out.println("WIN!");
            bank += betValue;
        } else if (playerVal < dealerVal) {
            System.out.println("LOSE!");
            bank -= betValue;
        }
    }

    private void clearHands(){
        player.clear();
        dealer.clear();
    }
}
