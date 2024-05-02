package com.example.mycasinofx.Model.games.Slots;


import com.example.mycasinofx.Model.games.GameSetUpInterface;
import com.example.mycasinofx.Model.games.Games;

import java.util.ArrayList;
import java.util.Collections;

public class SlotsSetUp extends Games implements GameSetUpInterface {

    private final int ALL_AMOUNT = 60;
    private final int AMOUNT_COLUMNS = 3;
    private final int AMOUNT_1 = 2; //1000
    private final int AMOUNT_2 = 3; //500
    private final int AMOUNT_3 = 6; //100
    private final int AMOUNT_4 = 11; //25
    private final int AMOUNT_5 = 38; //3


    private ArrayList<Integer> column1 = new ArrayList<>();
    private ArrayList<Integer> column2 = new ArrayList<>();
    private ArrayList<Integer> column3 = new ArrayList<>();

    private ArrayList<ArrayList<Integer>> generalArrayList = new ArrayList<>();

    public SlotsSetUp() {
        ;
    }


    public void setColumns() {
        generalArrayList.add(column1);
        generalArrayList.add(column2);
        generalArrayList.add(column3);

        for (int col = 0; col < AMOUNT_COLUMNS; col++) {

            for (int row = 0; row < AMOUNT_1; row++) {
                generalArrayList.get(col).add(1);
            }

            for (int row = 0; row < AMOUNT_2; row++) {
                generalArrayList.get(col).add(2);
            }

            for (int row = 0; row < AMOUNT_3; row++) {
                generalArrayList.get(col).add(3);
            }

            for (int row = 0; row < AMOUNT_4; row++) {
                generalArrayList.get(col).add(4);
            }

            for (int row = 0; row < AMOUNT_5; row++) {
                generalArrayList.get(col).add(5);
            }
        }
    }


    public ArrayList<ArrayList<Integer>> shuffleSequence() {
        for (int col = 0; col < AMOUNT_COLUMNS; col++) {
            Collections.shuffle(generalArrayList.get(col));
        }
        return generalArrayList;
    }


    @Override
    public Object gameSetUp() {
        setColumns();
        return shuffleSequence();
    }


    public void reset(){
        column1.clear();
        column2.clear();
        column3.clear();
        generalArrayList.clear();
    }


    public int getALL_AMOUNT() {
        return ALL_AMOUNT;
    }

    public int getAMOUNT_COLUMNS() {
        return AMOUNT_COLUMNS;
    }



    public ArrayList<ArrayList<Integer>> getGeneralArrayList() {
        return generalArrayList;
    }
}
