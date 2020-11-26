import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


class test extends SplitJokes {
    public test() throws IOException {
    }
    OutMessage message = new OutMessage();
    StringBuilder fileText = new StringBuilder();
    @Test
    void testMessage() throws Exception {
        String firstDoctor = message.findJoke("Про доктора", "1");
        Assertions.assertEquals(firstDoctor.split(" ")[0], "Зубной");
        String secondDoctor = message.findJoke("Про доктора", "1");
        Assertions.assertEquals(secondDoctor.split(" ")[0], "Бокс");
        String firstLove = message.findJoke("Про любовь", "1");
        Assertions.assertEquals(firstLove.split(" ")[0], "Девушка");
        String secondLove = message.findJoke("Про любовь", "2");
        Assertions.assertEquals(secondLove.split(" ")[0], "Девушка");
    }

    @Test
    void testWebJoke() throws Exception {
        WebJoke webJoke = new WebJoke();
        String randomJoke = message.findJoke("случайный анекдот", "1");
        Assertions.assertFalse(randomJoke.split(" ")[1].isEmpty());
        String testFile = getClass().getResource("testWebJoke").getFile();
        var listJokes = readFile(testFile);
        Assertions.assertEquals(listJokes.get(0), "<div class=\"anekdot\">");
        Assertions.assertEquals(listJokes.get(1), " Умер человек. Его пес рядом лег и тоже умер. ");
        var textJokes = webJoke.parseSite(fileText.toString());
        var words = textJokes.get(0).split(" ");
        Assertions.assertEquals(words[1], "Умер");
        var jokes = splitText(textJokes);
        var firstJoke = jokes.get(0).toString().split(" ");
        Assertions.assertEquals(firstJoke[1], "Умер");
        Assertions.assertEquals(firstJoke[firstJoke.length - 1], "друзей.");
    }

    private ArrayList<String> readFile(String file) throws Exception{
        ArrayList<String> listLines = new ArrayList<>();
        FileReader rf = new FileReader(file);
        try (rf) {
            Scanner scan = new Scanner(rf);
            while (scan.hasNextLine()) {
                listLines.add(scan.nextLine());
            }
        }
        for (var line: listLines){
            fileText.append(line);
            fileText.append("\n");
        }
        return listLines;
    }
}
