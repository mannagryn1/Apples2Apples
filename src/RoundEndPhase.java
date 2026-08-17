package src;
public class RoundEndPhase extends GamePhase {
    //non-judge players draw cards
    @Override public void execute(StateObject stateObject){
        for (int i = 0 ; i < stateObject.getNumberOfPlayers() ; i++){
            if (i != stateObject.judgeIDGet()){
                stateObject.playerGet(i).drawCard(stateObject.redApplesGet().drawCard());
            }
        }
    }
}
