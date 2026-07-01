
import java.util.ArrayList;

public class BotPlayer extends Player {

    public BotPlayer(int playerID) {
        super(playerID);
    }
    
    public PlayedApple play(){
        String card = hand.get(0);
        removeCardFromHand(0);
        PlayedApple apple = new PlayedApple(playerID, card);
        return apple;
    }
    
    public PlayedApple judge(ArrayList<PlayedApple> playedApples){
        return playedApples.get(0);
    }
}
