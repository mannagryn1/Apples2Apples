public class PlayPhase extends GamePhase {

        @Override void execute(StateObject stateObject){
            System.out.println("This round's green apple is: " + stateObject.greenAppleGet());


        }

        private void play(Player player){
            
        }
    
}
