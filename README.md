<h2>Project description</h2>
The programme simulates various popular games from casinos: 21, roulette, slots.
Each game has its own unique logic for its implementation.
In each game to play the user must make a bet. The user can independently choose what bet on what he wants to make (within the rules: for example, the minimum bet can not be less than 5). Initially each new user is given 50000 game money on the game account. If the user has run out of game money on the game balance, it means he lost and more from this account he can not interact with the games. In this way the player's choice is realised.

<br>There are two types of voting in the programme: voting for the best game (which is already present) and voting for the game that players would like to see (thus voting gives the developers an idea how to improve their application and what users want). After voting, users can see the statistics of how other people voted and the number of votes for each category. If a user changes their mind, they can change their vote and vote again. 

Games and voting are only available to registered users. Registered users are stored in a database, so if a user already has an account, they can simply log in. 

<h2>What was used</h2>
<b>Technologies used:</b> JavaFx, MySQL, Google Cloud.
<br><b>Patterns that were used:</b> Singleton, Strategy, Observer, DAO.

<h2>Launching the application</h2>
<b>Start Class:</b> "Application" or "Main".
<br><b>Note:</b> Open Application with internet connection as there must be an internet connection to connect to the database.
