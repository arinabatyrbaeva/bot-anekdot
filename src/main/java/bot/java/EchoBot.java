package bot.java;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import java.io.IOException;

public class EchoBot extends TelegramLongPollingBot {
    OutMessage outMessage = new OutMessage();
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

    public EchoBot() throws IOException {
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
        if (userText.equals("Меню") || userText.equals("привет")) {
            return this.outMessage.getMessage(replyKeyboardMarkup);
        }
        return userText.equals("/help") ? this.outMessage.help() : this.outMessage.findJoke(userText, message.getChatId().toString());
    }
}