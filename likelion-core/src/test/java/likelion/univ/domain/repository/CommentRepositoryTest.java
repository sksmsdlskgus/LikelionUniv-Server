package likelion.univ.domain.repository;

import likelion.univ.CoreApplicationTest;
import likelion.univ.domain.comment.repository.CommentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

//@DataJpaTest // 안됨
@SpringBootTest // 됨
@ContextConfiguration(classes = CoreApplicationTest.class)
class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;

    @Test
    @DisplayName("테스트")
    void findAllByPost() {

    }
}
