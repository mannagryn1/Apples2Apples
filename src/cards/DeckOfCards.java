package src.cards;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DeckOfCards {
    private ArrayList<String> deck;

    public DeckOfCards(String file) {
        try{    
            this.deck = new ArrayList<>(Files.readAllLines(Paths.get(".java/src/cards/cardDecks/", file), StandardCharsets.ISO_8859_1));}
        catch(Exception e) {
            System.out.println("Something went wrong when creating a deck of cards with file " +e);
        }
    }

    public void shuffle() {
        Random rnd = ThreadLocalRandom.current();
        for(int i=deck.size()-1; i>0; i--) {
			int index = rnd.nextInt(i+1);
			String a = deck.get(index); 
            deck.set(index, deck.get(i)); 
            deck.set(i, a); // SWAP
		}
    }

    public String drawCard() {
        String card = this.deck.get(0);
        this.deck.remove(0);
        return card;
    }

    public ArrayList<String> getDeck() {
        return this.deck;
    }
}
