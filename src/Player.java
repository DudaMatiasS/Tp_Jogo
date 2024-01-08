import java.util.HashMap;
public class Player {
    private HashMap<String,Item> inventory = new HashMap<>();
    public Player(){
    }
    public boolean addItemInventory(String nameItem,Item takeItem){
        if((inventoryWeight() + takeItem.getWeigth()<= getMaxWeight())){
            inventory.put(nameItem,takeItem);
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
        String itemInventory;
        if(inventory.isEmpty()){
            itemInventory ="Opss, there's nothing here rsrsrs";
        }else{
            itemInventory="Here are the items in your inventory \u2ba7 \n";
            for (String i : inventory.keySet()) {
                itemInventory+= i+" | ";
            }
        }
        return itemInventory;
    }
}
