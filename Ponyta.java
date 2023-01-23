/** This class is a represenatation and behavior of Ponyta, subclass of Pokemon*/
public class Ponyta extends Pokemon implements Fire{
  /** constructor 
   *Initializes the pokemon - Ponyta
   */
  public Ponyta(){
      super("Ponyta");
  }

  /** Description : Method for displaying the special menu to the user to choose between the atttacks to choose from 
    * @return returns the special menu for the user to choose the attack for the pokemon
    */
  public String getSpecialMenu(){
      return Fire.specialMenu;
  }

  /** Description : Method to choose the attack from the options between 1 to 3 that are dispayed to the user 
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
          return ember(p);
      }
      else if (move == 2){ 
          return fireBlast(p);
      }
      else{
          return firePunch(p);
      }
  }

  /** Description : This method is for dealing the amount damage to the opponent pokemon depending on the type of the pokemon and also displying the damage that is done to that pokemon  
    * @param p - refers to the pokemon that is going to use the ember attack
    * @return  displays the damage that is done to opposing pokemon
    */
  public String ember(Pokemon p){
      int attack = (int)((Math.random()*(4-0 + 1)) + 0);
      int damage;
      if(p instanceof Fire){
          damage = attack;
      }
      else if(p instanceof Water){
          damage  = attack/2;
      }
      else{
          damage = attack*2;
      }
      p.takeDamage(damage);
      return p.getName() + " is encased in EMBERS and takes "+ damage + " damage.";
  }

  /** Description : This method is for dealing the amount damage to the opponent pokemon depending on the type of the pokemon and also displying the damage that is done to that pokemon  
    * @param p - refers to the pokemon that is going to use the fire blast attack
    * @return  displays the damage that is done to opposing pokemon
    */
  public String fireBlast(Pokemon p){
      int attack = (int)((Math.random()*(5-2 + 1)) + 2);
      int damage;
      if(p instanceof Fire){
          damage = attack;
      }
      else if(p instanceof Water){
          damage  = attack/2;
      }
      else{
          damage = attack*2;
      }
      p.takeDamage(damage);
      return p.getName() + " is BLASTED with FIRE and takes "+ damage + " damage.";
  }

  /** Description : This method is for dealing the amount damage to the opponent pokemon depending on the type of the pokemon and also displying the damage that is done to that pokemon  
    * @param p - refers to the pokemon that is going to use the fire punch attack
    * @return  displays the damage that is done to opposing pokemon
    */
  public String firePunch(Pokemon p){
      int attack = (int)((Math.random()*(4 - 1 + 1)) + 1);
      int damage;
      if(p instanceof Fire){
          damage = attack;
      }
      else if(p instanceof Water){
          damage  = attack/2;
      }
      else{
          damage = attack*2;
      }
      p.takeDamage(damage);
      return p.getName() + " is PUNCHED by FIRE and takes "+ damage + " damage.";
  }
}