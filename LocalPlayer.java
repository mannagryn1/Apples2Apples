
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class LocalPlayer extends Player {
    
    public LocalPlayer(int playerID) {
        super(playerID);
    }

    @Override public void play(ArrayList<PlayedApple> apples){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please Select a card from your hand to play by typing a number");

            String input = br.readLine();
            int index = Integer.parseInt(input);
            System.out.println("You played the card '" + hand.get(index-1) + "'");

            String card = hand.get(index-1);
            removeCardFromHand(index-1); 
            PlayedApple apple = new PlayedApple(playerID, card);
            apples.add(apple);
        }
        catch (Exception e){
            System.out.println("Something went wrong while playing a card");
        }
    }   

    @Override public PlayedApple judge(ArrayList<PlayedApple> playedApples){

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please select which card should win this round by typing a nunmber");
        int index;
        try{
            String input = br.readLine();
            index = Integer.parseInt(input);
        }
        catch(Exception e){
            index = -1;
            System.out.println("Something went wrong while judging");
        }
        System.out.println("You selected the card '" + playedApples.get(index-1).redApple + "' to win this round");
        
        return playedApples.get(index-1);
   }
}
