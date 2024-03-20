package com.example.mycasinofx.Model.games;

import java.sql.SQLException;

public interface GameInterface {
    Object checkWinner() throws SQLException, ClassNotFoundException;
    Object generateResult();
}
