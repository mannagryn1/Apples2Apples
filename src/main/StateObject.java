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

    //used to send various data between all the phases
    //this way, all phases can have the same input, and not return anything in particular, and can therefore be more uniform from the outside looking in

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

    // -------Getters------- //

    public String greenAppleGet() { //returns this rounds green apple
        return this.greenApple;
    }

    public DeckOfCards greenApplesGet() { //returns the deck of green apples
        return this.greenApples;
    }

    public DeckOfCards redApplesGet() { //returns deck of red apples
        return this.redApples;
    }

    public PlayedApple playedRedGet() { //returns local players played red apple
        return this.playedRed;
    }

    public PlayedApple winningRedGet() { //returns this rounds winning play
        return this.winningRed;
    }

    public ArrayList<Player> playersGet() { //returns arraylist containing all players
        return this.players;
    }

    public int getNumberOfPlayers() { //returns number of players in the game
        return this.players.size();
    }

    public Player playerGet(int i) { //returns player with specified index. players are added in order, thus the index will also be the playerID
        return this.players.get(i);
    }

    public boolean gameEndedGet() { //returns if the game has ended or not
        return gameEnded;
    }

    public int judgeIDGet() { //gets playerID and index for this rounds judge
        return this.judgeID;
    }

    public ArrayList<PlayedApple> playedApplesGet() { //returns all red apples played this round
        return this.playedApples;
    }

    public int winconGet() { //returns number of aplles won needed to win the game
        return this.wincon;
    }

    // -------Setters------- //
    
    public void greenAppleSet(String greenApple) { //set this rounds green apple
        this.greenApple = greenApple;
    }
 
    public void playedRedSet(PlayedApple playedRed) { //set localPlayers played red apple
        this.playedRed = playedRed;
    }

    public void winningRedSet(PlayedApple winningRed) { //set this rounds winning red apple
        this.winningRed = winningRed;
    }

    public void playersAdd(Player player) { //add Player to list of Players
        this.players.add(player);
    }

    public void gameEndedSetTrue() { //set game ended to true
        this.gameEnded = true;
    }

    public void gameEndedSetFalse() {
        this.gameEnded = false;             //should never need to be used, here for completeness
    }

    public void judgeIDSet(int judgeID) { //set to ID of player who is judge this round
        this.judgeID = judgeID;
    }

    public void playedApplesSet(ArrayList<PlayedApple> playedApples) { 
        this.playedApples = playedApples;
    }

    public void winconSet(int winCondition) { //set how many round wins are needed to win the game
        this.wincon = winCondition;
    }
}
