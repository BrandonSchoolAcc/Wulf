import java.util.*;

class AI extends Player{

  public AI(AnimalCard[][] field){

  }

  public void playCard(AnimalCard[][] field){
    AnimalCard[][] simField = copyField(field);
    if(checkFreeSpace(field)){
      int card = hand.size() + 1;
      int space = 0;
      for(int i = 0; i < 4; i++){
        if(field[1][i] == null){
          space = i;
        }
      }

      if(checkEnemy(field)){
        int enemyStat = 0;
        for(int i = 0; i < 4; i++){
          if(field[0][i] != null && field[1][i] == null){
            int tempEStat = field[0][i].attack;
            if(enemyStat < tempEStat){
              enemyStat = tempEStat;
              space = i;
            }
          }
        }
      }

      int bestStats = 0;
      for(int i = 0; i < hand.size(); i++){
        int tempStats = hand.get(i).attack + hand.get(i).health;
        simField[1][space] = hand.get(i);
        if(bestStats < tempStats && simLoyaltyCheck(simField)){
          bestStats = tempStats;
          card = i;
        }
        simField = copyField(field);
      }

      if(card != hand.size() + 1){
        field[1][space] = hand.get(card);
        hand.remove(card);
      }

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

  private boolean checkFreeSpace(AnimalCard[][] field){
    boolean check = false;
    for(int i = 0; i < 4; i++){
      if(field[1][i] == null){
        check = true;
        break;
      }
    }
    return check;
  }

  private boolean checkEnemy(AnimalCard[][] field){
    boolean enemy = false;
    for(int i = 0; i < 4; i++){
      if(!(field[0][i] == null) && field[1][i] == null){
        enemy = true;
        break;
      }
    }
    return enemy;
  }

  private AnimalCard[][] copyField(AnimalCard[][] field){
    AnimalCard[][] tempField = new AnimalCard[2][4];
    for(int i = 0; i < 2; i++){
      for(int j = 0; j < 4; j++){
        if(field[i][j] != null){
          tempField[i][j] = new AnimalCard(
            field[i][j].getName(),
            field[i][j].attack,
            field[i][j].health,
            field[i][j].power,
            field[i][j].loyalty);
        }
      }
    }
    return tempField;

  }

}