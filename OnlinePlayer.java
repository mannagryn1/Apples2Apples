import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.util.ArrayList;

public class OnlinePlayer extends Player {

    public BufferedReader in;
    public DataOutputStream out;

    public OnlinePlayer(int playerID, BufferedReader in, DataOutputStream out) {
        super(playerID);
        this.in = in;
        this.out = out;
    }

    // needs methods for play() judge() various displays to send to client
    // client will hold a LocalPlayer, so it needs to be wrapped up neatly 

    @Override public void play(ArrayList<PlayedApple> apples){
        try{
            for (int i = 0 ; i < hand.size() ; i ++){
                String card = ("card!" + this.hand.get(i));
                out.writeBytes(card + "\n");
                System.out.println("Trace: sent a card");
            }                                           //Doing it this way and trusting that the messages will arraive in the order they are sent :)
            out.writeBytes("play!\n");
        }
        catch(Exception e){
            System.out.println("Something went wrong while an online player was getting their hand " + e);
        }
        try{
            String input = in.readLine();
            int index = Integer.parseInt(input);
            String card = hand.get(index);
            removeCardFromHand(index);
            PlayedApple apple = new PlayedApple(playerID, card);
            apples.add(apple);
        }
        catch(Exception e){
            System.out.println("Something went wrong while parsing the input from an online player " + e);
            String card = hand.get(0);
            PlayedApple apple = new PlayedApple(playerID, card);
            apples.add(apple);
            removeCardFromHand(0);              //if client crashes, proceed as if botplayer
        }
    }

    @Override public void judge(ArrayList<PlayedApple> playedApples, StateObject stateObject){
        try{
            out.writeBytes("judge!\n");
        }
        catch(Exception e){
            System.out.println("Something went wrong while sending played apples " + e);
        }
        try{
            String input = in.readLine();
            int index = Integer.parseInt(input);
            stateObject.winningRedSet(playedApples.get(index));
        }
        catch(Exception e){
            System.out.println("Something went wrong while an online player was judging " + e);
            stateObject.winningRedSet(playedApples.get(0));         //if client crashes, proceed as if botplayer
        }
    }

    @Override public void presentJudge(int i){
        try{
            String judge = ("presentJudge!" + i);
            out.writeBytes(judge + "\n");
        }
        catch(Exception e){
            System.out.println("Something went wrong while presenting judge to online player " + e);
        }
    }

    @Override public void presentPlayedApples(ArrayList<PlayedApple> apples){
        try{
            out.writeBytes("emptyPlayedApples!\n");
            for (int i = 0 ; i < apples.size() ; i++){
                String card = ("playedCard!"+apples.get(i).redApple);
                out.writeBytes(card + "\n");
            }
            out.writeBytes("presentPlayedApples!\n");
        }
        catch(Exception e){
            System.out.println("Something went wrong while sending played cards " + e);
        }
    }

    @Override public void presentWinner(int winnerID){
        try{
            String winner = ("win!" + winnerID);
            out.writeBytes(winner + "\n");
        }
        catch(Exception e){
            System.out.println("Something went wrong while presenting winner to online player " + e);
        }
    }
}
