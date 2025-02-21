package am.itspace.shoesmarket.repository;

import am.itspace.shoesmarket.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
  List<Comment> findByShoesId(int shoesId);
}