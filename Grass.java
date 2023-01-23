/**This class adds additional behaviour to the pokemons by assigning them to the type grass*/
public interface Grass {
  /**
  * Gives the string of the names of the special attacks.
  */
  public String specialMenu  = "1.Vine Whip\n2.Razor Leaf\n3.Solar Beam";
  /**
  * defines the total number of special attacks.
  */
  public int numSpecialMenuItems = 3;
  /**
  * This function is a special attack of grass type that gives damage to the opponent pokemon in the range from 3 to 1. The damage is buffed or nurffed according to the opponent's type. 
  * @param P the pokemon being attacked on.
  * @return String that that represents the interaction.
  */
  public String vineWhip(Pokemon p);
  /**
  * This function is a special attack of grass type that gives damage to the opponent pokemon in the range from 4 to 2. The damage is buffed or nurffed according to the opponent's type. 
  * @param P the pokemon being attacked on.
  * @return String that that represents the interaction.
  */
  public String razorLeaf(Pokemon p);
  /**
  * This function is a special attack of grass type that gives damage to the opponent pokemon in the range from 3 to 0. The damage is buffed or nurffed according to the opponent's type. 
  * @param P the pokemon being attacked on.
  * @return String that that represents the interaction.
  */
  public String solarBeam(Pokemon p);
}
    