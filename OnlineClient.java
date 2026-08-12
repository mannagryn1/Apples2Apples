import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class OnlineClient {
    BufferedReader in;
    DataOutputStream out;
    ArrayList<String> hand;
    ArrayList<PlayedApple> playedApples;
    Socket socket;
    Scanner input;
    LocalPlayer player;
    boolean gameEnded;

    public OnlineClient(String host, int port){
        player = new LocalPlayer(0); //Local ID doesnt matter

        try{
            
        }
        catch(Exception e){
            System.out.println("Error connecting to host " + e);
        }
    }

    public void startGame(){
        while(!gameEnded){
            gameLoop();
        }
    }

    public void gameLoop(){
        try{
            String input = in.readLine();
            String split[] = input.split("[!!]");

            switch (split[0]){
                case "card":
                    this.hand.add(split[1]);
                case "play":
                    player.setHand(hand);
                    ArrayList<PlayedApple> apple = new ArrayList<PlayedApple>();
                    player.play(apple);
                    int index = -1;         // Initializes variable but making sure if something goes wrong in coming loop there will still be an error
                    for(int i = 0 ; i < this.hand.size() ; i++){
                        if(apple.get(0).redApple == this.hand.get(i)){
                            index = i;
                        }
                    }
                    out.writeInt(index);

                    while (this.hand.size() > 0){
                        this.hand.remove(0);        //cleans up local hand in preparation for next round
                    }
                case "judge":
                    StateObject stateObject = new StateObject(null, null);
                    player.judge(this.playedApples, stateObject);
                    int output = stateObject.winningRedGet().PlayerID;
                    out.writeInt(output);
                case "presentJudge":
                    int judgeID = Integer.parseInt(split[1]);
                    player.presentJudge(judgeID);
                case "playedCard":
                    PlayedApple newApple = new PlayedApple(playedApples.size(), split[1]);
                    this.playedApples.add(newApple);
                case "presentPlayedApples":
                    player.presentPlayedApples(playedApples);
                case "emptyPlayedApples":
                    while(this.playedApples.size() > 0){
                        this.playedApples.remove(0);
                    }
                case "win":
                    int winnerID = Integer.parseInt(split[1]);
                    player.presentWinner(winnerID);
                    this.gameEnded = true;
                default:

            }
        }
        catch(Exception e){
            System.out.println("Soemthing wewnt wrong in the gameloop " + e);
        }
    }
}
