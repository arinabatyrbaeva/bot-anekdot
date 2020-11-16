import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.io.IOException;

public class StartBot {
    public static void main(String[] args) throws IOException {
        ApiContextInitializer.init();
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi();
            botsApi.registerBot(new Bot());
        }
        catch (TelegramApiRequestException e){
            e.printStackTrace();
        }
    }
}