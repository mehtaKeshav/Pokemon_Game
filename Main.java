/**
 * Dhruv Gorasiya, Jitsiripol Jason, Keshav Mehta
 * October 25, 2021
 * Description : This project mimics the game of pokemon where the player can explore around the map and fights with the wild pokemon that he/she encounters while travelling, in order to capture them. While their travel the player may experience events such as: interaction with a preson who gives essential item or damages the player, arrive a city and which enables him/her to access store or hospital, and find a random item on the map which is essential for the progress of the game. 
 */
import java.awt.*;
class Main {
  public static void main(String[] args) {
    System.out.println("Prof. Oak: Hello there new trainer, what is your name?");
    String trainer_name = CheckInput.getString();
    System.out.println("Great to meet you, " + trainer_name + ". Choose your first pokemon:\n1. Charmander\n2. Bulbasaur\n3. Squirtle");
    int pokemon_choice = CheckInput.getIntRange(1, 3);
    Pokemon first_pokemon;
    if (pokemon_choice == 1) { //for the user to choose the first pokemon 
      first_pokemon = new Charmander();
    }
    else if (pokemon_choice == 2) {
      first_pokemon = new Bulbasaur();
    }
    else {
      first_pokemon = new Squirtle();
    }

    Map map = new Map();
    int map_number = 1; //since the user will always start from the first map
    map.loadMap(map_number); //loads the map

    Trainer trainer = new Trainer(trainer_name, first_pokemon, map);

    boolean programRunning = true;
    while(programRunning){
      System.out.println(trainer.toString());
      boolean validDirection = false;
      while (validDirection == false){
        int direction = mainMenu();
        if (direction == 1){
          if (trainer.getLocation().x - 1  < 0) { //checks if the user can go in the north or not
            System.out.println("You cannot go that way.");
          } 
          else {
            validDirection = true;
            trainer.goNorth();
            
          }
        }
        else if (direction == 2){ 
          if (trainer.getLocation().x + 1  > 4) { //checks if the user can go in the south or not
            System.out.println("You cannot go that way.");
          } 
          else {
            validDirection = true;
            trainer.goSouth();
          }
        }
        else if (direction == 3){ 
          if (trainer.getLocation().y + 1  > 4) { //checks if the user can go in the east or not
            System.out.println("You cannot go that way.");
          } 
          else {
            validDirection = true;
            trainer.goEast();
          }
        }
        else if (direction == 4){
          if (trainer.getLocation().y - 1  < 0) { //checks if the user can go in the west or not
            System.out.println("You cannot go that way.");
          } 
          else {
            validDirection = true;
            trainer.goWest();
          }
        }
        else{
          System.out.println("Game Over");
          validDirection = true;
          programRunning = false;
        }
      }
      if (programRunning){ //if the user does not quit, the message and interactions will be done by the player depending on where he is on the

        //This part(line 76-151) is for the interaction that the player might make while playing the game whether with a wild pokemon, a person or a store 
        if (map.getCharAtLoc(trainer.getLocation()) == 'f'){ // when the player reaches 'f'(finish), next map in the sequence is loaded 
          System.out.println("You found the finish.");
          if (map_number == 1){
            map_number = 2;
            map.loadMap(map_number); 
          }
          else if (map_number == 2){ 
            map_number = 3;
            map.loadMap(map_number);
          }
          else{
            map_number = 1;
            map.loadMap(map_number);
          }
        }

        else if(map.getCharAtLoc(trainer.getLocation()) == 'i'){ //user gets the item when travelling in the map
          int item = (int)((Math.random()*(2-1 + 1)) + 1);
          if (item == 1){
            trainer.receivePotion();
          }
          else{
            trainer.recievePokeball();
          }
          map.removeCharAtLoc(trainer.getLocation());
        }

        else if(map.getCharAtLoc(trainer.getLocation()) == 'p'){ //user meets with the person while travelling in the map
          int interaction = (int)((Math.random()*(3-1 + 1)) + 1);
          if (interaction == 1){
            int item = (int)((Math.random()*(2-1 + 1)) + 1);
            if (item == 1){
              System.out.println("You ran into Brock and he gave you a potion.");
              trainer.receivePotion(); //receives potion
            }
            else{
              System.out.println("You ran into Brock and he gave you a pokeball.");
              trainer.recievePokeball(); //receives pokeball
            }
          }
          else if (interaction == 2){
            int amount = (int)((Math.random()*(7-5 + 1)) + 5);
            trainer.receiveMoney(amount); //receives money
            System.out.println("You ran into professor Oak and he gave you "+ amount + " dollars.");
          }
          else {
            int damage = (int)((Math.random()*(4-3 + 1)) + 3);
            trainer.takeDamage(damage); //user takes damage
            System.out.println("Team rocket ran into you to steal your pokemon, but to save it you took " + damage + " damage.");
          }
          map.removeCharAtLoc(trainer.getLocation()); //to remove the character 'p' from the map
        }
        
        else if(map.getCharAtLoc(trainer.getLocation()) == 'c'){ //user goes to a city
          System.out.println("You've entered the city.\nWhere would you like to go?\n1. Store\n2. Pokemon Hospital");
          int input = CheckInput.getIntRange(1,2);
          if (input == 1){
            store(trainer);
          }
          else{
            System.out.println("Hello! Welcome to the Pokemon Hospital.\nI'll fix your poor pokemon up in a jiffy!");
            trainer.healAllPokemon(); //heals all the pokemons
            System.out.println("There you go! See you again soon.");
          }
        }

        else if (map.getCharAtLoc(trainer.getLocation()) == 'w'){ //fights with the wild pokemon
          Pokemon wild_pokemon = chooseRandomPokemon();
          System.out.println("A wild " + wild_pokemon.getName() + " has appeared!");
          trainerAttack(trainer, wild_pokemon);
        }

        else{
          System.out.println("There is nothing there...");
        }
      }
    }
  }
  

  /** Description :  This method is for the user to buy some potions or pokeballs from the store that he visits while travelling through the map
   * @param t represents the trainer who is visiting the store to buy poekballs or potions 
   */
  public static void store(Trainer t){
    boolean inStore = true;
    boolean boughtItem = false; //checks if item the trainer successfully bought an item or not
    while (inStore){
      System.out.println("hello! What can I help you with?\n1. Buy Potions - $5\n2. Buy Poke Balls - $3\n3. Exit");
      int input = CheckInput.getIntRange(1, 3);
      if (input == 1) {
        boughtItem = t.spendMoney(5);
          if (boughtItem){
            t.receivePotion();
            System.out.println("Here's your Potion.");
          }
          else{
            System.out.println("You don't have enough money to buy that.");
        }
      }
      else if (input == 2){
        boughtItem = t.spendMoney(3);
        if (boughtItem){
          t.recievePokeball();
          System.out.println("Here's your Poke Ball.");
        }
        else{
          System.out.println("You don't have enough money to buy that.");
        }
      }
      else{
        inStore = false;
        System.out.println("Thank you, come again soon!");
      }
    }
  }

  /** Method for the user to make decision on which direction to go or to quit the game
   * @return returns the option number that the user has chose
   */
  public static int mainMenu(){
    System.out.println("Main Menu: ");
    System.out.println("1. Go North");
    System.out.println("2. Go South");
    System.out.println("3. Go East");
    System.out.println("4. Go West");
    System.out.println("5. Quit");

    int menu_input = CheckInput.getIntRange(1, 5);
    return menu_input;
  }

  /** This method generates random pokemons that the player might encounter while travelling through the map 
   * @return returns the random wild pokemon that the user is going to encounter while travelling in the map
   */
  public static Pokemon chooseRandomPokemon(){
    int randomPokemon = (int)((Math.random()*(6-1 + 1)) + 1);
    Pokemon p;
    if (randomPokemon == 1){
      p = new Charmander();
      return p;
    }
    else if (randomPokemon == 2){
      p = new Ponyta();
      return p;
    }
    else if (randomPokemon == 3){
      p  = new Squirtle();
      return p;
    }
    else if (randomPokemon == 4){
      p = new Staryu();
      return p;
    }
    else if (randomPokemon == 5){
      p = new Bulbasaur();
      return p;
    }
    else{
      p = new Oddish();
      return p;
    }
  }
  /**
   * Description: This fucntion allows the trainer to engage a fight with a wild pokemon by using his pokemon, use a portion to heal its pokemon while fighting, capture the wild pokemon with a pokeball, or run away bepending on the choice of the user. 
   * @param t trainer that uses its pokemon to engage in a fight with wild pokemon.
   * @param wild wild pokemon that had spawn on the map.
   */ 
  public static void trainerAttack( Trainer t, Pokemon wild){
    int choice = 0;
    while(choice != 4){
      System.out.println(wild.toString() + "\nWhat do you want to do?\n1. Fight\n2. Use Potion\n3. Throw Poke Ball\n4. Run Away\n");
      choice = CheckInput.getIntRange(1, 4);
      if(choice == 1){ // if the user chooses to fight.
        System.out.println("Choose a Pokemon:\n" + t.getPokemonList()); 
        Pokemon userPokemon = t.getPokemon(t.getNumPokemon()-1); // user's choice of pokemon to attack.
        System.out.println(userPokemon.getName() + ", I choose you!\n" + userPokemon.getAttackMenu()); 
        int userAttackType = userPokemon.getNumAttackMenuItems();
        if( userAttackType == 1){ // basic attack choice.
          System.out.println(userPokemon.getBasicMenu()); 
          int basicAttackType = userPokemon.getNumBasicMenuItems(); // choice of attack in basic attack 
          System.out.println(userPokemon.basicAttack(wild, basicAttackType));
        }
        else{ // special attack choice
          System.out.println( userPokemon.getSpecialMenu());
          int specialAttackType = userPokemon.getNumSpecialMenu(); // choice of attack in special attack
          System.out.println(userPokemon.specialAttack(wild, specialAttackType));
        }
        if(wild.getHp() != 0){ // wild pokemon attack
          int wildAttackType = (int)(Math.random()*(2-1 + 1) + 1); // randomly chooses to do special attack or basic attack
          if(wildAttackType == 1){
            int wildBasicType = (int)(Math.random()*(3-1 + 1) + 1); // randomly chooses an attack form basic attack.
            System.out.println("Your " + wild.basicAttack(userPokemon, wildBasicType));
          }
          else{
            int wildSpecialType = (int)(Math.random()*(3-1 + 1)+1);  // randomly chooses an attack form special attack.
            System.out.println(wild.specialAttack(userPokemon, wildSpecialType));
          }
        } 
      }
      else if(choice == 2){ 
        if(t.hasPotion()){
          System.out.println("Choose the pokemon to heal:");
          System.out.println(t.getPokemonList()); 
          int index = t.getNumPokemon() -1;
          Pokemon pokemonToHeal = t.getPokemon(index);
          if(pokemonToHeal.getMaxHp() != pokemonToHeal.getHp()){
            t.usePotion(index);
            System.out.println("Your pokemon has been healed!! ");
          }
          else{
            System.out.println("Cannot use portion. Your Pokemon is already with full health.");
          }
         
        }
        else{
          System.out.println("You do not have any potion.");
        }
        
      }
      else if(choice == 3){ // if the user chooses to throw pokeball.
        if(t.hasPokeball()){
          if(t.catchPokemon(wild)){
            System.out.println("Congratulations! You caught " + wild.getName() +".");
            choice = 4; // if the pokemon is caught the loop terminates by making choice 4 that is exit.
          }
          else{
            System.out.println("Attempt failed. "+ wild.getName() + " can still fight.");
          }
        }
        else{
          System.out.println("You are out of pokeballs.");
        }
      }
    }
  }
}
