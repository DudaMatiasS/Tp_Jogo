import java.util.HashMap;
public class Room {
    //responsavel pelas salas
    private String description;
    private HashMap<String, Room> room = new HashMap<>();
    private HashMap<String, Item> items = new HashMap<>();
    private String infoItemDescription;

    public Room(String description) {
        this.description = description;
        infoItemDescription = " ";
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
            infoExit += saida + " | ";
        }
        return infoExit;
    }
    public String getLongDescription() {
        return "You are " + getDescription() + ".\n" + getExitString() + "\n" + locationItem();
    }
    /**
     * @return The description of the room.
     */
    public String getDescription() {
        return description;
    }
    public void addItems(String nomeItem, Item item) {
        items.put(nomeItem, item);
    }

    public Item getItem(String item){
        if (items.get(item) != null) {
            return items.get(item);
        }
        return null;
    }
    public void removeItem(String nomeItem){
        items.remove(nomeItem);
    }
    public String locationItem() {
        String infoItems = "Items: ";

        for (String locationItem : items.keySet()) {
            String infoItem = locationItem;
            infoItems +="\n"+infoItem+" -> "+items.get(infoItem).getItemLongDescription();
        }
        return infoItems;
    }





}
