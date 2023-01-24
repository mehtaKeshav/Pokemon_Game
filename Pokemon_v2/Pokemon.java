/** Description: This class creates an entity called pokemon that is able to perform attacks and take damage.*/ 
public abstract class Pokemon extends Entity{
  static final double [][] battleTable = {{1,.5,2},{2,1,.5},{.5,2,1}}; 
  public Pokemon(String n, int h, int m){
      super(n,h,m);
  }
  public String getAttackTypeMenu(){
      return "1.Basic attack\n2.Special Attack";
  }

  public int getNumAttackTypeMenuItems(){
      return 2;
  }
  
  public String getAttackMenu(int atkType){
      return "1.Slam \n2.Tackle \n3. Punch";
  }
  public int getNumAttackMenuItems(int atkType){
    return 3;
}
  public String attack(Pokemon p, int atkType, int move){
    int damage = (int) (this.getAttackDamage(atkType, move) *this.getAttackMultiplier(p, atkType) + getAttackBonus(atkType));
    String string = this.getAttackString(atkType, move);
    p.takeDamage(damage);
    return p.getName() + " was " + string + " by " + this.getName() + " and take " + damage + " damage.";
}

  public String getAttackString(int atkType, int move){    
    if(move == 1){
        return "SLAMMED";
    }
    else if(move == 2){
        return "TACKLED";
    }
    else{
        return "PUNCHED";
    }
  }

  public int getAttackDamage(int atkType, int move){
    if(move == 1){
        return (int)(Math.random()*(6));
    }
    else if(move == 2){
        return (int)(Math.random()*(3-2 + 1) + 2);
    }
    else{
        return (int)(Math.random()*(4-1 + 1) + 2);
    }
  }

  public double getAttackMultiplier(Pokemon p, int atkType){
      return 1;
  }
  public int getAttackBonus(int atkType){
    return 0;
  }
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