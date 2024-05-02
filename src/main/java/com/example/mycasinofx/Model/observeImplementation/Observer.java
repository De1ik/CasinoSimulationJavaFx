package com.example.mycasinofx.Model.observeImplementation;

public class Observer {
    @FunctionalInterface
    public static interface ActionExecute{
        void execute (double number);
    }

    private ActionExecute function;

    public Observer(ActionExecute function){
        this.function = function;
    }

    public void inform(double newAmount){
        function.execute(newAmount);
    }


}
