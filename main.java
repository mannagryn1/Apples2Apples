public class main {

    public static void main(String[] args) {
        DeckOfCards redApples = new DeckOfCards("redApples.txt");
        DeckOfCards greenApples = new DeckOfCards("greenApples.txt");
        StateObject stateObject = new StateObject(greenApples, redApples);

        GamePhaseManager GPM = new GamePhaseManager(stateObject);
        GPM.mainLoop();
    }
    
}
