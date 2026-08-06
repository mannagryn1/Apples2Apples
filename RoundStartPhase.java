public class RoundStartPhase extends GamePhase {
    //switch judge, present new green
    @Override void execute(StateObject stateObject){
        
        int judgeID = stateObject.judgeIDGet();

        if (judgeID >= stateObject.getNumberOfPlayers()){
            judgeID = 0;
        }

        String newGreen = stateObject.greenApplesGet().drawCard();

        stateObject.judgeIDSet(judgeID);
        stateObject.greenAppleSet(newGreen);

        for(int i = 0 ; i < stateObject.getNumberOfPlayers() ; i++){
            stateObject.playerGet(i).presentGreenApple(newGreen);
        }
    }
}
