package uz.isystem.TelegramBot.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChat;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMember;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.isystem.TelegramBot.MainContoller.MainController;
import uz.isystem.TelegramBot.repository.UserRepository;
import uz.isystem.TelegramBot.users.Users;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Component
@RequiredArgsConstructor
public class BotConfig extends TelegramLongPollingBot {


    private final UserRepository userRepository;
    private final MainController mainController;

    private final List<String> commands = List.of("/start", "/stop", "/help", "/check/subscribe");
    private final List<String> callbackCommands = List.of("/back", "/back/2", "/foundation/online", "/foundation/offline", "/backend/online", "/backend/offline", "/f/o/kursga/yozilish");
    private final List<String> callbackTasksCommands = List.of("/foundation/tasks");
    private final List<String> callbackSocialMedia = List.of("/cv", "/social/media/back");
    private final List<String> callbackTasksCommand = List.of("/tasks/if", "/tasks/for", "/tasks/while", "/tasks/nested-loop", "/tasks/methods", "/tasks/arrays", "/tasks/string", "/tasks/back", "/foundation/tasks/back", "/foundation/task/back");
    private final List<String> buttonCommands = List.of("Men haqimda \uD83D\uDC40", "Foundation \uD83E\uDDD1\u200D\uD83D\uDCBB", "Backend \uD83D\uDC68\u200D\uD83D\uDCBB", "Vazifalar \uD83D\uDCD4", "Presentation", "2021");
    private final Map<Long, Integer> mapUsers = new HashMap<>();

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
                    case "/foundation/task/back" ->{
                        SendPhoto sendPhoto = this.mainController.tasksHandler(chatId);
                        codeMessage.setType(CodeMessageType.PHOTO);
                        codeMessage.setSendPhoto(sendPhoto);
                        execute();
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
                    }

                    case "/tasks/while" -> {
                        sendDocument = this.mainController.sendWhileTask(chatId);
                        codeMessage.setType(CodeMessageType.DOCUMENT);
                        codeMessage.setSendDocument(sendDocument);
                        execute();
                    }

                    case "/tasks/methods" -> {
                        sendDocument = this.mainController.sendFunctionTask(chatId);
                        codeMessage.setType(CodeMessageType.DOCUMENT);
                        codeMessage.setSendDocument(sendDocument);
                        execute();
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

                CodeMessage codeMessage1 = this.mainController.getLanguage(chatId);
                codeMessage.setType(CodeMessageType.PHOTO);
                codeMessage.setSendPhoto(codeMessage1.getSendPhoto());
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
//                if (!this.userRepository.existsByUserId(users.getUserId())) {
                userRepository.save(users);
                sendMessage(chatId, "<b>Siz bilan tez orada bog'lanamiz.</b>");

//                }else {
//                    sendMessage(chatId,"User saqlanmadi");
//                }
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
                    CodeMessage codeMessage1 = this.mainController.getLanguage(chatId);
                    codeMessage.setType(CodeMessageType.PHOTO);
                    codeMessage.setSendPhoto(codeMessage1.getSendPhoto());
                    execute();
                }
            } else if (buttonCommands.contains(text)) {

                if (text.equals("Men haqimda \uD83D\uDC40")) {
                    SendPhoto sendPhoto = this.mainController.aboutMe(chatId);
                    codeMessage.setType(CodeMessageType.PHOTO);
                    codeMessage.setSendPhoto(sendPhoto);
                    execute();
                } else if (text.equals("Foundation \uD83E\uDDD1\u200D\uD83D\uDCBB")) {
                    SendPhoto sendPhoto = this.mainController.foundationHandler(chatId);
                    codeMessage.setType(CodeMessageType.PHOTO);
                    codeMessage.setSendPhoto(sendPhoto);
                    execute();
                } else if (text.equals("Backend \uD83D\uDC68\u200D\uD83D\uDCBB")) {
                    SendPhoto sendPhoto = this.mainController.backendHandler(chatId);
                    codeMessage.setType(CodeMessageType.PHOTO);
                    codeMessage.setSendPhoto(sendPhoto);
                    execute();
                } else if (chatId == (1510894594L) && text.equals("Presentation") || chatId == (1510894594L) && text.equals("2021")) {
                    sendDocument = this.mainController.presentationHandler(chatId);
                    codeMessage.setType(CodeMessageType.DOCUMENT);
                    codeMessage.setSendDocument(sendDocument);
                    execute();
                } else if (text.equals("Vazifalar \uD83D\uDCD4")) {

                    SendPhoto sendPhoto = this.mainController.tasksHandler(chatId);
                    codeMessage.setType(CodeMessageType.PHOTO);
                    codeMessage.setSendPhoto(sendPhoto);
                    execute();

                } else {
                    sendMessage.setChatId(chatId);
                    sendMessage.setText("Hozirda tamirlash ishlari ketmoqda ming bor uzur");
                    codeMessage.setType(CodeMessageType.MESSAGE);
                    codeMessage.setSendMessage(sendMessage);
                    execute();
                }

            } else {
                if (text.equals("Ortga")) {
                    sendMessage = this.mainController.startHandler(chatId, false);
                } else {
                    sendMessage = this.mainController.commandNotFound(chatId);
                }
                codeMessage.setType(CodeMessageType.MESSAGE);
                codeMessage.setSendMessage(sendMessage);
                execute();
            }
        }
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

    @Override
    public String getBotUsername() {
        return "asadbek_abdinazarov_bot";
    }

    @Override
    public String getBotToken() {
        return "6965085755:AAFRLpjiHqUlFifCZxZmis2SvJjZhvu01uU";
    }


}
