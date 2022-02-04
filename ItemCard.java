class ItemCard extends Card{

  String effect;

  public ItemCard(String name, String effect){
    super(name);
    this.effect = effect;

  }

  public String effectDesc(){
    switch(effect){
      case "test":
        return "test";
      default:
        return "INVALID";
    }
  }
}