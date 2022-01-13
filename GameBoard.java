class GameBoard{

  AnimalCard[][] field = new AnimalCard[2][4];
  Player[] players = new Player[2];

  public GameBoard(){
    
  }

  private void damageStage(int turn){

  }

  private void loyaltyCheck(int player){
    int cardPower = 0;
    for(int i = 0; i < 4; i++){
      if(field[player][i] != null){
        cardPower += field[player][i].power;
      }
    }
    int powerBalance = cardPower - players[player].power;
    for(int i = 0; i < 4; i++){
      if(field[player][i] != null){
        if(powerBalance >= field[player][i].loyalty){
          field[player][i].loyal = false;
        }
        else field[player][i].loyal = true;
      }
    }
  }
}