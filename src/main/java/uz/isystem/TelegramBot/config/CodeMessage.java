package uz.isystem.TelegramBot.config;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;

@Setter
@Getter
public class CodeMessage {

    private CodeMessageType type;
    private SendMessage sendMessage;
    private SendPhoto sendPhoto;
    private SendDocument sendDocument;
    private DeleteMessage deleteMessage;
    private EditMessageCaption editMessageCaption;

}
