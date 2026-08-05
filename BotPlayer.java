
import java.util.ArrayList;

public class BotPlayer extends Player {

    public BotPlayer(int playerID) {
        super(playerID);
        this.isBot = true;
    }
    
    @Override public void play(ArrayList<PlayedApple> apples){
        String card = hand.get(0);
        removeCardFromHand(0);
        PlayedApple apple = new PlayedApple(playerID, card);
        apples.add(apple);
    }
    
    @Override public PlayedApple judge(ArrayList<PlayedApple> playedApples){
        return playedApples.get(0);
    }
}
