package uz.isystem.TelegramBot.utils;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class KeyboardMarkup {


    public static KeyboardButton button(String text) {
        KeyboardButton button = new KeyboardButton();
        button.setText(text);
        return button;
    }
    public static KeyboardButton button(String text, boolean contact) {
        KeyboardButton button = new KeyboardButton();
        button.setText(text);
        button.setRequestContact(contact);
        return button;
    }

    public static KeyboardButton button(String text, String emoji) {
        String emojiText = EmojiParser.parseToUnicode(emoji + " " + text);

        return button(text, emojiText);
    }

    public static List<KeyboardRow> row (KeyboardButton... buttons){
        KeyboardRow row = new KeyboardRow();
        row.addAll(Arrays.asList(buttons));
        return Collections.singletonList(row);
    }

    public static ReplyKeyboardMarkup keyboardMarkup(List<KeyboardRow>... rows){
        ReplyKeyboardMarkup replyMarkup = new ReplyKeyboardMarkup();
        replyMarkup.setOneTimeKeyboard(true);
        replyMarkup.setInputFieldPlaceholder("Nima gap...");
        replyMarkup.setResizeKeyboard(true);
        List<KeyboardRow> allRows = new LinkedList<>();

        for (List<KeyboardRow> row : rows) {
            allRows.addAll(row);
        }
        replyMarkup.setKeyboard(allRows);
        return replyMarkup;
    }


}
