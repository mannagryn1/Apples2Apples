package src.phases;

import src.main.StateObject;

public class RoundStartPhase extends GamePhase {
    //switch judge, present new green
    @Override public void execute(StateObject stateObject){
        
        if(stateObject.judgeIDGet()<stateObject.getNumberOfPlayers()-1) { //rotate who is judge, starting over at 0 if last player has been reached 
            stateObject.judgeIDSet((stateObject.judgeIDGet()+1));
        }
        else if(stateObject.judgeIDGet() == stateObject.getNumberOfPlayers()-1) {
            stateObject.judgeIDSet(0);
        }

        String newGreen = stateObject.greenApplesGet().drawCard(); //draw and set the green prompt card for next round
        stateObject.greenAppleSet(newGreen);

        for(int i = 0 ; i < stateObject.getNumberOfPlayers() ; i++) {  //show all players the newley drawn green apple
            stateObject.playerGet(i).presentGreenApple(newGreen);
        }
    }
}
