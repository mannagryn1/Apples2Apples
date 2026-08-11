import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class OnlineClient {
    BufferedReader in;
    DataOutputStream out;
    ArrayList<String> hand;
    ArrayList<String> playedApples;
    Socket socket;
    Scanner input;
    LocalPlayer player;
    boolean gameEnded;

    public OnlineClient(String host, int port){
        player = new LocalPlayer(0);

        try{
            
        }
        catch(Exception e){
            System.out.println("Error connecting to host " + e);
        }
    }
}
