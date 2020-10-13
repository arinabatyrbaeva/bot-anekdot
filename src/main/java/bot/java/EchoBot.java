package bot.java;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import java.util.*;



public class EchoBot extends TelegramLongPollingBot{

    OutMessage outMessage = new OutMessage();

    @Override
    public void onUpdateReceived(Update update) {
        try {
            //проверяем есть ли сообщение и текстовое ли оно
            if (update.hasMessage() && update.getMessage().hasText()) {
                //Извлекаем объект входящего сообщения
                Message inMessage = update.getMessage();
                //Создаем исходящее сообщение
                SendMessage outMessage = new SendMessage();
                //Указываем в какой чат будем отправлять сообщение
                //(в тот же чат, откуда пришло входящее сообщение)
                outMessage.setChatId(inMessage.getChatId());
                //Указываем текст сообщения
                outMessage.setText(getTextBot(inMessage));
                //Отправляем сообщение
                execute(outMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "Jdhxghsnzhxh_bot";
    }

    @Override
    public String getBotToken() {
        return "1320077488:AAEeFK97PWC5zVgfbgllwzv04C-6iPHcSdA";
    }

    public String getTextBot(Message message) throws Exception {
        String userText = message.getText();
        if (userText.equals("/help")){
            return outMessage.help();
        }
        return outMessage.findJoke(userText);
    }
}
