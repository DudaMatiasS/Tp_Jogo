import java.util.HashMap;
public class Player {
    private HashMap<String,Item> inventory = new HashMap<>();
    public Player(){
    }
    public boolean addItemInventory(String nomeItem,Item takeItem){
        if((inventoryWeight() + takeItem.getWeigth()<= getMaxWeight())){
            inventory.put(nomeItem,takeItem);
            return true;
        }
        return false;
    }
    public Item getItemRemoved(String dropItem){
        return inventory.remove(dropItem);
    }
    public boolean verifyInventoryItem(String item){
        if(inventory.get(item) != null){
            return true;
        }
        return false;
    }
    public int getMaxWeight(){
        int maxWeight= 5000;//gramas
        if (inventory.containsKey("backpack")) {
            maxWeight =10500;
        }
        return maxWeight;
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
