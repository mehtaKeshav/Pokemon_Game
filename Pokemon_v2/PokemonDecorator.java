public abstract class PokemonDecorator extends Pokemon{
  private Pokemon pokemon;

  public PokemonDecorator(Pokemon p, String extraName, int extrahp){
    super(extraName,extrahp, (p.getHp()+extrahp));
    pokemon = p;
  }
  
  @Override
  public String getAttackMenu(int atkType){
    return pokemon.getAttackMenu(atkType);
  }

  @Override
  public int getNumAttackMenuItems(int atkType){
    return 3;
  }
  
  @Override
  public String getAttackString(int atkType, int move) {
    return pokemon.getAttackString(atkType, move);
  }

  public int getAttackDamage(int atkType, int move){
    return pokemon.getAttackDamage(atkType, move);
  }

  public double getAttackMultiplier(Pokemon p, int type){
    return pokemon.getAttackMultiplier(p, type);
  }

  public int getAttackBonus(int type){
    return pokemon.getAttackBonus(type);
  }
}