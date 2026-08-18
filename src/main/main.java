package src.main;

public class main {

    public static void main(String[] args) {

        if (args.length == 0){
            System.out.println("Running the game as host with no online players");
            Server server =new Server(0);
            server.start();
        }
        else{
            try{
                int numberOfOnlinePlayers = Integer.parseInt(args[0]);
                System.out.println("Running the game as host with " + numberOfOnlinePlayers + " online players");
                Server server = new Server(numberOfOnlinePlayers);
                server.start();
            }
            catch(Exception e){
                System.out.println("Connecting to the host");
                OnlineClient client = new OnlineClient(args[0], 2048);
                client.gameLoop();
            }
        }
    }
    
}


//if args is empty, with 0 online players
//if theres just an int, run as host, with the int indicating number of online players
//if args is not parseable as an int, assume it is an ip address, run as client and connect to host
