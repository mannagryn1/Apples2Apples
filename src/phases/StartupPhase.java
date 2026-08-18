package src.phases;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import src.main.StateObject;
import src.players.BotPlayer;

public class StartupPhase extends GamePhase {
//created as class for consistency
// Should create players, fill up with botplayers
// give out hands, decide wincon etc.

private int minimumNumberOfPlayers = 4;

    @Override public void execute(StateObject stateObject) {
        while (stateObject.getNumberOfPlayers() < minimumNumberOfPlayers) {
            stateObject.playersAdd(new BotPlayer(stateObject.getNumberOfPlayers())); //create botplayers until there are at least 4 players total
        }

        decideWincon(stateObject);      //decide how many won apples are needed to win based on amount of players

        stateObject.redApplesGet().shuffle();       //shuffle decks, must happen before drawing hands
        stateObject.greenApplesGet().shuffle();

        for( int i = 0 ; i < stateObject.getNumberOfPlayers() ; i++){
            while (stateObject.playerGet(i).getHand().size() < 7){ //loop through all players and keep drawing until each have 7 cards on hand
                stateObject.playerGet(i).drawCard(stateObject.redApplesGet().drawCard());
            }
        }

        Random rnd = ThreadLocalRandom.current();
        int judgeID = rnd.nextInt(stateObject.getNumberOfPlayers());
        stateObject.judgeIDSet(judgeID);            // randomize who starts as judge


    }

    private void decideWincon(StateObject stateObject){  //decide number of rounds won needed to win the game based on number of players in the game. Numbers lifted from the rules of the game
        switch(stateObject.getNumberOfPlayers()){
            case 4:
                stateObject.winconSet(8);
                break;
            case 5:
                stateObject.winconSet(7);
                break;
            case 6:
                stateObject.winconSet(6);
                break;
            case 7:
                stateObject.winconSet(5);
                break;
            default:
                stateObject.winconSet(4);  //wincondition for 8 or more players
        }
    }
    
}
