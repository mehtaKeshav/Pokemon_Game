/**
* Description: This class increases the attack damage of the pokemon this is applied to.
*/
public class AttackUp extends PokemonDecorator {
  /** Constructor
  * Description: Creates a pokemon with an increased value.
  * @param p is the pokemon that is getting the attack increase
  */
  public AttackUp(Pokemon p) {
    super(p,"+ATK " + p.getName(), 0);
  }
  /** 
  * Description: getAttackBonus has an additional 1 or 2 attack when AttackUp is applied to the pokemon.
  * @param type is the type of the pokemon
  * @return the new attack bonus value.
  */
  public int getAttackBonus(int type) {
    int random_attackBonus = (int)(Math.random()*(2) + 1);
    int new_attackBonus = super.getAttackBonus(type) + random_attackBonus;
    return new_attackBonus;
  }
}