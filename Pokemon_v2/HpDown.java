public class HpDown extends PokemonDecorator{
  public HpDown(Pokemon p){
    super(p,"-HP " + p.getName(), (int)(Math.random()*(2-1 + 1) + 1));
  }
}