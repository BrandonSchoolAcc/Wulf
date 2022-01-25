import java.util.*;

class AI extends Player{

  AnimalCard[][] field = new AnimalCard[2][4];

  public AI(AnimalCard[][] field){
    this.field = field;
  }

  public void doSomething(){
    AnimalCard[][] simField = field;
    if(checkFree()){
      int card;
      int space;
      
    }
  }

  public void playCard(){
    int[] choice = new int[2];
    int counter = 0;
    AnimalCard[][] simField = field;

    if(checkFree()){
      int card = 0;
      int pos = 0;
      for(int i = 0; i < 4; i++){
        if(field[1][i] == null){
          pos = i;
          break;
        }
      }
      int xStats = 0;
      if(checkEnemy()){
        int eStats = 0;
        for(int i = 0; i < 4; i++){
          if(field[0][i] != null && field[1][i] == null){
            int tStats = field[0][i].attack;
            if(eStats < tStats && checkSpace(i)){
              eStats = tStats;
              pos = i;
            }
          }
        }
      }
      for(int i = 0; i < hand.size(); i++){
        int yStats = hand.get(i).attack + hand.get(i).health;
        simField[1][0] = hand.get(i);
        if(simLoyaltyCheck(simField) && xStats < yStats){
          card = i;
          xStats = yStats;
        }
        else counter++;

        simField = field;
      }
      if(counter == hand.size()){
        choice = null;
      }
      choice[0] = pos;
      choice[1] = card;
    }
    else choice = null;
    
    if(choice != null){
      field[1][choice[0]] = hand.get(choice[1]);
      hand.remove(choice[1]);
    }
  }

  private boolean simLoyaltyCheck(AnimalCard[][] simField){
    boolean check = true;
    int cardPower = 0;
    for(int i = 0; i < 4; i++){
      if(simField[1][i] != null){
        cardPower += simField[1][i].power;
      }
    }
    int powerBalance = cardPower - power;
    for(int i = 0; i < 4; i++){
      if(simField[1][i] != null){
        if(powerBalance >= simField[1][i].loyalty){
          check = false;
          break;
        }
      }
    }

    return check;
  }

  private boolean checkFree(){
    boolean check = false;
    for(int i = 0; i < 4; i++){
      if(field[1][i] == null){
        check = true;
        break;
      }
    }
    return check;
  }

  private boolean checkEnemy(){
    boolean enemy = false;
    for(int i = 0; i < 4; i++){
      if(field[0][i] != null && field[1][i] == null){
        enemy = true;
        break;
      }
    }
    return enemy;
  }

  private boolean checkSpace(int pos){
    boolean check = true;
    if(field[1][pos] != null){
      check = false;
    }
    return check;
  }

}