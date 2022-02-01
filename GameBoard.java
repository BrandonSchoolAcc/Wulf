import java.util.*;

class GameBoard{

  AnimalCard[][] field = new AnimalCard[2][4];
  User player = new User();
  AI computer = new AI(field);
  Console myConsole = new Console(field, player, computer);
  AnimalCard[] starter = new AnimalCard[]{
    new AnimalCard("Rat",1,1,1,1),
    new AnimalCard("Rat",1,1,1,1),
    new AnimalCard("Rat",1,1,1,1),
    new AnimalCard("Cat",2,2,3,2),
    new AnimalCard("Cat",2,2,3,2),
    new AnimalCard("Cat",2,2,3,2),
    new AnimalCard("Wolf",4,3,4,4),
    new AnimalCard("Wolf",4,3,4,4),
    new AnimalCard("Wolf",4,3,4,4),
    new AnimalCard("Alpha",5,4,6,5),
    new AnimalCard("Dog",3,2,3,8),
    new AnimalCard("Dog",3,2,3,8),
    new AnimalCard("Dog",3,2,3,8),
    new AnimalCard("Hawk",3,1,3,5),
    new AnimalCard("Hawk",3,1,3,5),
    new AnimalCard("Hawk",3,1,3,5),
    new AnimalCard("Turtle",1,6,2,4),
    new AnimalCard("Turtle",1,6,2,4),
    new AnimalCard("Turtle",1,6,2,4),
    new AnimalCard("Bear",6,8,8,3),
    new AnimalCard("Bear",6,8,8,3)
    };
  AnimalCard[] ratman = new AnimalCard[]{
    new AnimalCard("Rat",1,1,1,1),
    new AnimalCard("Rat",1,1,1,1),
    new AnimalCard("Rat",1,1,1,1),
    new AnimalCard("Rat",1,1,1,1),
    new AnimalCard("Rat",1,1,1,1),
    new AnimalCard("Rat",1,1,1,1),
    new AnimalCard("Rat",1,1,1,1),
    new AnimalCard("Rat",1,1,1,1),
    new AnimalCard("Rat",1,1,1,1),
    new AnimalCard("Rat",1,1,1,1),
    new AnimalCard("Rat",1,1,1,1),
    new AnimalCard("Rat",1,1,1,1),
    new AnimalCard("Rat",1,1,1,1),
    new AnimalCard("Rat",1,1,1,1),
    new AnimalCard("Rat",1,1,1,1),
    new AnimalCard("Rat",1,1,1,1),
    new AnimalCard("Rat",1,1,1,1),
    new AnimalCard("Rat",1,1,1,1),
    new AnimalCard("Rat",1,1,1,1),
    new AnimalCard("Rat",1,1,1,1),
    new AnimalCard("Rat",1,1,1,1),
    new AnimalCard("Rat",1,1,1,1),
    new AnimalCard("Rat",1,1,1,1),
    new AnimalCard("Rat",1,1,1,1)
  };
  AnimalCard[] AiStarter = new AnimalCard[]{
    new AnimalCard("Rat",1,1,1,1),
    new AnimalCard("Rat",1,1,1,1),
    new AnimalCard("Rat",1,1,1,1),
    new AnimalCard("Cat",2,2,3,2),
    new AnimalCard("Cat",2,2,3,2),
    new AnimalCard("Cat",2,2,3,2),
    new AnimalCard("Wolf",4,3,4,4),
    new AnimalCard("Wolf",4,3,4,4),
    new AnimalCard("Wolf",4,3,4,4),
    new AnimalCard("Alpha",5,4,6,5),
    new AnimalCard("Dog",3,2,3,8),
    new AnimalCard("Dog",3,2,3,8),
    new AnimalCard("Dog",3,2,3,8),
    new AnimalCard("Hawk",3,1,3,5),
    new AnimalCard("Hawk",3,1,3,5),
    new AnimalCard("Hawk",3,1,3,5),
    new AnimalCard("Turtle",1,6,2,4),
    new AnimalCard("Turtle",1,6,2,4),
    new AnimalCard("Turtle",1,6,2,4),
    new AnimalCard("Bear",6,8,8,3),
    new AnimalCard("Bear",6,8,8,3)
  };

  public GameBoard(){
    Collections.addAll(player.deck, starter);
    Collections.addAll(computer.deck, AiStarter);
  }

  public void playGame(){
    Scanner scn = new Scanner(System.in);
    Collections.shuffle(player.deck);
    Collections.shuffle(computer.deck);
    for(int i = 0; i < 5; i++){
      player.addToHand();
      computer.addToHand();
    }
    myConsole.title();
    myConsole.intro();
    int turn = 0;
    while(!winCheck()){
      if(turn == 0){
        myConsole.drawBattlefield();
        myConsole.takeTurn();
        myConsole.drawBattlefield();
        loyaltyCheck(0);
        damageStage(0);
        scn.nextLine();
        player.power += 1;
        player.addToHand();
        turn = 1;
      }
      else{
        computer.playCard(field);
        myConsole.drawBattlefield();
        loyaltyCheck(1);
        damageStage(1);
        scn.nextLine();
        computer.power += 1;
        computer.addToHand();
        turn = 0;
      }
    }
  }

  private void damageStage(int turn){
    for(int i = 0; i < 4; i++){
      if(field[turn][i] != null){
        if(turn == 0){
          if(field[0][i].loyal){
            if(field[1][i] != null){
              field[1][i].health -= field[turn][i].attack;
              System.out.println("Your "
              +field[turn][i].getName()+" attacks Computer's " + field[1][i].getName()+
              "!");
              deathCheck(1,i);
            }
            else{
              computer.health -= field[0][i].attack;
              System.out.println("Your "
              +field[turn][i].getName()+" attacks Computer directly!");
            }
          }
          else{
            player.health -= field[turn][i].attack;
            System.out.println("Your "
              +field[0][i].getName()+" attacks You directly!");
          }
        }
        else{
          if(field[turn][i].loyal){
            if(field[0][i] != null){
              field[0][i].health -= field[turn][i].attack;
              System.out.println("Computer's "
              +field[turn][i].getName()+" attacks Your " + field[0][i].getName()+
              "!");
              deathCheck(0,i);
            }
            else{
              player.health -= field[1][i].attack;
              System.out.println("Computer "
              +field[1][i].getName()+" attacks You directly!");
            }
          }
          else{
            computer.health -= field[1][i].attack;
            System.out.println("Computer's "
              +field[1][i].getName()+" attacks Computer directly!");
          }
        }
      }
    }

  }

  private void loyaltyCheck(int turn){
    int cardPower = 0;
    for(int i = 0; i < 4; i++){
      if(field[turn][i] != null){
        cardPower += field[turn][i].power;
      }
    }
    int powerBalance;
    if(turn == 0){
      powerBalance = cardPower - player.power;
    }
    else{
      powerBalance = cardPower - computer.power;
    }
    
    for(int i = 0; i < 4; i++){
      if(field[turn][i] != null){
        if(powerBalance >= field[turn][i].loyalty){
          field[turn][i].loyal = false;
          if(turn == 0){
            System.out.println(field[turn][i].getName()
            +" turns on you!");
          }
          else{
            System.out.println(field[turn][i].getName()
          +" turns on computer!");
          }
        }
        else if(field[turn][i].loyal == false){
          field[turn][i].loyal = true;
          if(turn == 0){
            System.out.println(field[turn][i].getName() +" returns under your control.");
          }
          else{
            System.out.println(field[turn][i].getName() +" returns under computers control.");
          }
        }
      }
    }
  }

  
  private void deathCheck(int side, int pos){
    if(field[side][pos].health <= 0){
      if(side == 0){
        System.out.println("Your "
        + field[side][pos].getName()+" has died!");
      }
      else{
        System.out.println("Computer's "
        + field[side][pos].getName()+" has died!");
      }
      field[side][pos] = null;
    }
  }

  private boolean winCheck(){
    boolean check = false;
    if(player.health <= 0){
      System.out.println("You lost.");
      check = true;
    }
    else if(computer.health <= 0){
      System.out.println("You won!");
      check = true;
    }
    return check;
  }
}