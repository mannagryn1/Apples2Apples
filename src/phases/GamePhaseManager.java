package src.phases;

import src.cards.DeckOfCards;
import src.main.StateObject;

public class GamePhaseManager {

    public StateObject stateObject;
    public DeckOfCards redApples;
    public DeckOfCards greenApples;
    public StartupPhase startupPhase;
    public RoundStartPhase roundStartPhase;
    public RoundEndPhase roundEndPhase;
    public JudgePhase judgePhase;
    public PlayPhase playPhase;


    public GamePhaseManager(StateObject stateObject){
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
