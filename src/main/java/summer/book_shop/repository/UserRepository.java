package summer.book_shop.repository;

import org.springframework.stereotype.Repository;
import summer.book_shop.domain.Grade;
import summer.book_shop.domain.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private Connection conn; // 데이터베이스 연결, 쿼리 전송(insert, delete, update)
    private PreparedStatement pstmt;
    private ResultSet rs;

    public UserRepository() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String hostName = "localhost";
        String databaseName = "book_shop";
        String utf8Connection = "?useUnicode=true&characterEncoding=utf8";

        String url = "jdbc:mysql://" + hostName + ":3306/" + databaseName + utf8Connection;
        String userName = "root";
        String password = "skq1w2e#";

        try {
//            Class.forName(driver); // forName 을 통해 JVM 에 메모리를 올린다 (JDBC 4.0 이후로는 사용하지 않아도 자동 초기화)
            conn = DriverManager.getConnection(url, userName, password); // 이후 등록된 정보를 통해 데이터베이스 연결
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(User user) {
        String sql = "insert into user values(?, ?, ?, ?, ?, ?, ?);";

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getPhoneNum());
            pstmt.setString(4, user.getNickname());
            pstmt.setDate(5, (Date) user.getBirthDate());
            pstmt.setString(6, Grade.NONE.toString()); //초기에 비회원 설정
            pstmt.setDate(7, Date.valueOf(LocalDate.now()));

            pstmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String userId) {
        String sql = "delete from user where user_id=?";

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, userId);

            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePassword(String userId, String nPassword) {
        String sql = "update user set password=? where user_id=?";

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, nPassword);
            pstmt.setString(2, userId);
            pstmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean existByUserId(String userId) {
        String sql = "select * from user where user_id=?"; // 쿼리 수정 예정

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, userId);
            return pstmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User findByUserId(String userId) {
        String sql = "select * from user where user_id=?";

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, userId);

            rs = pstmt.executeQuery();

            User user = new User();

            while(rs.next()) {
                getUser(user);
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> findAll() {
        String sql = "select * from user";
        ArrayList<User> users = new ArrayList<>();

        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery(sql);

            while(rs.next()) {
                User user = new User();
                getUser(user);
                users.add(user);
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void getUser(User user) throws SQLException {
        user.setUserId(rs.getString(1));
        user.setPassword(rs.getString(2));
        user.setPhoneNum(rs.getString(3));
        user.setNickname(rs.getString(4));
        user.setBirthDate(rs.getDate(5));
        user.setGrade(Grade.valueOf(rs.getString(6)));
        user.setCreatedAt(rs.getDate(7));
    }

    public int countByUser() {
        String sql = "select count(*) from user;";

        try {
            pstmt = conn.prepareStatement(sql);

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void deleteAll() {
        String sql = "delete from User";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

