package uz.isystem.TelegramBot.MainContoller;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import uz.isystem.TelegramBot.users.Users;
import org.springframework.stereotype.Controller;
import uz.isystem.TelegramBot.enums.CodeMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import uz.isystem.TelegramBot.utils.InlineKeyboards;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;

import java.time.LocalDateTime;

import static uz.isystem.TelegramBot.utils.KeyboardMarkup.keyboardMarkup;
import static uz.isystem.TelegramBot.utils.InlineKeyboards.collection;
import static uz.isystem.TelegramBot.utils.KeyboardMarkup.button;
import static uz.isystem.TelegramBot.utils.KeyboardMarkup.row;

@Controller
public class MainController {


//    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    public SendMessage startHandler(Long chatId, boolean text) {
        try {

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            if (text) {
                sendMessage.setText("""
                        Botga xush kelibsiz, ⚠️Diqqat bot hozirda test holatida ishlamoqda.
                        """);
            } else {
                sendMessage.setText("Siz bosh menudasiz.");
            }
            if (chatId == 1510894594L) {
                ReplyKeyboardMarkup keyboardMarkup = keyboardMarkup(
                        row(button("Foundation \uD83E\uDDD1\u200D\uD83D\uDCBB")/*, button("Backend \uD83D\uDC68\u200D\uD83D\uDCBB")*/),
                        row(button("Vazifalar \uD83D\uDCD4"), button("Kerakli Kompiyuter \uD83D\uDCBB")),
                        row(button("Men haqimda \uD83D\uDC40")),
                        row(button("Users \uD83D\uDC65"), button("Users Info \uD83E\uDDD1")),
                        row(button("Send Warning message \uD83D\uDEB7")),
                        row(button("Send Bot Started message \uD83D\uDE04"))
                );
                keyboardMarkup.setOneTimeKeyboard(false);
                keyboardMarkup.setResizeKeyboard(true);
                keyboardMarkup.setInputFieldPlaceholder("Og'animagap...");
                sendMessage.setReplyMarkup(keyboardMarkup);
                return sendMessage;
            } else {
                sendMessage.setReplyMarkup(keyboardMarkup(row(button("Foundation \uD83E\uDDD1\u200D\uD83D\uDCBB")/*, button("Backend \uD83D\uDC68\u200D\uD83D\uDCBB")*/), row(button("Vazifalar \uD83D\uDCD4"), button("Kerakli Kompiyuter \uD83D\uDCBB")), row(button("Men haqimda \uD83D\uDC40"))));
                return sendMessage;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SendDocument aboutMeHandler(Long chatId) {
        try {
            SendDocument sendDocument = new SendDocument();
            sendDocument.setChatId(chatId);
            sendDocument.setCaption("<i>Men haqimda to'liqroq o'qing</i>");
            sendDocument.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Ortga ⬅️", "/social/media/back")))));
            sendDocument.setParseMode(ParseMode.HTML);
            sendDocument.setDocument(new InputFile("BQACAgIAAxkBAAIE5mZaDqfqnq40xYqx632zrxbG0Sh5AAIxSwACl3bRSjl_PoA8EJcxNQQ"));
            return sendDocument;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SendPhoto foundationHandler(Long chatId) {
        try {
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setPhoto(new InputFile());
            sendPhoto.setChatId(chatId);
            sendPhoto.setCaption("""
                    Yana bir bor sizni Foundation bo'limizda
                    ko'rib turganimizdan Hursandmiz hozirda
                    faqat Online kurslarimiz mavjud to'liq
                    ma'lumot olish uchun tugmani bosing 👇""");
            sendPhoto.setPhoto(new InputFile("AgACAgIAAxkBAAIFCmZaEv33xjIas70CCnJCg6sovFAyAAJD2zEbl3bRSvPMIRTOXG7IAQADAgADeAADNQQ"));
            sendPhoto.setAllowSendingWithoutReply(true);
            sendPhoto.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("\uD83D\uDC49 Online \uD83D\uDC48", "/foundation/online")/*,
                                        InlineKeyboards.button("\uD83D\uDC49 Offline \uD83D\uDC48", "/foundation/offline")*/), InlineKeyboards.row(InlineKeyboards.button("Ortga ⬅️", "/back")))));
            return sendPhoto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public EditMessageCaption onlineCourseHandler(Integer messageId, Long id) {
        try {
            EditMessageCaption editMessageCaption = new EditMessageCaption();
            editMessageCaption.setChatId(id);
            editMessageCaption.setMessageId(messageId);
            editMessageCaption.setCaption("""
                    Online kursimiz haqida, ma'lumotlar.
                    Keling avval nimalarni o'rganishimiz
                    haqida gaplashib o'tsak, Kusda siz
                    Java dasturlash tilini boshlang'ich
                    bilimlarini o'zlashtirasiz
                    \s
                    01 Kirish va Asoslar (JVM, JRE, JDK)
                    02 Sintaksis va O'zgaruvchilar
                    03 Full operatorlar va Ifodalar
                    04 Shart Operatorlari
                    05 Massivlar
                    06 Funktsiyalar va Metodlar
                    07 Obyektga Yo'naltirilgan Dasturlash (OOP)
                    08 Paketlar va Kataloglar
                    09 Java Collections
                    10 Exception Handling
                    11 Fayllar bilan Ishlash
                    \s
                    Bu albatta
                    qisqartirilgan mundarija va qolaversa
                    qolgan mavzular bilan birga puhta o'rganib
                    boramiz bu qiyin bo'lmaydi biz bilan, endi
                    esa kusrsimiz haqidag gapirib o'tsam,
                    Kurs Davomiyligi: 4 oy
                           1 oyda 12 ta dars.
                           Haftada: 3 marta.
                           Har bir dars 2 soatdan va
                           (RECORDING) yani yozib
                           boriladi. Modullar(Qisimlar)
                           4 ta har oyda yangi modulga
                           o'tiladi.""");
            editMessageCaption.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Kursga Yozilish", "/f/o/kursga/yozilish", ":multiple_houses:")), InlineKeyboards.row(InlineKeyboards.button("Ortga ⬅️", "/back/2")))));
            return editMessageCaption;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public CodeMessage onlineCourseRegisterHandler(Long id) {
        try {
            CodeMessage codeMessage = new CodeMessage();
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(id);
            sendPhoto.setCaption("""
                    Siz bilan bo'glanishimiz
                    uchun bizga Contact ulashing.""");
            sendPhoto.setPhoto(new InputFile("AgACAgIAAxkBAAIFC2ZaEyb_zyi77KZ1YUCYuxuvGCUsAAJE2zEbl3bRSqMinhnyZVesAQADAgADeAADNQQ"));
            sendPhoto.setReplyMarkup(keyboardMarkup(row(button("Raqam yuborish", true))));
            codeMessage.setSendPhoto(sendPhoto);
            return codeMessage;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SendPhoto backendHandler(Long chatId) {
        try {
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(chatId);
            sendPhoto.setPhoto(new InputFile("AgACAgIAAxkBAAIFDWZaE6zEr4GqTYc_1tLrXB-6HE8YAAJH2zEbl3bRSnDZj5_ro5JgAQADAgADeAADNQQ"));
            sendPhoto.setCaption("""
                     Online kursimiz haqida, ma'lumotlar.
                     Keling avval nimalarni o'rganishimiz
                     haqida gaplashib o'tsak kurs davomida
                     Java Dastushlash tili Backend yani
                     proffessional dasturlash uchun ko'plab bilimlarni
                     olasiz masalan:
                                        \s
                     01 Java Thread.
                     02 Java Stream API.
                     04 Java HTTP(URL), HTTP Client.
                     05 Java S.O.L.I.D.
                     06 Java JDBC.
                             (Java Database Connection)
                     07 Java Telegram Bot.
                     08 Lambda Expression
                     09 Spring Core
                     10 Spring Boot va RestApi
                             (LIBRARIES - KUTUBXONALAR)
                     09 Loombok
                     10 Hibernate
                     11 Spring Framework
                                        \s
                     Bu albatta qisqartirilgan mundarija
                     va qolaversa qolgan mavzular bilan birga puhta
                     o'rganib boramiz bu qiyin bo'lmaydi biz bilan 😁
                     endi esa kusrsimiz haqidag gapirib o'tsam,
                     Kurs Davomiyligi: 6-8 oy (o'zlashtrishga bog'liq)
                            1 oyda 12 ta dars.
                            Haftada: 3 marta.
                            Har bir dars 2 soatdan va
                            (RECORDING) yani yozib boriladi.
                            Modullar(Qisimlar) 6-8 ta har oyda
                            yangi modulga o'tiladi.
                            Vaqtlar hali yo'lga qo'yilmadi.
                    \s""");

            sendPhoto.setAllowSendingWithoutReply(true);

            sendPhoto.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Online", "/backend/online", ":boom:")/*,
                                        InlineKeyboards.button("Offline", "/backend/offline", ":boom:")*/), InlineKeyboards.row(InlineKeyboards.button("Ortga ⬅️", "/back")))));

            return sendPhoto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SendDocument presentationHandler(Long chatId) {
        try {
            SendDocument sendDocument = new SendDocument();
            sendDocument.setChatId(chatId);
            sendDocument.setDocument(new InputFile("BQACAgIAAxkBAAOzZljFqm8Sm7neyZPWmG193gT7HOgAAvtFAAKZ5clKfh3Ny1rfEbs1BA"));
            sendDocument.setCaption("✋DIQQAT\uD83D\uDED1: Bu Presentation Java bo'yicha va offline kurs uchun mo'jallangan..");
            return sendDocument;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
        try {
            EditMessageCaption editMessageCaption = new EditMessageCaption();
            editMessageCaption.setChatId(id);
            editMessageCaption.setMessageId(messageId);
            editMessageCaption.setCaption("""
                    Backend kursigi hush kelib siz
                    Kurs Turini tanlang;""");

            editMessageCaption.setReplyMarkup(InlineKeyboards.keyboardMarkup(collection(InlineKeyboards.row(InlineKeyboards.button("\uD83D\uDC49 Online \uD83D\uDC48", "/foundation/online")/*,
                                        InlineKeyboards.button("\uD83D\uDC49 Offline \uD83D\uDC48", "/foundation/offline")*/), InlineKeyboards.row(InlineKeyboards.button("Ortga ⬅️", "/back")))));
            return editMessageCaption;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SendPhoto getLanguage(Long chatId) {
        try {
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(chatId);
            sendPhoto.setCaption("""
                    <b>\uD83C\uDDFA\uD83C\uDDFFAssalomu Alaykum bot hozirda
                    faqat O'zbek tilida ishlamoqda.

                    \uD83C\uDDF7\uD83C\uDDFAЗдравствуйте, бот сейчас работает
                    только на узбекском языке.

                    \uD83C\uDDFA\uD83C\uDDF8Hello, Bot is currently operating
                    only in Uzbek language.</b>""");
            sendPhoto.setParseMode(ParseMode.HTML);
            sendPhoto.setPhoto(new InputFile("AgACAgIAAxkBAAIFBWZaElbg6O8chIRBrgLajjkNL2B3AAI72zEbl3bRSksOtiOQWMSPAQADAgADeAADNQQ"));
            sendPhoto.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("O'zbek Tili \uD83C\uDDFA\uD83C\uDDFF", "language/o'zbek")))));

            return sendPhoto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SendPhoto tasksHandler(Long chatId) {

        try {
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(chatId);
            sendPhoto.setParseMode(ParseMode.HTML);
            sendPhoto.setCaption("Vazifalar bo'limidasiz.");
            sendPhoto.setPhoto(new InputFile("AgACAgIAAxkBAAIFCWZaEtr5-dBVDMeqNd07n0cXDnLBAAJB2zEbl3bRSqwzi1pi0RvgAQADAgADeAADNQQ"));
            sendPhoto.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Foundation", "/foundation/tasks", ":mag:")
                                        /*,
                                        InlineKeyboards.button("Backend", "/backend/tasks", ":mag:")*///TODO: /backend/tasks
            ), InlineKeyboards.row(InlineKeyboards.button("Ortga ⬅️", "/foundation/tasks/back")))));
            return sendPhoto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SendPhoto foundationTask(Long chatId) {
        try {
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(chatId);
            sendPhoto.setParseMode(ParseMode.HTML);
            sendPhoto.setCaption("Kerakli Mavzuni tanlang!!!");
            sendPhoto.setPhoto(new InputFile("AgACAgIAAxkBAAIFCGZaErk72chLdu6rXt9uwj5JjNZAAAJA2zEbl3bRSqyN4hMgo-nuAQADAgADeAADNQQ"));
            sendPhoto.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("PRINT", "/tasks/print-function"), InlineKeyboards.button("IF", "/tasks/if"), InlineKeyboards.button("FOR", "/tasks/for")), InlineKeyboards.row(InlineKeyboards.button("WHILE", "/tasks/while"), InlineKeyboards.button("MATH", "/tasks/maths"), InlineKeyboards.button("ARRAYS", "/tasks/arrays")), InlineKeyboards.row(InlineKeyboards.button("METHODS", "/tasks/methods")), InlineKeyboards.row(InlineKeyboards.button("Ortga ⬅️", "/foundation/task/back")))));
            return sendPhoto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SendPhoto checkUserChannelMember(Long chatId) {

        try {
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(chatId);
            sendPhoto.setCaption("Botdan foydalanish uchun Guruhga a'zo bo'ling.");
            sendPhoto.setPhoto(new InputFile("AgACAgIAAxkBAAIFBmZaEnnd1wn2LyPlkPweU7FPtIBkAAI-2zEbl3bRSs0WznkjYbX_AQADAgADeAADNQQ"));
            sendPhoto.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Obuna bo'lish", "https://t.me/javachiuz", true)), InlineKeyboards.row(InlineKeyboards.button("Tekshirish", "/check/subscribe")))));
            return sendPhoto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SendPhoto aboutMe(Long chatId) {
        try {
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(chatId);
            sendPhoto.setCaption("Men haqimda To'liqroq tanishib chiqing.");
            sendPhoto.setParseMode(ParseMode.HTML);
            sendPhoto.setPhoto(new InputFile("AgACAgIAAxkBAAIFB2ZaEp3IiZTFU0FQFwFn2QVXV305AAI_2zEbl3bRSjNYT4Rtld1RAQADAgADeAADNQQ"));
            sendPhoto.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("LinkedIn \uD83D\uDD0E", "https://www.linkedin.com/in/asadbek-abdinazarov-97b844234/", true), InlineKeyboards.button("Instagram \uD83D\uDD0E", "https://www.instagram.com/_abdunazarov_a/", true)), InlineKeyboards.row(InlineKeyboards.button("LeetCode \uD83D\uDD0E", "https://leetcode.com/u/abdunazarov04/", true), InlineKeyboards.button("Telegram \uD83D\uDD0E", "https://t.me/AsadbekAbdinazarov", true)), InlineKeyboards.row(InlineKeyboards.button("CV \uD83D\uDD0E", "/cv")), InlineKeyboards.row(InlineKeyboards.button("Ortga ⬅️", "/back")))));

            return sendPhoto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SendDocument sendForTask(Long chatId) {
        try {
            SendDocument sendDocument = new SendDocument();
            sendDocument.setChatId(chatId);
            sendDocument.setCaption("For operatoriga oid maslalar to'plami.");
            sendDocument.setParseMode(ParseMode.HTML);
            sendDocument.setDocument(new InputFile("BQACAgIAAxkBAAPHZljK-pdZq6MpTkZ04Mv7tmBDjRQAAjlGAAKZ5clKY6TkbnE5WMY1BA"));
            sendDocument.setCaption("DONE ✅");
            sendDocument.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Misollarga qaytish ⬅️", "/tasks/back")))));
            return sendDocument;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SendDocument sendIfTask(Long chatId) {
        try {
            SendDocument sendDocument = new SendDocument();
            sendDocument.setChatId(chatId);
            sendDocument.setCaption("IF operatoriga oid maslalar to'plami.");
            sendDocument.setParseMode(ParseMode.HTML);
            sendDocument.setDocument(new InputFile("BQACAgIAAxkBAAPFZljIzliS5yaA-LMtvBVe3W3Fg8QAAhNGAAKZ5clKqgv2hJEG60A1BA"));
            sendDocument.setCaption("DONE ✅");
            sendDocument.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Misollarga qaytish ⬅️", "/tasks/back")))));
            return sendDocument;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SendDocument sendWhileTask(Long chatId) {
        try {
            SendDocument sendDocument = new SendDocument();
            sendDocument.setChatId(chatId);
            sendDocument.setCaption("While operatoriga oid maslalar to'plami.");
            sendDocument.setParseMode(ParseMode.HTML);
            sendDocument.setDocument(new InputFile("BQACAgIAAxkBAAPIZljMJObUUAgvpS4SPdsRsLYsw6cAAkdGAAKZ5clKL4ICKa4eCP41BA"));
            sendDocument.setCaption("DONE ✅");
            sendDocument.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Misollarga qaytish ⬅️", "/tasks/back")))));
            return sendDocument;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SendDocument sendPrintTasks(Long chatId) {
        try {
            SendDocument sendDocument = new SendDocument();
            sendDocument.setChatId(chatId);
            sendDocument.setCaption("Print functionga oid maslalar to'plami.");
            sendDocument.setParseMode(ParseMode.HTML);
            sendDocument.setDocument(new InputFile("BQACAgIAAxkBAAPDZljIR5PrSdhFDpgKYi9ztZ8EFnoAAg1GAAKZ5clKV_y4XN6BN9w1BA"));
            sendDocument.setCaption("DONE ✅");
            sendDocument.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Misollarga qaytish ⬅️", "/tasks/back")))));
            return sendDocument;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SendDocument sendArrayTasks(Long chatId) {
        try {

            SendDocument sendDocument = new SendDocument();
            sendDocument.setChatId(chatId);
            sendDocument.setCaption("2D arraysga oid maslalar to'plami.");
            sendDocument.setParseMode(ParseMode.HTML);
            sendDocument.setDocument(new InputFile("BQACAgIAAxkBAAPWZljPbLdkZ2zgy2I5yx4-BJe5jLMAAmBGAAKZ5clKK8-XUK_Obpc1BA"));
            sendDocument.setCaption("DONE ✅");
            sendDocument.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Misollarga qaytish ⬅️", "/tasks/back")))));
            return sendDocument;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SendDocument sendMethods(Long chatId) {
        try {
            SendDocument sendDocument = new SendDocument();
            sendDocument.setChatId(chatId);
            sendDocument.setCaption("Methodlarga oid maslalar to'plami.");
            sendDocument.setParseMode(ParseMode.HTML);
            sendDocument.setDocument(new InputFile("BQACAgIAAxkBAAPGZljJuiETldIRfiCoX_sEBiSrlb8AAiFGAAKZ5clKcHf2cB0vQTo1BA"));
            sendDocument.setCaption("DONE ✅");
            sendDocument.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Misollarga qaytish ⬅️", "/tasks/back")))));
            return sendDocument;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SendDocument sendMath(Long chatId) {
        try {
            SendDocument sendDocument = new SendDocument();
            sendDocument.setChatId(chatId);
            sendDocument.setCaption("Matematikaga oid maslalar to'plami.");
            sendDocument.setParseMode(ParseMode.HTML);
            sendDocument.setDocument(new InputFile("BQACAgIAAxkBAAPCZljHFjaW5bmOq34kAAFOhfTKqvSQAAICRgACmeXJSnK79NgjAVdPNQQ"));
            sendDocument.setCaption("DONE ✅");
            sendDocument.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Misollarga qaytish ⬅️", "/tasks/back")))));
            return sendDocument;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SendDocument laptopHandler(Long chatId) {
        try {
            SendDocument sendDocument = new SendDocument();
            sendDocument.setChatId(chatId);
            sendDocument.setDocument(new InputFile("BQACAgIAAxkBAAIE7GZaD7Z5ybP1E3NvAbUmo-aYmrzpAAJKSwACl3bRSucO6gPaXSbxNQQ"));
            sendDocument.setCaption("Agarda Dasturlash bo'yicha to'liq malumotga ega bo'lmoqchi bo'lsangiz Presentation tugmasini bosing.");
            sendDocument.setParseMode(ParseMode.HTML);
            sendDocument.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Presentation \uD83D\uDCD4", "/presentation"), InlineKeyboards.button("Ortga  ⬅️", "/back")))));
            return sendDocument;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SendDocument sendPresentation(Long chatId) {
        try {
            SendDocument sendDocument = new SendDocument();
            sendDocument.setChatId(chatId);
            sendDocument.setDocument(new InputFile("BQACAgIAAxkBAAIE7WZaEHpK3lIsWOWBzLJt_Pv0O30CAAJWSwACl3bRSi_Etr9TkbXhNQQ"));
            sendDocument.setCaption("Presentation da xatolik va muammo yuzaga kelsa /help groupga murojat qiling.");
            sendDocument.setParseMode(ParseMode.HTML);
            sendDocument.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Ortga ⬅️", "/back/resource")))));
            return sendDocument;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SendPhoto helpHandler(Long chatId) {
        try {
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(chatId);
            sendPhoto.setCaption("""
                     Yordam bo'limiga istalgan bot tarafdan\s
                     yuzaga kelgan muammolar bilan murojat\s
                     qilsangiz bo'ladi.
                    \s""");
            sendPhoto.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Help \uD83C\uDD98", "https://t.me/javachiuzhelp", true)), InlineKeyboards.row(InlineKeyboards.button("Ortga ⬅️", "/back")))));
            sendPhoto.setPhoto(new InputFile("AgACAgIAAxkBAAIFAWZaEfkdBGN1IQr7HcUWyuEqavrSAAI02zEbl3bRSjCgHjinSpDBAQADAgADeAADNQQ"));
            return sendPhoto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SendPhoto sendBadNews(Long chatId) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto(new InputFile("AgACAgIAAxkBAAIGg2ZbgVASfkZkc2SLXedbLUyYB6_HAALt2zEbl3bhSnOWH2PungMQAQADAgADeAADNQQ"));
        sendPhoto.setCaption("BAD NEWS\nAssalomu Alaykum\nbot hozirda faol emas\ntez orada qayta ishga\nishlay boshlaydi \uD83E\uDD15 \n\nJAVACHI GROUP");
        sendPhoto.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Habarni o'chirish ♻️", "bad/news/delete/message")))));
        return sendPhoto;
    }

    public SendPhoto sendGoodNews(Long userId) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(userId);
        sendPhoto.setPhoto(new InputFile("AgACAgIAAxkBAAIGhGZbgZbXHW6EGLhxZgpcVRN-XndcAALv2zEbl3bhSpVwLbKxUQ85AQADAgADeAADNQQ"));
        sendPhoto.setCaption("GOOD NEWS\nAssalomu Alaykum\nbot yana o'z faoliyatida\ndavom etmoqda tabrikleshin\nqutleshin \uD83D\uDE01 \n\nJAVACHI GROUP");
        sendPhoto.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Habarni o'chirish ♻️", "good/news/delete/message")))));
        return sendPhoto;
    }
}
