import java.util.HashMap;
public class Room {
    //responsavel pelas salas
    private String description;
    private HashMap<String, Room> room = new HashMap<>();
    private HashMap<String, Item> items = new HashMap<>();
    private String infoItem;

    public Room(String description) {
        this.description = description;
        infoItem = " ";
    }

    public void setExit(String direction, Room sala) {
        room.put(direction, sala);
    }

    public Room getExit(String direction) {// verifica se a direção existe
        if (room.get(direction) != null) {
            return room.get(direction); //retorna o room
        }
        return null;
    }
    public String getExitString() {
        String infoExit = "Exits: ";
        //Set<String> chave = room.keySet();set é um objeto que ira guardar as keys(direction) de room
        for (String saida : room.keySet()) {
            infoExit += saida + ", ";
        }
        return infoExit;
    }
    public String getLongDescription() {
        return "You are " + getDescription() + ".\n" + getExitString() + "\n\n" + locationItem()+infoItem;
    }
    
    /**
     * @return The description of the room.
     */

    public String getDescription() {
        return description;
    }

    public void addItems(String nomeItem, Item item) {
        items.put(nomeItem, item);
        infoItem = "\n" + items.get(nomeItem).getItemLongDescription();
    }

    public String locationItem() {
        String infoItem = "Items: ";
        for (String locationItem : items.keySet()) {
            infoItem += locationItem + ", ";
        }
        return infoItem;
    }

    public Item getItem(String item){
        if (items.get(item) != null) {
            return items.get(item);
        } else{
            infoItem = " ";
        }

        return null;
    }

    public void removeItem(String nomeItem){
        items.remove(nomeItem);
    }
}
