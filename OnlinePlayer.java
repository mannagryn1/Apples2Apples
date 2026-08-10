import java.io.BufferedReader;
import java.io.DataOutputStream;

public class OnlinePlayer extends Player {

    public BufferedReader in;
    public DataOutputStream out;

    public OnlinePlayer(int playerID, BufferedReader in, DataOutputStream out) {
        super(playerID);
        this.in = in;
        this.out = out;
    }

    // needs methods for play() judge() various displays
    
}
