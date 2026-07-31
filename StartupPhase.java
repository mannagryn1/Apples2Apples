public class StartupPhase extends GamePhase {
//created as class for consistency
// Should create players, fill up with botplayers
// give out hands, decide wincon etc.
    @Override void execute(StateObject stateObject){
        while (stateObject.getNumberOfPlayers() < 4){
            stateObject.playersAdd(new BotPlayer(stateObject.getNumberOfPlayers()));
        }

        decideWincon(stateObject);

        

    }

    private void decideWincon(StateObject stateObject){
        switch(stateObject.getNumberOfPlayers()){
            case 4:
                stateObject.winconSet(8);
                break;
            case 5:
                stateObject.winconSet(7);
                break;
            case 6:
                stateObject.winconSet(6);
                break;
            case 7:
                stateObject.winconSet(5);
                break;
            default:
                stateObject.winconSet(4);
        }
    }
    
}
