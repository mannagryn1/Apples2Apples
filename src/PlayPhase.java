package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class PlayPhase extends GamePhase {

        @Override public void execute(StateObject stateObject){
            ExecutorService threadpool = Executors.newFixedThreadPool(stateObject.getNumberOfPlayers());

            List<PlayedApple> playedApplesMutex;
            playedApplesMutex = Collections.synchronizedList(new ArrayList<>());

            for (int i = 0 ; i < stateObject.getNumberOfPlayers() ; i++){
                if (!(stateObject.judgeIDGet() == i)){

                    Player currentPlayer = stateObject.playerGet(i);

                    Runnable task = new Runnable(){
                        @Override
                        public void run(){
                            currentPlayer.play(playedApplesMutex);
                        }
                    };
                    threadpool.execute(task);
                }
            }
            threadpool.shutdown();
            while(!threadpool.isTerminated()){
                try{
                    Thread.sleep(100);
                }
                catch(InterruptedException e){
                    System.out.println("sometihng happened: multithreading " + e);
            }

            ArrayList<PlayedApple> playedApples = new ArrayList<>(playedApplesMutex);
            Random rnd = ThreadLocalRandom.current();
            for(int i = playedApples.size() -1 ; i > 0 ; i--) {
				int index = rnd.nextInt(i+1);
				PlayedApple a = playedApples.get(index); playedApples.set(index, playedApples.get(i)); playedApples.set(i, a); // SWAP
			}


            stateObject.playedApplesSet(playedApples);
        }
    
    }
}