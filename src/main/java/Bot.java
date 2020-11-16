import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import java.io.IOException;

public class Bot extends TelegramLongPollingBot {
    private final OutMessage outMessage = new OutMessage();
    private final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

    public Bot() throws IOException {
    }

    public synchronized void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage() && update.getMessage().hasText()) {
                Message inMessage = update.getMessage();
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(inMessage.getChatId());
                sendMessage.setText(this.getTextBot(inMessage));
                sendMessage.setReplyMarkup(replyKeyboardMarkup);
                this.execute(sendMessage);
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    public String getBotUsername() {
        return "Jdhxghsnzhxh_bot";
    }

    public String getBotToken() {
        return "1320077488:AAEeFK97PWC5zVgfbgllwzv04C-6iPHcSdA";
    }

    public String getTextBot(Message message) throws Exception {
        String userText = message.getText();
        if (userText.equals("/help")) return outMessage.help();
        if (userText.toLowerCase().equals("привет") || userText.toLowerCase().equals("меню")){
            return outMessage.getMessage(replyKeyboardMarkup);
        }
        return outMessage.findJoke(userText, message.getChatId().toString());
    }
}