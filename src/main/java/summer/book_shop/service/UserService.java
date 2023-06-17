package summer.book_shop.service;

import summer.book_shop.domain.User;
import summer.book_shop.repository.UserRepository;

public class UserService {

    private UserRepository userRepository;

    public void register(User user) {

        Long user_id = userRepository.save(user);

        if (user_id == 1) {
            System.out.println("success");
        } else {
            System.out.println("fail");
        }
    }
}
