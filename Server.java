
public class Server {
    StateObject stateObject;
    GamePhaseManager GPM;
    int numberOfOnlinePlayers;
   // OnlineManager onlineManager;

    Server(int numberOfOnlinePlayers){
        DeckOfCards redApples = new DeckOfCards("redApples.txt");
        DeckOfCards greenApples = new DeckOfCards("greenApples.txt");
        this.stateObject = new StateObject(greenApples, redApples);
        this.GPM = new GamePhaseManager(stateObject);
        this.numberOfOnlinePlayers = numberOfOnlinePlayers;
    }

    public void start(){
        createHostPlayer();
        if(numberOfOnlinePlayers > 0){
            createOnlinePlayers(numberOfOnlinePlayers);
        }
        GPM.mainLoop();
    }

    void createHostPlayer(){
        Player hostPlayer = new LocalPlayer(0);
        stateObject.playersAdd(hostPlayer);
    }

    void createOnlinePlayers(int numberOfOnlinePlayers){
        OnlineManager onlineManager = new OnlineManager();
        onlineManager.createOnlinePlayers(numberOfOnlinePlayers, stateObject, 2048); 
    }
}
