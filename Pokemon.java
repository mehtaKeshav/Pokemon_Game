/** Description: This class creates an entity called pokemon that is able to perform attacks and take damage.*/ 
public abstract class Pokemon extends Entity{
  static final double [][] battleTable = {{1,.5,2},{2,1,.5},{.5,2,1}}; 
  /**
   * Initates the pokemon with a name and random Hp by calling entity's constructor.
   * @param n name of the pokemon.
   */
  public Pokemon(String n){
      super(n,(int)((Math.random()*(6-4+1)+4))*5);
  }
  /**
   * Description: An abstract method that gets the special attack menu of a perticular pokemon.
   * @return String of the special attack menu
   */
  abstract String getSpecialMenu();
  /**
   * Description:  An abstract method that gets the choice of special attack of a perticular pokemon from the user.
   * @return integer number of the special attack.
   */
  abstract int getNumSpecialMenu();
  /**
   * Description:  An abstract method that perfroms a special attack of a perticular pokemon depending upon the choosen move.
   * @param p pokemon that takes the special attack.
   * @param move number value of the move choice.
   * @return String of the interaction between the two pokemon.
   */
  abstract String specialAttack(Pokemon p, int move);
  /**
   * Description: Gives the list of basic moves perfromed by all the pokemon.
   * @return String of the basic attack moves.
   */
  public String getBasicMenu(){
      return "1.Slam\n2.Tackle\n3.Punch";
  }
  /**
   * Description: Prompts the user to choose the attack form the basic attack menu.
   * @return integer value of the choice of basic attack moves.
   */
  public int getNumBasicMenuItems(){
      return CheckInput.getIntRange(1, 3);
  }
  /**
   * Description: Perfroms a perticular basic attack (Slam, Tackle, or Punch) based on the move choice.
   * @param p pokemon that takes the basic attack.
   * @param move integer value of the choice of the move.
   * @return integer value of the choice of basic attack moves.
   */
  public String basicAttack(Pokemon p, int move){
      if(move == 1){
          return slam(p);
      }
      else if (move == 2){
          return tackle(p);
      }
      else{
          return punch(p);
      }
  }
   /**
   * Description: Gives the menu of attack types that is basic and special.
   * @return String of the attack types.
   */
  public String getAttackMenu(){
      return"1.Basic Attack\n2.Special Attack";
  }
  /**
   * Description: Prompts the user to choose form basic attack and special attack.
   * @return integer value of the choice of the attack.
   */
  public int getNumAttackMenuItems(){
      return CheckInput.getIntRange(1, 2);
  }

  /**
   * Description: Performs a basic Slam attack and gives damage between 1 to 5 to the opponent pokemon.
   * @param p opponent pokemon that takes damage.
   * @return String that describes the interaction.
   */
  public String slam(Pokemon p){
      int damage = (int)(Math.random()*(5+1));
      p.takeDamage(damage);
      return p.getName() + " is SLAMMED by " + this.getName() + " and takes " + damage + " damage.";
  }
    /**
   * Description: Performs a basic Tackle attack and gives damage between 2 to 3 to the opponent pokemon.
   * @param p opponent pokemon that takes damage.
   * @return String that describes the interaction.
   */
  public String tackle(Pokemon p){
      int damage = (int)(Math.random()*(3-2 + 1) + 2);
      p.takeDamage(damage);
      return p.getName() + " is TACKLED by " + this.getName() + " and takes " + damage + " damage.";
  }
    /**
   * Description: Performs a basic Punch attack and gives damage between 1 to 4 to the opponent pokemon.
   * @param p opponent pokemon that takes damage.
   * @return String that describes the interaction.
   */
  public String punch(Pokemon p){
      int damage = (int)(Math.random()*(4-1 + 1) + 1);
      p.takeDamage(damage);
      return p.getName() + " is PUNCHED by " + this.getName() + " and takes " + damage + " damage.";
  }
    /**
   * Description: This fuction provides the interface type (Fire, Water or Grass) of the perticular pokemon implements.
   * @return integer value that represents the type of the pokemon.
   */
  public int getType(){
      if(this instanceof Fire){
          return 0;
      }
      else if(this instanceof Water){
          return 1;
      }
      else{
          return 2;
      }
  }
}   