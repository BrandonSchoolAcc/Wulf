class AnimalCard extends Card{

  private String name;
  public int attack;
  public int startHealth;
  public int health;
  public int power;
  public int loyalty;
  public boolean loyal = true;

  public AnimalCard(String name, int attack, int health, int power, int loyalty){

    super(name);
    this.name = name;
    this.attack = attack;
    this.startHealth = health;
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