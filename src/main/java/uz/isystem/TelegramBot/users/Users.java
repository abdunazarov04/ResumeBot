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

        // Check for null and assign default value if necessary
        String languageSymbol;
        if (languageCode != null) {
            switch (languageCode) {
                case "uz":
                    languageSymbol = "\uD83C\uDDFA\uD83C\uDDFF";
                    break;
                case "en":
                    languageSymbol = "\uD83C\uDDFA\uD83C\uDDF8";
                    break;
                case "ru":
                    languageSymbol = "\uD83C\uDDF7\uD83C\uDDFA";
                    break;
                default:
                    languageSymbol = languageCode;
            }
        } else {
            languageSymbol = "N/A";
        }

        return "New Message\n" +
                "---------------------\n" +
                "\uD83C\uDD94: " + (userId != null ? userId : "N/A") +
                "\n\uD83D\uDC64 First Name: " + (firstName != null ? firstName : "N/A") +
                "\n\uD83D\uDC64 Last Name: " + (lastName != null ? lastName : "N/A") +
                "\n\uD83D\uDC68\u200D\uD83D\uDCBB Username: @" + (username != null ? username : "N/A") +
                "\n\uD83D\uDD04 Language: " + languageSymbol +
                "\n\uD83C\uDF81 Is Premium ?: " + (isPremium != null ? isPremium : "N/A") +
                "\n\uD83E\uDD16 Is Bot ?: " + (isBot != null ? isBot : "N/A") +
                "\n\uD83D\uDCC5 Created Date: " + (createAt != null ? createAt.format(formatter) : "N/A") + "\n" +
                "---------------------";
    }

}
