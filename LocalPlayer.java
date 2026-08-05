
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class LocalPlayer extends Player {
    
    public LocalPlayer(int playerID) {
        super(playerID);
        this.isBot = false;
    }

    @Override public void play(ArrayList<PlayedApple> apples){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            System.out.println("**********************************************************************");
            System.out.println("Your hand is: ");

            for(int i = 0 ; i < hand.size() ; i++){
                System.out.println("(" + (i+1) + ")    " + hand.get(i));
            }
            System.out.println("\nPlease Select a card from your hand to play by typing a number");

            String input = br.readLine();
            int index = Integer.parseInt(input);
            System.out.println("You played the card '" + hand.get(index-1) + "'");
 //           System.out.println("**********************************************************************");
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

        System.out.println("**********************************************************************");
        System.out.println("You are the judge!");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("**********************************************************************");
        System.out.println("The played cards this round were: ");

        for(int i = 0 ; i < playedApples.size() ; i++) {
            System.out.println("(" + (i+1) + ")    " + playedApples.get(i).redApple);
        }
        System.out.println("\nPlease select which card should win this round by typing a nunmber");
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
//        System.out.println("**********************************************************************");
        return playedApples.get(index-1);
   }

   @Override public void playerXIsJudging(int i){
    System.out.println("**********************************************************************");
    System.out.println("Player " + i + " is judging!");
   }
}
