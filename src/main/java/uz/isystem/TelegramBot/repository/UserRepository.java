package uz.isystem.TelegramBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.isystem.TelegramBot.users.Users;


@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Boolean existsByUserId(Long id);
}
