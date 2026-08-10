import java.util.ArrayList;

abstract class Player {
    protected int playerID;
    protected ArrayList<String> hand;
    protected ArrayList<String> wonApples;
    protected boolean judge;

    public Player(int playerID){
        this.playerID = playerID;
        this.hand = new ArrayList<String>();
        this.wonApples = new ArrayList<String>();
    }

    public int getPlayerID(){
        return playerID;
    }

    public ArrayList<String> getHand(){
        return hand;
    }

    public void drawCard(String card){
        hand.add(card);
    }

    public void addWonApple(String apple){
        wonApples.add(apple);
    }

    public int numberOfWonApples(){
        return wonApples.size();
    }

    public void removeCardFromHand(int card){
        hand.remove(card);
    }

    public void play(ArrayList<PlayedApple> apples){

    }

    public void judge(ArrayList<PlayedApple> playedApples, StateObject stateObject){
    }

    public void playerXIsJudging(int i){
        
    }

    public void presentPlayedApples(ArrayList<PlayedApple> playedApples){

    }

    public void presentGreenApple(String greenApple){

    }

    public void presentWinningApple(PlayedApple winningRed){

    }

    public void presentWinner(int playerID){

    }
}