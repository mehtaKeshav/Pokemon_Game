/** Interface for Fire type behavior of an object */
public interface Fire{

  /** for displying the special menu */
  public String specialMenu = "1.Ember\n2.Fire Blast\n3. Fire Punch";

  /** for providing the number of special menu items */
  public int namSpecialMenuItems  = 3;

  /** This method is for dealing the amount damage to the opponent pokemon depending on the type of the pokemon 
   * @param p - refers to the pokemon that is going to use the ember attack  
   * @return  displays the damage that is done to opposing pokemon
   */
  public String ember(Pokemon p);

  /** This method is for dealing the amount damage to the opponent pokemon depending on the type of the pokemon
   *  @param p - refers to the pokemon that is going to use the fire blast attack
   * @return  displays the damage that is done to opposing pokemon
   */
  public String fireBlast(Pokemon p);

  /** This method is for dealing the amount damage to the opponent pokemon depending on the type of the 
   * @param p - refers to the pokemon that is going to use the fire punch attack
   * @return  displays the damage that is done to opposing pokemon
   */
  public String firePunch(Pokemon p);
}