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

        stateObject.playerGet(stateObject.judgeIDGet()).judge(stateObject.playedApplesGet(), stateObject);
        // tells player w judgeID to run judge() w input playedApples and stateObject
        
        PlayedApple winningApple = stateObject.winningRedGet();
        
        stateObject.playerGet(winningApple.PlayerID).addWonApple(winningApple.redApple);

        for (int i = 0 ; i < stateObject.getNumberOfPlayers() ; i++){
            stateObject.playerGet(i).presentWinningApple(winningApple);
        }

        if (stateObject.playerGet(winningApple.PlayerID).numberOfWonApples() >= stateObject.winconGet()){
            for (int i = 0 ; i < stateObject.getNumberOfPlayers() ; i++){
                stateObject.playerGet(i).presentWinner(winningApple.PlayerID);
            }
            stateObject.gameEndedSetTrue();
        }
    }
}
