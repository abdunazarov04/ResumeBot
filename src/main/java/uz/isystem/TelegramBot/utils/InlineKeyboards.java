package uz.isystem.TelegramBot.utils;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class InlineKeyboards {
    public static InlineKeyboardButton button(String text, String callBackDate, String emoji) {
        String emojiText = EmojiParser.parseToUnicode(text + " " + emoji);

        return button(emojiText, callBackDate);
    }

    public static InlineKeyboardButton button(String text, String callBackDate) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callBackDate);
        return button;
    }

    public static InlineKeyboardButton button(String text, String url, boolean boolUrl) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        if (boolUrl) {
            button.setUrl(url);
        }
        return button;
    }

    public static List<InlineKeyboardButton> row(InlineKeyboardButton... inlineKeyboardButtons) {
        return new LinkedList<>(Arrays.asList(inlineKeyboardButtons));
    }

    public static List<List<InlineKeyboardButton>> collection(List<InlineKeyboardButton>... rows) {
        return new LinkedList<>(Arrays.asList(rows));
    }

    public static InlineKeyboardMarkup keyboardMarkup(List<List<InlineKeyboardButton>> collections) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.setKeyboard(collections);
        return keyboardMarkup;
    }
}
