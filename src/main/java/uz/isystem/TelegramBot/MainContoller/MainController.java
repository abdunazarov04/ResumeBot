package uz.isystem.TelegramBot.MainContoller;

import java.time.LocalDateTime;

import uz.isystem.TelegramBot.users.Users;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.objects.*;
import uz.isystem.TelegramBot.utils.InlineKeyboards;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;


import static uz.isystem.TelegramBot.utils.KeyboardMarkup.row;
import static uz.isystem.TelegramBot.utils.KeyboardMarkup.button;
import static uz.isystem.TelegramBot.utils.KeyboardMarkup.keyboardMarkup;

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
                        row(button("Users \uD83D\uDC65")),
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
        return getSendPhoto(chatId);
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

    public Users getUsers(Update update) {
        User from = update.getMessage().getFrom();
        Users users = new Users();
        users.setUserId(update.getMessage().getChatId());
        users.setFirstName(from.getFirstName());
        users.setLastName(from.getLastName());
        users.setUsername(from.getUserName());
        users.setLanguageCode(from.getLanguageCode());
        users.setIsPremium(from.getIsPremium());
        users.setIsBot(from.getIsBot());
        users.setCreateAt(LocalDateTime.now());
        return users;
    }

    public SendPhoto backOnlineCourse(Long chatId) {
        return getSendPhoto(chatId);
    }

    private SendPhoto getSendPhoto(Long chatId) {
        try {
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setPhoto(new InputFile());
            sendPhoto.setChatId(chatId);
            sendPhoto.setCaption("""
                    Yana bir bor sizni Foundation
                    bo'limida ko'rib turganimizdan
                    Hursandmiz hozirda faqat Online
                    kurslarimiz mavjud iltimos
                    yo'nalishlardan birini tanlang👇""");
            sendPhoto.setPhoto(new InputFile("AgACAgIAAxkBAAIFCmZaEv33xjIas70CCnJCg6sovFAyAAJD2zEbl3bRSvPMIRTOXG7IAQADAgADeAADNQQ"));
            sendPhoto.setAllowSendingWithoutReply(true);
            sendPhoto.setReplyMarkup(InlineKeyboards.keyboardMarkup
                    (InlineKeyboards.collection(
                            InlineKeyboards.row(
                                    InlineKeyboards.button(" Networking \uD83D\uDC48", "/foundation/networking")
                            ),
                            InlineKeyboards.row(
                                    InlineKeyboards.button(" Java Foundation \uD83D\uDC48", "/foundation/java")
                            ),
                            InlineKeyboards.row(
                                    InlineKeyboards.button(" C++ Foundation \uD83D\uDC48", "/foundation/c++")
                            ), InlineKeyboards.row(
                                    InlineKeyboards.button(" Java Telegram Bot \uD83D\uDC48", "/foundation/telegram-bot")
                            ),
                            InlineKeyboards.row(InlineKeyboards.button("Ortga ⬅️", "/back")))));
            return sendPhoto;
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

    public SendPhoto onlineCourseJavaFoundation(Long id) {
        try {
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(id);
            sendPhoto.setPhoto(new InputFile("AgACAgIAAxkBAAIHRmZcvLTHtijk3c5rB22zzuAi6cFQAAII4jEblfLoSrWxKhU5t6DbAQADAgADeAADNQQ"));
            sendPhoto.setCaption("""
                    Java dasturlash tilini o'rganish bo'yicha
                    online kursimizga taklif qilamiz. Bu kurs
                    dasturlashning asosiy tamoyillarini o'rganish
                    va kuchli dasturchi bo'lish yo'lida birinchi
                    qadamlarni qo'yishga yordam beradi. Kelajakda
                    IT sohasida muvaffaqiyatga erishishni xohlasangiz,
                    bu kurs aynan siz uchun!
                       \s
                       1 Nimalarni o'rganasiz?
                       2 Kirish va Asoslar (JVM, JRE, JDK)
                       3 Sintaksis va O'zgaruvchilar
                       4 Operatorlar va Ifodalar
                       5 Shart Operatorlari
                       6 Massivlar
                       7 Funktsiyalar va Metodlar
                       8 Obyektga Yo'naltirilgan Dasturlash (OOP)
                       9 Paketlar va Kataloglar
                       10 Java Collections
                       11 Exception Handling
                       12 Fayllar bilan Ishlash
                       \s
                       Kurs Davomiyligi
                        Davomiyligi: 4 oy
                         Modullar: 4 ta
                          Oylik darslar: 12 ta
                           Haftalik darslar: 3 marta
                            Har bir dars: 2 soat (darslar yozib boriladi)
                        \s
                    Sizni kursda ko'rishdan mamnun bo'lamiz!
                    """);
            sendPhoto.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Kursga Yozilish", "/f/o/kursga/yozilish", ":multiple_houses:")), InlineKeyboards.row(InlineKeyboards.button("Ortga ⬅️", "/back/2")))));
            return sendPhoto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public SendPhoto onlineCourseRegisterHandler(Long id) {
        try {
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(id);
            sendPhoto.setCaption("Maqsadlar sari yana bir qadam \uD83D\uDE09");
            sendPhoto.setPhoto(new InputFile("AgACAgIAAxkBAAIHnWZczKDj_59rcBg86JGw7c1TabAxAAJt4jEblfLoSmQ88AT2pvWPAQADAgADeAADNQQ"));
            sendPhoto.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(
                    InlineKeyboards.row(InlineKeyboards.button("Murojat qilish", "https://t.me/AsadbekAbdinazarov", true)),
                    InlineKeyboards.row(InlineKeyboards.button("Ortga ⬅️", "/back/2"))

            )));
            return sendPhoto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SendPhoto onlineCourseTelegramBot(Long chatId) {
        try {
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(chatId);
            sendPhoto.setPhoto(new InputFile("AgACAgIAAxkBAAIHQGZcu6RC0w97Af_-0IPLeosBC4BRAAL94TEblfLoSiALU4nGeAJyAQADAgADeAADNQQ"));
            sendPhoto.setCaption("""
                     Ushbu kurs orqali siz zamonaviy texnologiyalardan
                    foydalanib, o'zingizning Telegram botlaringizni
                    yaratishni o'rganasiz. Agar siz dasturlashga
                    qiziqsangiz yoki IT sohasida yangi ko'nikmalarni
                    o'rganishni xohlasangiz, bu kurs aynan siz uchun!
                      \s
                      1 Kurs Dasturi
                      2 Kerakli taminotlar
                      3 Telegram API va BotFather
                      4 Java bilan Telegram botini yaratish
                      5 Botni sozlash va ishga tushirish
                      6 Xabarlarni qabul qilish va qayta ishlash
                      7 Qo'shimcha funksiyalar qo'shish
                      8 Botni test qilish va debugging
                      9 Botni serverga joylashtirish
                      10 Foydalanuvchi ma'lumotlarini himoya qilish
                      11 Botni optimallashtirish va monitoring qilish
                      12 Ma'lumotlar Bazasi bilan ishlash
                      13 Amaliy loyihalar
                      \s
                    Kursga yozilish uchun hoziroq bizga murojaat
                    qiling va yangi bilimlar olamiga qadam qo'ying!
                      \s
                       Kurs Davomiyligi
                        Davomiyligi: ? oy
                         Oylik darslar: 12 ta
                          Haftalik darslar: 3 marta
                           Har bir dars: 2 soat (darslar yozib boriladi)
                      \s
                    Sizni kursda ko'rishdan mamnun bo'lamiz!""");
            sendPhoto.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Kursga Yozilish", "/f/o/kursga/yozilish", ":multiple_houses:")), InlineKeyboards.row(InlineKeyboards.button("Ortga ⬅️", "/back/2")))));
            return sendPhoto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SendPhoto onlineCourseCPlusPlus(Long chatId) {
        try {
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(chatId);
            sendPhoto.setPhoto(new InputFile("AgACAgIAAxkBAAIHZmZcwx_FRe5amz64paopuNvHKrZVAAIp4jEblfLoSmZ4hP1eaXA0AQADAgADeAADNQQ"));
            sendPhoto.setCaption("""
                    Ushbu kurs orqali siz C++ dasturlash
                    tilini o'rganib, zamonaviy dasturlash
                    ko'nikmalariga ega bo'lasiz. Agar siz
                    dasturlashga qiziqsangiz yoki IT sohasida
                    yangi ko'nikmalarni o'rganishni xohlasangiz,
                    bu kurs aynan siz uchun!
                      \s
                      1 Kurs Dasturi
                      2 Kirish
                      3 C++ dasturlash tilining asoslari
                      4 Asosiy sintaksis va ma'lumot turlari
                      5 O'zgaruvchilar, operatorlar va shartlar
                      6 Massivlar va pointerlar
                      7 Funksiyalar va metodlar
                      8 Ob'ektga yo'naltirilgan dasturlash (OOP)
                      9 Ma'lumotlar strukturalari va algoritmlar
                      10 Amaliy loyihalar
                      \s
                    C++ dasturlash tilini o'rganish va zamonaviy
                    dasturlash ko'nikmalariga ega bo'lish imkoniyatini
                    qo'ldan boy bermang! Kursga yozilish uchun hoziroq
                    bizga murojaat qiling va yangi bilimlar olamiga
                    qadam qo'ying!
                      \s
                       Kurs Davomiyligi
                        Davomiyligi: ? oy
                         Oylik darslar: 12 ta
                          Haftalik darslar: 3 marta
                           Har bir dars: 2 soat (darslar yozib boriladi)
                      \s
                    Sizni kursda ko'rishdan mamnun bo'lamiz!""");
            sendPhoto.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Kursga Yozilish", "/f/o/kursga/yozilish", ":multiple_houses:")), InlineKeyboards.row(InlineKeyboards.button("Ortga ⬅️", "/back/2")))));
            return sendPhoto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SendPhoto onlineCourseNetworking(Long chatId) {
        try {
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(chatId);
            sendPhoto.setPhoto(new InputFile("AgACAgIAAxkBAAIHfWZcyg4wn7Sk8Bq8U0OGOpyI6m66AAJa4jEblfLoSgq9SJcjJaAOAQADAgADeAADNQQ"));
            sendPhoto.setCaption("""
                    Ushbu kurs orqali siz tarmoq (networking)
                    asoslarini o'rganib, zamonaviy tarmoq texnologiyalari
                    bo'yicha ko'nikmalarga ega bo'lasiz.
                    Agar siz IT sohasiga qiziqsangiz yoki yangi
                    ko'nikmalarni o'rganishni xohlasangiz,
                    bu kurs aynan siz uchun!
                      \s
                    Kurs Dasturi
                      1 Kirish.
                      2 Tarmoq asoslari.
                      3 OSI va TCP/IP modellari.
                      4 IP manzillash.
                      5 Subnetting va VLAN.
                      6 Routing va Switching.
                      7 Tarmoq xavfsizligi.
                      8 Wi-Fi va simsiz tarmoqlar.
                      9 Tarmoq monitoringi va troubleshooting.
                      10 Amaliy loyihalar.
                      \s
                    Tarmoq texnologiyalarini o'rganish va zamonaviy
                    ko'nikmalarga ega bo'lish imkoniyatini qo'ldan
                    boy bermang! Kursga yozilish uchun hoziroq bizga
                    murojaat qiling va yangi bilimlar olamiga qadam qo'ying!
                      \s
                       Kurs Davomiyligi
                        Davomiyligi: ? oy
                         Oylik darslar: 12 ta
                          Haftalik darslar: 3 marta
                           Har bir dars: 2 soat (darslar yozib boriladi)
                      \s
                    Sizni kursda ko'rishdan mamnun bo'lamiz!""");
            sendPhoto.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Kursga Yozilish", "/f/o/kursga/yozilish", ":multiple_houses:")), InlineKeyboards.row(InlineKeyboards.button("Ortga ⬅️", "/back/2")))));
            return sendPhoto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
