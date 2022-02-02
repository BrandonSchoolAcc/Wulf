import java.util.*;

class Player{

  public ArrayList<Card> deck = new ArrayList<Card>();

  public ArrayList<Card> hand = new ArrayList<Card>();

  public int tHealth = 20;
  public int health = tHealth;
  public int power = 5;

  public Player(){
    
  }

  public void addCard(String name, int attack, int health, int power, int loyalty){
    deck.add(new AnimalCard(name, attack, health, power, loyalty));
  }

  public void addToHand(){
      hand.add(deck.get(0));
      deck.remove(0);
  }

  public void takeDamage(int damage){
    health -= damage;
  }
  
}