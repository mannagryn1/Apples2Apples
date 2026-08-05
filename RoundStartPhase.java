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

        System.out.println("**********************************************************************");
        System.out.println("This round's green apple is: " + stateObject.greenAppleGet());
    }
}
