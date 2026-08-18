package test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.Test;
import java.util.concurrent.ThreadLocalRandom;

import src.cards.DeckOfCards;
import src.cards.PlayedApple;
import src.main.Server;
import src.players.BotPlayer;
import src.players.Player;

public class tests {
    
    @Test
    public void testReadDecks(){       // REQ 1 and 2
        Server server = new Server(0);
        DeckOfCards redApples = server.stateObjectGet().redApplesGet();
        DeckOfCards greenApples = server.stateObjectGet().greenApplesGet();

        assertTrue(!(redApples.getDeck().isEmpty()));
        assertTrue(!(greenApples.getDeck().isEmpty()));
    }

    @Test
    public void testshuffle(){         //REQ 3
        Server server = new Server(0);
        DeckOfCards redShuffled = server.stateObjectGet().redApplesGet();
        DeckOfCards greenShuffled = server.stateObjectGet().greenApplesGet();

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

    @Test
    public void testInitialDeal(){     //REQ 4
        Server server = new Server(0);
        server.GPMGet().startupPhase.execute(server.stateObjectGet());

        boolean allPlayersHave7Cards = true;

        for (int i = 0 ; i < server.stateObjectGet().getNumberOfPlayers() ; i++){
            if ((server.stateObjectGet().playerGet(i).getHand().size() != 7)){
                allPlayersHave7Cards = false;
            }
        }

        assertTrue(allPlayersHave7Cards);
    }

    @Test
    public void testJudgeRandom(){     //REQ 5
        boolean differentJudges = false;

        Server firstServer = new Server(0);
        firstServer.GPMGet().startupPhase.execute(firstServer.stateObjectGet());
        int firstJudge = firstServer.stateObjectGet().judgeIDGet();

        for (int i = 0 ; i < 100 ; i++){
            Server newServer = new Server(0);
            newServer.GPMGet().startupPhase.execute(newServer.stateObjectGet());
            int newJudge = newServer.stateObjectGet().judgeIDGet();

            if (firstJudge != newJudge){
                differentJudges = true;
            }
        }

        assertTrue(differentJudges);

        //It is random who is judge, and thus this test might fail, but because we try 100 times, it should be fine
    }

    @Test
    public void testDrawGreenApple(){  //REQ 6
        //stateObject.greenApple is initialized to null, so if it is not null after
        //we have run the roundStartPhase, it means a green apple has been dealt
        //and will be shown to everyone
        Server server  = new Server(0);
        server.GPMGet().roundStartPhase.execute(server.stateObjectGet());

        assertTrue(server.stateObjectGet().greenAppleGet() != null);
    }

    @Test
    public void testPlayRed(){         //REQ 7 and 9
        //there should be one played apple fewer than total number of players, as the judge does not play
        Server server = new Server(0);
        server.GPMGet().startupPhase.execute(server.stateObjectGet());
        server.GPMGet().playPhase.execute(server.stateObjectGet());

        boolean correctNumberOfPlayedApples = (server.stateObjectGet().playedApplesGet().size() == (server.stateObjectGet().getNumberOfPlayers() - 1));

        assertTrue(correctNumberOfPlayedApples);
    }

    @Test
    public void testShufflePlayed(){   //REq 8
        Server server = new Server(0);
        server.GPMGet().startupPhase.execute(server.stateObjectGet());
        server.GPMGet().playPhase.execute(server.stateObjectGet());

        int firstPlayerID = server.stateObjectGet().playedApplesGet().get(0).PlayerID;
        boolean notSameFirstPlayerID = false;

        for (int i = 0 ; i < 100 ; i++){
            server.GPMGet().startupPhase.execute(server.stateObjectGet());
            server.GPMGet().playPhase.execute(server.stateObjectGet());

            int newFirstPlayerID = server.stateObjectGet().playedApplesGet().get(0).PlayerID;

            if(firstPlayerID != newFirstPlayerID){
                notSameFirstPlayerID = true;
                break;
            }
        }
        assertTrue(notSameFirstPlayerID);
    }

    // REQ 9 is already tested in testPlayRed()

    @Test
    public void testJudging(){         //REQ 10 and 14
        Server server = new Server(0);
        server.GPMGet().startupPhase.execute(server.stateObjectGet());
        server.GPMGet().playPhase.execute(server.stateObjectGet());
        server.GPMGet().judgePhase.execute(server.stateObjectGet());

        assertTrue(server.stateObjectGet().winningRedGet() != null); //initiated to null, if someone won it will no longer be null

        PlayedApple winningRed = server.stateObjectGet().winningRedGet();
        Player winningPlayer = server.stateObjectGet().playerGet(winningRed.PlayerID);
        
        boolean winningPlayerGotApple = false;
        for (int i = 0 ; i < winningPlayer.wonApplesGet().size() ; i++){
            if (winningPlayer.wonApplesGet().get(i) == winningRed.redApple){
                winningPlayerGotApple = true;
            }
        }

        assertTrue(winningPlayerGotApple);
    }

    @Test
    public void testDiscardAndNewDraw(){    //REQ 11 and 12
        Server server = new Server(0);
        server.GPMGet().startupPhase.execute(server.stateObjectGet());
        server.GPMGet().roundStartPhase.execute(server.stateObjectGet());
        server.GPMGet().playPhase.execute(server.stateObjectGet());

        boolean everyoneButJudgeHasPlayed = true;
        int correctHandSize = 7;

        for (int i = 0 ; i < server.stateObjectGet().getNumberOfPlayers() ; i ++){
            Player currentPlayer = server.stateObjectGet().playerGet(i);

            //checks so that everyone who has played a card loses that card            
            if (currentPlayer.playerIDGet() != server.stateObjectGet().judgeIDGet()){
                if (currentPlayer.getHand().size() != (correctHandSize - 1)){
                    everyoneButJudgeHasPlayed = false;
                }
            }
        }

        assertTrue(everyoneButJudgeHasPlayed);

        server.GPMGet().roundEndPhase.execute(server.stateObjectGet());
        boolean drawnNewCards = true;

        //checks so that at the end of every round, everyone has 7 cards again
        for (int i = 0 ; i < server.stateObjectGet().getNumberOfPlayers() ; i ++){
            Player currentPlayer = server.stateObjectGet().playerGet(i);

            if (currentPlayer.getHand().size() != correctHandSize){
                drawnNewCards = false;
            }
        }

        assertTrue(drawnNewCards);
        
    }

    @Test
    public void testJudgeChange(){     //REQ 13
        //who is judge is iterated in roundStartPhase, so if it has changed correctly after that phase has run, it works as it should
        Server server = new Server(0);
        server.GPMGet().startupPhase.execute(server.stateObjectGet());
        int startingJudgeID = server.stateObjectGet().judgeIDGet();

        server.GPMGet().roundStartPhase.execute(server.stateObjectGet());
        int newJudgeID = server.stateObjectGet().judgeIDGet();
        boolean correctJudgeSwitch = false;

        if(startingJudgeID < (server.stateObjectGet().getNumberOfPlayers()-1)){
            if(newJudgeID == (startingJudgeID + 1)){
                correctJudgeSwitch = true;
            }
        }
        else if(startingJudgeID == (server.stateObjectGet().getNumberOfPlayers()-1)){
            if(newJudgeID == 0){
                correctJudgeSwitch = true;
            }
        }

        assertTrue(correctJudgeSwitch);

    }
                        //create random number of players, minimum 4, and check that the win condition gets initialized correctly
    @Test    
    public void testWinCon(){       //REQ 14
        int minimumNumberOfPlayers = 4;
        int maximumNumberOfPlayers = 15;

        for (int i = 0 ; i < 20 ; i++){
            Server server = new Server(0);
            int randomNumberOfPlayers = ThreadLocalRandom.current().nextInt(minimumNumberOfPlayers, maximumNumberOfPlayers + 1);

            for (int j = 0 ; j < randomNumberOfPlayers ; j++){
                Player player = new BotPlayer(j);
                server.stateObjectGet().playersAdd(player);
            }

            server.GPMGet().startupPhase.execute(server.stateObjectGet());

            int currentWinCondition = server.stateObjectGet().winconGet();

            switch(server.stateObjectGet().getNumberOfPlayers()){
            case 4:
                assertTrue(currentWinCondition == 8);
                break;
            case 5:
                assertTrue(currentWinCondition == 7);
                break;
            case 6:
                assertTrue(currentWinCondition == 6);
                break;
            case 7:
                assertTrue(currentWinCondition == 5);
                break;
            default:
                assertTrue(currentWinCondition ==4);
            }
        }
    }
}
