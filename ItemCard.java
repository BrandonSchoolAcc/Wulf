import java.util.*;
class ItemCard extends Card{
  
  enum Effects{
    KILL_OWN,
    CULL,
    RAT_BUFF,
    FORCE_TURN,
    HEAL,
    RETURN_TO_DECK,
    ADD_BAIT,
    ADD_ATTACK
  }

  Effects effect;

  public ItemCard(String name, Effects effect){
    super(name);
    this.effect = effect;

  }

  public String effectDesc(){
    switch(effect){
      case KILL_OWN:
        return "kill 1 of your own animals on the field.";
      case CULL:
        return "kill all animals one the field with 2 or less power.";
      case RAT_BUFF:
        return "all rats on the field gain 2 attack and 1 power.";
      case FORCE_TURN:
        return "force 1 of your opponents animals on the field to turn.";
      case HEAL:
        return "return 1 of your damaged animals to full health.";
      case RETURN_TO_DECK:
        return "return 1 animal on the field to its owners deck.";
      case ADD_BAIT:
        return "give yourself 1 bait.";
      case ADD_ATTACK:
        return "give one of your animals 1 attack.";
      default:
        return "INVALID";
    }
  }

  public void playEvent(AnimalCard[][] field, AI computer, User player){
    Scanner scn = new Scanner(System.in);
    switch(effect){
      case KILL_OWN:
        System.out.print("Choose animal to kill: ");
        int choice = scn.nextInt();
        while(choice < 1 || choice > 4 && field[0][choice-1] == null){
          System.out.print("Choose animal to kill: ");
          choice = scn.nextInt();
        }
        field[0][choice-1] = null;
        break;
      case CULL:
        for(int i = 0; i < 2; i++){
          for(int j = 0; j < 4; j++){
            if(field[i][j] != null && field[i][j].power <= 2){
              field[i][j] = null;
            }
          }
        }
        break;
      case RAT_BUFF:
        for(int i = 0; i < 2; i++){
          for(int j = 0; j < 4; j++){
            if(field[i][j] != null && field[i][j].getName().equals("Rat")){
              field[i][j].attack += 2;
              field[i][j].power += 1;
            }
          }
        }
        break;
      case FORCE_TURN:
        System.out.print("Choose opponent's animal to turn: ");
        int choices = scn.nextInt();
        while(choices < 1 || choices > 4 && field[0][choices-1] == null){
          System.out.print("Choose opponent's animal to turn: ");
          choices = scn.nextInt();
        }
        field[1][choices].loyal = false;
        break;
      case HEAL:
        System.out.print("Choose animal to heal: ");
        int healChoice = scn.nextInt();
        while(healChoice < 1 || healChoice > 4 || field[0][healChoice-1] == null){
          System.out.print("Choose animal to heal: ");
          healChoice = scn.nextInt();
        }
        char anotherChoice = '0';
        if(!(field[0][healChoice-1].health < field[0][healChoice-1].startHealth)){
          System.out.print("Animal not hurt, would you like to (C)hoose another animal or (P)lay another card? ");
          anotherChoice = scn.next().toLowerCase().charAt(0);
          if(anotherChoice == 'c'){
            System.out.print("Choose animal to heal: ");
            healChoice = scn.nextInt();
            while(healChoice < 1 || healChoice > 4 || field[0][healChoice-1] == null){
              System.out.print("Choose animal to heal: ");
              healChoice = scn.nextInt();
            }
          }
        }
        if(anotherChoice == '0' || anotherChoice == 'c'){
          field[0][healChoice-1].health = field[0][healChoice-1].startHealth;
        }
        break;
      case RETURN_TO_DECK:
        System.out.print("Would you like to return 1 of (Y)our animals or 1 of your (O)pponent's animals? ");
        char sideChoice = scn.next().toLowerCase().charAt(0);
        while(sideChoice != 'y' && sideChoice != 'o'){
          System.out.print("Would you like to return 1 of (Y)our animals or 1 of your (O)pponent's animals? ");
          sideChoice = scn.next().toLowerCase().charAt(0);
        }
        System.out.print("Which animal to return to deck? ");
        char returnChoice = scn.next().toLowerCase().charAt(0);
        int sideInt = 0;
        if(sideChoice == 'o'){
          sideInt = 1;
        }
        while(returnChoice < 1 || returnChoice > 4 ||field[sideInt][returnChoice-1] == null){
          System.out.print("Which animal to return to deck? ");
         returnChoice = scn.next().toLowerCase().charAt(0);
        }
        if(sideChoice == 'y'){
          player.deck.add(field[0][returnChoice-1]);
          field[0][returnChoice] = null;
        }
        else{
          computer.deck.add(field[1][returnChoice-1]);
          field[1][returnChoice] = null;
        }
        break;
      case ADD_BAIT:
        player.bait += 1;
        System.out.println("1 bait added.");
        break;
      case ADD_ATTACK:
        System.out.print("Choose animal to add attack: ");
        int addChoice = scn.nextInt();
        while(addChoice < 1 || addChoice > 4 ||field[0][addChoice-1] == null){
          System.out.print("Choose animal to add attack: ");
          addChoice = scn.nextInt();
        }
        field[0][addChoice-1].attack += 1;
        break;
    }
  }
}