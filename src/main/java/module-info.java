module com.example.mycasinofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.mycasinofx to javafx.fxml;
    exports com.example.mycasinofx;
    exports com.example.mycasinofx.controllers;
    opens com.example.mycasinofx.controllers to javafx.fxml;
    exports com.example.mycasinofx.controllers.games.roulette;
    opens com.example.mycasinofx.controllers.games.roulette to javafx.fxml;
    exports com.example.mycasinofx.controllers.games.slots;
    opens com.example.mycasinofx.controllers.games.slots to javafx.fxml;
    exports com.example.mycasinofx.controllers.Registration;
    opens com.example.mycasinofx.controllers.Registration to javafx.fxml;
    exports com.example.mycasinofx.controllers.mainMenu;
    opens com.example.mycasinofx.controllers.mainMenu to javafx.fxml;
    exports com.example.mycasinofx.controllers.voting;
    opens com.example.mycasinofx.controllers.voting to javafx.fxml;
    exports com.example.mycasinofx.controllers.voting.votingProcces;
    opens com.example.mycasinofx.controllers.voting.votingProcces to javafx.fxml;
    exports com.example.mycasinofx.controllers.voting.resultVoting;
    opens com.example.mycasinofx.controllers.voting.resultVoting to javafx.fxml;
    exports com.example.mycasinofx.controllers.custom_dialog_stake;
    opens com.example.mycasinofx.controllers.custom_dialog_stake to javafx.fxml;
    exports com.example.mycasinofx.controllers.games.twentyOne;
    opens com.example.mycasinofx.controllers.games.twentyOne to javafx.fxml;
}