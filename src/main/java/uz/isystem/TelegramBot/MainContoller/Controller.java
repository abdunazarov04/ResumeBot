package uz.isystem.TelegramBot.MainContoller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import uz.isystem.TelegramBot.utils.InlineKeyboards;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static uz.isystem.TelegramBot.utils.KeyboardMarkup.button;
import static uz.isystem.TelegramBot.utils.KeyboardMarkup.keyboardMarkup;
import static uz.isystem.TelegramBot.utils.KeyboardMarkup.row;

@org.springframework.stereotype.Controller
public class Controller {


    private static final Logger log = LoggerFactory.getLogger(Controller.class);

    public SendMessage startHandler(Long chatId, boolean text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        if (text) {
            sendMessage.setText("Assalomu Alaykum botga xush kelibsiz...");
        } else {
            sendMessage.setText("Siz bosh menudasiz...");
        }
        sendMessage.setReplyMarkup(
                keyboardMarkup(
                        row(button("Foundation \uD83E\uDDD1\u200D\uD83D\uDCBB"), button("Backend \uD83D\uDC68\u200D\uD83D\uDCBB")),
                        row(button("Vazifalar \uD83D\uDCD4")),
                        row(button("Men haqimda \uD83D\uDC40"))
                )
        );
        return sendMessage;
    }

    /*public SendMessage commandNotFound(Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Buyruq topilmadi!!!");
        sendMessage.setChatId(chatId);

        return sendMessage;
    }*/

    public SendDocument aboutMeHandler(Long chatId) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId);
        sendDocument.setCaption("<i>Men haqimda to'liqroq o'qing</i>");
        sendDocument.setReplyMarkup(keyboardMarkup(
                row(button("Ortga"))
        ));
        sendDocument.setParseMode(ParseMode.HTML);
        sendDocument.setDocument(new InputFile(new File("/Users/macbookair/isystem/TelegramBot/src/main/java/uz/isystem/TelegramBot/images/Asadbek-Abdinazarov.pdf")));
        return sendDocument;
    }

    public SendPhoto foundationHandler(Long chatId) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        try {
            sendPhoto.setCaption(Files.readString(Path.of("/Users/macbookair/isystem/TelegramBot/src/main/java/uz/isystem/TelegramBot/TXT/FoundationInfo.txt")));
        } catch (IOException e) {
            log.error("Foundation txt file topilmadi yoki qandaydur xatolik shep.");
        }
        sendPhoto.setPhoto(new InputFile(new File("/Users/macbookair/isystem/TelegramBot/src/main/java/uz/isystem/TelegramBot/images/FoundationPhoto.png")));
        sendPhoto.setAllowSendingWithoutReply(true);

        sendPhoto.setReplyMarkup(
                InlineKeyboards.keyboardMarkup(
                        InlineKeyboards.collection(
                                InlineKeyboards.row(
                                        InlineKeyboards.button("\uD83D\uDC49 Online \uD83D\uDC48", "/foundation/online"),
                                        InlineKeyboards.button("\uD83D\uDC49 Offline \uD83D\uDC48", "/foundation/offline")
                                ),
                                InlineKeyboards.row(
                                        InlineKeyboards.button("Ortga ⬅️", "/back")
                                )
                        )
                )
        );
        return sendPhoto;
    }

    public EditMessageCaption onlineCourseHandler(Integer messageId, Long id) {
        EditMessageCaption editMessageCaption = new EditMessageCaption();
        editMessageCaption.setChatId(id);
        editMessageCaption.setMessageId(messageId);
        try {
            editMessageCaption.setCaption(Files.readString(Path.of("/Users/macbookair/isystem/TelegramBot/src/main/java/uz/isystem/TelegramBot/TXT/FoundationOnlineCourse.txt")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        editMessageCaption.setReplyMarkup(
                InlineKeyboards.keyboardMarkup(
                        InlineKeyboards.collection(
                                InlineKeyboards.row(
                                        InlineKeyboards.button("Kursga Yozilish", "/kursga/yozilish",":multiple_houses:")
                                ),
                                InlineKeyboards.row(
                                        InlineKeyboards.button("Ortga ⬅️", "/back/2")
                                )
                        )
                )
        );
        return editMessageCaption;
    }

    public SendPhoto onlineCourseRegisterHandler(Long id) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(id);
        sendPhoto.setCaption("Iltimos siz bilan\nbo'glanishimiz uchun\nraqamingizni yuboring.");
        sendPhoto.setPhoto(new InputFile(new File("/Users/macbookair/isystem/TelegramBot/src/main/java/uz/isystem/TelegramBot/images/FoundationContactPhoto.png")));
        sendPhoto.setReplyMarkup(
              keyboardMarkup(
                      row(
                              button("Raqam yuborish",true)
                      )
              )
        );
        return sendPhoto;
    }

    public SendPhoto backendHandler(Long chatId) {

        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto(new InputFile(new File("/Users/macbookair/isystem/TelegramBot/src/main/java/uz/isystem/TelegramBot/images/BackendPhoto.png")));
        try {
            sendPhoto.setCaption(Files.readString(Path.of("/Users/macbookair/isystem/TelegramBot/src/main/java/uz/isystem/TelegramBot/TXT/BackendOnline.txt")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sendPhoto.setAllowSendingWithoutReply(true);

        sendPhoto.setReplyMarkup(
                InlineKeyboards.keyboardMarkup(
                        InlineKeyboards.collection(
                                InlineKeyboards.row(
                                        InlineKeyboards.button("Online", "/backend/online", ":boom:"),
                                        InlineKeyboards.button("Offline", "/backend/offline", ":boom:")
                                ),
                                InlineKeyboards.row(
                                        InlineKeyboards.button("Ortga ⬅️", "/back")
                                )
                        )
                )
        );

        return sendPhoto;
    }

    public SendDocument presentationHandler(Long chatId) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId);
        sendDocument.setDocument(new InputFile(new File("/Users/macbookair/isystem/TelegramBot/src/main/java/uz/isystem/TelegramBot/images/Presentation.pdf")));
        sendDocument.setCaption("✋DIQQAT\uD83D\uDED1: Bu Presentation Java bo'yicha va offline kurs uchun mo'jallangan..");
        return sendDocument;
    }
}
