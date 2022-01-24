import java.util.*;

class Console{

  Player[] players = new Player[2];
  AnimalCard[][] field = new AnimalCard[2][4];

  public Console(Player[] players, AnimalCard[][] field){
    this.players = players;
    this.field = field;
  }

  public void intro(){
    System.out.println();
  }

  public void drawBattlefield(){
    System.out.println("  Computer");
    System.out.println();
    drawSide(1);
    drawSide(0);
    System.out.println();
    System.out.println("  Player");
      
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

  private void drawSide(int side){

    for(int i = 0; i < 4; i++){
      System.out.print("_______  ");
    }
    System.out.println();

    for(int i = 0; i < 4; i++){
      if(field[side][i] == null){
        System.out.print("|     |  ");
      }
      else{
        String name = field[side][i].getName();
        switch(name.length()){
          case 1:
            System.out.print("|  "+name+"  |  ");
          case 2:
            System.out.print("| "+name+"  |  ");
          case 3:
            System.out.print("| "+name+" |  ");
          case 4:
            System.out.print("|"+name+" |  ");
          case 5:
            System.out.print("|"+name+"|  ");
          default:
            name = name.substring(0, 5);
            System.out.print("|"+name+"|  ");
        }
      }
    }
    System.out.println();

    for(int i = 0; i < 4; i++){
      if(field[side][i] != null){
        System.out.print("|"
        +field[side][i].attack+"   "
        +field[side][i].health+"|  ");
      }
      else{
        System.out.print("|  "+i+"  |  ");
      }
    }
    System.out.println();

    for(int i = 0; i < 4; i++){
      if(field[side][i] != null){
        System.out.print("|"
        +field[side][i].power+"   "
        +field[side][i].loyalty+"|  ");
      }
      else{
        System.out.print("|     |  ");
      }
    }
    System.out.println();

    for(int i = 0; i < 4; i++){
      System.out.print("_______  ");
    }
    System.out.println();
  }

}