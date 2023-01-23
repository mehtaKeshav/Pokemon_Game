/**
* Description: This class defines the base entity that the trainers and pokemon are the subclasses of.
*/
public abstract class Entity {
  private String name;
  private int hp;
  private int maxHp;
  /** Constructor
  * Description: Constructs an entity with a name and an hp (health points) value.
  * @param n is the name of the entity
  * @param mHp is the maximum hp value of the entity
  */
  public Entity(String n, int mHp){
    name = n;
    hp = mHp;
    maxHp = mHp;
  }
  /**
  * Description: gets the current hp from the entity
  * @return the value of the current hp as an int
  */
  public int getHp() {
    return hp;
  }
  /**
  * Description: gets the max hp from the entity
  * @return the value of the max hp as an int
  */
  public int getMaxHp() {
    return maxHp;
  }
  /**
  * Description: The entity takes damage from an attack. If hp would go below zero from the attack, it goes to zero instead
  * @param d is the damage of the attack that the entity is taking.
  */
  public void takeDamage(int d) {
    hp -= d;
    if (hp < 0) {
      hp = 0;
    }
  }
  /**
  * Description: heals the entity to their max hp.
  */
  public void heal() {
   hp = maxHp;
  }
  /**
  * Description: gets the name of the entity
  * @return String which is the name of the entity.
  */
  public String getName() {
    return name;
  }
  
  
  /**
  * Description: returns the name of the entity and their current and max hp, displays as a string.
  * @return a string which is the name of the entity which its current and max hp value.
  */
@Override
  public String toString() {
  return name + " HP: " + hp + "/" + maxHp;
  }
}