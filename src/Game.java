import java.util.Stack;

public class Game {
    // processa o jogo

    private Player player;
    private Parser parser;
    private Stack<Room> places = new Stack<>();
    private Room currentRoom;
    public Game(){
        createRooms();
        parser = new Parser();
        player = new Player();
    }
    private void createRooms(){
        Room inside, kitchen, bathroom, bedroom, office, basement,livingRoom,secondFloor;
        inside = new Room("You are already inside the house");
        kitchen = new Room("in the kitchen");
        bathroom = new Room("in the bathroom");
        bedroom = new Room("in the bedroom");
        office = new Room("in the office");
        basement = new Room("in the basement");
        livingRoom = new Room("in the LivingRoom");
        secondFloor= new Room("in the second floor");


        inside.setExit("secondFloor",secondFloor);
        inside.setExit("basement",basement);
        inside.setExit("LivingRoom", livingRoom);
        inside.setExit("kitchen", kitchen);

        kitchen.setExit("LivingRoom", livingRoom);
        kitchen.setExit("secondFloor", secondFloor);

        livingRoom.setExit("kitchen", kitchen);
        livingRoom.setExit("secondFloor", secondFloor);

        basement.setExit("inside",inside);

        secondFloor.setExit("inside", inside);
        secondFloor.setExit("bedroom", bedroom);
        secondFloor.setExit("bathroom", bathroom);
        secondFloor.setExit("office", office);

        bedroom.setExit("secondFloor", secondFloor);

        office.setExit("secondFloor", secondFloor);

        bathroom.setExit("secondFloor",secondFloor);

        currentRoom =  inside;


        Item cuttingPliers, key, backpack,codPaper;

        cuttingPliers = new Item("pliers","And you can use to cut a pump wire",2000);
        key = new Item("key","And you can use to help defuse the bomb",3000);
        backpack = new Item("backpack","The backpack will help load more items",0);
        codPaper = new Item("paperCode","Keep this code that is on this paper to help you defuse the bomb",5000);


        livingRoom.addItems("pliers",cuttingPliers);
        office.addItems("key",key);
        bedroom.addItems("backpack",backpack);
        office.addItems("paperCode",codPaper);

    }
    public void play(){
        printWelcome();

        boolean finished = false;
        while(!finished){
            Command command = parser.getCommand();
            finished = processComand(command);
        }

    }
    private void printWelcome(){
        System.out.println("Welcome to the Bomb defuse");
        System.out.println("Bomb defuse is a new game and the name speaks for himself");
        System.out.println("Have fun trying to defuse the BOMBBBB!");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        LocationInfo();
    }

    private boolean processComand(Command command){

        boolean wantQuit = false;
        if (command.isUnknown()){
            System.out.println("I don't know what you mean...");
            return false;
        }
        String commandWord = command.getCommandWord();
        if(commandWord.equals("help")){
            printHelp();
        } else if (commandWord.equals("go")) {
            goRoom(command);
        }else if(commandWord.equals("quit")){
            wantQuit = quit(command);
        }else if (commandWord.equals("look")) {
            look();
        }else if (commandWord.equals("back")){
            back(command);
        } else if (commandWord.equals("take")) {
            take(command);
        } else if (commandWord.equals("drop")) {
            drop(command);
        }
        else if (commandWord.equals("items")) {
            items();
        }
        return  wantQuit;
    }
    private void goRoom(Command command){
        //places.push();
        if(!command.hasSecondWord()){
            System.out.println("Go where?");
            return;
        }
        String direction = command.getSecondWord();
        places.push(currentRoom);

        Room nextRoom = currentRoom.getExit(direction);

        if(nextRoom == null){
            System.out.println("There is no door!");
        }else{
            currentRoom = nextRoom;
            LocationInfo();
        }

    }
    private boolean quit(Command command){
        if (command.hasSecondWord()){
            System.out.println("Quit what?");
            return false;
        }else{
            return true;
        }
    }
    private void back(Command command){
        if (command.hasSecondWord()){
            System.out.println("You can only go back to your previous room");
            return;
        }else{
            currentRoom = places.pop();
            LocationInfo();
        }
    }
    private void take(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("What are you trying to take?");
            return;
        }
        String whatItem = command.getSecondWord();
        if (!player.verifyInventoryWeight()) {
            Item takeItem = currentRoom.getItem(whatItem);
            if (takeItem == null) {
                System.out.println("This item does not belong in this room");
            } else {
                player.addItemInventory(whatItem, takeItem);
                currentRoom.removeItem(whatItem);
            }
       } else {
            System.out.println("Your inventory is full, try to drop something before picking up that item\nCall the drop command");
        }

    }
    private void drop(Command command){
        if(!command.hasSecondWord()){
            System.out.println("What are you trying to drop?");
            return;
        }
        String whatItem = command.getSecondWord();
        if(player.verifyInventoryItem(whatItem)) {
            Item itemDropped = player.getItemRemoved(whatItem);
            player.removeEspecificItem(whatItem);
            currentRoom.addItems(whatItem, itemDropped);
        }else{
            System.out.println("You are trying to drop something that you don't have it yet");
        }
    }
    private void items(){
        String invItems = "Here are the items in your inventory: \n"+ player.getItemsInventory();
        System.out.println(invItems);
        System.out.println(player.inventoryWeight());
    }
    private void look(){
        LocationInfo();
    }
    private void printHelp(){
        System.out.println("You don't know what are you doing, right?");
        System.out.println("Sorry, i will try to help you");
        System.out.println();
        System.out.println("You are alone in the house and you have to find where is the bomb...");
        System.out.println("Good luck! Don't let the things blow up");
        System.out.println("Your command words are: ");
        parser.showCommands();
    }

    public void LocationInfo(){
        System.out.println(currentRoom.getLongDescription());
    }

}
