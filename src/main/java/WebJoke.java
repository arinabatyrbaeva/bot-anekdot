import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class WebJoke extends SplitJokes{
    private final Map<String, Integer> usersNumbers = new HashMap<String, Integer>();
    public ArrayList<StringBuilder> jokes;
    public WebJoke() throws IOException {
        Document site = Jsoup.connect("http://anekdotov.net/").get();
        String jokes = site.select("div.anekdot").toString();
        this.jokes = splitText(parseSite(jokes));
    }

    public String getJoke(String id){
        if (!usersNumbers.containsKey(id)) usersNumbers.put(id, 0);
        usersNumbers.put(id, usersNumbers.get(id) + 1);
        if (usersNumbers.get(id) == jokes.size() - 1) usersNumbers.put(id, 1);
        return jokes.get(usersNumbers.get(id)).toString();
    }

    public ArrayList<String> parseSite(String jokes){
        ArrayList<String> listJokes = new ArrayList<String>();
        for (String line: jokes.split("\n")) {
            if (line.charAt(0) == '<') {
                var secondLetter = line.charAt(1);
                if (secondLetter == 'd') continue;
                if (secondLetter == '/') {
                    listJokes.add("\n");
                    continue;
                }
            }
            if (line.charAt(1) == '<') {
                listJokes.add(line.substring(5));
                continue;
            }
            listJokes.add(line);
        }
        return listJokes;
    }
}
