
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class PlayPhase extends GamePhase {

        @Override void execute(StateObject stateObject){

            ArrayList<PlayedApple> playedApples = new ArrayList<>() ;

            for (int i = 0 ; i < stateObject.getNumberOfPlayers() ; i++){
                if (!(stateObject.judgeIDGet() == i)){
                    stateObject.playerGet(i).play(playedApples);
                }
            }

            Random rnd = ThreadLocalRandom.current();
            for(int i = playedApples.size() -1 ; i > 0 ; i--) {
				int index = rnd.nextInt(i+1);
				PlayedApple a = playedApples.get(index); playedApples.set(index, playedApples.get(i)); playedApples.set(i, a); // SWAP
			}


            stateObject.playedApplesSet(playedApples);
        }
    
}
