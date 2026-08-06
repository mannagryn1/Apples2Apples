import java.util.*; 
import java.nio.charset.StandardCharsets; 
import java.nio.file.*; 
import java.io.*; 
import java.net.*;
import java.util.concurrent.*;


public class OnlineManager {
    ServerSocket connection;

    public OnlineManager(){
    }

    public void createOnlinePlayers(int numberOfOnlinePlayers, StateObject stateObject, int socket){

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
