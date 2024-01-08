public class Item {
    private String des;
    private int weigth;
    private String name;

    public Item(String name, String description,int weight){
        this.name = name;
        this.des = description;
        this.weigth = weight;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return des;
    }
    public int getWeigth(){
        return weigth;
    }
    public String getItemLongDescription(){
        return getName()+" \u2b62 "+getDescription()+". It weighs: "+getWeigth()+" grams";
    }

}
