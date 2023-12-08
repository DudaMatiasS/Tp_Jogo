import java.util.ArrayList;
import java.util.Stack;

public class Player {
    private Room roomPlayer;
    private int maxWeight;
    private boolean maxItens;
    private ArrayList<Item> itemInventory = new ArrayList<>();

    public Player() {
        maxWeight = 5000; //gramas
        maxItens = false;
    }

    private String getWherePlayerIs() {
        return roomPlayer.getLongDescription();
    }

    public void addItemInventory (Item takeItem){
        itemInventory.add(takeItem);

        if (inventoryWeight() > maxWeight){
            // System.out.println("You have to drop an item to take more");
            // removeItemInventory(takeItem); // chamar drop() de game
            maxItens = true;
        }
    }

    public void removeItemInventory(Item takeItem) {
        itemInventory.remove(takeItem);
        maxItens = false;
    }

    public boolean verifyWeight() {
        return maxItens;
    }
    
    public int inventoryWeight() {
        int inventWeight = 0;
        for (Item i : itemInventory){
            inventWeight += i.getWeigth();
        }
        return  inventWeight;
    }
}
