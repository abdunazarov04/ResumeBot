package uz.isystem.TelegramBot.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMember;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.isystem.TelegramBot.MainContoller.MainController;
import uz.isystem.TelegramBot.enums.CodeMessage;
import uz.isystem.TelegramBot.enums.CodeMessageType;
import uz.isystem.TelegramBot.repository.InfoRepository;
import uz.isystem.TelegramBot.repository.UserRepository;
import uz.isystem.TelegramBot.users.Info;
import uz.isystem.TelegramBot.users.Users;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;


@Slf4j
@Component
@RequiredArgsConstructor
public class BotConfig extends TelegramLongPollingBot {


    private final UserRepository userRepository;
    private final MainController mainController;

    private final List<String> commands = List.of("/start", "/stop", "/help", "/check/subscribe");
    private final List<String> callbackCommands = List.of("/back", "/back/2", "/foundation/online", "/foundation/offline", "/backend/online", "/backend/offline", "/f/o/kursga/yozilish", "/presentation", "/back/resource");
    private final List<String> callbackTasksCommands = List.of("/foundation/tasks", "/tasks/add-button");
    private final List<String> callbackSocialMedia = List.of("/cv", "/social/media/back");
    private final List<String> myCommands = List.of("2021", "Users Info \uD83E\uDDD1", "Users \uD83D\uDC65");
    private final List<String> callbackTasksCommand = List.of("/tasks/if", "/tasks/for", "/tasks/while", "/tasks/methods", "/tasks/arrays", "/tasks/back", "/foundation/tasks/back", "/foundation/task/back", "/tasks/print-function", "/tasks/maths");
    private final List<String> buttonCommands = List.of("Men haqimda \uD83D\uDC40", "Foundation \uD83E\uDDD1\u200D\uD83D\uDCBB", "Backend \uD83D\uDC68\u200D\uD83D\uDCBB", "Vazifalar \uD83D\uDCD4", "Kerakli Kompiyuter \uD83D\uDCBB");
    private final Map<Long, Integer> mapUsers = new HashMap<>();
    private final InfoRepository infoRepository;


    static int timeControl = 15;

    public void setMapUsers(Long chatId, Integer messageId) {
        mapUsers.put(chatId, messageId);
    }

    public Integer getMapUsers(Long chatId) {
        return mapUsers.get(chatId);
    }

    private final CodeMessage codeMessage = new CodeMessage();

    @Override
    public void onUpdateReceived(Update update) {
        SendDocument sendDocument;
      /* if (update.getMessage().hasPhoto()){

           PhotoSize photoSize = update.getMessage().getPhoto().get(update.getMessage().getPhoto().size() - 1);
           System.out.println(photoSize.getFileId());

       }*/
        if (update.hasCallbackQuery()) {

            CallbackQuery callbackQuery = update.getCallbackQuery();
            Long chatId = callbackQuery.getFrom().getId();
            Integer messageId = callbackQuery.getMessage().getMessageId();
            String dataText = callbackQuery.getData();

            long userId = callbackQuery.getFrom().getId();
            String channel = "-1002010093080";
            boolean isMember = isUserMember(channel, userId);

            if (!isMember) {
                DeleteMessage deleteMessage = getDeleteMessage(chatId, messageId);
                codeMessage.setType(CodeMessageType.DELETE_MESSAGE);
                codeMessage.setDeleteMessage(deleteMessage);
                execute();

                SendPhoto sendPhoto = this.mainController.checkUserChannelMember(chatId);
                codeMessage.setType(CodeMessageType.PHOTO);
                codeMessage.setSendPhoto(sendPhoto);
                execute();
            } else if (callbackSocialMedia.contains(dataText)) {
                if (dataText.equals("/cv")) {
                    DeleteMessage deleteMessage = getDeleteMessage(chatId, messageId);
                    codeMessage.setType(CodeMessageType.DELETE_MESSAGE);
                    codeMessage.setDeleteMessage(deleteMessage);
                    execute();

                    sendDocument = this.mainController.aboutMeHandler(chatId);
                    codeMessage.setType(CodeMessageType.DOCUMENT);
                    codeMessage.setSendDocument(sendDocument);
                    execute();
                    return;
                } else if (dataText.equals("/social/media/back")) {
                    DeleteMessage deleteMessage = getDeleteMessage(chatId, messageId);
                    codeMessage.setType(CodeMessageType.DELETE_MESSAGE);
                    codeMessage.setDeleteMessage(deleteMessage);
                    execute();

                    SendPhoto sendPhoto = this.mainController.aboutMe(chatId);
                    codeMessage.setType(CodeMessageType.PHOTO);
                    codeMessage.setSendPhoto(sendPhoto);
                    execute();
                    return;
                }
            } else if (callbackCommands.contains(dataText)) {
                String data = callbackQuery.getData();
                switch (data) {
                    case "/back" -> {
                        DeleteMessage deleteMessage = getDeleteMessage(chatId, messageId);
                        codeMessage.setType(CodeMessageType.DELETE_MESSAGE);
                        codeMessage.setDeleteMessage(deleteMessage);
                        execute();

                        SendMessage sendMessage = this.mainController.startHandler(chatId, false);
                        codeMessage.setType(CodeMessageType.MESSAGE);
                        codeMessage.setSendMessage(sendMessage);
                        execute();
                        return;
                    }
                    case "/back/2" -> {
                        EditMessageCaption editMessageCaption = this.mainController.getEditMessageCaption(chatId, messageId);
                        codeMessage.setType(CodeMessageType.EDIT_MESSAGE);
                        codeMessage.setEditMessageCaption(editMessageCaption);
                        execute();
                        return;
                    }
                    case "/foundation/online" -> {
                        EditMessageCaption editMessageCaption;
                        editMessageCaption = this.mainController.onlineCourseHandler(messageId, chatId);
                        codeMessage.setType(CodeMessageType.EDIT_MESSAGE);
                        codeMessage.setEditMessageCaption(editMessageCaption);
                        execute();
                        return;
                    }
                    case "/foundation/offline", "/backend/offline" -> {
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setChatId(chatId);
                        sendMessage.setText("Offline course hozircha tamirda");
                        codeMessage.setType(CodeMessageType.MESSAGE);
                        codeMessage.setSendMessage(sendMessage);
                        execute();
                        return;
                    }
                    case "/backend/online" -> {
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setChatId(chatId);
                        sendMessage.setText("Online course hozircha tamirda");
                        codeMessage.setType(CodeMessageType.MESSAGE);
                        codeMessage.setSendMessage(sendMessage);
                        execute();
                        return;
                    }

                    case "/back/resource" -> {
                        DeleteMessage deleteMessage = getDeleteMessage(chatId, messageId);
                        codeMessage.setType(CodeMessageType.DELETE_MESSAGE);
                        codeMessage.setDeleteMessage(deleteMessage);
                        execute();

                        sendDocument = this.mainController.laptopHandler(chatId);
                        codeMessage.setType(CodeMessageType.DOCUMENT);
                        codeMessage.setSendDocument(sendDocument);
                        execute();
                        return;
                    }

                    case "/presentation" -> {
                        DeleteMessage deleteMessage = getDeleteMessage(chatId, messageId);
                        codeMessage.setType(CodeMessageType.DELETE_MESSAGE);
                        codeMessage.setDeleteMessage(deleteMessage);
                        execute();

                        sendDocument = this.mainController.sendPresentation(chatId);
                        codeMessage.setType(CodeMessageType.DOCUMENT);
                        codeMessage.setSendDocument(sendDocument);
                        execute();
                        return;
                    }
                    default -> {
                        if (data.equals("/f/o/kursga/yozilish")) {
                            DeleteMessage deleteMessage = getDeleteMessage(chatId, callbackQuery.getMessage().getMessageId());
                            codeMessage.setType(CodeMessageType.DELETE_MESSAGE);
                            codeMessage.setDeleteMessage(deleteMessage);
                            execute();
                            CodeMessage codeMessage = this.mainController.onlineCourseRegisterHandler(chatId);

                            try {
                                Message message = execute(codeMessage.getSendPhoto());
                                setMapUsers(callbackQuery.getMessage().getChatId(), message.getMessageId());
                            } catch (TelegramApiException e) {
                                log.error(e.getMessage());
                            }


                        }
                    }
                }

            } else if (callbackTasksCommands.contains(dataText)) {

                DeleteMessage deleteMessage = getDeleteMessage(chatId, messageId);
                codeMessage.setType(CodeMessageType.DELETE_MESSAGE);
                codeMessage.setDeleteMessage(deleteMessage);
                execute();

                SendPhoto sendPhoto = this.mainController.foundationTask(chatId);
                codeMessage.setType(CodeMessageType.PHOTO);
                codeMessage.setSendPhoto(sendPhoto);
                execute();

            } else if (callbackTasksCommand.contains(dataText)) {
                DeleteMessage deleteMessage = getDeleteMessage(chatId, callbackQuery.getMessage().getMessageId());
                codeMessage.setType(CodeMessageType.DELETE_MESSAGE);
                codeMessage.setDeleteMessage(deleteMessage);
                execute();

                switch (dataText) {

                    case "/tasks/if" -> {
                        sendDocument = this.mainController.sendIfTask(chatId);
                        codeMessage.setType(CodeMessageType.DOCUMENT);
                        codeMessage.setSendDocument(sendDocument);
                        execute();
                    }
                    case "/tasks/back" -> {
                        SendPhoto sendPhoto = this.mainController.foundationTask(chatId);
                        codeMessage.setType(CodeMessageType.PHOTO);
                        codeMessage.setSendPhoto(sendPhoto);
                        execute();
                    }
                    case "/foundation/task/back" -> {
                        SendPhoto sendPhoto = this.mainController.tasksHandler(chatId);
                        codeMessage.setType(CodeMessageType.PHOTO);
                        codeMessage.setSendPhoto(sendPhoto);
                        execute();
                        return;
                    }
                    case "/foundation/tasks/back" -> {
                        SendMessage sendMessage = this.mainController.startHandler(chatId, false);
                        codeMessage.setType(CodeMessageType.MESSAGE);
                        codeMessage.setSendMessage(sendMessage);
                        execute();
                        return;
                    }
                    case "/tasks/for" -> {
                        sendDocument = this.mainController.sendForTask(chatId);
                        codeMessage.setType(CodeMessageType.DOCUMENT);
                        codeMessage.setSendDocument(sendDocument);
                        execute();
                        return;
                    }

                    case "/tasks/while" -> {
                        sendDocument = this.mainController.sendWhileTask(chatId);
                        codeMessage.setType(CodeMessageType.DOCUMENT);
                        codeMessage.setSendDocument(sendDocument);
                        execute();
                        return;
                    }

                    case "/tasks/print-function" -> {
                        sendDocument = this.mainController.sendPrintTasks(chatId);
                        codeMessage.setType(CodeMessageType.DOCUMENT);
                        codeMessage.setSendDocument(sendDocument);
                        execute();
                        return;
                    }

                    case "/tasks/arrays" -> {
                        sendDocument = this.mainController.sendArrayTasks(chatId);
                        codeMessage.setType(CodeMessageType.DOCUMENT);
                        codeMessage.setSendDocument(sendDocument);
                        execute();
                        return;
                    }
                    case "/tasks/methods" -> {
                        sendDocument = this.mainController.sendMethods(chatId);
                        codeMessage.setType(CodeMessageType.DOCUMENT);
                        codeMessage.setSendDocument(sendDocument);
                        execute();
                        return;
                    }

                    case "/tasks/maths" -> {
                        sendDocument = this.mainController.sendMath(chatId);
                        codeMessage.setType(CodeMessageType.DOCUMENT);
                        codeMessage.setSendDocument(sendDocument);
                        execute();
                        return;
                    }
                    default -> sendMessage(chatId, "NO YET");
                }

            } else if (update.getCallbackQuery().getData().equals("language/o'zbek")) {
                DeleteMessage deleteMessage = getDeleteMessage(chatId, messageId);
                mapUsers.put(chatId, update.getCallbackQuery().getMessage().getMessageId());
                SendMessage sendMessage;
                sendMessage = this.mainController.startHandler(chatId, true);
                codeMessage.setType(CodeMessageType.MESSAGE);
                codeMessage.setSendMessage(sendMessage);
                execute();

                codeMessage.setType(CodeMessageType.DELETE_MESSAGE);
                codeMessage.setDeleteMessage(deleteMessage);
                execute();
                return;
            } else if (dataText.equals("/check/subscribe")) {
                DeleteMessage deleteMessage = new DeleteMessage();
                deleteMessage.setChatId(chatId);
                deleteMessage.setMessageId(messageId);
                codeMessage.setType(CodeMessageType.DELETE_MESSAGE);
                codeMessage.setDeleteMessage(deleteMessage);
                execute();

                SendPhoto sendPhoto = this.mainController.getLanguage(chatId);
                codeMessage.setType(CodeMessageType.PHOTO);
                codeMessage.setSendPhoto(sendPhoto);
                execute();
                return;
            } else {
                sendMessage(update.getCallbackQuery().getMessage().getChatId(), "Aniqlanmagan Xatolik yuzaga keldi siz uni topdingiz");
                return;
            }
        }

        if (update.hasMessage()) {
            long userId = update.getMessage().getFrom().getId();
            String channel = "-1002010093080";
            boolean isMember = isUserMember(channel, userId);

            SendMessage sendMessage = new SendMessage();

            if (update.getMessage().hasContact()) {
                Contact contact = update.getMessage().getContact();
                Long chatId = update.getMessage().getFrom().getId();
                DeleteMessage deleteMessage = getDeleteMessage(update.getMessage().getFrom().getId(), getMapUsers(chatId));
                sendMessage.setChatId(update.getMessage().getFrom().getId());

                codeMessage.setType(CodeMessageType.DELETE_MESSAGE);
                codeMessage.setDeleteMessage(deleteMessage);
                execute();
                mapUsers.remove(chatId);

                sendMessage = this.mainController.startHandler(update.getMessage().getFrom().getId(), false);
                codeMessage.setType(CodeMessageType.MESSAGE);
                codeMessage.setSendMessage(sendMessage);
                execute();

                Users users = this.mainController.getUsers(update, contact);
                user(users);
                if (!this.userRepository.existsByUserId(users.getUserId())) {
                    userRepository.save(users);
                    sendMessage(chatId, "<b>Siz bilan tez orada bog'lanamiz.</b>");
                } else {
                    userFound(chatId);
                }
                return;
            }

            Message message = update.getMessage();
            String text = message.getText();
            Long chatId = message.getChatId();

            if (!isMember) {
                SendPhoto sendPhoto = this.mainController.checkUserChannelMember(chatId);
                codeMessage.setType(CodeMessageType.PHOTO);
                codeMessage.setSendPhoto(sendPhoto);
                execute();
            } else if (commands.contains(text)) {
                if (text.equals("/start")) {

                    if (!this.infoRepository.existsByUserId(chatId)) {
                        infoRepository.save(new Info(chatId, LocalDateTime.now()));
                    }
                    SendPhoto sendPhoto = this.mainController.getLanguage(chatId);
                    codeMessage.setType(CodeMessageType.PHOTO);
                    codeMessage.setSendPhoto(sendPhoto);
                    execute();
                } else if (text.equals("/help")) {

                    SendPhoto sendPhoto = this.mainController.helpHandler(chatId);
                    codeMessage.setType(CodeMessageType.PHOTO);
                    codeMessage.setSendPhoto(sendPhoto);
                    execute();
                }
            } else if (buttonCommands.contains(text)) {

                switch (text) {
                    case "Men haqimda \uD83D\uDC40" -> {
                        SendPhoto sendPhoto = this.mainController.aboutMe(chatId);
                        codeMessage.setType(CodeMessageType.PHOTO);
                        codeMessage.setSendPhoto(sendPhoto);
                        execute();
                    }
                    case "Foundation \uD83E\uDDD1\u200D\uD83D\uDCBB" -> {
                        SendPhoto sendPhoto = this.mainController.foundationHandler(chatId);
                        codeMessage.setType(CodeMessageType.PHOTO);
                        codeMessage.setSendPhoto(sendPhoto);
                        execute();
                    }
                    case "Kerakli Kompiyuter \uD83D\uDCBB" -> {
                        sendDocument = this.mainController.laptopHandler(chatId);
                        codeMessage.setType(CodeMessageType.DOCUMENT);
                        codeMessage.setSendDocument(sendDocument);
                        execute();
                    }
                    case "Backend \uD83D\uDC68\u200D\uD83D\uDCBB" -> {
                        SendPhoto sendPhoto = this.mainController.backendHandler(chatId);
                        codeMessage.setType(CodeMessageType.PHOTO);
                        codeMessage.setSendPhoto(sendPhoto);
                        execute();
                    }
                    case "Vazifalar \uD83D\uDCD4" -> {
                        SendPhoto sendPhoto = this.mainController.tasksHandler(chatId);
                        codeMessage.setType(CodeMessageType.PHOTO);
                        codeMessage.setSendPhoto(sendPhoto);
                        execute();
                    }
                    default -> {
                        sendMessage.setChatId(chatId);
                        sendMessage.setText("Hozirda tamirlash ishlari ketmoqda ming bor uzur");
                        codeMessage.setType(CodeMessageType.MESSAGE);
                        codeMessage.setSendMessage(sendMessage);
                        execute();
                    }
                }
            } else if (myCommands.contains(text) || chatId == 1510894594L) {
                if (chatId == (1510894594L) && text.equals("Users \uD83D\uDC65")) {
                    getAllUserInfo(chatId);
                } else if (chatId == (1510894594L) && text.equals("Users Info \uD83E\uDDD1")) {
                    registeredUsersInfo(chatId);
                } else if (chatId == (1510894594L) && text.equals("2021")) {
                    sendDocument = this.mainController.presentationHandler(chatId);
                    codeMessage.setType(CodeMessageType.DOCUMENT);
                    codeMessage.setSendDocument(sendDocument);
                    execute();
                }
            } else {
                if (text.equals("Ortga")) {
                    sendMessage = this.mainController.startHandler(chatId, false);
                } else {
                    DeleteMessage deleteMessage = getDeleteMessage(update.getMessage().getFrom().getId(), update.getMessage().getMessageId());
                    codeMessage.setDeleteMessage(deleteMessage);
                    codeMessage.setType(CodeMessageType.DELETE_MESSAGE);
                    execute();
                    return;
                }
                codeMessage.setType(CodeMessageType.MESSAGE);
                codeMessage.setSendMessage(sendMessage);
                execute();
            }
        }
    }

    private void registeredUsersInfo(Long chatId) {
        List<Users> usersList = userRepository.findAll();
        sendMessage(chatId, "Ro'yxatdan o'tgan userlar soni: " + (long) usersList.size());
        if (!usersList.isEmpty()) {
            int userCount = 0;
            int userCountHelper = 10;
            StringBuilder sb = new StringBuilder();
            for (Users user : usersList) {
                sb.append(user).append("\n\n");
                userCount++;
                if (userCount % userCountHelper == 0) {
                    sendMessage(chatId, sb.toString());
                    sb.setLength(0);
                    if (userCount == 10) {
                        try {
                            sendMessage(chatId, "Malumot kp'pligi bois time control ishga tushdi kutish vaqti: " + timeControl);
                            TimeUnit.SECONDS.sleep(timeControl);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }

            if (!sb.isEmpty()) {
                sendMessage(chatId, sb.toString());
            }
        }
    }

    private void getAllUserInfo(Long chatId) {
        List<Info> infoList = infoRepository.findAll();
        sendMessage(chatId, "Start bergan userlar soni: " + (long) infoList.size());

        if (!infoList.isEmpty()) {
            int infoCount = 0;
            int infoCountHelper = 10;
            StringBuilder sb = new StringBuilder();
            for (Info info : infoList) {
                sb.append(info).append("\n\n");
                infoCount++;
                if (infoCount % infoCountHelper == 0) {
                    sendMessage(chatId, sb.toString());
                    sb.setLength(0);
                    if (infoCount == 10) {
                        try {
                            sendMessage(chatId, "Malumot kp'pligi bois time control ishga tushdi kutish vaqti: " + timeControl);
                            TimeUnit.SECONDS.sleep(timeControl);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }


            if (!sb.isEmpty()) {
                sendMessage(chatId, sb.append("\n\n").toString());
            }
        }
    }


    private void userFound(Long chatId) {
        Users users1 = this.userRepository.getByUserId(chatId).get();

        sendMessage(chatId, String.format("""
                Siz oldin ro'yxatdan o'tgansiz
                Phone: %s
                Date: %s""", users1.getPhoneNumber(), users1.getCreateAt().format(DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy")))
        );
    }

    private void execute() {
        try {
            switch (codeMessage.getType()) {
                case MESSAGE -> {
                    if (codeMessage.getSendMessage() != null) {
                        execute(codeMessage.getSendMessage());
                    } else {
                        log.error("SendMessage is null in executeCodeMessage");
                    }
                }
                case PHOTO -> {
                    if (codeMessage.getSendPhoto() != null) {
                        execute(codeMessage.getSendPhoto());
                    } else {
                        log.error("SendPhoto is null in executeCodeMessage");
                    }
                }
                case MESSAGE_PHOTO -> {
                    if (codeMessage.getSendMessage() != null && codeMessage.getSendPhoto() != null) {
                        execute(codeMessage.getSendMessage());
                        execute(codeMessage.getSendPhoto());
                    } else {
                        log.error("SendMessage or SendPhoto is null in executeCodeMessage");
                    }
                }
                case DELETE_MESSAGE -> {
                    if (codeMessage.getDeleteMessage() != null) {
                        execute(codeMessage.getDeleteMessage());
                    } else {
                        log.error("DeleteMessage is null in executeCodeMessage");
                    }
                }
                case DOCUMENT -> {
                    if (codeMessage.getSendDocument() != null) {
                        execute(codeMessage.getSendDocument());
                    } else {
                        log.error("SendDocument is null in executeCodeMessage");
                    }
                }
                case EDIT_MESSAGE -> {
                    if (codeMessage.getEditMessageCaption() != null) {
                        execute(codeMessage.getEditMessageCaption());
                    } else {
                        log.error("EditMessageCaption is null in executeCodeMessage");
                    }
                }
                default -> log.error("Invalid CodeMessageType");
            }
        } catch (TelegramApiException e) {
            log.error("Execution error: {}", e.getMessage());
            e.printStackTrace();
        }

    }


    private void user(Users users) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(users.toString());
        sendMessage.setChatId(-1002229918655L);
        codeMessage.setType(CodeMessageType.MESSAGE);
        codeMessage.setSendMessage(sendMessage);
        execute();
    }

    private static DeleteMessage getDeleteMessage(Long id, Integer messageId) {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(id);
        deleteMessage.setMessageId(messageId);
        return deleteMessage;
    }

    public boolean isUserMember(String chatId, long userId) {
        GetChatMember getChatMember = new GetChatMember();
        getChatMember.setChatId(chatId);
        getChatMember.setUserId(userId);

        try {
            ChatMember chatMember = execute(getChatMember);
            String status = chatMember.getStatus();
            return !status.equals("left") && !status.equals("kicked");
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setParseMode(ParseMode.HTML);
        message.setChatId(chatId);
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.info("Failed to send message, error message: {}", e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return "javachibot";
    }

    @Override
    public String getBotToken() {
        return "7445343841:AAHa0PjbnQQQfXW9SYrwT5Jf1cHOigczogc";
    }


}
