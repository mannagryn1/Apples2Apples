import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class StartupPhase extends GamePhase {
//created as class for consistency
// Should create players, fill up with botplayers
// give out hands, decide wincon etc.
    @Override void execute(StateObject stateObject){
        while (stateObject.getNumberOfPlayers() < 4){
            stateObject.playersAdd(new BotPlayer(stateObject.getNumberOfPlayers())); //create botplayers until there are at least 4 players total
        }

        decideWincon(stateObject);      //decide how many won apples are neede to win based on amount of players

        for( int i = 0 ; i < stateObject.getNumberOfPlayers() ; i++){
            while (stateObject.playerGet(i).hand.size() < 7){ //loop through all players and keep drawing until each have 7 cards on hand
                stateObject.playerGet(i).drawCard(stateObject.redApplesGet().drawCard());
            }
        }

        Random rnd = ThreadLocalRandom.current();
        int judgeID = rnd.nextInt(stateObject.getNumberOfPlayers());
        stateObject.judgeIDSet(judgeID);            // randomize who starts as judge

    }

    private void decideWincon(StateObject stateObject){
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
                stateObject.winconSet(4);
        }
    }
    
}
