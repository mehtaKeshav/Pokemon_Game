/**
* This class crates a pokemon named Oddish that imbibes the behaviour of type Grass and implements pokemon class.
*/
public class Oddish extends Pokemon implements Grass {
  /** Constructor
   * Initializes the pokemon, Oddish.
   */
  public Oddish(){
      super("Oddish");
  }
  /**
   * Description : This function gives all the list of names of special attacks in a form of a menu.
   * @return String of all special attack.
   */
  public String getSpecialMenu(){
      return Grass.specialMenu;
  }
  
  /**
   * Description : prompts the user to choose the special attack form the special attack menu.
   * @return the number of the attack the user chooses to use.
   */
  public int getNumSpecialMenu(){
      return CheckInput.getIntRange(1, 3);
  }
  /**
   * Description : performs the special attack (Vine Whipe, Razor Leaf or Solar Beam) depending * upon the move choosen.
   * @param p pokemon that takes the attack.
   * @param move the integer choice of attack.
   * @return String that describes the interaction.
   */
  public String specialAttack(Pokemon p, int move){
      if(move ==1){
          return vineWhip(p);
      }
      else if (move == 2){ 
          return razorLeaf(p);
      }
      else{
          return solarBeam(p);
      }
  }
  /**
   * Description : performs the special attack Vine Whipe on the opponent pokemon and gives a buffed or nurffed damage based on the behavour type of the opponent pokemon.
   * @param p pokemon that takes the attack.
   * @return String that describes the interaction.
   */
  public String vineWhip(Pokemon p){
      int attack = (int)((Math.random()*(3-1 + 1)) + 1);
      int damage;
      if(p.getType() == 0){
          damage = attack/2;
      }
      else if(p.getType() == 1){
          damage  = attack*2;
      }
      else{
          damage = attack;
      }
      p.takeDamage(damage);
      return p.getName() + " is whipped by VINE WHIP and takes "+ damage + " damage.";
  }
  /**
   * Description : performs the special attack Razor Leaf on the opponent pokemon and gives a buffed or nurffed damage based on the behavour type of the opponent pokemon.
   * @param p pokemon that takes the attack.
   * @return String that describes the interaction.
   */
  public String razorLeaf(Pokemon p){
      int attack = (int)((Math.random()*(4-2 + 1)) + 2);
      int damage;
      if(p.getType() == 0){
          damage = attack/2;
      }
      else if(p.getType() == 1){
          damage  = attack*2;
      }
      else{
          damage = attack;
      }
      p.takeDamage(damage);
      return p.getName() + " is slashed by RAZOR LEAF and takes "+ damage + " damage.";
  }
  /**
   * Description : performs the special attack Solar Beam on the opponent pokemon and gives a buffed or nurffed damage based on the behavour type of the opponent pokemon.
   * @param p pokemon that takes the attack.
   * @return String that describes the interaction.
   */
  public String solarBeam(Pokemon p){
      int attack = (int)((Math.random()*(3 + 1)));
      int damage;
      if(p.getType() == 0){
          damage = attack/2;
      }
      else if(p.getType() == 1){
          damage  = attack*2;
      }
      else{
          damage = attack;
      }
      p.takeDamage(damage);
      return p.getName() + " is dazzled by SOLAR BEAM and takes "+ damage + " damage.";
  }
}