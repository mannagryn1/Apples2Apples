
import java.util.ArrayList;
import java.util.Scanner;

public class LocalPlayer extends Player {
    
    public LocalPlayer(int playerID) {
        super(playerID);
    }

    public PlayedApple play(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Select a card from your hand to play by typing a number");

        int index = scanner.nextInt();
        System.out.println("You played the card '" + hand.get(index-1) + "'");

        String card = hand.get(index-1);
        removeCardFromHand(index-1); 
        PlayedApple apple = new PlayedApple(playerID, card);
        scanner.close();
        return apple;
    }   

    public PlayedApple judge(ArrayList<PlayedApple> playedApples){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select which card should win this round by typing a nunmber");

        int index = scanner.nextInt();
        System.out.println("You selected the card '" + playedApples.get(index-1).redApple + "' to win this round");

        scanner.close();
        return playedApples.get(index-1);
    }
    
}
