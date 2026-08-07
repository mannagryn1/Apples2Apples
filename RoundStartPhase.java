public class RoundStartPhase extends GamePhase {
    //switch judge, present new green
    @Override void execute(StateObject stateObject){
        
        if(stateObject.judgeIDGet()<stateObject.getNumberOfPlayers()-1){
            stateObject.judgeIDSet((stateObject.judgeIDGet()+1));
        }
        else if(stateObject.judgeIDGet() == stateObject.getNumberOfPlayers()-1){
            stateObject.judgeIDSet(0);
        }

        String newGreen = stateObject.greenApplesGet().drawCard();

        stateObject.greenAppleSet(newGreen);

        for(int i = 0 ; i < stateObject.getNumberOfPlayers() ; i++){
            stateObject.playerGet(i).presentGreenApple(newGreen);
        }
    }
}
