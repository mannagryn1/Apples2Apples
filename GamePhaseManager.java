
public class GamePhaseManager {

    StateObject stateObject;
    DeckOfCards redApples;
    DeckOfCards greenApples;

    GamePhaseManager(){
        try{
            this.redApples = new DeckOfCards("redApples.txt");
            this.greenApples = new DeckOfCards("greenApples.txt");
        }
        catch(Exception e){
            System.out.println("Something went wrong when creating the decks in GamePhaseManager");
        }

        this.stateObject = new StateObject(this.greenApples, this.redApples);
    }

    public void mainLoop(){
        
    }
    
}
