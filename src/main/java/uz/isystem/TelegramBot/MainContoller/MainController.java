package uz.isystem.TelegramBot.MainContoller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import uz.isystem.TelegramBot.users.Users;
import org.springframework.stereotype.Controller;
import uz.isystem.TelegramBot.config.CodeMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import uz.isystem.TelegramBot.utils.InlineKeyboards;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Objects;

import static uz.isystem.TelegramBot.utils.KeyboardMarkup.keyboardMarkup;
import static uz.isystem.TelegramBot.utils.InlineKeyboards.collection;
import static uz.isystem.TelegramBot.utils.KeyboardMarkup.button;
import static uz.isystem.TelegramBot.utils.KeyboardMarkup.row;

@Controller
public class MainController {


    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    public SendMessage startHandler(Long chatId, boolean text) {
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
                    row(
                            button("Vazifalar \uD83D\uDCD4"),
                            button("Kerakli Kompiyuter \uD83D\uDCBB")
                    ),
                    row(button("Users \uD83D\uDC65"), button("Users Info \uD83E\uDDD1")),
                    row(button("Men haqimda \uD83D\uDC40"))
            );
            keyboardMarkup.setOneTimeKeyboard(false);
            keyboardMarkup.setResizeKeyboard(true);
            keyboardMarkup.setInputFieldPlaceholder("Og'animagap...");
            sendMessage.setReplyMarkup(keyboardMarkup);
            return sendMessage;
        } else {
            sendMessage.setReplyMarkup(
                    keyboardMarkup(
                            row(button("Foundation \uD83E\uDDD1\u200D\uD83D\uDCBB")/*, button("Backend \uD83D\uDC68\u200D\uD83D\uDCBB")*/),
                            row(button("Vazifalar \uD83D\uDCD4"), button("Kerakli Kompiyuter \uD83D\uDCBB")),
                            row(button("Men haqimda \uD83D\uDC40"))));
            return sendMessage;
        }
    }

   /* public SendMessage commandNotFound(Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Buyruq topilmadi!!!");
        sendMessage.setChatId(chatId);

        return sendMessage;
    }*/

    public SendDocument aboutMeHandler(Long chatId) {
        try {
            InputStream stream = getClass().getResource("/images/AsadbekAbdinazarov.pdf").openStream();
            InputFile inputFile = new InputFile(stream, "AsadbekAbdinazarov.pdf");
            SendDocument sendDocument = new SendDocument();
            sendDocument.setChatId(chatId);
            sendDocument.setCaption("<i>Men haqimda to'liqroq o'qing</i>");
            sendDocument.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Ortga ⬅️", "/social/media/back")))));
            sendDocument.setParseMode(ParseMode.HTML);
            sendDocument.setDocument(inputFile);
            return sendDocument;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public SendPhoto foundationHandler(Long chatId) {
        try {
            InputStream stream = Objects.requireNonNull(getClass().getResource("/images/FoundationPhoto.png")).openStream();
            InputFile inputFile = new InputFile(stream, "FoundationPhoto.png");

            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(chatId);
            sendPhoto.setCaption("""
                    Yana bir bor sizni Foundation bo'limizda
                    ko'rib turganimizdan Hursandmiz hozirda
                    faqat Online kurslarimiz mavjud to'liq
                    ma'lumot olish uchun tugmani bosing 👇""");
            sendPhoto.setPhoto(inputFile);
            sendPhoto.setAllowSendingWithoutReply(true);

            sendPhoto.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("\uD83D\uDC49 Online \uD83D\uDC48", "/foundation/online")/*,
                                        InlineKeyboards.button("\uD83D\uDC49 Offline \uD83D\uDC48", "/foundation/offline")*/), InlineKeyboards.row(InlineKeyboards.button("Ortga ⬅️", "/back")))));
            return sendPhoto;
        } catch (IOException e) {
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
            InputStream stream = Objects.requireNonNull(getClass().getResource("/images/FoundationContactPhoto.png")).openStream();
            InputFile inputFile = new InputFile(stream, "FoundationContactPhoto.png");

            CodeMessage codeMessage = new CodeMessage();
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(id);
            sendPhoto.setCaption("""
                    Siz bilan bo'glanishimiz
                    uchun bizga Contact ulashing.""");
            sendPhoto.setPhoto(inputFile);
            sendPhoto.setReplyMarkup(keyboardMarkup(row(button("Raqam yuborish", true))));
            codeMessage.setSendPhoto(sendPhoto);
            return codeMessage;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public SendPhoto backendHandler(Long chatId) {
        try {
            InputStream stream = Objects.requireNonNull(getClass().getResource("/images/BackendPhoto.png")).openStream();
            InputFile inputFile = new InputFile(stream, "BackendPhoto.png");

            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(chatId);
            sendPhoto.setPhoto(inputFile);
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public SendDocument presentationHandler(Long chatId) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId);
        sendDocument.setDocument(new InputFile("BQACAgIAAxkBAAOzZljFqm8Sm7neyZPWmG193gT7HOgAAvtFAAKZ5clKfh3Ny1rfEbs1BA"));
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
            InputStream stream = Objects.requireNonNull(getClass().getResource("/images/UzbLanguage.png")).openStream();
            if (stream == null) {
                throw new FileNotFoundException("File not found in the resources directory");
            }
            InputFile inputFile = new InputFile(stream, "UzbLanguage.png");

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
            sendPhoto.setPhoto(inputFile);
            sendPhoto.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("O'zbek Tili \uD83C\uDDFA\uD83C\uDDFF", "language/o'zbek")))));

            return sendPhoto;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public SendPhoto tasksHandler(Long chatId) {

        try {
            InputStream stream = getClass().getResource("/images/FoundationTask.png").openStream();
            InputFile inputFile = new InputFile(stream, "FoundationTask.png");
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(chatId);
            sendPhoto.setParseMode(ParseMode.HTML);
            sendPhoto.setCaption("Vazifalar bo'limidasiz.");
            sendPhoto.setPhoto(inputFile);
            sendPhoto.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Foundation", "/foundation/tasks", ":mag:")
                                        /*,
                                        InlineKeyboards.button("Backend", "/backend/tasks", ":mag:")*///TODO: /backend/tasks
            ), InlineKeyboards.row(InlineKeyboards.button("Ortga ⬅️", "/foundation/tasks/back")))));
            return sendPhoto;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public SendPhoto foundationTask(Long chatId) {
        try {
            InputStream stream = Objects.requireNonNull(getClass().getResource("/images/FoundationTaskPhoto.png")).openStream();
            InputFile inputFile = new InputFile(stream, "FoundationTaskPhoto.png");
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(chatId);
            sendPhoto.setParseMode(ParseMode.HTML);
            sendPhoto.setCaption("Kerakli Mavzuni tanlang!!!");
            sendPhoto.setPhoto(inputFile);
            sendPhoto.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("PRINT", "/tasks/print-function"), InlineKeyboards.button("IF", "/tasks/if"), InlineKeyboards.button("FOR", "/tasks/for")), InlineKeyboards.row(InlineKeyboards.button("WHILE", "/tasks/while"), InlineKeyboards.button("MATH", "/tasks/maths"), InlineKeyboards.button("ARRAYS", "/tasks/arrays")), InlineKeyboards.row(InlineKeyboards.button("METHODS", "/tasks/methods")), InlineKeyboards.row(InlineKeyboards.button("Ortga ⬅️", "/foundation/task/back")))));
            return sendPhoto;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
   /* private final List<InlineKeyboardButton> customButtons = new ArrayList<>();

    public SendPhoto foundationTask(Long chatId) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setParseMode(ParseMode.HTML);
        sendPhoto.setCaption("Kerakli Mavzuni tanlang!!!");
        sendPhoto.setPhoto(new InputFile(new File("/Users/macbookair/isystem/TelegramBot/src/main/java/uz/isystem/TelegramBot/images/FoundationTaskPhoto.png")));

        List<List<InlineKeyboardButton>> buttons = InlineKeyboards.collection(
                InlineKeyboards.row(
                        InlineKeyboards.button("PRINT", "/tasks/print-function"),
                        InlineKeyboards.button("IF", "/tasks/if"),
                        InlineKeyboards.button("FOR", "/tasks/for")
                ),
                InlineKeyboards.row(
                        InlineKeyboards.button("WHILE", "/tasks/while"),
                        InlineKeyboards.button("MATH", "/tasks/maths"),
                        InlineKeyboards.button("ARRAYS", "/tasks/arrays")
                ),
                InlineKeyboards.row(
                        InlineKeyboards.button("METHODS", "/tasks/methods"),
                        InlineKeyboards.button("ADD", "/tasks/add-button")
                ),
                InlineKeyboards.row(
                        InlineKeyboards.button("Ortga ⬅️", "/foundation/task/back")
                )
        );

        sendPhoto.setReplyMarkup(InlineKeyboards.keyboardMarkup(buttons));
        buttons.addAll(3, getCustomButtons());

        return sendPhoto;
    }

    private List<List<InlineKeyboardButton>> getCustomButtons() {
        List<List<InlineKeyboardButton>> customButtonRows = new ArrayList<>();
        for (InlineKeyboardButton button : customButtons) {
            customButtonRows.add(InlineKeyboards.row(button));
        }
        return customButtonRows;
    }

    public void addButton(String buttonText, String callbackData) {
        InlineKeyboardButton newButton = InlineKeyboards.button(buttonText, callbackData);
        customButtons.add(newButton);
    }*///TODO: chala part

    public SendPhoto checkUserChannelMember(Long chatId) {

        try {
            InputStream stream = Objects.requireNonNull(getClass().getResource("/images/SubscribePhoto.png")).openStream();
            InputFile inputFile = new InputFile(stream, "SubscribePhoto.png");

            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(chatId);
            sendPhoto.setCaption("Botdan foydalanish uchun Guruhga a'zo bo'ling.");
            sendPhoto.setPhoto(inputFile);
            sendPhoto.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Obuna bo'lish", "https://t.me/javachiuz", true)), InlineKeyboards.row(InlineKeyboards.button("Tekshirish", "/check/subscribe")))));
            return sendPhoto;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public SendPhoto aboutMe(Long chatId) {
        try {
            InputStream stream = Objects.requireNonNull(getClass().getResource("/images/SocialMedia.png")).openStream();
            InputFile inputFile = new InputFile(stream, "SocialMedia.png");
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(chatId);
            sendPhoto.setCaption("Men haqimda To'liqroq tanishib chiqing.");
            sendPhoto.setParseMode(ParseMode.HTML);
        /*try {

            BufferedImage image = ImageIO.read(new File("/Users/macbookair/isystem/TelegramBot/src/main/java/uz/isystem/TelegramBot/images/SocialMedia.png"));
            // Graphics2D obyekti yaratish
            Graphics2D g2d = image.createGraphics();

            // Shrift sozlash
            g2d.setFont(new Font("Montserrat", Font.BOLD, 10));
            g2d.setColor(Color.BLACK);

            // Hozirgi sanani olish
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss yyyy-MM-dd ");
            String dateText = now.format(dtf);

            // Matnni rasimga chizish
            g2d.drawString(dateText, 10, image.getHeight() - 10);

            // Graphics2D ni tozalash
            g2d.dispose();

            // Yangi rasmni saqlash
            ImageIO.write(image, "png", new File("/Users/macbookair/isystem/TelegramBot/src/main/java/uz/isystem/TelegramBot/images/output.png"));
        } catch (IOException e) {
            log.error("Image {}", e.getMessage());
        }*/

            sendPhoto.setPhoto(inputFile);
            sendPhoto.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("LinkedIn \uD83D\uDD0E", "https://www.linkedin.com/in/asadbek-abdinazarov-97b844234/", true), InlineKeyboards.button("Instagram \uD83D\uDD0E", "https://www.instagram.com/_abdunazarov_a/", true)), InlineKeyboards.row(InlineKeyboards.button("LeetCode \uD83D\uDD0E", "https://leetcode.com/u/abdunazarov04/", true), InlineKeyboards.button("Telegram \uD83D\uDD0E", "https://t.me/AsadbekAbdinazarov", true)), InlineKeyboards.row(InlineKeyboards.button("CV \uD83D\uDD0E", "/cv")), InlineKeyboards.row(InlineKeyboards.button("Ortga ⬅️", "/back")))));

            return sendPhoto;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public SendDocument sendForTask(Long chatId) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId);
        sendDocument.setCaption("For operatoriga oid maslalar to'plami.");
        sendDocument.setParseMode(ParseMode.HTML);
        sendDocument.setDocument(new InputFile("BQACAgIAAxkBAAPHZljK-pdZq6MpTkZ04Mv7tmBDjRQAAjlGAAKZ5clKY6TkbnE5WMY1BA"));
        sendDocument.setCaption("DONE ✅");
        sendDocument.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Misollarga qaytish ⬅️", "/tasks/back")))));
        return sendDocument;
    }

    public SendDocument sendIfTask(Long chatId) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId);
        sendDocument.setCaption("IF operatoriga oid maslalar to'plami.");
        sendDocument.setParseMode(ParseMode.HTML);
        sendDocument.setDocument(new InputFile("BQACAgIAAxkBAAPFZljIzliS5yaA-LMtvBVe3W3Fg8QAAhNGAAKZ5clKqgv2hJEG60A1BA"));
        sendDocument.setCaption("DONE ✅");
        sendDocument.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Misollarga qaytish ⬅️", "/tasks/back")))));
        return sendDocument;
    }

    public SendDocument sendWhileTask(Long chatId) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId);
        sendDocument.setCaption("While operatoriga oid maslalar to'plami.");
        sendDocument.setParseMode(ParseMode.HTML);
        sendDocument.setDocument(new InputFile("BQACAgIAAxkBAAPIZljMJObUUAgvpS4SPdsRsLYsw6cAAkdGAAKZ5clKL4ICKa4eCP41BA"));
        sendDocument.setCaption("DONE ✅");
        sendDocument.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Misollarga qaytish ⬅️", "/tasks/back")))));
        return sendDocument;
    }

    public SendDocument sendPrintTasks(Long chatId) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId);
        sendDocument.setCaption("Print functionga oid maslalar to'plami.");
        sendDocument.setParseMode(ParseMode.HTML);
        sendDocument.setDocument(new InputFile("BQACAgIAAxkBAAPDZljIR5PrSdhFDpgKYi9ztZ8EFnoAAg1GAAKZ5clKV_y4XN6BN9w1BA"));
        sendDocument.setCaption("DONE ✅");
        sendDocument.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Misollarga qaytish ⬅️", "/tasks/back")))));
        return sendDocument;
    }

    public SendDocument sendArrayTasks(Long chatId) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId);
        sendDocument.setCaption("2D arraysga oid maslalar to'plami.");
        sendDocument.setParseMode(ParseMode.HTML);
        sendDocument.setDocument(new InputFile("BQACAgIAAxkBAAPWZljPbLdkZ2zgy2I5yx4-BJe5jLMAAmBGAAKZ5clKK8-XUK_Obpc1BA"));
        sendDocument.setCaption("DONE ✅");
        sendDocument.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Misollarga qaytish ⬅️", "/tasks/back")))));
        return sendDocument;
    }

    public SendDocument sendMethods(Long chatId) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId);
        sendDocument.setCaption("Methodlarga oid maslalar to'plami.");
        sendDocument.setParseMode(ParseMode.HTML);
        sendDocument.setDocument(new InputFile("BQACAgIAAxkBAAPGZljJuiETldIRfiCoX_sEBiSrlb8AAiFGAAKZ5clKcHf2cB0vQTo1BA"));
        sendDocument.setCaption("DONE ✅");
        sendDocument.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Misollarga qaytish ⬅️", "/tasks/back")))));
        return sendDocument;
    }

    public SendDocument sendMath(Long chatId) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId);
        sendDocument.setCaption("Matematikaga oid maslalar to'plami.");
        sendDocument.setParseMode(ParseMode.HTML);
        sendDocument.setDocument(new InputFile("BQACAgIAAxkBAAPCZljHFjaW5bmOq34kAAFOhfTKqvSQAAICRgACmeXJSnK79NgjAVdPNQQ"));
        sendDocument.setCaption("DONE ✅");
        sendDocument.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Misollarga qaytish ⬅️", "/tasks/back")))));
        return sendDocument;
    }

    public SendDocument laptopHandler(Long chatId) {
        try {
            InputStream stream = Objects.requireNonNull(getClass().getResource("/images/Resource.pdf")).openStream();
            InputFile inputFile = new InputFile(stream, "Resource.pdf");
            SendDocument sendDocument = new SendDocument();
            sendDocument.setChatId(chatId);
            sendDocument.setDocument(inputFile);
            sendDocument.setCaption("Agarda Dasturlash bo'yicha to'liq malumotga ega bo'lmoqchi bo'lsangiz Presentation tugmasini bosing.");
            sendDocument.setParseMode(ParseMode.HTML);
            sendDocument.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Presentation \uD83D\uDCD4", "/presentation"), InlineKeyboards.button("Ortga  ⬅️", "/back")))));
            return sendDocument;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public SendDocument sendPresentation(Long chatId) {

        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId);
        sendDocument.setDocument(new InputFile("BQACAgIAAxkBAAMeZliEiyP4H6PiNB7zRMt6IwSQadYAAj9GAAKZ5cFKfitb5nUn7y41BA"));
        sendDocument.setCaption("Presentation da xatolik va muammo yuzaga kelsa /help groupga murojat qiling.");
        sendDocument.setParseMode(ParseMode.HTML);
        sendDocument.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(InlineKeyboards.row(InlineKeyboards.button("Ortga ⬅️", "/back/resource")))));

        return sendDocument;
    }

    public SendPhoto helpHandler(Long chatId) {
        try {
            InputStream stream = Objects.requireNonNull(getClass().getResource("/images/Help.png")).openStream();
            InputFile inputFile = new InputFile(stream, "Help.png");
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(chatId);
            sendPhoto.setCaption("""
                     Yordam bo'limiga istalgan bot tarafdan\s
                     yuzaga kelgan muammolar bilan murojat\s
                     qilsangiz bo'ladi.
                    \s""");
            sendPhoto.setReplyMarkup(InlineKeyboards.keyboardMarkup(InlineKeyboards.collection(
                    InlineKeyboards.row(InlineKeyboards.button("Help \uD83C\uDD98", "https://t.me/javachiuzhelp", true)),
                    InlineKeyboards.row(InlineKeyboards.button("Ortga ⬅️", "/back")))));
            sendPhoto.setPhoto(inputFile);
            return sendPhoto;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
