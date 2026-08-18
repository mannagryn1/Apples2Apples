package src.players;

import java.util.ArrayList;
import java.util.List;

import src.cards.PlayedApple;
import src.main.StateObject;

public class BotPlayer extends Player {

    public BotPlayer(int playerID) {
        super(playerID);
    }
    
    @Override public void play(List<PlayedApple> apples){
        String card = hand.get(0);
        removeCardFromHand(0);
        PlayedApple apple = new PlayedApple(playerID, card);
        apples.add(apple);
    }
    
    @Override public void judge(ArrayList<PlayedApple> playedApples, StateObject stateObject){
        stateObject.winningRedSet(playedApples.get(0));
    }
}
