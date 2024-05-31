package uz.isystem.TelegramBot.users;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Entity
@Data
@NoArgsConstructor
public class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long userId;
    private LocalDateTime startedDate;
    public Info(Long userId, LocalDateTime startedDate) {
        this.userId = userId;
        this.startedDate = startedDate;
    }

    @Override
    public String toString() {
        return String.format("Id: %d\nUser id: %d\nRegister date: %s", id, userId, startedDate.format(DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy")));
    }
}
