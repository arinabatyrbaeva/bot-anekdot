import java.util.ArrayList;

public class SplitJokes {
    public ArrayList<StringBuilder> splitText(ArrayList<String> jokes){
        ArrayList<StringBuilder> listJokes = new ArrayList<>();
        StringBuilder joke = new StringBuilder();
        for (String line : jokes) {
            if (line.isEmpty() || line.equals("\n")) {
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
