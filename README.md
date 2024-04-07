Short Documentation for the first termin 

Start Class: "Application"

Inheritance is mainly used in models for games. There is a general abstract class "Games" from which classes of concrete games are inherited: "RouletteSetUp", "SlotsSetUp" and "TwentyOne". Double inheritance is present for classes "Roulette", "Slots", which inherit from "RouletteSetUp", "SlotsSetUp" respectively. 
It should be noted that at the moment the double inheritance may look a bit unnecessary, but it is necessary in the future possible expansion of the game functionality. Inheritance is also used in the DatabaseManage class, which inherits from the Config class (in this case there was not much point in adding inheritance, we could use a static class, but in any case inheritance is possible here).

Polymorphism is used much more often than inheritance, it is used in game classes ("Model" -> "Games" -> ["roulete", "Slots"]) to implement the interface "GameInterface". Polymorphism is also used to work with database in the DatabaseManager class ("Model" -> "database" -> "DatabaseManager"). In addition, polymorphism is used to implement the "RegistrationWarningsInterface" interface ("Controllers" -> "Registration" -> "Warnings"). Another place where polymorphism is used is the "SwitchPage" class which implements the "PageSwitchInterface" interface ("Controllers" -> "swithcPage" -> "SwithcPage"). These were not all places where polymorphism is used, but only examples.

Aggregation is used very often, so here are just some examples of classes where it is used: 
Roulette" class has an object of "Player" class inside it.
class "TwentyOne" has an object of class "Cards" inside it.
Almost every controller class has an object of class "SwitchPage", which is responsible for changing the scene.
