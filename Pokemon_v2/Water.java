/**
* Description: Creates a water type pokemon. This gives them a unique list of moves and a new set of attack multiplier.
*/
public class Water extends Pokemon {
  /** Constructor
  * Description: Constructs an water pokemon with a name and an hp (health points) value.
  * @param n is the name of the pokemon
  * @param h is the current hp value of the pokemon
  * @param m is the maximum hp value of the pokemon
  */
  public Water(String n, int h, int m) {
    super(n, h, m);
  }

  /**
  * Description: getAttackMenu either gets the original menu from the original pokemon class or returns the specialMenu.
  * @param atkType is the typing of the attack
  * @return the menu with a list of attacks based on the type of the attack.
  */
  @Override
  public String getAttackMenu(int atkType) {
    String specialMenu = "1. Water Gun\n2. Bubble Beam\n3. Waterfall";
    if(atkType == 1){
      return super.getAttackMenu(atkType);
    }
    return specialMenu;
  }
  /**
  * Description: Gets the number of items in the attack menu.
  * @param atkType is the typing of the attack
  * @return the number of items in the menu.
  */
  @Override
  public int getNumAttackMenuItems(int atkType) {
    if(atkType == 2){
      return 3;
    }
    else{
      return super.getNumAttackMenuItems(atkType);
    }
  }
  /**
  * Description: Gets the descriptor of the attack.
  * @param atkType is the typing of the attack
  * @param move is the attack associated/represented with a number.
  * @return the descriptor of the attack
  */
  @Override
  public String getAttackString(int atkType, int move) {
    if(atkType == 1){
      return super.getAttackString(atkType, move);
    }
    else{
      if(move == 1){
        return "WATER GUNNED";
      }
      else if(move == 2){
        return "BUBBLE BEAMED";
      }
      else{
        return "WATERFALLED";
      }
    }
  }
  /**
  * Description: Gets the damage of the specified attack.
  * @param atkType is the typing of the attack
  * @param move is the attack associated/represented with a number.
  * @return the damage of the specified attack.
  */
  @Override
  public int getAttackDamage(int atkType, int move) {
    if(atkType == 1){
      return super.getAttackDamage(atkType, move);
    }
    else{
      if(move == 1){
        return (int)(Math.random()*(3) + 1);
      }
      else if(move == 2){
        return (int)(Math.random()*(4) + 1);
      }
      else{
        return (int)(Math.random()*(6) + 1);
      }
    }
  }
  /**
  * Description: Gets multiplier of the damage of the attack based on the type of the attack and the typing of the attack.
  * @param p is the pokemon being attacked.
  * @param atkType is the typing of the attack
  * @return the damage of the specified attack.
  */
  @Override
  public double getAttackMultiplier(Pokemon p, int atkType) {
    if(atkType == 1){
      return super.getAttackMultiplier(p, atkType);
    }
    return battleTable[this.getType()][p.getType()];
  }
}