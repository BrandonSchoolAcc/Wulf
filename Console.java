import java.util.*;

class Console{

  Player[] players = new Player[2];
  AnimalCard[][] field = new AnimalCard[2][4];

  public Console(Player[] players, AnimalCard[][] field){
    this.players = players;
    this.field = field;
  }

  public void drawBattlefield(int turn){

    if(turn == 1){
      System.out.println("Player 2");
      System.out.println();
      for(int i = 0; i < 5; i++){
        switch(i){
          case 0 & 4:
            
        }
      }
    }
  }

  public void takeTurn(int player){
    Scanner scn = new Scanner(System.in);

    System.out.print("(P)lay card or (E)nd turn: ");
    char input = scn.next().charAt(0);
    while(input != 'p' || input != 'e'){
      System.out.print("(P)lay card or (E)nd turn: ");
      input = scn.next().charAt(0);
    }

    if(input == 'p'){
      for(int i = 0; i < players[player].hand.size(); i++){
        System.out.println(i+1+". "
        +players[player].hand.get(i).getName()+" "
        +players[player].hand.get(i).attack+" attack "
        +players[player].hand.get(i).health+" health "
        +players[player].hand.get(i).power+" power "
        +players[player].hand.get(i).loyalty+" loyalty.");
      }

      System.out.println();
      System.out.print("Choose card to play: ");
      int cardInput = scn.nextInt();

      while(cardInput < 1 || cardInput > players[player].hand.size()){
        System.out.print("Choose card to play: ");
        cardInput = scn.nextInt();
      }

      System.out.println();
      System.out.print("Choose position for animal: ");
      int posInput = scn.nextInt();

      while(posInput < 1 || posInput > 4){
        System.out.print("Choose position for animal: ");
        posInput = scn.nextInt();
      }
    
      field[player+1][posInput-1] = players[player].hand.get(cardInput-1);
      players[player].hand.remove(cardInput-1);
    }
  }
}