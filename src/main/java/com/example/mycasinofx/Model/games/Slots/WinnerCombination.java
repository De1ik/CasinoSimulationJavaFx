package com.example.mycasinofx.Model.games.Slots;

/**
 * Provides methods to check for winning combinations in a game.
 */
public class WinnerCombination {

    /**
     * Checks for a winning combination in a game with three numbers.
     * The number represents the symbol that appeared as a result in each column of the slot game.
     * @param numb1 The first number.
     * @param numb2 The second number.
     * @param numb3 The third number.
     * @return The winning combination value if a winning combination is found, otherwise -1.
     */
    public static int WinnerCombinationCheckingFor3(int numb1, int numb2, int numb3){
        if (numb1 == numb2 && numb1 == numb3){
            switch (numb1){
                case 1:
                    return 100;
                case 2:
                    return 60;
                case 3:
                    return 30;
                case 4:
                    return 20;
                case 5:
                    return 3;
            }
        }
        return -1;
    }

}
