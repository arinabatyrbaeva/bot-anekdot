package bot.java;


import java.io.FileReader;
import java.util.*;

public class OutMessage {
    String outText;
    ArrayList<Integer> numberJoke = new ArrayList<>();
    OutMessage(){
        this.outText = "a";
        for (Integer i = 0; i < 4; i++)
            numberJoke.add(-1);
    }
    public String help(){
        return outText;
    }
    public String findJoke(String message) throws Exception {
        String fileName = " ";
        Integer number = 0;
        message = message.toLowerCase();
        if (message.equals("про доктора")) {
            fileName = "C:/Users/админ/IdeaProjects/untitled1/src/main/java/bot/java/jokes/анекдоты про доктора.txt";
            numberJoke.set(0, numberJoke.get(0) + 1);
            number = 0;
            if (numberJoke.get(0) == 9) numberJoke.set(0, 0);
        }
        if (message.equals("про любовь")) {
            fileName = "C:/Users/админ/IdeaProjects/untitled1/src/main/java/bot/java/jokes/анекдоты про любовь.txt";
            numberJoke.set(1, numberJoke.get(1) + 1);
            number = 1;
            if (numberJoke.get(1) == 9) numberJoke.set(1, 0);
        }
        if (message.equals("про соседей")) {
            fileName = "C:/Users/админ/IdeaProjects/untitled1/src/main/java/bot/java/jokes/анекдоты про соседей.txt";
            numberJoke.set(2, numberJoke.get(2) + 1);
            number = 2;
            if (numberJoke.get(2) == 9) numberJoke.set(2, 0);
        }
        if (message.equals("про школу")) {
            fileName = "C:/Users/админ/IdeaProjects/untitled1/src/main/java/bot/java/jokes/анекдоты про школу.txt";
            numberJoke.set(3, numberJoke.get(3) + 1);
            number = 3;
            if (numberJoke.get(3) == 9) numberJoke.set(3, 0);
        }
        return makeFileReader(fileName).get(numberJoke.get(number)).toString();
    }

    private ArrayList<StringBuilder> makeFileReader(String file) throws Exception {
        ArrayList<String> listLines = new ArrayList<>();
        FileReader rf = new FileReader(file);
        Scanner scan = new Scanner(rf);
        while (scan.hasNextLine()){
            listLines.add(scan.nextLine());
        }
        rf.close();
        return makeListJokes(listLines);
    }

    private ArrayList<StringBuilder> makeListJokes(ArrayList<String> jokes){
        ArrayList<StringBuilder> listJokes = new ArrayList<>();
        StringBuilder joke = new StringBuilder();
        for (String line: jokes
        ) {
            if (line.isEmpty()) {
                listJokes.add(joke);
                joke = new StringBuilder();
                continue;
            }
            joke.append(line);
        }
        listJokes.add(joke);
        return listJokes;
    }
}