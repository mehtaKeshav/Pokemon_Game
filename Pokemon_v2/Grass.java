 /**This class adds additional behaviour to the pokemons by assigning them to the type grass*/
public class Grass extends Pokemon{
  public Grass(String n,int h, int m){
    super(n,h,m);
  }
  @Override
  public String getAttackMenu(int atkType){
    if(atkType == 1){
      return super.getAttackMenu(atkType);
    }
    return "1.Vine Whip\n2.Razor Leaf\n3.Solar Beam";
  }
  @Override
  public int getNumAttackMenuItems(int atkType){
    if (atkType == 2){
      return 3;
    }
    return super.getNumAttackMenuItems(atkType);
  }
  @Override
  public String getAttackString(int atkType, int move){
    if(atkType == 1){
      return super.getAttackString(atkType, move);
    }
    else{
      if(move == 1){
        return "VINE WHIP";
      }
      else if(move == 2){
        return "RAZOR LEAF";
      }
      else{
        return "SOLAR BEAM";
      }
    }
  }
  @Override
  public int getAttackDamage(int atkType, int move){
    if(atkType == 1){
      return super.getAttackDamage(atkType, move);
    }
    else{
      if(move == 1){
        return (int)(Math.random()*(3-1 +1) + 1);
      }
      else if(move == 2){
        return (int)(Math.random()*(4-2 +1) + 1);
      }
      else{
        return (int)(Math.random()*(6));
      }
    }
  }
  @Override
  public double getAttackMultiplier(Pokemon p, int atkType){
    if(atkType == 1){
      return super.getAttackMultiplier(p, atkType);
    }
    return battleTable[this.getType()][p.getType()];
  }
}