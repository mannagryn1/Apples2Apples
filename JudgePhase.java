public class JudgePhase extends GamePhase {
    // take input from judge, give winning apple to correct player, check against win condition

    @Override void execute(StateObject stateObject){

        for (int i = 0 ; i < stateObject.getNumberOfPlayers() ; i++){
            if (stateObject.playerGet(i).isJudge()){
                System.out.println("You are the judge");
            }
            else {
                System.out.println("Player " + stateObject.judgeIDGet() + " is judging");
            }
        }

        PlayedApple winningApple = stateObject.playerGet(stateObject.judgeIDGet()).judge(stateObject.playedApplesGet());
        
        stateObject.playerGet(winningApple.PlayerID).addWonApple(winningApple.redApple);

        if (stateObject.playerGet(winningApple.PlayerID).numberOfWonApples() >= stateObject.winconGet()){
            System.out.println("Player " + winningApple.PlayerID + " has won the game!");
            stateObject.gameEndedSetTrue();
        }
    }
}
