import java.util.Stack;

public class Game {
    private Player player;
    private Parser parser;
    private Stack<Room> places = new Stack<>();
    private Room currentRoom;
    private int estado;
    private int timeToExplode;
    private Room basement;
    public Game(){
        createRooms();
        parser = new Parser();
        player = new Player();
        estado=0;
        timeToExplode=20;
    }
    public void play(){
        printWelcome();
        boolean isFinished=false;

        while(!isFinished){
            Command command = parser.getCommand();
            isFinished = processComand(command);
        }

    }

    private void createRooms(){
        Room firstFloor, kitchen, bathroom, bedroom, office,livingRoom,secondFloor;
        firstFloor = new Room("already in the firstFloor of the house");
        kitchen = new Room("in the kitchen");
        bathroom = new Room("in the bathroom");
        bedroom = new Room("in the bedroom");
        office = new Room("in the office");
        basement = new Room("in the basement. Look at him!\nNow that you are here and you have the necessary items\nThe Bomb is inside the suitcase");
        livingRoom = new Room("in the livingRoom");
        secondFloor= new Room("in the second floor");

        firstFloor.setExit("secondFloor",secondFloor);
        firstFloor.setExit("basement",basement);
        firstFloor.setExit("livingRoom", livingRoom);
        firstFloor.setExit("kitchen", kitchen);

        kitchen.setExit("livingRoom", livingRoom);
        kitchen.setExit("secondFloor", secondFloor);
        kitchen.setExit("basement", basement);

        livingRoom.setExit("kitchen", kitchen);
        livingRoom.setExit("secondFloor", secondFloor);
        livingRoom.setExit("basement",basement);

        basement.setExit("firstFloor",firstFloor);

        secondFloor.setExit("firstFloor", firstFloor);
        secondFloor.setExit("bedroom", bedroom);
        secondFloor.setExit("bathroom", bathroom);
        secondFloor.setExit("office", office);

        bedroom.setExit("secondFloor", secondFloor);

        office.setExit("secondFloor", secondFloor);

        bathroom.setExit("secondFloor",secondFloor);

        currentRoom =  firstFloor;

        Item cuttingPliers, key, backpack,codPaper,knife,scisors,greekStatue,monaLisaPainting,suitcase;
        suitcase = new Item("suitcase","This is the case that contains the bomb, to open it you must have the key in your inventory",10000);
        cuttingPliers = new Item("pliers","Is used to cut a bomb wire",3);
        key = new Item("key","Is used to open the suitcase",300);
        backpack = new Item("backpack","The backpack will help you to carry more items",0);
        codPaper = new Item("paperCode","Has a code inside. You will need it to use when you are defusing the bomb",200);
        knife = new Item("knife","A knife is used to cut everything",2000);
        scisors = new Item("scisors","With a scissors you can cut and build different things",2500);
        greekStatue= new Item("greekStatue","It is possible to see a beautiful statue of Pissed Zeus",5500);
        monaLisaPainting = new Item("MonaLisaPainting","It is an art painting painted by Leonardo Di Caprio",2300);

        basement.addItems("suitcase",suitcase);
        firstFloor.addItems("greekStatue",greekStatue);
        secondFloor.addItems("monaLisaPainting",monaLisaPainting);
        kitchen.addItems("knife",knife);
        office.addItems("scisors",scisors);
        livingRoom.addItems("pliers",cuttingPliers);
        bathroom.addItems("key",key);
        bedroom.addItems("backpack",backpack);
        office.addItems("paperCode",codPaper);


    }
    private void printWelcome(){
        System.out.println("Welcome to the Bomb defuse");
        System.out.println("Bomb defuse is a new game and the name speaks for itself!!!");
        System.out.println();
        System.out.println("The Bomb are at the basement and you have to collect the following items:\n1. A key to unlock the suitcase\n2. A pliers to cut the bomb wires\n3. Finally, a cod paper which has the cod to defuse the bomb!");
        System.out.println();
        System.out.println("Explore the house, have fun, pick up the items and remember, your time is short!");
        System.out.println();
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        locationInfo();
    }
    public void locationInfo(){
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processComand(Command command){

        boolean wantQuit = false;
        if (parser.getMoviments()==timeToExplode) {
            System.out.println("THE BOMB EXPLODED!!!\nYou couldn't defuse it in time and you are dead\nByyyeeee!");
            return true;
        }
        if (command.isUnknown()){
            System.out.println("I don't know what you mean...");
            return false;
        }
        String commandWord = command.getCommandWord();
        switch (commandWord) {
            case "help" -> printHelp();
            case "go" -> goRoom(command);
            case "quit" -> wantQuit = quit(command);
            case "look" -> look();
            case "back" -> back(command);
            case "take" -> take(command);
            case "drop" -> drop(command);
            case "items" -> items();
            case "use" -> use(command);
            case "time" -> time();
        }
        return  wantQuit;
    }
    private void printHelp(){
        System.out.println("You don't know what are you doing, right?");
        System.out.println("Sorry, i will try to help you");
        System.out.println();
        System.out.println("The Bomb are at the basement and you have to collect the following items:\n1. A key to unlock the suitcase\n2. A pliers to cut the bomb wires\n3. Finally, a cod paper whic has the cod to defuse the bomb!");
        System.out.println();
        System.out.println("Good luck! Don't let the things blow up");
        System.out.println("Your command words are: ");
        System.out.println();
        parser.showCommands();
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
            locationInfo();
        }
    }
    private boolean quit(Command command){
        if (command.hasSecondWord()){
            System.out.println("Quit what?");
            return false;
        }else{
            System.out.println("I'm disappointed with you, but we'll see you again... (I HOPE)");
            return true;
        }
    }
    private void look(){
        locationInfo();
    }
    private void back(Command command){
        if (command.hasSecondWord()){
            System.out.println("You can only go back to your previous room");
            return;
        }else{
            if(places.isEmpty()){
                System.out.println("There's nowhere to go back cumpadre!");
                return;
            }else{
                currentRoom = places.pop();
                locationInfo();
            }

        }
    }
    private void take(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("What are you trying to take?");
            return;
        }
        String whatItem = command.getSecondWord();
        if(whatItem.equals("suitcase")){
            System.out.println("You can't take the suitcase, you need to unlock it");
            return;
        }else {
            Item takeItem = currentRoom.getItem(whatItem);
            if (takeItem == null) {
                System.out.println("This item does not belong in this room");
            } else {
                if (player.addItemInventory(whatItem, takeItem)) {
                    currentRoom.removeItem(whatItem);
                    System.out.println("Done!");
                } else {
                    System.out.println("Your inventory is full\nYou can try looking for a backpack or use the drop command, good luck (you will need) :)");
                }
            }
        }

    }
    private void drop(Command command){
        if(!command.hasSecondWord()){
            System.out.println("Drop whaaaaat?");
            return;
        }
        String whatItem = command.getSecondWord();


        if (whatItem.equals("backpack")) {
            System.out.println("You cannot drop your backpack");
            return;
        }else {
            if (player.verifyInventoryItem(whatItem)) {
                Item itemDropped = player.getItemRemoved(whatItem);
                currentRoom.addItems(whatItem, itemDropped);
                System.out.println("Done!");
            } else {
                System.out.println("You are trying to drop something that you don't have it yet");
            }
        }

    }
    private void items(){
        String invItems = player.getItemsInventory();
        System.out.println(invItems);
    }
    private void use(Command command){
        if(currentRoom==basement){
            if(!command.hasSecondWord()){
                System.out.println("What are you trying to useee?!");
                return;
            }else {
                String itemUSed = command.getSecondWord();
                boolean hasItems = player.verifyInventoryItem(itemUSed);
                if(hasItems){
                    if(estado==0){
                        if (itemUSed.equals("key")) {
                            estado = 1;
                            System.out.println("Alright, you opened the suitcase\nHere is the bomb, I'm praying that you have the necessary items to defuse it.\nUse your items");

                        } else {
                            System.out.println("You must use the key first to open the suitcase");
                            return;
                        }

                    }else if (estado==1) {
                        if(itemUSed.equals("pliers")){
                            estado=2;
                            System.out.println("Cool, you cut the right wire, the only thing left is the last step\nYou can do it ");

                        }else if(itemUSed.equals("paperCode")){
                            estado =3;
                            System.out.println("Cool, you entered the code correctly, the only thing left is the last step\nYou can do it ");

                        }else{
                            System.out.println("You need to cut the wire or enter the code to defuse the bomb");
                            return;
                        }
                    } else if (estado==2) {
                        if(itemUSed.equals("paperCode")){
                            estado=4;
                            System.out.println("You done it\nBut why the bomb is still activated.....");
                            System.out.println();
                            System.out.println();
                            if (estado==4) {
                                System.out.println("I just wanted to scare you a little haha. SORRY!\nYou defused the bomb\nCONGRATULATIONS YOU WON'T DIE");

                            }
                        }else{
                            System.out.println("You need to enter the code to defuse the bomb");
                            return;
                        }
                    }else if(estado==3) {
                        if (itemUSed.equals("pliers")) {
                            estado = 4;
                            System.out.println("You done it\nBut why the bomb is still activated.....");
                            System.out.println();
                            System.out.println();
                            if (estado == 4) {
                                System.out.println("I just wanted to scare you a little haha. SORRY!\nYou defused the bomb\nCONGRATULATIONS YOU WON'T DIE");

                            }
                        } else {
                            System.out.println("You need to cut the wire");
                            return;
                        }
                    }
                }else{
                    System.out.println("You need to capture this item first, then you can use it to defuse the bomb");
                   return;
                }
            }
        }else{
            System.out.println("You can't use the use command without being in the basement, remember?");
            return;
        }
    }
    private void time(){
        System.out.println("You have "+(timeToExplode-parser.getMoviments())+" moves to defuse the bomb");

    }

}