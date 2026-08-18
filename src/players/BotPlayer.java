package src.players;

import java.util.ArrayList;
import java.util.List;

import src.cards.PlayedApple;
import src.main.StateObject;

public class BotPlayer extends Player {

    public BotPlayer(int playerID) {
        super(playerID);
    }
    
    @Override public void play(List<PlayedApple> apples) {  //always plays the first card from hand
        String card = hand.get(0);
        removeCardFromHand(0);
        PlayedApple apple = new PlayedApple(playerID, card);
        apples.add(apple);
    }
    
    @Override public void judge(ArrayList<PlayedApple> playedApples, StateObject stateObject) {  //as the played apples are shuffled before they are sent to the player, the bot player also always picks the first card to win each round
        stateObject.winningRedSet(playedApples.get(0));
    }
}
