import java.awt.*;
import java.util.ArrayList;
public class Trainer extends Entity{
  private int money;
  private int potions;
  private int pokeballs;
  private Map map;
  private Point loc;
  private ArrayList<Pokemon> pokemon;

  /** Constructor
   * Description: Constructs an trainer with a name ,a a pokemon and a map
   * @param n represents the name of the Trainer
   * @param p represents the pokemon
   * @param m represents the map in which the user is going to travel
   */
  public Trainer(String n, Pokemon p, Map m){
    super(n, 25);
    money = 50;
    potions = 5;
    pokeballs = 5;
    map = m;
    pokemon = new ArrayList <Pokemon> ();
    pokemon.add(p);
    loc = m.findStart();
  }

  /** Description : displays the money that the user has
   * @return returns the money that the user has
   */
  public int getMoney(){
    return money;
  }

  /** Desciption : method to check whether the user has spent the money or not 
   * @param amt represents the amount of money that the trainer is going to spend
   * @return returns true if the trainer has spent the money otherwise returns false
   */
  public boolean spendMoney(int amt){
    if (money >= amt){
      money = money - amt;
      return true;
    }
    else{
      return false;
    }
  }
  
  /** Description : method to get the money to the user 
   * @param amt represents the amount of money that the user will get
   */
  public void receiveMoney(int amt){
    money += amt;
  }

  /** Description : method to check if the user has potions or not 
   * @return returns true if the user has the potions otherwise returns false
   */
  public boolean hasPotion(){
    if(potions > 0){
      return true;
    }
    else{
      return false;
    }
  }

  /** Desciption : method for the user to receive potions */
  public void receivePotion(){
    potions++;
  }

  /** Desciption : method for the user to receive pokeballs */
  public void recievePokeball(){
    pokeballs++;
  }

  /** Description : method for the user to use potions
   * @param pokeIndex represents the pokemon in the list on which the potion is going to be used
   */
  public void usePotion(int pokeIndex){
    if (hasPotion() == true){
      getPokemon(pokeIndex).heal();
      potions--;
    }
  }

  /** Description : method to check whether the user has pokeballs or not
   * @return returns true if the user has the pokeballs otherwise returns false
   */
  public boolean hasPokeball(){
    if(pokeballs > 0){
      return true;
    }
    else{
      return false;
    }
  }

  /** Description : method for the user to capture the pokemon
   * @param p represents the pokemon which the user is going to capture
   * @return returns true if the user captures the pokemon otherwise returns false 
   */
  public boolean catchPokemon(Pokemon p){
    System.out.println("Shake.....Shake.....Shake");
    int probability = (p.getHp()/p.getMaxHp())*100;
    int factor =(int)(Math.random()*(100-1 + 1) +1);
    if( factor > probability){
      pokemon.add(p);
      pokeballs --;
      map.removeCharAtLoc(loc);
      return true;
    }
    else{
      return false;
    }
  }

  /** Desctription : method to get the location of the user
   * @return returns the location of the user
   */
  public Point getLocation(){
    return loc;
  }

  /** Description : Method for the user to go North
   * @return returns the location of the user after going north  
   */
  public char goNorth(){
    map.reveal(loc);
    loc.translate(-1, 0); 
    return map.getCharAtLoc(loc);
  }

  /** Description : Method for the user to go South
   * @return returns the location of the user after going South  
   */
  public char goSouth(){
    map.reveal(loc);
    loc.translate(1, 0);
    return map.getCharAtLoc(loc);
  }

  /** Description : Method for the user to go East
   * @return returns the location of the user after going East  
   */
  public char goEast(){
    map.reveal(loc);
    loc.translate(0, 1);
    return map.getCharAtLoc(loc);
  }

  /** Description : Method for the user to go West
   * @return returns the location of the user after going West  
   */
  public char goWest(){
    map.reveal(loc);
    loc.translate(0, -1);
    return map.getCharAtLoc(loc);
  }

  /** Description : Method for the user to choose the pokemon
   * @return returns the number that the user chose to choose the pokemon
   */
  public int getNumPokemon(){
    return CheckInput.getIntRange(1, pokemon.size());
  }

  /** Description : Method to heal all the pokemons */
  public void healAllPokemon(){
    for(int i = 0; i < pokemon.size(); i++){
      pokemon.get(i).heal();
    }
  }

  /** Desciption : Method for the user to get the specefic pokemon 
   * @param index represents the index on which the pokemon is in the list
   * @return returns the pokemon that the user chose
   */
  public Pokemon getPokemon(int index){
    return pokemon.get(index);
  }
  
  /** Desciption : Method to represent the list of the pokemon as a String
   * @return returns the list of the pokemon as a String
   */
  public String getPokemonList(){
    String s = new String();
    for(int i = 0; i < pokemon.size(); i++){
      s +=  "" + (i+1) +". " + pokemon.get(i).getName() + " HP: " + getPokemon(i).getHp() + "/" + getPokemon(i).getMaxHp() + "\n";
    }
    return s;
  }

  /** Desciption : method to display the name of the trainer with his name, hp, the number of potions and pokeballs he has and the list of pokemon that he can use and also desplays the map
   * @return returns the name of the trainer with his name, hp, the number of potions and pokeballs he has and the list of pokemon that he can use and also desplays the map
   */
  @Override
  public String toString(){
    return getName() + " HP: " + getHp() + "/" + getMaxHp() + "\nMoney: " + getMoney() + "\nPotions: " + potions + "\nPoke Balls: " + pokeballs + "\nPokemon\n-------\n" + getPokemonList() + "\nMap:\n" + map.mapToString(loc);
  }
}