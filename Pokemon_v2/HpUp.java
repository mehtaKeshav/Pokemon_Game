public class HpUp extends PokemonDecorator{
  public HpUp(Pokemon p){
    super(p,"+HP " + p.getName(), (int)(Math.random()*(2-1 + 1) + 1));
  }
}