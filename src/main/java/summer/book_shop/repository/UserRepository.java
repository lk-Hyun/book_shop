package summer.book_shop.repository;

// 회원 저장, 회원 정보 수정, 회원 삭제

import summer.book_shop.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// save, update, delete

// Connection, Database(mysql)

public class UserRepository { // 사용자 관련 저장소
    // User
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;

    public Long save(User user) {
        //database connection

        //mysql query - insert

        //return user_id
        try {


        } catch () {
            return -1l;
        }

    }
}
