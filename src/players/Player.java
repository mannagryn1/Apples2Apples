package src.players;
import java.util.ArrayList;
import java.util.List;

import src.cards.PlayedApple;
import src.main.StateObject;

public abstract class Player {
    protected int playerID;
    protected ArrayList<String> hand;
    protected ArrayList<String> wonApples;

    //An abstract class, so that all kinds of players (local, online, and bot) can be added to the same lists and iterated over, as well have the same methods called on them as needed. makes it easier everywhere else


    public Player(int playerID) {
        this.playerID = playerID;
        this.hand = new ArrayList<String>();
        this.wonApples = new ArrayList<String>();
    }

    public int getPlayerID() {
        return playerID;
    }

    public ArrayList<String> getHand() {
        return hand;
    }

    public void drawCard(String card) {
        hand.add(card); 
    }

    public void setHand(ArrayList<String> newHand) {
        this.hand = (ArrayList)newHand.clone();
    }

    public void addWonApple(String apple) {
        wonApples.add(apple);
    }

    public int numberOfWonApples() {
        return wonApples.size();
    }

    public void removeCardFromHand(int card) {
        hand.remove(card);
    }

    public void play(List<PlayedApple> apples) {

    }

    public void judge(ArrayList<PlayedApple> playedApples, StateObject stateObject) {
    }

    public void presentJudge(int i) {
        
    }

    public void presentPlayedApples(ArrayList<PlayedApple> playedApples) {

    }

    public void presentGreenApple(String greenApple) {

    }

    public void presentWinningApple(PlayedApple winningRed) {

    }

    public void presentWinner(int playerID) {

    }

    public ArrayList<String> wonApplesGet() {
        return this.wonApples;
    }

    public int playerIDGet() {
        return this.playerID;
    }
}