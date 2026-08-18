package src.main;
import java.util.ArrayList;

import src.cards.DeckOfCards;
import src.cards.PlayedApple;
import src.players.Player;

public class StateObject {

    private String greenApple; 
    private DeckOfCards greenApples;
    private PlayedApple playedRed;
    private DeckOfCards redApples;
    private PlayedApple winningRed;
    private ArrayList<Player> players;
    private boolean gameEnded;
    private int judgeID;
    private ArrayList<PlayedApple> playedApples;
    private int wincon;

    public StateObject(DeckOfCards greenApples, DeckOfCards redApples){
        this.greenApple = null;
        this.greenApples = greenApples;
        this.playedRed = null;
        this.redApples = redApples;
        this.winningRed = null;
        this.players = new ArrayList<Player>();
        this.gameEnded = false;
        this.judgeID = -1;                  //no player will have ID -1, it is a placeholder until the correct ID can be set
        this.playedApples = null;
        this.wincon = -1;
    }
    
    public void greenAppleSet(String greenApple){
        this.greenApple = greenApple;
    }

    public String greenAppleGet(){
        return this.greenApple;
    }

    public DeckOfCards greenApplesGet(){
        return this.greenApples;
    }

    public DeckOfCards redApplesGet(){
        return this.redApples;
    }

    public void playedRedSet(PlayedApple playedRed){
        this.playedRed = playedRed;
    }

    public PlayedApple playedRedGet(){
        return this.playedRed;
    }

    public void winningRedSet(PlayedApple winningRed){
        this.winningRed = winningRed;
    }

    public PlayedApple winningRedGet(){
        return this.winningRed;
    }

    public ArrayList<Player> playersGet(){
        return this.players;
    }

    public int getNumberOfPlayers(){
        return this.players.size();
    }

    public Player playerGet(int i){
        return this.players.get(i);
    }

    public void playersAdd(Player player){
        this.players.add(player);
    }

    public boolean gameEndedGet(){
        return gameEnded;
    }

    public void gameEndedSetTrue(){
        this.gameEnded = true;
    }

    public void gameEndedSetFalse(){
        this.gameEnded = false;             //should never need to be used
    }

    public int judgeIDGet(){
        return this.judgeID;
    }

    public void judgeIDSet(int judgeID){
        this.judgeID = judgeID;
    }

    public ArrayList<PlayedApple> playedApplesGet(){
        return this.playedApples;
    }

    public void playedApplesSet(ArrayList<PlayedApple> playedApples){
        this.playedApples = playedApples;
    }

    public void winconSet(int wincon){
        this.wincon = wincon;
    }

    public int winconGet(){
        return this.wincon;
    }
}
