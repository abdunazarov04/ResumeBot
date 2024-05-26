package uz.isystem.TelegramBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import uz.isystem.TelegramBot.config.BotConfig;

@SpringBootApplication
public class TelegramBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelegramBotApplication.class, args);

        try {
            TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
			api.registerBot(new BotConfig());

        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }

}
