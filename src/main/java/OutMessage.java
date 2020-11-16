import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

public class OutMessage extends SplitJokes {
    private final Map<String, ArrayList<Integer>> numberJokes = new HashMap<>();
    private String fileName;
    private Integer number;
    private final WebJoke joke = new WebJoke();

    public OutMessage() throws IOException {
    }

    public String help() {
        return "Бот присылает анекдоты 3 категорий. Для получения анекдота введите одну" +
                "из тем: про доктора, про любовь, про школу, про соседей";
    }

    public String findJoke(String message, String id) throws Exception {
        if (!numberJokes.containsKey(id)){
            ArrayList<Integer> numbers = new ArrayList<>();
            for (int i = 0; i <= 3; i++){
                numbers.add(-1);
            }
            numberJokes.put(id, numbers);
        }

        message = message.toLowerCase();
        if (message.equals("про доктора"))
            makeNumberJoke(getClass().getResource("doctor.txt").getFile(), 0, id);

        if (message.equals("про любовь"))
            makeNumberJoke(getClass().getResource("love.txt").getFile(), 1, id);

        if (message.equals("про соседей"))
            makeNumberJoke(getClass().getResource("neighbour.txt").getFile(), 2, id);

        if (message.equals("про школу"))
            makeNumberJoke(getClass().getResource("school.txt").getFile(), 3, id);

        if (message.equals("случайный анекдот"))
            return joke.getJoke(id);

        return makeFileReader(fileName).get(numberJokes.get(id).get(number)).toString();
    }

    private void makeNumberJoke(String file, Integer num, String id){
        fileName = file;
        numberJokes.get(id).set(num, numberJokes.get(id).get(num) + 1);
        number = num;
        if (numberJokes.get(id).get(num) == 9) numberJokes.get(id).set(num, 0);
    }

    private ArrayList<StringBuilder> makeFileReader(String file) throws Exception {
        ArrayList<String> listLines = new ArrayList<>();
        FileReader rf = new FileReader(file);
        try (rf) {
            Scanner scan = new Scanner(rf);
            while (scan.hasNextLine()) {
                listLines.add(scan.nextLine());
            }
        }
        return splitText(listLines);
    }

    public String getMessage(ReplyKeyboardMarkup replyKeyboardMarkup) {
        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        keyboardFirstRow.add("про доктора");
        keyboardFirstRow.add("про любовь");
        keyboardSecondRow.add("про соседей");
        keyboardSecondRow.add("про школу");
        keyboardSecondRow.add("случайный анекдот");
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
        return "выбрать...";
    }
}
