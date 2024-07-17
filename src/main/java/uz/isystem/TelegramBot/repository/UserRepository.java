package uz.isystem.TelegramBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.isystem.TelegramBot.users.Users;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {


    Boolean existsUsersByUserId(Long id);
    Optional<Users> getByUserId(Long id);

}
