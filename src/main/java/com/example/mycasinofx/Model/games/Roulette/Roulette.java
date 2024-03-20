package com.example.mycasinofx.Model.games.Roulette;

import com.example.mycasinofx.Model.games.GameSetUpInterface;
import com.example.mycasinofx.Model.games.Games;
import com.example.mycasinofx.Model.player.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import static javafx.scene.input.KeyCode.R;

public class Roulette extends Games {
    private boolean isGamed;
    private boolean evenStakeSet = false;
    private boolean oddStakeSet;
    private boolean redStakeSet;
    private boolean blackStakeSet;
    private boolean greenStakeSet;
    private boolean exactNumber;
    private int resultNumber;

    public static Roulette instanceRoulette = null;

    private final String[] curStakes = new String[6];

    private ArrayList<Integer> exactNumberArray;
    private GameSetUpInterface gameSetUpInterface;
    private Player player;


    private Roulette() {
        gameSetUpInterface = new RouletteSetUp();
        isGamed = false;
        gameSetUpInterface.gameSetUp();
        resetCurStakes();
        player = Player.getPlayer();
        exactNumberArray = new ArrayList<>();

    }

    public static Roulette getRoulette() {
        if (instanceRoulette == null) {
            instanceRoulette = new Roulette();
        }
        return instanceRoulette;
    }


    @Override
    public Object checkWinner()  {
        int curGeneration = getResult();
        //check the colours
        String color = RouletteSetUp.getColours(curGeneration);
        if (isRedStakeSet() && color.equals("Red")) {
            player.setBalance(player.getBalance() + getMULTIPLIER2x() * player.getCurrentStake());
        }
        if (isBlackStakeSet() && color.equals("Black")) {
            player.setBalance(player.getBalance() + getMULTIPLIER2x() * player.getCurrentStake());
        }
        if (isGreenStakeSet() && color.equals("Green")) {
            player.setBalance(player.getBalance() + getMULTIPLIER36x() * player.getCurrentStake());
        }

        //check if some of exact number won
        if (checkInExactNumberArray(curGeneration)) {
            player.setBalance(player.getBalance() + getMULTIPLIER36x() * player.getCurrentStake());
        }

        //check even / odd
        if (isEvenStakeSet() && (curGeneration % 2 == 0)) {
            player.setBalance(player.getBalance() + player.getCurrentStake() * getMULTIPLIER2x());
        }
        if (isOddStakeSet() && (curGeneration % 2 == 1)) {
            player.setBalance(player.getBalance() + player.getCurrentStake() * getMULTIPLIER2x());
        }
        return null;
    }

    @Override
    public Object generateResult() {
        this.setGamed(true);
        this.resultNumber = (int) (Math.random() * 37);
        return null;
    }

//    public int getCurrentResult(){
//        return this.resultNumber;
//    }

    //------------------------------Array cur Stakes------------------------------------
    public String getCurStakes(int index) {
        return curStakes[index];
    }

    public void setCurStakes(String curStakes, int index) {
        this.curStakes[index] = curStakes;
    }

    public void resetCurStakes() {
        Arrays.fill(this.curStakes, null);
    }
    //----------------------------------------------------------------------------------

    //--------------------------------operations with Array-----------------------------
    public boolean isEmptyExactNumberArray() {
        return exactNumberArray.isEmpty();
    }

    public boolean checkInExactNumberArray(int number) {
        return exactNumberArray.contains(number);
    }

    public void addExactNumberArray(int number) {
        exactNumberArray.add(number);
    }

    public void removeExactNumberArray(int number) {
        try {
            exactNumberArray.remove((Integer) number);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void clearExactNumberArray() {
        exactNumberArray.clear();
    }

    //------------------------------------------------------------------------------


    //---------------------------generate the string with all selected numbers------------------------
    public String getStringExactNumber() {
        String res = "";
        for (Integer value : exactNumberArray) {
            res += " " + value + " ";
        }
        return res;
    }
    //----------------------------------------------------------------------------------------------


    //if player did the stake, but did not play and go to main menu, then return money
    public void returnStakeBeforeEnd() {
        if (!isGamed()) {
            System.out.println("RED:" + isRedStakeSet());
            if (isEvenStakeSet()) player.setBalance(player.getBalance() + player.getCurrentStake());
            if (isOddStakeSet()) player.setBalance(player.getBalance() + player.getCurrentStake());
            if (isRedStakeSet()) player.setBalance(player.getBalance() + player.getCurrentStake());
            if (isBlackStakeSet()) player.setBalance(player.getBalance() + player.getCurrentStake());
            if (isGreenStakeSet()) player.setBalance(player.getBalance() + player.getCurrentStake());
            if (!isEmptyExactNumberArray()) {
                double amount = exactNumberArray.size() * player.getCurrentStake();
                player.setBalance(player.getBalance() + amount);
            }
        }
        setGamed(false);
    }

    //If player did at least one stake, then he can start game
    public boolean checkStartGame() {
        return evenStakeSet || oddStakeSet || redStakeSet || blackStakeSet || greenStakeSet || exactNumber;
    }

    //set all boolean stakes to false
    public void resetBooleanStake() {
        evenStakeSet = false;
        redStakeSet = false;
        blackStakeSet = false;
        greenStakeSet = false;
        oddStakeSet = false;
        exactNumber = false;
    }

    //----------------------------------------Getters/Setters-------------------------------------------------
    public int getResult() {
        return resultNumber;
    }

    //----------------------------getters/setters of the access to start game---------------------------------
    public boolean isGamed() {
        return isGamed;
    }

    public void setGamed(boolean gamed) {
        isGamed = gamed;
    }

    //-----------------------------getters/setters of the boolean stakes---------------------------------------
    public boolean isEvenStakeSet() {
        return evenStakeSet;
    }

    public void setEvenStakeSet(boolean evenStakeSet) {
        this.evenStakeSet = evenStakeSet;
    }

    public boolean isOddStakeSet() {
        return oddStakeSet;
    }

    public void setOddStakeSet(boolean oddStakeSet) {
        this.oddStakeSet = oddStakeSet;
    }

    public boolean isRedStakeSet() {
        return redStakeSet;
    }

    public void setRedStakeSet(boolean redStakeSet) {
        this.redStakeSet = redStakeSet;
    }

    public boolean isBlackStakeSet() {
        return blackStakeSet;
    }

    public void setBlackStakeSet(boolean blackStakeSet) {
        this.blackStakeSet = blackStakeSet;
    }

    public boolean isGreenStakeSet() {
        return greenStakeSet;
    }

    public boolean isExactNumber() {
        return exactNumber;
    }

    public void setExactNumber(boolean exactNumber) {
        this.exactNumber = exactNumber;
    }

    public void setGreenStakeSet(boolean greenStakeSet) {
        this.greenStakeSet = greenStakeSet;
    }
}
