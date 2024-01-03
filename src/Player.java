import java.util.HashMap;
public class Player {
    private int maxWeight;
    private HashMap<String,Item> inventory = new HashMap<>();
    public Player(){
        maxWeight =5000;//gramas

    }
    public boolean addItemInventory(String nomeItem,Item takeItem){
        if (inventory.containsKey("backpack")) {
            maxWeight =7500;
        }
        if((inventoryWeight() + takeItem.getWeigth()<= maxWeight)){
            inventory.put(nomeItem,takeItem);
            return true;
        }
        return false;
    }
    public Item getItemRemoved(String dropItem){
        if (inventory.get(dropItem) != null) {
            return inventory.get(dropItem);
        }
        inventory.remove(dropItem);
        return null;
    }
    public boolean verifyInventoryItem(String item){
        if(inventory.get(item) != null){
            return true;
        }
        return false;
    }
    public int inventoryWeight(){
        int invWeigth=0;
        for(Item i: inventory.values()){
            invWeigth+=i.getWeigth();
        }
        return invWeigth;
    }
    public String getItemsInventory() {
        String itemInventory = "| ";
        for (String i : inventory.keySet()) {
            itemInventory += i + " | ";
        }
        return itemInventory;
    }

}
