class GameBoard{

  AnimalCard[][] field = new AnimalCard[2][4];
  User player = new User();
  AI computer = new AI(field);
  Console myConsole;

  public GameBoard(Console myConsole){
    this.myConsole = myConsole;
  }

  public void playGame(){
    
    while(winCheck()){
      
    }
  }

  private void damageStage(int turn){
    for(int i = 0; i < 4; i++){
      if(field[turn][i] != null){
        if(turn == 0){
          if(checkSpace(1,i)){
            field[1][i].health -= field[0][i].attack;
            deathCheck(1,i);
          }
          else{
            computer.health -= field[0][i].attack;
          }
        }
        else{
          if(checkSpace(0,i)){
            field[0][i].health -= field[1][i].attack;
            deathCheck(0,i);
          }
          else{
            player.health -= field[1][i].attack;
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
        }
        else field[turn][i].loyal = true;
      }
    }
  }

  private boolean checkSpace(int side, int pos){
    boolean check = false;
    if(field[side][pos] != null){
      check = true;
    }
    return check;
  }
  private void deathCheck(int side, int pos){
    if(field[side][pos].health <= 0){
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
      System.out.println("You won.");
      check = true;
    }
    return check;
  }
}