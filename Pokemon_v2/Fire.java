public class Fire extends Pokemon {
  
  public Fire(String n, int h, int m) {
    super(n, h, m);
  }

  @Override
  public String getAttackMenu(int atkType) {
    String specialMenu = "1. ember\n2. Fire Blast\n3. Fire Punch";
    if(atkType == 1){
      return super.getAttackMenu(atkType);
    }
    return specialMenu;
  }
  
  
  @Override
  public int getNumAttackMenuItems(int atkType) {
    if(atkType == 2){
      return 3;
    }
    else{
      return super.getNumAttackMenuItems(1);
    }
  }

  @Override
  public String getAttackString(int atkType, int move) {
    if (atkType == 2){
      if (move == 1){
        return "EMBER";
      }
      else if(move == 2){
        return "FIRE BLAST";
      }
      else{
        return "FIRE PUNCH";
      }
    }
    else{
      return super.getAttackString(atkType,move);
    }
  }
  
  @Override
  public int getAttackDamage(int atkType, int move) {
    if (atkType == 2){
      if (move == 1){
        return (int)((Math.random()*(3-0 + 1)) + 0);
      }
      else if(move == 2){
        return (int)((Math.random()*(4-1 + 1)) + 1);
      }
      else{
        return (int)((Math.random()*(3-1 + 1)) + 1);
      }
    }
    else{
      return super.getAttackDamage(atkType,move);
    }
  }

  @Override
  public double getAttackMultiplier(Pokemon p, int atkType) {
    if(atkType == 2){
      return battleTable[this.getType()][p.getType()];
    }
    else{
      return super.getAttackMultiplier(p, atkType);
    }
  }
}