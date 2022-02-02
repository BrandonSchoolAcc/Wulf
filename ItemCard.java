class ItemCard extends Card{

  String effect;

  public ItemCard(String name, String effect){
    super(name);
    this.effect = effect;

  }

  public void effectDesc(){
    switch(effect){
      case "test":
        System.out.print("test");
      default:
        System.out.print("INVALID");
        break;
    }
  }
}