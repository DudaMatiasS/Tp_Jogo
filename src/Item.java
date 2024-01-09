public class Item {
    private String des;
    private int weight;
    private String name;

    public Item(String name, String description,int weight) {
        this.name = name;
        this.des = description;
        this.weight = weight;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return des;
    }
    public int getWeight() {
        return weight;
    }
    public String getItemLongDescription() {
        return getName()+ " \u2b62 " + getDescription() + ". It weighs: " + getWeight() + " grams";
    }

}
