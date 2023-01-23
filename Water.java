/**
* Description: This interface creates the type of water for the pokemon class
*/
public interface Water {
  /**
  * Description: Displays the menu of attacks for water types.
  */
  public String specialMenu = "1. Water Gun\n2. Bubble Beam\n3. Waterfall";
  public int numSpecialMenuItems = 3;
  
  /**
  * Description: defines the attack Water Gun.
  * @param p is the pokemon that this attack is being used against
  */
  public String waterGun(Pokemon p);
  /**
  * Description: defines the attack Bubble Beam.
  * @param p is the pokemon that this attack is being used against
  */
  public String bubbleBeam(Pokemon p);
  /**
  * Description: defines the attack Waterfall.
  * @param p is the pokemon that this attack is being used against
  */
  public String waterfall(Pokemon p);
}