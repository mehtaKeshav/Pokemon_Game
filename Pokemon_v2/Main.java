import java.util.ArrayList;

/**
 * Dhruv Gorasiya, Jason Jitsiripol, Keshav Mehta
 * November 30, 2021
 * Description : This project mimics the game of pokemon where the player can explore around the map and fights with the wild pokemon that he/she encounters while travelling, in order to capture them. While their travel the player may experience events such as: interaction with a preson who gives essential item or damages the player, arrive a city and which enables him/her to access store or hospital, and find a random item on the map which is essential for the progress of the game. 
 */

class Main {
  public static void main(String[] args) {
    PokemonGenerator poke_gen = PokemonGenerator.getInstance();
    System.out.println("Prof. Oak: Hello there new trainer, what is your name?");
    String trainer_name = CheckInput.getString();
    Pokemon first_pokemon = poke_gen.generateRandomPokemon(1);
    System.out.println("Great to meet you, " + trainer_name + ". Here is your first pokemon, " + first_pokemon.getName() + ".");

    Map map = new Map();
    int map_number = 1; //since the user will always start from the first map
    map.loadMap(map_number); //loads the map

    Trainer trainer = new Trainer(trainer_name, first_pokemon);
    int level = 1;
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
          System.out.println("Defeat gym pokemon to proceed to next level");
          Pokemon gym_pokemon = PokemonGenerator.getInstance().generateRandomPokemon(level + 2);
          ArrayList<Pokemon> fainted = new ArrayList<Pokemon>();
          while(fainted.size() != 6 && gym_pokemon.getHp() != 0){
            System.out.println("1.Fight\n2.Use Portion");
            int choice = CheckInput.getIntRange(1, 2);
            if(choice == 1){
              System.out.println(trainer.getPokemonList());
              int pokeChoice = CheckInput.getIntRange(1,trainer.getNumPokemon());
              Pokemon userPokemon = trainer.getPokemon(pokeChoice);
              if(userPokemon.getHp() != 0){
                System.out.println(userPokemon.getName() + ", I choose you!\n" + userPokemon.getAttackTypeMenu()); 
                int userAttackType = CheckInput.getIntRange(1, userPokemon.getNumAttackTypeMenuItems());
                System.out.println(userPokemon.getAttackMenu(userAttackType));
                int userAttackMove = CheckInput.getIntRange(1, userPokemon.getNumAttackMenuItems(userAttackType));
                System.out.println(userPokemon.attack(gym_pokemon,userAttackType,userAttackMove));
                int debuff_chance = (int)(Math.random()*(100-0 + 1) + 0);
                if (debuff_chance <= 25){
                  System.out.println("Your attak has debuffed the enemy pokemon.");
                  gym_pokemon = PokemonGenerator.getInstance().addRandomDebuff(gym_pokemon);
                }
              }
              else{
                System.out.println("You cannot fight with your" + userPokemon.getName() +", its HP is 0.");
              }
            }
          }
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
          level += 1;
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
          Pokemon wild_pokemon = PokemonGenerator.getInstance().generateRandomPokemon(level);
          boolean wild_battle = true;
          System.out.println("A wild " + wild_pokemon.getName() + " has appeared!");
          while (wild_battle) {
            int wild_choice;
            System.out.println(wild_pokemon.toString() + "\nWhat do you want to do?\n1. Fight\n2. Use Potion\n3. Throw Poke Ball\n4. Run Away\n");
            wild_choice = CheckInput.getIntRange(1, 4);
            if (wild_choice == 1) {
              trainerAttack(trainer, wild_pokemon);
            }
            else if (wild_choice == 2) {
              if(trainer.hasPotion()){
                System.out.println("Choose the pokemon to heal:");
                System.out.println(trainer.getPokemonList()); 
                int index = trainer.getNumPokemon() - 1;
                Pokemon pokemonToHeal = trainer.getPokemon(index);
                if (pokemonToHeal.getMaxHp() != pokemonToHeal.getHp() && pokemonToHeal.getHp() != 0){
                trainer.usePotion(index);
                System.out.println("Your pokemon has been healed!! ");
                }
              else {
                System.out.println("Cannot use potion on this pokemon.");
              }
            }
            
          }
          
        }
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

  /**
   * Description: This fucntion allows the trainer to engage a fight with a wild pokemon by using his pokemon, use a portion to heal its pokemon while fighting, capture the wild pokemon with a pokeball, or run away bepending on the choice of the user. 
   * @param t trainer that uses its pokemon to engage in a fight with wild pokemon.
   * @param wild wild pokemon that had spawn on the map.
   */ 
  // public static Pokemon trainerAttack( Trainer t, Pokemon wild){
  //   int choice = 0;
  //   while(choice != 4){
  //     System.out.println(wild.toString() + "\nWhat do you want to do?\n1. Fight\n2. Use Potion\n3. Throw Poke Ball\n4. Run Away\n");
  //     choice = CheckInput.getIntRange(1, 4);
  //     if(choice == 1){ // if the user chooses to fight.
  //       System.out.println("Choose a Pokemon:\n" + t.getPokemonList()); 
  //       Pokemon userPokemon = t.getPokemon(t.getNumPokemon()-1); // user's choice of pokemon to attack.
  //       if(userPokemon.getHp() != 0){
  //         System.out.println(userPokemon.getName() + ", I choose you!\n" + userPokemon.getAttackTypeMenu()); 
  //         int userAttackType = CheckInput.getIntRange(1, userPokemon.getNumAttackTypeMenuItems());
  //         System.out.println(userPokemon.getAttackMenu(userAttackType));
  //         int userAttackMove = CheckInput.getIntRange(1, userPokemon.getNumAttackMenuItems(userAttackType));
  //         System.out.println(userPokemon.attack(wild,userAttackType,userAttackMove));
  //         // if( userAttackType == 1){ // basic attack choice.
  //         //   System.out.println(userPokemon.getAttackMenu(userAttackType)); 
  //         //   int basicAttackType = CheckInput.getIntRange(1,3); // choice of attack in basic attack 
  //         //   System.out.println(userPokemon.attack(userPokemon,userAttackType,basicAttackType));
  //         // }
  //         // else{ // special attack choice
  //         //   System.out.println( userPokemon.getAttackMenu(userAttackType));

  //         //   int specialAttackType = CheckInput.getIntRange(1,3); // choice of attack in special attack
  //         //   System.out.println(userPokemon.attack(userPokemon, userAttackType, specialAttackType));
  //         }

  //         if(wild.getHp() != 0){ // wild pokemon attack
  //           int wildAttackType = (int)(Math.random()*(wild.getNumAttackTypeMenuItems()-1 + 1) + 1); // randomly chooses to do special attack or basic attack
  //           System.out.println(userPokemon.getAttackMenu(wildAttackType));
  //           int wildAttackMove = CheckInput.getIntRange(1, wild.getNumAttackMenuItems(wildAttackType));
  //           System.out.println(wild.attack(userPokemon,wildAttackType,wildAttackMove));
  //           // if(wildAttackType == 1){
  //           //   int wildBasicType = (int)(Math.random()*(3-1 + 1) + 1); // randomly chooses an attack form basic attack.
  //           //   System.out.println("Your " + wild.basicAttack(userPokemon, wildBasicType));
  //           // }
  //           // else{
  //           //   int wildSpecialType = (int)(Math.random()*(3-1 + 1)+1);  // randomly chooses an attack form special attack.
  //           //   System.out.println("Your " + wild.specialAttack(userPokemon, wildSpecialType));
  //           // }
  //         } 
  //       else{
  //         System.out.print("Your pokemon can not fight due to 0 Hp\n");
  //       }
  //     }
  //     else if(choice == 2){ 
  //       if(t.hasPotion()){
  //         System.out.println("Choose the pokemon to heal:");
  //         System.out.println(t.getPokemonList()); 
  //         int index = t.getNumPokemon() -1;
  //         Pokemon pokemonToHeal = t.getPokemon(index);
  //         if(pokemonToHeal.getMaxHp() != pokemonToHeal.getHp() && pokemonToHeal.getHp() != 0){
  //           t.usePotion(index);
  //           System.out.println("Your pokemon has been healed!! ");
  //         }
  //         else{
  //           System.out.println("Cannot use portion on this pokemon.");
  //         }
  //       }
  //       else{
  //         System.out.println("You do not have any potion.");
  //       }
  //     }
  //     else if(choice == 3){ // if the user chooses to throw pokeball.
  //       if(t.hasPokeball()){
  //         if(t.catchPokemon(wild)){
  //           System.out.println("Congratulations! You caught " + wild.getName() +".");
  //           choice = 4; // if the pokemon is caught the loop terminates by making choice 4 that is exit.
  //         }
  //         else{
  //           System.out.println("Attempt failed. "+ wild.getName() + " can still fight.");
  //         }
  //       }
  //       else{
  //         System.out.println("You are out of pokeballs.");
  //       }
  //     }
  //   }
  // }
  public static Pokemon trainerAttack(Trainer t, Pokemon wild){
    PokemonGenerator debuffing = PokemonGenerator.getInstance();
    System.out.println("Choose a Pokemon:\n" + t.getPokemonList()); 
    Pokemon userPokemon = t.getPokemon(t.getNumPokemon()-1); // user's choice of pokemon to attack.
    if(userPokemon.getHp() != 0){
      System.out.println(userPokemon.getName() + ", I choose you!\n" + userPokemon.getAttackTypeMenu()); 
      int userAttackType = CheckInput.getIntRange(1, userPokemon.getNumAttackTypeMenuItems());
      System.out.println(userPokemon.getAttackMenu(userAttackType));
      int userAttackMove = CheckInput.getIntRange(1, userPokemon.getNumAttackMenuItems(userAttackType));
      System.out.println(userPokemon.attack(wild,userAttackType,userAttackMove));
      int debuff_chance = (int)(Math.random()*(100-0 + 1) + 0);
      if (debuff_chance <= 25){
        wild = debuffing.addRandomDebuff(wild);
      }
      }
      if(wild.getHp() != 0){ // wild pokemon attack
        int wildAttackType = (int)(Math.random()*(wild.getNumAttackTypeMenuItems()-1 + 1) + 1); // randomly chooses to do special attack or basic attack
        int wildAttackMove = (int)(Math.random()*(wild.getNumAttackTypeMenuItems()-3 + 1) + 1);
        System.out.println(wild.attack(userPokemon,wildAttackType,wildAttackMove));
        int user_debuff_chance = (int)(Math.random()*(10) + 1);
        if (user_debuff_chance == 1) {
          debuffing.addRandomDebuff(userPokemon);
        }
      } 
      
    else{
      System.out.print("Your pokemon can not fight due to 0 Hp\n");
    }
    return wild;
  }
}

