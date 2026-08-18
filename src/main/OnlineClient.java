package src.main;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import src.cards.PlayedApple;
import src.players.LocalPlayer;

public class OnlineClient {
    BufferedReader in;
    DataOutputStream out;
    ArrayList<String> hand;
    ArrayList<PlayedApple> playedApples;
    Socket socket;
    Scanner input;
    LocalPlayer player;
    boolean gameEnded = false;

    public OnlineClient(String host, int port) {
        player = new LocalPlayer(0); //Local ID doesnt matter
        this.hand = new ArrayList<String>();
        this.playedApples = new ArrayList<PlayedApple>();

        try{
            socket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());
        }
        catch(Exception e) {
            System.out.println("Error connecting to host " + e);
        }
    }

    public void startGame() {
        while(!gameEnded){
            gameLoop();
        }
        System.out.println("trace: exiting");
    }

    public void gameLoop() {
        while(!gameEnded) {
        try{
            //System.out.println("Waiting");
            String input = in.readLine();
            String split[] = input.split("#");  //splits incoming string into a key word for the switch case, and any relevant sent information

            switch (split[0]) {
                case "play":
                    player.setHand(hand);
                    //ArrayList<PlayedApple> apple = new ArrayList<PlayedApple>();
                    List<PlayedApple> apple = new ArrayList<PlayedApple>() {};
                    player.play(apple);
                    int index = -1;         // Initializes variable but making sure if something goes wrong in coming loop there will still be an error
                    for(int i = 0 ; i < this.hand.size() ; i++) {
                        if(apple.get(0).redApple.equals(this.hand.get(i))) {
                            index = i;
                        }
                    }
                    out.writeBytes(index + "\n");

                    while (this.hand.size() > 0) {
                        this.hand.remove(0);        //cleans up local hand in preparation for next round
                    }
                    break;
                case "card":
                    this.hand.add(split[1]);
                    //System.out.println("trace: client card = " + this.hand.get(this.hand.size()-1));
                    break;
                case "judge":
                    StateObject stateObject = new StateObject(null, null);
                    player.judge(this.playedApples, stateObject);
                    int output = stateObject.winningRedGet().PlayerID;
                    out.writeBytes(output + "\n");
                    break;
                case "presentJudge":
                    int judgeID = Integer.parseInt(split[1]);
                    player.presentJudge(judgeID);
                    break;
                case "playedCard":
                    PlayedApple newApple = new PlayedApple(playedApples.size(), split[1]);
                    this.playedApples.add(newApple);
                    break;
                case "presentPlayedApples":
                    player.presentPlayedApples(playedApples);
                    break;
                case "emptyPlayedApples":
                    while(this.playedApples.size() > 0) {
                        this.playedApples.remove(0);
                    }
                    break;
                case "win":
                    int winnerID = Integer.parseInt(split[1]);
                    player.presentWinner(winnerID);
                    this.gameEnded = true;
                    break;
                case "winningApple":
                    int appleID = Integer.parseInt(split[2]);
                    String winningRed = split[1];
                    PlayedApple winningApple = new PlayedApple(appleID, winningRed);
                    player.presentWinningApple(winningApple);
                    break;
                    case "greenApple":
                        player.presentGreenApple(split[1]);
                        break;
                default:
                    System.out.println("this wasnt supposed to happen... " + split[1]);
            }
        }

        catch(Exception e){
            System.out.println("Soemthing wewnt wrong in the gameloop " + e);
        }

        //System.out.println("trace: plsplsplsplspls");
    }
    }
}
