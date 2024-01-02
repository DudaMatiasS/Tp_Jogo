import java.util.ArrayList;

import java.util.HashMap;
import java.util.Stack;

public class Player {
    private Room roomPlayer;
    private int maxWeight;

    private HashMap<String,Item> inventory = new HashMap<>();
    public Player(){
        maxWeight =5000;//gramas
    }
    private String getWherePlayerIs(){
        return roomPlayer.getLongDescription();
    }
    public void addItemInventory(String nomeItem,Item takeItem){
        inventory.put(nomeItem,takeItem);
        if (inventory.containsKey("backpack")) {
            maxWeight =7500;
        }
    }
    public Item getItemRemoved(String dropItem){
        if (inventory.get(dropItem) != null) {
            return inventory.get(dropItem);
        }
        return null;
    }
    public void removeEspecificItem(String dropItem){
        inventory.remove(dropItem);
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
    public boolean verifyInventoryWeight(){
        return inventoryWeight()>maxWeight;
    }
    public String getItemsInventory() {
        String itemInventory = "| ";
        for (String i : inventory.keySet()) {
            itemInventory += i + " | ";
        }
        return itemInventory;
    }

}
