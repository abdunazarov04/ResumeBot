package uz.isystem.TelegramBot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.isystem.TelegramBot.repository.UserRepository;
import uz.isystem.TelegramBot.users.Users;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public void save(Users users) {
        if (users != null){
            userRepository.save(users);
        }
    }
}
