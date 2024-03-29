package smarthome.model;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import smarthome.repository.UserRepository;

import java.util.Arrays;
import java.util.List;


@Service
public class DbInit implements CommandLineRunner {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public DbInit(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Delete all
        this.userRepository.deleteAll(); // erases the entire user DB. Should be replaced by an actual user management component.

        // Create users
        User regular = new User("regular",passwordEncoder.encode("regular123"),"USER","");
        User admin = new User("admin",passwordEncoder.encode("admin123"),"ADMIN","ACCESS_TEST1,ACCESS_TEST2");

        List<User> users = Arrays.asList(regular,admin);

        // Save to db
        this.userRepository.saveAll(users);




	}
}