
import java.util.*;

class Console{

  User player = new User();
  AI computer;
  AnimalCard[][] field = new AnimalCard[2][4];

  public Console(AnimalCard[][] field, User player,AI computer){
    this.field = field;
    this.player = player;
    this.computer = computer;
  }

  public void title(){
    System.out.println("Welcome to");
    System.out.println("__                __  "
    +" _      _    _          "
    +"________");
    System.out.println("\\ \\      __      / /  "
    +"| |    | |  | |         "
    +"| ______|");
    System.out.println(" \\ \\    /  \\    / /   "
    +"| |    | |  | |         "
    +"| |___");
    System.out.println("  \\ \\  / /\\ \\  / /    "
    +"| |    | |  | |         "
    +"| ____|");
    System.out.println("   \\ \\/ /  \\ \\/ /     "
    +"\\ \\____/ /  | |______   "
    +"| |");
    System.out.println("    \\__/    \\__/      "
    +" \\______/   |________|  "
    +"|_|");
  }

  public void intro(){
    Scanner scn = new Scanner(System.in);
    System.out.println();
    System.out.print("Would you like to (P)lay game or (V)iew instructions? ");
    char input = scn.next().toLowerCase().charAt(0);
    while(!(input == 'p' || input == 'v')){
      System.out.print("Would you like to (P)lay game or (V)iew instructions? ");
      input = scn.next().toLowerCase().charAt(0);
    }
    if(input == 'v'){
      System.out.println();
      System.out.println("Wulf is a 1v1 card game focused on not only balancing your health ");
      System.out.println("but the power between you and your cards or they will turn on you.");
      System.out.println();
      System.out.println("The most important part of the game is the battlefield, it is a two ");
      System.out.println("sided field with 4 spaces for creatures on each side, you may place ");
      System.out.println("one creature from your hand onto the field per turn. At the end ");
      System.out.println("of each players turn their creatures will attack the space infront of ");
      System.out.println("them, if no creatures occupy that space they will attack their");
      System.out.println("opponent directly but if they turn on their player they will attack ");
      System.out.println("them instead. You have to balance the power of yourself and your ");
      System.out.println("creatures on the board or they will start to turn on you. You gain ");
      System.out.println("power each round allowing you to play more powerfull creatures ");
      System.out.println("without them turning on you.");
      System.out.println();
      System.out.println("This is what the game battlefield looks like:");
      System.out.println();
      drawBattlefield();
      System.out.println();
      System.out.print("Would you like to play now? ");
      input = scn.next().toLowerCase().charAt(0);
      if(input != 'y'){
        System.out.println("Too bad.");
        scn.nextLine();
      }
    }
  }

  public void drawBattlefield(){
    int cardPower = 0;
    for(int i = 0; i < 4; i++){
      if(field[0][i] != null){
        cardPower += field[0][i].power;
      }
    }
    int AiCardPower = 0;
    for(int i = 0; i < 0; i++){
      if(field[1][i] != null){
        AiCardPower += field[1][i].power;
      }
    }
    System.out.print("\033[H\033[2J");  
    System.out.flush();
    System.out.println();
    System.out.println("  "+computer.health+"\\"+computer.tHealth
      +"       Computer       "
      + AiCardPower +"\\"+ computer.power);
    System.out.println();
    drawSide(1);
    drawSide(0);
    System.out.println();
    System.out.println("  "+player.health+"\\"+player.tHealth
      +"        Player        "
      + cardPower +"\\" + player.power);
      
  }

  public void takeTurn(){
    Scanner scn = new Scanner(System.in);
    System.out.println();
    System.out.print("(P)lay card or (E)nd turn: ");
    char input = scn.next().charAt(0);
    while(!(input == 'p' || input == 'e')){
      System.out.print("(P)lay card or (E)nd turn: ");
      input = scn.next().charAt(0);
    }

    if(input == 'p'){
      for(int i = 0; i < player.hand.size(); i++){
        System.out.println(i+1+". "
        +player.hand.get(i).getName()+" "
        +player.hand.get(i).attack+" attack "
        +player.hand.get(i).health+" health "
        +player.hand.get(i).power+" power "
        +player.hand.get(i).loyalty+" loyalty.");
      }

      System.out.println();
      System.out.print("Choose card to play: ");
      int cardInput = scn.nextInt();

      while(cardInput < 1 || cardInput > player.hand.size()){
        System.out.print("Choose card to play: ");
        cardInput = scn.nextInt();
      }

      System.out.println();
      System.out.print("Choose position for animal: ");
      int posInput = scn.nextInt();

      while(posInput < 1 || posInput > 4 || field[0][posInput] != null){
        System.out.print("Choose position for animal: ");
        posInput = scn.nextInt();
      }
    
      field[0][posInput-1] = player.hand.get(cardInput-1);
      player.hand.remove(cardInput-1);
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
            break;
          case 2:
            System.out.print("| "+name+"  |  ");
            break;
          case 3:
            System.out.print("| "+name+" |  ");
            break;
          case 4:
            System.out.print("|"+name+" |  ");
            break;
          case 5:
            System.out.print("|"+name+"|  ");
            break;
          case 6:
            name = name.substring(0, name.length()-1);
            System.out.print("|"+name+"|  ");
            break;
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
        System.out.print("|  "+(i+1)+"  |  ");
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
      System.out.print("|_____|  ");
    }
    System.out.println();
  }

}