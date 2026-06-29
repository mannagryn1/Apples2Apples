import java.util.ArrayList;

abstract class Player {
    private int playerID;
    private ArrayList<String> hand;
    private ArrayList<String> wonApples;
    private boolean judge;

    public Player(int playerID){
        this.playerID = playerID;
        this.wonApples = null;
        this.hand = null;
        this.judge = false;
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

    public void setJudge(boolean isJudge){
        judge = isJudge;
    }

    public boolean isJudge(){
        return judge;
    }

    public void removeCardFromHand(int card){
        hand.remove(card);
    }
}