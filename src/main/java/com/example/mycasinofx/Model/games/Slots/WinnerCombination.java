package com.example.mycasinofx.Model.games.Slots;

public class WinnerCombination {

    public static int WinnerCombinationCheckingFor3(int numb1, int numb2, int numb3){
        if (numb1 == numb2 && numb1 == numb3){
            switch (numb1){
                case 1:
                    return 1000;
                case 2:
                    return 500;
                case 3:
                    return 100;
                case 4:
                    return 25;
                case 5:
                    return 3;
            }
        }
        return -1;
    }

}
