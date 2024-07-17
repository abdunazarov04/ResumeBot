package uz.isystem.TelegramBot.users;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class Users {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String username;

    @Column(name = "language_code")
    private String languageCode;
    @Column(name = "is_premium")
    private Boolean isPremium;
    @Column(name = "is_bot")
    private Boolean isBot;
    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        switch (languageCode) {
            case "uz" -> languageCode = "\uD83C\uDDFA\uD83C\uDDFF";
            case "en" -> languageCode = "\uD83C\uDDFA\uD83C\uDDF8";
            case "ru" -> languageCode = "\uD83C\uDDF7\uD83C\uDDFA";
        }
        return "New Message\n" +
                "---------------------" +
                "\uD83C\uDD94: " + userId +
                "\nFirst Name: " + firstName +
                "\nLast Name: " + lastName +
                "\nUsername: @" + username +
                "\nLanguage: " + languageCode +
                "\n\uD83C\uDF81 Is Premium ?: " + isPremium +
                "\n\uD83E\uDD16 Is Bot ?: " + isBot +
                "\n\uD83D\uDCC5 Created Date: " + createAt.format(formatter) +
                "---------------------";
    }
}
