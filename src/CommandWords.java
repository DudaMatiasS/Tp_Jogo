public class CommandWords {
    //essa classe reconhece os comandos digitados, ou seja, ela é responsável quando for adicionar um comando novo, o parser so ira interpretar o comando
    private static final String[] validCommands = {"go", "quit", "help", "look","back","take","drop","items"};
    public CommandWords(){
    }
    public boolean isCommand(String commandAvaliable) {
        for(int i=0;i<validCommands.length;i++){
            if(validCommands[i].equals(commandAvaliable)){
                return true;
            }
        }
        return false;
    }
    public void showAll(){
        String allComands = String.join(" ",validCommands);
        System.out.println(allComands);
    }
}
