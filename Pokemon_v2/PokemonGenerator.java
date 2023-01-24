import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.*;

/**
* Description: Creates a generator that allows the program to create random pokemon that can be given random stats.
*/
public class PokemonGenerator {
  private HashMap <String, String> pokemon = new HashMap <String, String>();
  static PokemonGenerator instance = null;
  /** Constructor
  * Description: reads a file that is a list of pokemon that has their name and their typing.
  */
  private PokemonGenerator() {
    try {
      Scanner read = new Scanner(new File("PokemonList.txt"));
      while (read.hasNext()) {
        String line = read.nextLine(); //reads the new line in the txt file
        String [] pokemon_info = line.split(",");
        pokemon.put(pokemon_info[0], pokemon_info[1]);
      }
      read.close();
    }
    catch(FileNotFoundException e) {
      System.out.println("File Not Found");
    }
  }
  /**
  * Description: gets the instance of the PokemonGenerator or creates a new instance if there is no PokemonGenerator instance already.
  * @return the instance of the PokemonGenerator
  */
  public static PokemonGenerator getInstance() {
    if (instance == null) {
      instance = new PokemonGenerator();
    }
    return instance;
  }
  /**
  * Description: Creates a random pokemon and adds random stats to it based off its level.
  * @param level is the level of the pokemon
  * @return the random pokemon that was generated.
  */
  public Pokemon generateRandomPokemon(int level) {
    int random_value = (int)(Math.random() * pokemon.size());
    ArrayList<String> pokemon_names = new ArrayList<String> ();
    for (String i : pokemon.keySet()) {
      pokemon_names.add(i);
    }
    String random_pokemon = pokemon_names.get(random_value);
    Pokemon new_pokemon = getPokemon(random_pokemon);
    
    //buffs the pokemon equal to its level - 1.
    for (int i = 1; i < level; i++) {
      addRandomBuff(new_pokemon);
    }
    
    return new_pokemon;
  }

  /**
  * Description: Creates a new pokemon based off the typing of the pokemon that is found based on the name of the pokemon
  * @param name is the name of the Pokemon
  * @return the new pokemon that was created.
  */
  public Pokemon getPokemon(String name) {
    String pokemon_type = pokemon.get(name);
    Pokemon new_pokemon = null;
    if (pokemon_type.equals("Grass")) {
      new_pokemon = new Grass(name, 20, 20);
    }
    else if (pokemon_type.equals("Water")) {
      new_pokemon = new Water(name, 20, 20);
    }
    else if (pokemon_type.equals("Fire")) {
      new_pokemon = new Fire(name, 20, 20);
    }
    return new_pokemon;
  }
  
  /**
  * Description: adds a random buff to the Pokemon
  * @param p is the Pokemon recieving the buff
  * @return the pokemon that recieved the buff
  */
  public Pokemon addRandomBuff(Pokemon p) {
    int random_number = (int)(Math.random()*(2) + 1);
    Pokemon buffed_pokemon = p;
    if (random_number == 1) {
      buffed_pokemon = new AttackUp(p);
    }
    else {
      buffed_pokemon = new HpUp(p);
    }
    return buffed_pokemon;
  }

  /**
  * Description: adds a random debuff to the Pokemon
  * @param p is the Pokemon recieving the debuff
  * @return the pokemon that recieved the debuff
  */
  public Pokemon addRandomDebuff(Pokemon p) {
    int random_number = (int)(Math.random()*(2) + 1);
    Pokemon debuffed_pokemon = p;
    if (random_number == 1) {
      debuffed_pokemon = new AttackDown(p);
    }
    else {
      debuffed_pokemon = new HpDown(p);
    }
    return debuffed_pokemon;
  }
}