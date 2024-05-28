package uz.isystem.TelegramBot.MainContoller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;
import org.telegram.telegrambots.meta.api.objects.*;
import uz.isystem.TelegramBot.config.CodeMessage;
import uz.isystem.TelegramBot.config.CodeMessageType;
import uz.isystem.TelegramBot.users.Users;
import uz.isystem.TelegramBot.utils.InlineKeyboards;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

import static uz.isystem.TelegramBot.utils.InlineKeyboards.collection;
import static uz.isystem.TelegramBot.utils.KeyboardMarkup.button;
import static uz.isystem.TelegramBot.utils.KeyboardMarkup.keyboardMarkup;
import static uz.isystem.TelegramBot.utils.KeyboardMarkup.row;

@Controller
public class MainController {


    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    public SendMessage startHandler(Long chatId, boolean text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        if (text) {
            sendMessage.setText("Botga xush kelibsiz.");
        } else {
            sendMessage.setText("Siz bosh menudasiz.");
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

    public SendMessage commandNotFound(Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Buyruq topilmadi!!!");
        sendMessage.setChatId(chatId);

        return sendMessage;
    }

    public SendDocument aboutMeHandler(Long chatId) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId);
        sendDocument.setCaption("<i>Men haqimda to'liqroq o'qing</i>");
        sendDocument.setReplyMarkup(
                InlineKeyboards.keyboardMarkup(
                        InlineKeyboards.collection(
                                InlineKeyboards.row(
                                        InlineKeyboards.button("Ortga ⬅️", "/social/media/back")
                                )
                        )
                )
        );
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
                                        InlineKeyboards.button("\uD83D\uDC49 Online \uD83D\uDC48", "/foundation/online")/*,
                                        InlineKeyboards.button("\uD83D\uDC49 Offline \uD83D\uDC48", "/foundation/offline")*/
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
                                        InlineKeyboards.button("Kursga Yozilish", "/f/o/kursga/yozilish", ":multiple_houses:")
                                ),
                                InlineKeyboards.row(
                                        InlineKeyboards.button("Ortga ⬅️", "/back/2")
                                )
                        )
                )
        );
        return editMessageCaption;
    }

    public CodeMessage onlineCourseRegisterHandler(Long id) {
        CodeMessage codeMessage = new CodeMessage();
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(id);
        sendPhoto.setCaption("Iltimos siz bilan\nbo'glanishimiz uchun\nraqamingizni yuboring.");
        sendPhoto.setPhoto(new InputFile(new File("/Users/macbookair/isystem/TelegramBot/src/main/java/uz/isystem/TelegramBot/images/FoundationContactPhoto.png")));
        sendPhoto.setReplyMarkup(
                keyboardMarkup(
                        row(
                                button("Raqam yuborish", true)
                        )
                )
        );
        codeMessage.setSendPhoto(sendPhoto);
        return codeMessage;
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
                                        InlineKeyboards.button("Online", "/backend/online", ":boom:")/*,
                                        InlineKeyboards.button("Offline", "/backend/offline", ":boom:")*/
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

    public Users getUsers(Update update, Contact contact) {
        User from = update.getMessage().getFrom();
        Users users = new Users();
        users.setUserId(contact.getUserId());
        users.setFirstName(from.getFirstName());
        users.setLastName(from.getLastName());
        users.setUsername(from.getUserName());
        users.setPhoneNumber(contact.getPhoneNumber());
        users.setLanguageCode(from.getLanguageCode());
        users.setIsPremium(from.getIsPremium());
        users.setIsBot(from.getIsBot());
        users.setCreateAt(LocalDateTime.now());
        return users;
    }

    public EditMessageCaption getEditMessageCaption(Long id, Integer messageId) {
        EditMessageCaption editMessageCaption = new EditMessageCaption();
        editMessageCaption.setChatId(id);
        editMessageCaption.setMessageId(messageId);
        try {
            editMessageCaption.setCaption(Files.readString(Path.of("/Users/macbookair/isystem/TelegramBot/src/main/java/uz/isystem/TelegramBot/TXT/FoundationInfo.txt")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        editMessageCaption.setReplyMarkup(
                InlineKeyboards.keyboardMarkup(
                        collection(
                                InlineKeyboards.row(
                                        InlineKeyboards.button("\uD83D\uDC49 Online \uD83D\uDC48", "/foundation/online")/*,
                                        InlineKeyboards.button("\uD83D\uDC49 Offline \uD83D\uDC48", "/foundation/offline")*/
                                ),
                                InlineKeyboards.row(
                                        InlineKeyboards.button("Ortga ⬅️", "/back")
                                )
                        )
                )
        );
        return editMessageCaption;
    }

    public CodeMessage getLanguage(Long chatId) {
        CodeMessage message = new CodeMessage();

        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setCaption("""
                <b>\uD83C\uDDFA\uD83C\uDDFFAssalomu Alaykum bot hozirda
                faqat O'zbek tilida ishlamoqda.

                \uD83C\uDDF7\uD83C\uDDFAЗдравствуйте, бот сейчас работает
                 \
                только на узбекском языке.

                \uD83C\uDDFA\uD83C\uDDF8Hello, Bot is currently operating
                only in Uzbek language.</b>""");
        sendPhoto.setParseMode(ParseMode.HTML);
        sendPhoto.setPhoto(new InputFile(new File("/Users/macbookair/isystem/TelegramBot/src/main/java/uz/isystem/TelegramBot/images/UZB-LANGUAGE.png")));
        sendPhoto.setReplyMarkup(
                InlineKeyboards.keyboardMarkup(
                        InlineKeyboards.collection(
                                InlineKeyboards.row(
                                        InlineKeyboards.button("O'zbek Tili \uD83C\uDDFA\uD83C\uDDFF", "language/o'zbek")
                                )
                        )
                )
        );

        message.setSendPhoto(sendPhoto);
        message.setType(CodeMessageType.PHOTO);
        return message;
    }

    public SendPhoto tasksHandler(Long chatId) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setParseMode(ParseMode.HTML);
        sendPhoto.setCaption("Vazifalar bo'limidasiz.");
        sendPhoto.setPhoto(new InputFile(new File("/Users/macbookair/isystem/TelegramBot/src/main/java/uz/isystem/TelegramBot/images/FoundationTask.png")));
        sendPhoto.setReplyMarkup(
                InlineKeyboards.keyboardMarkup(
                        InlineKeyboards.collection(
                                InlineKeyboards.row(
                                        InlineKeyboards.button("Foundation", "/foundation/tasks", ":mag:")
                                        /*,
                                        InlineKeyboards.button("Backend", "/backend/tasks", ":mag:")*///TODO: /backend/tasks
                                ),
                                InlineKeyboards.row(InlineKeyboards.button("Ortga ⬅️", "/foundation/tasks/back"))
                        )
                )
        );
        return sendPhoto;
    }

    public SendPhoto foundationTask(Long chatId) {

        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setParseMode(ParseMode.HTML);
        sendPhoto.setCaption("Kerakli Mavzuni tanlang!!!");
        sendPhoto.setPhoto(new InputFile(new File("/Users/macbookair/isystem/TelegramBot/src/main/java/uz/isystem/TelegramBot/images/FoundationTaskPhoto.png")));
        sendPhoto.setReplyMarkup(
                InlineKeyboards.keyboardMarkup(
                        InlineKeyboards.collection(
                                InlineKeyboards.row(
                                        InlineKeyboards.button("IF", "/tasks/if"),
                                        InlineKeyboards.button("FOR", "/tasks/for")
                                ),
                                InlineKeyboards.row(
                                        InlineKeyboards.button("WHILE", "/tasks/while"),
                                        InlineKeyboards.button("STRING", "/tasks/string")
                                ),
                                InlineKeyboards.row(
                                        InlineKeyboards.button("ARRAYS", "/tasks/arrays"),
                                        InlineKeyboards.button("NESTED LOOP", "/tasks/nested-loop")
                                ),
                                InlineKeyboards.row(InlineKeyboards.button("METHODS", "/tasks/methods")),
                                InlineKeyboards.row(InlineKeyboards.button("Ortga ⬅️", "/foundation/task/back"))
                        )
                )
        );
        return sendPhoto;
    }

    public SendDocument sendIfTask(Long chatId) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId);
        sendDocument.setCaption("IF operatoriga oid maslalar to'plami.");
        sendDocument.setParseMode(ParseMode.HTML);
        sendDocument.setDocument(new InputFile(new File("/Users/macbookair/isystem/TelegramBot/src/main/java/uz/isystem/TelegramBot/tasks/if-else.pdf")));
        sendDocument.setCaption("DONE ✅");
        sendDocument.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Misollarga qaytish ⬅️", "/tasks/back")))));
        return sendDocument;
    }

    public SendPhoto checkUserChannelMember(Long chatId) {

        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setCaption("Botdan foydalanish uchun Guruhga a'zo bo'ling.");
        sendPhoto.setPhoto(new InputFile(new File("/Users/macbookair/isystem/TelegramBot/src/main/java/uz/isystem/TelegramBot/images/SubscribePhoto.png")));
        sendPhoto.setReplyMarkup(
                InlineKeyboards.keyboardMarkup(
                        InlineKeyboards.collection(
                                InlineKeyboards.row(InlineKeyboards.button("Obuna bo'lish", "/subscribe", "https://t.me/javachiuz", true)),
                                InlineKeyboards.row(InlineKeyboards.button("Tekshirish", "/check/subscribe"))
                        )
                )
        );
        return sendPhoto;
    }

    public SendPhoto aboutMe(Long chatId) {

        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setCaption("Men haqimda To'liqroq tanishib chiqing.");
        sendPhoto.setParseMode(ParseMode.HTML);
        sendPhoto.setPhoto(new InputFile(new File("/Users/macbookair/isystem/TelegramBot/src/main/java/uz/isystem/TelegramBot/images/SocialMedia.png")));
        sendPhoto.setReplyMarkup(
                InlineKeyboards.keyboardMarkup(
                        InlineKeyboards.collection(
                                InlineKeyboards.row(InlineKeyboards.button("LinkedIn \uD83D\uDD0E", "/linkedin", "https://www.linkedin.com/in/asadbek-abdinazarov-97b844234/", true)),
                                InlineKeyboards.row(InlineKeyboards.button("Instagram \uD83D\uDD0E", "/instagram", "https://www.instagram.com/_abdunazarov_a/", true)),
                                InlineKeyboards.row(InlineKeyboards.button("Telegram \uD83D\uDD0E", "/telegram", "https://t.me/AsadbekAbdinazarov", true)),
                                InlineKeyboards.row(InlineKeyboards.button("LeetCode \uD83D\uDD0E", "/leetcode", "https://leetcode.com/u/abdunazarov04/", true)),
                                InlineKeyboards.row(InlineKeyboards.button("CV \uD83D\uDD0E", "/cv")),
                                InlineKeyboards.row(InlineKeyboards.button("Ortga ⬅️", "/back"))
                        )
                )
        );
        return sendPhoto;
    }

    public SendDocument sendForTask(Long chatId) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId);
        sendDocument.setCaption("For operatoriga oid maslalar to'plami.");
        sendDocument.setParseMode(ParseMode.HTML);
        sendDocument.setDocument(new InputFile(new File("/Users/macbookair/isystem/TelegramBot/src/main/java/uz/isystem/TelegramBot/tasks/for.pdf")));
        sendDocument.setCaption("DONE ✅");
        sendDocument.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Misollarga qaytish ⬅️", "/tasks/back")))));
        return sendDocument;
    }

    public SendDocument sendWhileTask(Long chatId) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId);
        sendDocument.setCaption("While operatoriga oid maslalar to'plami.");
        sendDocument.setParseMode(ParseMode.HTML);
        sendDocument.setDocument(new InputFile(new File("/Users/macbookair/isystem/TelegramBot/src/main/java/uz/isystem/TelegramBot/tasks/while.pdf")));
        sendDocument.setCaption("DONE ✅");
        sendDocument.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Misollarga qaytish ⬅️", "/tasks/back")))));
        return sendDocument;
    }

    public SendDocument sendFunctionTask(Long chatId) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId);
        sendDocument.setCaption("Function operatoriga oid maslalar to'plami.");
        sendDocument.setParseMode(ParseMode.HTML);
        sendDocument.setDocument(new InputFile(new File("/Users/macbookair/isystem/TelegramBot/src/main/java/uz/isystem/TelegramBot/tasks/function.pdf")));
        sendDocument.setCaption("DONE ✅");
        sendDocument.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Misollarga qaytish ⬅️", "/tasks/back")))));
        return sendDocument;
    }
}
