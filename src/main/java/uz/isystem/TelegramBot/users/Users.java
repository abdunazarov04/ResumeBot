package uz.isystem.TelegramBot.users;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
    @Column(name = "phone_number")
    private String phoneNumber;
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
        return "New Message\n" +
                "userId: " + userId +
                "\nfirstName: " + firstName +
                "\nlastName: " + lastName +
                "\nusername: " + username +
                "\nphoneNumber: " + phoneNumber +
                "\nlanguageCode: " + languageCode +
                "\nisPremium: " + isPremium +
                "\nisBot: " + isBot +
                "\nCreate at: " + createAt;
    }
}
