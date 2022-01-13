class AnimalCard{

  private String name;
  public int attack;
  public int health;
  public int power;
  public int loyalty;
  public boolean loyal = true;

  public AnimalCard(String name, int attack, int health, int power, int loyalty){

    this.name = name;
    this.attack = attack;
    this.health = health;
    this.power = power;
    this.loyalty = loyalty;

  }

  public String getName(){
    return this.name;
  }

  public void playEvent(){

  }

  public void deathEvent(){
    
  }

  
}