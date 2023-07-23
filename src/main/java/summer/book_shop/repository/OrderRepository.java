package summer.book_shop.repository;

import summer.book_shop.domain.Order;
import java.sql.*;
import static org.apache.coyote.http11.Constants.a;

public class OrderRepository {

    private Connection conn; // 데이터베이스 연결, 쿼리 전송(insert, delete, update)
    private PreparedStatement pstmt; //
    private ResultSet rs;

    public OrderRepository() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String hostName = "localhost";
        String databaseName = "Bookshop";
        String utf8Connection = "?useUnicode=true&characterEncoding=utf8";

        String url = "jdbc:mysql://" + hostName + ":3306/" + databaseName + utf8Connection;
        String userName = "root";
        String password = "onlyroot";

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

        //주문취소
        public void orderdelete(String orderCode) {
            String sql = "delete from orders where orderCode = ?";

            try {
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, orderCode);
                pstmt.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //주문저장
//        public void save(Order a){
//            String sql = "insert into orders values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
//
//            try {
//                pstmt = conn.prepareStatement(sql);
//
//                pstmt.setString(1, a.getOrderId());
//                pstmt.setString(2, a.getOrder_name());
//                pstmt.setString(3, a.getBookCode());
//                pstmt.setString(4, a.getPhone_number());
//                pstmt.setDate(5, a.getTotalPrice());
//                pstmt.setDate(6, (Date) a.getOrderDate());
//                pstmt.setDate(7, a.getProductCount());
//                pstmt.setInt(8, a.getTotalProductCount());
//                pstmt.setString(9, a.getPayment());
//                pstmt.setDate(10, a.getOrderCode());
//                pstmt.setString(11, a.getShipping_address());
//
//
//                pstmt.execute();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            //주문수정
//
//            //할인적용??
//        }

        //주문조회
//        public Order selectOrder(String orderCode){
//            String sql = "select * from orders where orderCode = ?";
//            try{
//                pstmt = conn.prepareStatement(sql);
//                pstmt setString(1, orderCode);
//
//                rs = pstmt.executeQuery(sql);
//                Order order = new Order();
//                while (rs.next()){
//                    order.setOrderCode(rs.getString(1));
//                    order.setOrderId(rs.getString(2));
//                    order.setOrder_name(rs.getString(3));
//                    order.setBookCode(rs.getString(4));
//                    order.setPhone_number(rs.getString(5));
//                    order.setTotalPrice(rs.getInt(6));
//                    order.setOrderDate(rs.getDate(7));
//                    order.setProductCount(rs.getInt(8));
//                    order.setTotalProductCount(rs.getInt(9));
//                    order.setPayment(rs.next());
//                    order.setStatus(rs.getBoolean(11));
//                    order.setShipping_address(rs.getString(12));
//                }
//                return order;
//            }catch (Exception e) {
//                e.printStackTrace();
//                return null;
//            }
//        }
    }

