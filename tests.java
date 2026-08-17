import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.*;

import java.lang.annotation.Target;
import java.util.ArrayList;

public class tests {

    void testReadDecks(){       // REQ 1 and 2
        Server server = new Server(0);
        DeckOfCards redApples = server.stateObject.redApplesGet();
        DeckOfCards greenApples = server.stateObject.greenApplesGet();

        assertTrue(!(redApples.getDeck().isEmpty()));
        assertTrue(!(greenApples.getDeck().isEmpty()));
    }

    void testshuffle(){         //REQ 3
        Server server = new Server(0);
        DeckOfCards redShuffled = server.stateObject.redApplesGet();
        DeckOfCards greenShuffled = server.stateObject.greenApplesGet();

        DeckOfCards redUnshuffled = new DeckOfCards("redApples.txt");
        DeckOfCards greenUnshuffled = new DeckOfCards("greenApples.txt");

        boolean greenMatch = true;
        boolean redMatch = true;

        while (!(redShuffled.getDeck().isEmpty() && greenShuffled.getDeck().isEmpty())){
            if(!(redShuffled.drawCard() == redUnshuffled.drawCard())){
                redMatch = false;
            }
            
            if(!(greenShuffled.drawCard() == greenUnshuffled.drawCard())){
                greenMatch = false;
            }

            if ((!greenMatch && !redMatch)){
                break;
            }
        }

        assertTrue(!greenMatch);
        assertTrue(!redMatch);
    }

    void testInitialDeal(){     //REQ 4
        Server server = new Server(0);
        server.GPM.startupPhase.execute(server.stateObject);

        boolean allPlayersHave7Cards = true;

        for (int i = 0 ; i < server.stateObject.getNumberOfPlayers() ; i++){
            if ((server.stateObject.playerGet(i).getHand().size() != 7)){
                allPlayersHave7Cards = false;
            }
        }

        assertTrue(allPlayersHave7Cards);
    }

    void testJudgeRandom(){     //REQ 5
        boolean differentJudges = false;

        Server firstServer = new Server(0);
        firstServer.GPM.startupPhase.execute(firstServer.stateObject);
        int firstJudge = firstServer.stateObject.judgeIDGet();

        for (int i = 0 ; i < 100 ; i++){
            Server newServer = new Server(0);
            newServer.GPM.startupPhase.execute(newServer.stateObject);
            int newJudge = newServer.stateObject.judgeIDGet();

            if (firstJudge != newJudge){
                differentJudges = true;
            }
        }

        assertTrue(differentJudges);

        //It is random who is judge, and thus this test might fail, but because we try 100 times, it should be fine
    }

    void testDrawGreenApple(){  //REQ 6
        Server server  = new Server(0);
        server.GPM.roundStartPhase.execute(server.stateObject);

        assertTrue(server.stateObject.greenAppleGet() != null);
        //stateObject.greenApple is initialized to null, so if it is not null after
        //we have run the roundStartPhase, it means a green apple has been dealt
        //and will be shown to everyone
    }

    void testPlayRed(){         //REQ 7 and 9
        Server server = new Server(0);
        server.GPM.startupPhase.execute(server.stateObject);
        server.GPM.playPhase.execute(server.stateObject);

        boolean correctNumberOfPlayedApples = (server.stateObject.playedApplesGet().size() == (server.stateObject.getNumberOfPlayers() - 1));

        assertTrue(correctNumberOfPlayedApples);
        //there should be one played apple fewer than total number of players, as the judge does not play
    }

    void testShufflePlayed(){   //REq 8
        Server server = new Server(0);
        server.GPM.startupPhase.execute(server.stateObject);
        server.GPM.playPhase.execute(server.stateObject);

        int firstPlayerID = server.stateObject.playedApplesGet().get(0).PlayerID;
        boolean notSameFirstPlayerID = false;

        for (int i = 0 ; i < 100 ; i++){
            server.GPM.startupPhase.execute(server.stateObject);
            server.GPM.playPhase.execute(server.stateObject);

            int newFirstPlayerID = server.stateObject.playedApplesGet().get(0).PlayerID;

            if(firstPlayerID != newFirstPlayerID){
                notSameFirstPlayerID = true;
                break;
            }
        }
        assertTrue(notSameFirstPlayerID);
    }

    // REQ 9 is already tested in testPlayRed()

    void testJudging(){         //REQ 10 and 14
        Server server = new Server(0);
        server.GPM.startupPhase.execute(server.stateObject);
        server.GPM.playPhase.execute(server.stateObject);
        server.GPM.judgePhase.execute(server.stateObject);

        assertTrue(server.stateObject.winningRedGet() != null); //initiated to null, if someone won it will no longer be null

        PlayedApple winningRed = server.stateObject.winningRedGet();
        Player winningPlayer = server.stateObject.playerGet(winningRed.PlayerID);
        
        boolean winningPlayerGotApple = false;
        for (int i = 0 ; i < winningPlayer.wonApples.size() ; i++){
            if (winningPlayer.wonApples.get(i) == winningRed.redApple){
                winningPlayerGotApple = true;
            }
        }

        assertTrue(winningPlayerGotApple);
    }

    void testDiscardAndNewDraw(){    //REQ 11 and 12
        Server server = new Server(0);
        server.GPM.startupPhase.execute(server.stateObject);
        server.GPM.roundStartPhase.execute(server.stateObject);

        boolean everyoneButJudgeHasPlayed = true;

        for (int i = 0 ; i < server.stateObject.getNumberOfPlayers() ; i ++){
            Player currentPlayer = server.stateObject.playerGet(i);

            if (currentPlayer.playerID != server.stateObject.judgeIDGet()){
                if (currentPlayer.hand.size() != 6){
                    everyoneButJudgeHasPlayed = false;
                }
            }
        }

        assertTrue(everyoneButJudgeHasPlayed);
        //checks so that everyone who has played a card loses that card
        server.GPM.roundEndPhase.execute(server.stateObject);
        boolean drawnNewCards = true;

        for (int i = 0 ; i < server.stateObject.getNumberOfPlayers() ; i ++){
            Player currentPlayer = server.stateObject.playerGet(i);

            if (currentPlayer.hand.size() != 7){
                drawnNewCards = false;
            }
        }

        assertTrue(drawnNewCards);
        //checks so that at the end of every round, everyone has 7 cards again
    }

    void testJudgeChange(){     //REQ 13
        Server server = new Server(0);
        server.GPM.startupPhase.execute(server.stateObject);
        int startingJudgeID = server.stateObject.judgeIDGet();

        server.GPM.roundStartPhase.execute(server.stateObject);
        int newJudgeID = server.stateObject.judgeIDGet();
        boolean correctJudgeSwitch = false;

        if(startingJudgeID < (server.stateObject.getNumberOfPlayers()-1)){
            if(newJudgeID == (startingJudgeID + 1)){
                correctJudgeSwitch = true;
            }
        }
        else if(startingJudgeID == (server.stateObject.getNumberOfPlayers()-1)){
            if(newJudgeID == 0){
                correctJudgeSwitch = true;
            }
        }

        assertTrue(correctJudgeSwitch);
        //who is judge is iterated in roundStartPhase, so if it has changed correctly after that phase has run, it works as it should
    }
    
    void testWinCon(){
        
    }
}
