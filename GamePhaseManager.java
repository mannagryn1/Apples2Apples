
public class GamePhaseManager {

    StateObject stateObject;
    DeckOfCards redApples;
    DeckOfCards greenApples;
    StartupPhase startupPhase;
    RoundStartPhase roundStartPhase;
    RoundEndPhase roundEndPhase;
    JudgePhase judgePhase;
    PlayPhase playPhase;


    GamePhaseManager(StateObject stateObject){
        this.stateObject = stateObject;
        this.startupPhase = new StartupPhase();
        this.roundStartPhase = new RoundStartPhase();
        this.roundEndPhase = new RoundEndPhase();
        this.judgePhase = new JudgePhase();
        this.playPhase = new PlayPhase();
    }

    public void mainLoop(){
        startupPhase.execute(stateObject);

        while(!stateObject.gameEndedGet()){
            roundStartPhase.execute(stateObject);
            playPhase.execute(stateObject);
            judgePhase.execute(stateObject);
            roundEndPhase.execute(stateObject);
        }
    }
    
}
