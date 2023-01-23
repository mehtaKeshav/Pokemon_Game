/** This class is a represenatation and behavior of Squirtle, subclass of Pokemon*/
public class Squirtle extends Pokemon implements Water{
  /** Constructor 
   * Initializes the pokemon - Squirtle
   */
  public Squirtle(){
    super("Squirtle");
  }
  
  /** Description : Method for displaying the special menu to the user to choose between the atttacks to choose from 
   * @return returns the special menu for the user to choose the attack for the pokemon
   */
  public String getSpecialMenu(){
    return Water.specialMenu;
  }

  /**Description :  Method to choose the attack from the options between 1 to 3 that are dispayed to the user
   * @return returns the number between 1 to 3 for the user to choose
   */
  public int getNumSpecialMenu(){
    return CheckInput.getIntRange(1, 3);
  }

  /** Description : This method is used for using the pokemon attack corresponding to the option that the user chose 
   *  @param p - refers to the pokemon which is used to attack 
   *  @param move - refers to the move that the user chose to attack
   *  @return returns the attack the pokemon is going to use 
   */
  public String specialAttack(Pokemon p, int move){
    if(move ==1){
      return waterGun(p);
    }
    else if (move == 2){ 
      return bubbleBeam(p);
    }
    else{
      return waterfall(p);
    }
  }

  /** Description : This method is for dealing the amount damage to the opponent pokemon depending on the type of the pokemon
   * @param p - refers to the pokemon that is going to use the watergun attack
   * @return  displays the damage that is done to opposing pokemon
   */
  public String waterGun(Pokemon p){
    int attack = (int)(Math.random() * 4) + 2;
    int damage;
    int target_type = p.getType();
    if(target_type == 0){
      damage = attack * 2;
    }
    else if(target_type == 2){
      damage  = attack / 2;
    }
    else{
      damage = attack;
    }
    p.takeDamage(damage);
    return p.getName() + " is SPRAYED with WATER and takes "+ damage + " damage.";
  }

  /** Description : This method is for dealing the amount damage to the opponent pokemon depending on the type of the pokemon
   * @param p - refers to the pokemon that is going to use the buuble beam attack
   * @return  displays the damage that is done to opposing pokemon
   */
  public String bubbleBeam(Pokemon p){
    int attack = (int)(Math.random() * 3) + 1;
    int damage;
    int target_type = p.getType();
    if(target_type == 0){
      damage = attack * 2;
    }
    else if(target_type == 2){
      damage  = attack / 2;
    }
    else{
      damage = attack;
    }
    p.takeDamage(damage);
    return p.getName() + " takes a BEAM of BUBBLES and takes "+ damage + " damage.";
  }

  /** Description : This method is for dealing the amount damage to the opponent pokemon depending on the type of the pokemon
   * @param p - refers to the pokemon that is going to use the waterfall attack
   * @return  displays the damage that is done to opposing pokemon
   */
  public String waterfall(Pokemon p){
    int attack = (int)(Math.random() * 4) + 2;
    int damage;
    int target_type = p.getType();
    if(target_type == 0){
      damage = attack * 2;
    }
    else if(target_type == 2){
      damage  = attack / 2;
    }
    else{
      damage = attack;
    }
    p.takeDamage(damage);
    return p.getName() + " is charged at with the might of a WATERFALL and takes "+ damage + " damage.";
  }
}