package src.main;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.*;

import src.players.OnlinePlayer;


public class OnlineManager {
    ServerSocket connection;

    public OnlineManager(int ServerSocket){
        try{
            this.connection = new ServerSocket(ServerSocket);
        }
        catch (Exception e){
            System.out.println("Something went wrong while connecting: " + e);
        }
    }

    public void createOnlinePlayers(int numberOfOnlinePlayers, StateObject stateObject){
        try{
            for (int i =1; i <= numberOfOnlinePlayers ; i++){
                Socket socket = connection.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                OnlinePlayer player = new OnlinePlayer(i, in, out);
                stateObject.playersAdd(player);
            }
        }
        catch(Exception e){
            System.out.println("Something went wrong while creating online players: " + e);
        }
    }

    public void connectionClose(){
        try {
            connection.close();
        }
        catch(Exception e){
            System.out.println("Something went wrong while closing the server");
        }
    }
}
