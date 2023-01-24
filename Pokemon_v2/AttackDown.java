/**
* Description: This class decreases the attack damage of the pokemon this is applied to.
*/
public class AttackDown extends PokemonDecorator {
  public AttackDown(Pokemon p) {
    super(p,"-ATK " + p.getName(), 0);
  }
  /** 
  * Description: getAttackBonus has an decrease of 1 or 2 attack when AttackDown is applied to the pokemon.
  * @param type is the type of the pokemon
  * @return the new attack bonus value.
  */
  public int getAttackBonus(int type) {
    int random_attackBonus = (int)(Math.random()*(2) + 1);
    int new_attackBonus = super.getAttackBonus(type) - random_attackBonus;
    return new_attackBonus;
  }
}