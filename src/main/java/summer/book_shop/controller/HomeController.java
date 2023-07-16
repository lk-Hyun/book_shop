package summer.book_shop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import summer.book_shop.domain.User;
import summer.book_shop.exception.UserException;
import summer.book_shop.repository.UserRepository;
import summer.book_shop.service.UserService;

@RestController
public class HomeController { // 주문 성공, 수정 성공, 삭제 성공

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() { // 200 success / 404 프론트앤드 파일 경로 에러(못찾음) / 500 서버 에러 (비즈니스 로직, 데이터베이스 연결 등)
        System.out.println("hello index!");
        return "ok";
    }

    @PostMapping("/register") // HTTP method - GET(정보를 받아올 때) / POST(정보를 줄 때) / PUT / DELETE
    public ResponseEntity home(@RequestBody String id) {
        User user = new User(id, "1234", "seed");

        try {
            userService.signUp(user);
        } catch (UserException e) {
            return new ResponseEntity(e.getExceptionType().getErrorMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("register success", HttpStatus.OK);
    }
}
