package bot.java;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.Jsoup;


public class WebJoke extends SplitJokes {
    private final Map<String, Integer> usersNumbers = new HashMap<String, Integer>();
    public ArrayList<StringBuilder> jokes;
    public WebJoke() throws IOException {
        Document site = Jsoup.connect("http://anekdotov.net/").get();
        String jokes = site.select("div.anekdot").toString();
        this.jokes = SplitText(ParseSite(jokes));
        this.jokes.remove(0);
    }

    public String GetJoke(String id){
        if (!usersNumbers.containsKey(id)) usersNumbers.put(id, 0);
        usersNumbers.put(id, usersNumbers.get(id) + 1);
        if (usersNumbers.get(id) == jokes.size() - 1) usersNumbers.put(id, 1);
        return jokes.get(usersNumbers.get(id)).toString();
    }

    private ArrayList<String> ParseSite(String jokes){
        ArrayList<String> listJokes = new ArrayList<String>();
        for (String line: jokes.split("\n")) {
            if (line.charAt(0) == '<') {
                char secondLetter = line.charAt(1);
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