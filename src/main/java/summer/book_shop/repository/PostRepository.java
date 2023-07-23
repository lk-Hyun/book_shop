package summer.book_shop.repository;

import summer.book_shop.domain.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostRepository {
        private Connection conn;
        private PreparedStatement pstmt;
        private ResultSet rs;

        public PostRepository() {
            String driver = "com.mysql.cj.jdbc.Driver";
            String hostName = "localhost";
            String databaseName = "book_shop";
            String utf8Connection = "?useUnicode=true&characterEncoding=utf8";

            String url = "jdbc:mysql://" + hostName + ":3306/" + databaseName + utf8Connection;
            String userName = "user_root";
            String password = "1234";

            try {
                conn = DriverManager.getConnection(url, userName, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //post 저장
        public void save(Post post) {
            String sql = "INSERT INTO post (post_id, title, content, review, like_count, views, book_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try {
                pstmt = conn.prepareStatement(sql);

                pstmt.setLong(1, post.getPostId());
                pstmt.setString(2, post.getTitle());
                pstmt.setString(3, post.getContent());
                pstmt.setString(4, post.getReview());
                pstmt.setInt(5, post.getLike());
                pstmt.setInt(6, post.getViews());
                pstmt.setString(7, post.getBookId());
                pstmt.setDate(8, (Date) post.getUpdateDate());

                pstmt.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //post삭제
        public void delete(Long postId) {
            String sql = "DELETE FROM post WHERE post_id = ?";

            try {
                pstmt = conn.prepareStatement(sql);
                pstmt.setLong(1, postId);

                pstmt.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public boolean existByPostId(Long postId) {
            String sql = "select * from post where post_id=?";

            try {
                pstmt = conn.prepareStatement(sql);

                pstmt.setLong(1, postId);
                return pstmt.execute();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        //Id값을 이용해 Post찾기
        public Post findByPostId(String postId) {
            String sql = "SELECT * FROM post WHERE post_id = ?";

            try {
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, postId);

                rs = pstmt.executeQuery();

                Post post = new Post();

                while (rs.next()) {
                    post.setPostId(rs.getLong("post_id"));
                    post.setTitle(rs.getString("title"));
                    post.setContent(rs.getString("content"));
                    post.setReview(rs.getString("review"));
                    post.setLike(rs.getInt("like_count"));
                    post.setViews(rs.getInt("views"));
                    post.setBookId(rs.getString("book_id"));
                    post.setUpdateDate(rs.getDate("updateDate"));
                }
                return post;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        //최신순 정렬
        public List<Post> findPostsOrderByLatest(Date updateDate) {
            String sql = "SELECT * FROM post ORDER BY updateDate DESC";
            try {
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();

                List<Post> posts = new ArrayList<>();

                while (rs.next()) {
                    posts.add(createPostFromResultSet(rs));
                }

                if (posts.isEmpty()) {
                    throw new Exception("포스트를 찾을 수 없습니다.");
                }

                return posts;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        //좋아요순 정렬
        public List<Post> findPostsOrderByLikes(int Like) {
            String sql = "SELECT * FROM post ORDER BY like DESC";
            try {
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();

                List<Post> posts = new ArrayList<>();

                while (rs.next()) {
                    posts.add(createPostFromResultSet(rs));
                }

                if (posts.isEmpty()) {
                    throw new Exception("포스트를 찾을 수 없습니다.");
                }

                return posts;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        //조회수순 정렬
        public List<Post> findPostsOrderByViews(int views) {
            String sql = "SELECT * FROM post ORDER BY views DESC";
            try {
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();

                List<Post> posts = new ArrayList<>();

                while (rs.next()) {
                    posts.add(createPostFromResultSet(rs));
                }

                if (posts.isEmpty()) {
                    throw new Exception("포스트를 찾을 수 없습니다.");
                }

                return posts;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }


        public Post createPostFromResultSet(ResultSet rs) throws SQLException {
            Post post = new Post();
            post.setPostId(rs.getLong(1));
            post.setTitle(rs.getString(2));
            post.setContent(rs.getString(3));
            post.setReview(rs.getString(4));
            post.setLike(rs.getInt(5));
            post.setViews(rs.getInt(6));
            post.setBookId(rs.getString(7));
            post.setUpdateDate(rs.getDate(8));
            return post;
        }


    }

