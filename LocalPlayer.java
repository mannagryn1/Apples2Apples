
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LocalPlayer extends Player {
    
    public LocalPlayer(int playerID) {
        super(playerID);
    }

    @Override public void play(List<PlayedApple> apples){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
    System.out.println("\n**********************************************************************\n");
            System.out.println("Your hand is: ");

            boolean validCardSelected = false;
            String input = "";
            while(!validCardSelected){
                for(int i = 0 ; i < hand.size() ; i++){
                    System.out.println("(" + (i+1) + ")    " + hand.get(i));
                }
                System.out.println("\nPlease Select a card from your hand to play by typing a number");
        
                input = br.readLine();
                try{
                    if ((Integer.parseInt(input) <=7 ) && (Integer.parseInt(input) >= 1)){
                        validCardSelected = true;
                    }
                    else{
                        System.out.println("\nPlease enter a number between 1 and 7\n");
                    }
                }
                catch (NumberFormatException e){
                    System.out.println("\nPlease enter a valid number\n");
                }
            }
            int index = Integer.parseInt(input);
            System.out.println("\nYou played the card: " + hand.get(index-1) + "'");
 //           System.out.println("**********************************************************************");
            String card = hand.get(index-1);
            removeCardFromHand(index-1); 
            PlayedApple apple = new PlayedApple(playerID, card);
            apples.add(apple);
        }
        catch (Exception e){
            System.out.println("Something went wrong while LocalPlayer played a card" + e);
        }
    }   

    @Override public void judge(ArrayList<PlayedApple> playedApples, StateObject stateObject){

     System.out.println("\n**********************************************************************\n");
        System.out.println("You are the judge!");

        boolean checkValidInput = false;
        String input = "";

        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while(!checkValidInput){
                System.out.println("\nPlease select which card should win this round by typing a number");

                input = br.readLine();

                try{
                    if((Integer.parseInt(input) <= playedApples.size()) && (Integer.parseInt(input) >= 1)){
                        checkValidInput = true;
                    }
                    else{
                        System.out.println("\nPlease select one of the played cards\n");
                    }
                }
                catch(NumberFormatException e){
                    System.out.println("\nPlease enter a valid number\n");
                }
            }
            int index = Integer.parseInt(input);

            System.out.println("\nYou selected the card '" + playedApples.get(index-1).redApple + "' to win this round");
    //        System.out.println("**********************************************************************");
            stateObject.winningRedSet(playedApples.get(index-1));
        }
        catch (Exception e){
            System.out.println("Something went wrong while judging");
        }
   }

   @Override public void presentJudge(int i){
    System.out.println("\n**********************************************************************\n");
    System.out.println("Player " + i + " is judging!");
   }

   @Override public void presentPlayedApples(ArrayList<PlayedApple> apples){
    System.out.println("\n**********************************************************************\n");
    System.out.println("The played apples this round were: ");
    for (int i = 0 ; i < apples.size() ; i++){
        System.out.println("(" + (i+1) + ")    " + apples.get(i).redApple);
    }
   }

   @Override public void presentGreenApple(String apple){
    System.out.println("\n**********************************************************************\n");
    System.out.println("This round's green apple is: " + apple);
    
   }

   @Override public void presentWinningApple(PlayedApple winningRed){
    System.out.println("\nThe winning apple this round was " + winningRed.redApple + ". It was played by Player " + winningRed.PlayerID);
   }

   @Override public void presentWinner(int winnerID){
    System.out.println("\n**********************************************************************\n");
    System.out.println("Player " + winnerID + " has won the game!");
    System.out.println("\n**********************************************************************\n");
   }
}
