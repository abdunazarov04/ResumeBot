package uz.isystem.TelegramBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.isystem.TelegramBot.users.Info;

public interface InfoRepository extends JpaRepository<Info, Integer> {
    Boolean existsByUserId(Long id);
}
