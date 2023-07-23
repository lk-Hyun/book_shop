package summer.book_shop.service;

import summer.book_shop.domain.Post;
import summer.book_shop.repository.PostRepository;
import java.sql.Date;
import java.util.List;

public class PostService {
    private PostRepository postRepository;

    public void createPost(Post post) {
        //예외처리 필요
        postRepository.save(post);
    }

    public void removePost(Long postId) throws Exception {
        if (!postRepository.existByPostId(postId)) {
            throw new RuntimeException("포스트를 찾을 수 없습니다.");
        }
        postRepository.delete(postId);
    }

    //최신순으로 정렬
    public List<Post> orderPostByUpdateDate(Date updateDate) throws Exception{
        return postRepository.findPostsOrderByLatest(updateDate);
    }
    //좋아요순
    public List<Post> orderPostByLike(int like) throws Exception{
        return postRepository.findPostsOrderByLikes(like);
    }
    //조회수순
    public List<Post> orderPostByviews(int views) throws Exception{
        return postRepository.findPostsOrderByViews(views);
    }

}