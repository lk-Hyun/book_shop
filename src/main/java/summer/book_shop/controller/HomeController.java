package summer.book_shop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import summer.book_shop.domain.User;
import summer.book_shop.exception.UserException;
import summer.book_shop.service.UserService;

@RestController
public class HomeController { // 주문 성공, 수정 성공, 삭제 성공

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

}
