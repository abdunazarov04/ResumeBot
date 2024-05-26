package uz.isystem.TelegramBot.config;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.isystem.TelegramBot.MainContoller.Controller;
import uz.isystem.TelegramBot.users.Users;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;


import static uz.isystem.TelegramBot.utils.InlineKeyboards.*;

@Slf4j
@org.springframework.stereotype.Controller
public class BotConfig extends TelegramLongPollingBot {


    private final Controller controller;
    //    private final UserService userService;
    private static Integer contactPhotoId;

    public BotConfig() {
//        this.userService = new UserService();
        controller = new Controller();
    }

    private final List<String> commands = List.of("/start", "/stop", "/help");
    private final List<String> buttonCommands = List.of("Men haqimda \uD83D\uDC40", "Foundation \uD83E\uDDD1\u200D\uD83D\uDCBB", "Backend \uD83D\uDC68\u200D\uD83D\uDCBB", "Vazifalar \uD83D\uDCD4", "Presentation","2021");


    @Override
    public void onUpdateReceived(Update update) {
        SendDocument sendDocument;

        if (update.hasCallbackQuery()) {

            CallbackQuery callbackQuery = update.getCallbackQuery();
            Long id = callbackQuery.getFrom().getId();
            Integer messageId = callbackQuery.getMessage().getMessageId();
            String data = callbackQuery.getData();
            if (data.equals("/back")) {
                DeleteMessage deleteMessage = getDeleteMessage(id, messageId);
                deleteMessageExecute(deleteMessage);
                SendMessage sendMessage = this.controller.startHandler(id, false);
                sendMessageExecute(sendMessage);
                return;
            } else if (data.equals("/back/2")) {
                EditMessageCaption editMessageCaption = getEditMessageCaption(id, messageId);
                editCaptionExecute(editMessageCaption);
               /* EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup();
                editMessageReplyMarkup.setMessageId(messageId);
                editMessageReplyMarkup.setChatId(id);
                editMessageReplyMarkup.setReplyMarkup(
                        InlineKeyboards.keyboardMarkup(
                                InlineKeyboards.collection(
                                        InlineKeyboards.row(
                                                InlineKeyboards.button("Online", "/online", ":boom:"),
                                                InlineKeyboards.button("Offline", "/offline", ":boom:")
                                        ),
                                        InlineKeyboards.row(
                                                InlineKeyboards.button("Ortga", "/ortga")
                                        )
                                )
                        )
                );
                try {
                    execute(editMessageReplyMarkup);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }*/
                return;
            } else if (data.equals("/foundation/online")) {
                EditMessageCaption editMessageCaption;
                editMessageCaption = this.controller.onlineCourseHandler(messageId, id);
                editCallbackQueryTextExecute(editMessageCaption);
                return;
            } else if (data.equals("/foundation/offline")) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(id);
                sendMessage.setText("Offline course hozircha tamirda");
                sendMessageExecute(sendMessage);
                return;
            } else if (data.equals("/backend/online")) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(id);
                sendMessage.setText("Online course hozircha tamirda");
                sendMessageExecute(sendMessage);
                return;
            } else if (data.equals("/backend/offline")) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(id);
                sendMessage.setText("Offline course hozircha tamirda");
                sendMessageExecute(sendMessage);
                return;
            } else {
                if (data.equals("/kursga/yozilish")) {
                    DeleteMessage deleteMessage = getDeleteMessage(id, messageId);
                    deleteMessageExecute(deleteMessage);
                    SendPhoto sendPhoto = this.controller.onlineCourseRegisterHandler(id);

                    try {
                        Message message = execute(sendPhoto);
                        int messageIds = message.getMessageId();

                        new Thread(() -> {
                            try {
                                Thread.sleep(30000);
                                deleteMessage(id, messageIds);
                            } catch (InterruptedException | TelegramApiException e) {
                                e.printStackTrace();
                            }
                        }).start();
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }


                }
            }
        }


        if (update.hasMessage()) {

            DeleteMessage deleteMessage = new DeleteMessage();
            SendMessage sendMessage = new SendMessage();

            if (update.getMessage().hasContact()) {
                Contact contact = update.getMessage().getContact();
                sendMessage.setChatId(update.getMessage().getFrom().getId());
                sendMessage.setText("Rahmat siz bilan tez orada bog'lanamiz...");

                deleteMessage.setChatId(update.getMessage().getFrom().getId());
                deleteMessage.setMessageId(update.getMessage().getMessageId());
                deleteMessageExecute(deleteMessage);
                sendMessage = this.controller.startHandler(update.getMessage().getFrom().getId(), false);
                sendMessageExecute(sendMessage);

                Users users = getUsers(update, contact);

                user(users);
                return;
            }

            Message message = update.getMessage();
            String text = message.getText();
            Long chatId = message.getChatId();
            if (commands.contains(text)) {
                sendMessage = this.controller.startHandler(chatId, true);
            } else if (buttonCommands.contains(text)) {

                if (text.equals("Men haqimda \uD83D\uDC40")) {
                    sendDocument = this.controller.aboutMeHandler(chatId);
                    sendDocumentExecute(sendDocument);
                    return;
                } else if (text.equals("Foundation \uD83E\uDDD1\u200D\uD83D\uDCBB")) {
                    SendPhoto sendPhoto = this.controller.foundationHandler(chatId);
                    sendPhotoExecute(sendPhoto);
                    return;
                } else if (text.equals("Backend \uD83D\uDC68\u200D\uD83D\uDCBB")) {
                    SendPhoto sendPhoto = this.controller.backendHandler(chatId);
                    sendPhotoExecute(sendPhoto);
                    return;
                } else if (chatId == (1510894594L) && text.equals("Presentation") || chatId == (1510894594L) && text.equals("2021")) {

                    sendDocument = this.controller.presentationHandler(chatId);
                    sendDocumentExecute(sendDocument);
                    return;
                } else {

                    sendMessage.setChatId(chatId);
                    sendMessage.setText("Hozirda tamirlash ishlari ketmoqda ming bor uzur");
                }

            } else {
                if (text.equals("Ortga")) {
                    sendMessage = this.controller.startHandler(chatId, false);
                } else {
                    try {
                        deleteMessage(update.getMessage().getChatId(), update.getMessage().getMessageId());
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    return;
//                    sendMessage = this.controller.commandNotFound(chatId);
                }
            }

            if (sendMessage != null) {
                sendMessageExecute(sendMessage);
            } else {
                log.error("SendMessage object or chatId is null");
                System.err.println("SendMessage object or chatId is null.");
            }
        } else {


        }


    }

    private Message sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void deleteMessage(Long id, int messageIds) throws TelegramApiException {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(id);
        deleteMessage.setMessageId(messageIds);
        execute(deleteMessage);
    }

    private Users getUsers(Update update, Contact contact) {
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
//        this.userService.save(users);
        return users;
    }

    private void user(Users users) {
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setText(users.toString());
        sendMessage1.setChatId(-1002229918655L);
        sendMessageExecute(sendMessage1);
    }

    private static DeleteMessage getDeleteMessage(Long id, Integer messageId) {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(id);
        deleteMessage.setMessageId(messageId);
        return deleteMessage;
    }

    private void deleteMessageExecute(DeleteMessage deleteMessage) {
        try {
            execute(deleteMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void editCaptionExecute(EditMessageCaption editMessageCaption) {
        try {
            execute(editMessageCaption);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private static EditMessageCaption getEditMessageCaption(Long id, Integer messageId) {
        EditMessageCaption editMessageCaption = new EditMessageCaption();
        editMessageCaption.setChatId(id);
        editMessageCaption.setMessageId(messageId);
        try {
            editMessageCaption.setCaption(Files.readString(Path.of("/Users/macbookair/isystem/TelegramBot/src/main/java/uz/isystem/TelegramBot/TXT/FoundationInfo.txt")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        editMessageCaption.setReplyMarkup(
                keyboardMarkup(
                        collection(
                                row(
                                        button("\uD83D\uDC49 Online \uD83D\uDC48", "/foundation/online"),
                                        button("\uD83D\uDC49 Offline \uD83D\uDC48", "/foundation/offline")
                                ),
                                row(
                                        button("Ortga ⬅️", "/back")
                                )
                        )
                )
        );
        return editMessageCaption;
    }

    private void editCallbackQueryTextExecute(EditMessageCaption editMessageCaption) {
        try {
            execute(editMessageCaption);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    /* private void editMessageText(EditMessageText editMessageText) {
         try {
             execute(editMessageText);
         } catch (TelegramApiException e) {
             throw new RuntimeException(e);
         }
     }
 */
    private void sendPhotoExecute(SendPhoto sendPhoto) {
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendDocumentExecute(SendDocument sendDocument) {
        try {
            execute(sendDocument);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendMessageExecute(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
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
