package src.phases;

import src.cards.PlayedApple;
import src.main.StateObject;

public class JudgePhase extends GamePhase {
    // take input from judge, give winning apple to correct player, check against win condition

    @Override public void execute(StateObject stateObject) {

        //System.out.println("**********************************************************************");
        //System.out.println("This rounds green apple was: " + stateObject.greenAppleGet());

        for (int i = 0 ; i < stateObject.getNumberOfPlayers() ; i++) {  //Tell all players who is the judge
            if (stateObject.judgeIDGet() == i){
                continue;
            }
            else {
                stateObject.playerGet(i).presentJudge(stateObject.judgeIDGet());
            }
        }

        for (int i = 0 ; i < stateObject.getNumberOfPlayers() ; i++) {   //show this rounds played apples to all players
            stateObject.playerGet(i).presentPlayedApples(stateObject.playedApplesGet());
        }

        stateObject.playerGet(stateObject.judgeIDGet()).judge(stateObject.playedApplesGet(), stateObject); // tells player w judgeID to run judge() w input playedApples and stateObject
        
        PlayedApple winningApple = stateObject.winningRedGet();
        stateObject.playerGet(winningApple.PlayerID).addWonApple(winningApple.redApple);

        for (int i = 0 ; i < stateObject.getNumberOfPlayers() ; i++) {    //show this rounds winning apple to all players
            stateObject.playerGet(i).presentWinningApple(winningApple);
        }

        if (stateObject.playerGet(winningApple.PlayerID).numberOfWonApples() >= stateObject.winconGet()) { //check if the player who won this round have enough won cards to win the game, in which case, end game and tell everyone who won
            for (int i = 0 ; i < stateObject.getNumberOfPlayers() ; i++) {
                stateObject.playerGet(i).presentWinner(winningApple.PlayerID);
            }
            stateObject.gameEndedSetTrue();
        }
    }
}
