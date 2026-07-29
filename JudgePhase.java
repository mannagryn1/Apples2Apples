public class JudgePhase extends GamePhase {
    // take input from judge, give winning apple to correct player, check against win condition

    @Override void execute(StateObject stateObject){
        int wincon = 4;

        for (int i = 0 ; i < stateObject.getNumberOfPlayers() ; i++){
            if (stateObject.playerGet(i).isJudge()){
                System.out.println("You are the judge");
            }
            else {
                System.out.println("Player " + stateObject.judgeIDGet() + " is judging");
            }
        }

        PlayedApple winningApple = stateObject.playerGet(stateObject.judgeIDGet()).judge(stateObject.playedApplesGet());
        //give winning player Apple here before next step
        if (stateObject.playerGet(winningApple.PlayerID).numberOfWonApples() >= wincon){
            System.out.println("Player " + winningApple.PlayerID + " has won the game!");
        }
    }
}
