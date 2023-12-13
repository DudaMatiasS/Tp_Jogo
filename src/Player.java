import java.util.ArrayList;

import java.util.Stack;

public class Player {
    private Room roomPlayer;
    private int maxWeight;
    private ArrayList<Item> itemInventory= new ArrayList<>();
    public Player(){
        maxWeight =5000;//gramas
    }
    private String getWherePlayerIs(){
        return roomPlayer.getLongDescription();
    }
    public void addItemInventory(Item takeItem){
        itemInventory.add(takeItem);
    }
    public  void removeItemInventory(Item takeItem){
        itemInventory.remove(takeItem);
    }
    public ArrayList<Item> getItemInventory() {
        return itemInventory;
    }

    public int inventoryWeight(){
        int inventWeight = 0;
        for(Item i: itemInventory){
            inventWeight+=i.getWeigth();
        }
        return  inventWeight;
    }
    public boolean verifyInventoryWeight(){
        if(inventoryWeight()>=maxWeight){
            return true;
        }
        return false;
    }
}
