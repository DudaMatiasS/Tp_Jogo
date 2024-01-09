import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Parser {
    // é responsavel por interpretar os comandos digitado!
    private CommandWords commands;
    private Scanner reader;
    private int contMoviments;
    public Parser(){
        commands = new CommandWords();
        reader = new Scanner(System.in);
        contMoviments = 0;
    }
    public Command getCommand() {
        String inputLine;
        String word1 = null;
        String word2 = null;

        System.out.print("> ");

        inputLine = this.reader.nextLine();

        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();      // get first word
            contMoviments++;
            if(word1.equals("moves")){
                contMoviments--;
            }
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next();      // get second word
                // note: we just ignore the rest of the input line.
            }
        }
        if(commands.isCommand(word1)) { // ver se o firstCommand é válido
            return new Command(word1, word2);
        }
        else {
            return new Command(null, word2);
        }
    }
    public void showCommands(){
        commands.showAll();
    }
    public int getMoviments(){
        return contMoviments;
    }
    public String time(LocalTime initialHour) {
        LocalTime now = LocalTime.now();
        long hoursDifference = ChronoUnit.HOURS.between(initialHour, now);
        long minutesDifference = ChronoUnit.MINUTES.between(initialHour, now);
        long secondsDifference = ChronoUnit.SECONDS.between(initialHour, now);

        return formatTime(hoursDifference) + ":" + formatTime(minutesDifference) + ":" + formatTime(secondsDifference);
    }
    private String formatTime(long time) {
        return time > 9 ? String.valueOf(time) : "0" + time;
    }
}
