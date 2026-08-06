public class JudgePhase extends GamePhase {
    // take input from judge, give winning apple to correct player, check against win condition

    @Override void execute(StateObject stateObject){

        //System.out.println("**********************************************************************");
        //System.out.println("This rounds green apple was: " + stateObject.greenAppleGet());

        for (int i = 0 ; i < stateObject.getNumberOfPlayers() ; i++){
            if (stateObject.judgeIDGet() == i){
                continue;
            }
            else {
                stateObject.playerGet(i).playerXIsJudging(stateObject.judgeIDGet());
            }
        }

        for (int i = 0 ; i < stateObject.getNumberOfPlayers() ; i++){
            stateObject.playerGet(i).presentPlayedApples(stateObject.playedApplesGet());
        }

        PlayedApple winningApple = stateObject.playerGet(stateObject.judgeIDGet()).judge(stateObject.playedApplesGet());
        
        stateObject.playerGet(winningApple.PlayerID).addWonApple(winningApple.redApple);

        System.out.println("\nThe winning apple this round was " + winningApple.redApple);

        if(stateObject.judgeIDGet()<stateObject.getNumberOfPlayers()-1){
            stateObject.judgeIDSet((stateObject.judgeIDGet()+1));
        }
        else if(stateObject.judgeIDGet() == stateObject.getNumberOfPlayers()-1){
            stateObject.judgeIDSet(0);
        }
        else{
            System.out.println("Something went wrong in the assignment of the next judge");
        }

        if (stateObject.playerGet(winningApple.PlayerID).numberOfWonApples() >= stateObject.winconGet()){
            System.out.println("Player " + winningApple.PlayerID + " has won the game!");
            stateObject.gameEndedSetTrue();
        }
    }
}
