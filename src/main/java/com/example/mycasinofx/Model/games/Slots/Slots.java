package com.example.mycasinofx.Model.games.Slots;

import com.example.mycasinofx.Model.games.GameInterface;
import com.example.mycasinofx.Model.games.GameSetUpInterface;
import com.example.mycasinofx.Model.games.Games;
import com.example.mycasinofx.Model.games.Roulette.Roulette;
import com.example.mycasinofx.Model.games.Roulette.RouletteSetUp;
import com.example.mycasinofx.Model.player.Player;

import java.util.ArrayList;

public class Slots extends SlotsSetUp implements GameInterface {

    private final int ROUNDS = 50;
    private final ArrayList<Integer> startIndex;
    private final ArrayList<Integer> finishIndex;
    private ArrayList<ArrayList<Integer>> generalArray;
    public static Slots instanceSlots = null;


    private Slots() {
        startIndex = new ArrayList<>();
        finishIndex = new ArrayList<>();
        generalArray = null;
    }


    public static Slots getSlots() {
        if (instanceSlots == null) {
            instanceSlots = new Slots();
        }
        return instanceSlots;
    }



    @Override
    public Object checkWinner() {
        if (getGeneralArrayList().size() == 3) {
            int numb1, numb2, numb3;
            numb1 = getGeneralArrayList().get(0).get(finishIndex.get(0));
            numb2 = getGeneralArrayList().get(1).get(finishIndex.get(1));
            numb3 = getGeneralArrayList().get(2).get(finishIndex.get(2));
            System.out.println(""+ numb1+ " "+numb2+" "+ numb3);;
            return WinnerCombination.WinnerCombinationCheckingFor3(numb1, numb2, numb3);
        }
        return -1;
    }

    @Override
    public Object generateResult() {
        try {
            generalArray = (ArrayList<ArrayList<Integer>>) gameSetUp();
            for (int i = 0; i < getAMOUNT_COLUMNS(); i++) {
                int randomStart = (int) (Math.random() * getALL_AMOUNT() + 1);
                this.startIndex.add(randomStart);
                this.finishIndex.add((ROUNDS + randomStart) % getALL_AMOUNT());
            }
            return generalArray;
        } catch (Exception e){
            System.out.println(e);
        }
        finally {
            return null;
        }
    }


    public int getPrevIndexArray(ArrayList<ArrayList<Integer>> array, int col, int curIndex){
        if (curIndex != 0){
            return array.get(col).get(curIndex - 1);
        }
        return array.get(col).getLast();
    }

    public int getNextIndexArray(ArrayList<ArrayList<Integer>> array, int col, int curIndex){
        if (curIndex != (array.get(col).size()-1)){
            return array.get(col).get(curIndex + 1);
        }
        return array.get(col).get(0);
    }


    public ArrayList<ArrayList<Integer>> getGeneralArray() {
        return generalArray;
    }

    public ArrayList<Integer> getStartIndex() {
        return startIndex;
    }

    public ArrayList<Integer> getFinishIndex() {
        return finishIndex;
    }


    public void reset(){
        startIndex.clear();
        finishIndex.clear();
    }
}
