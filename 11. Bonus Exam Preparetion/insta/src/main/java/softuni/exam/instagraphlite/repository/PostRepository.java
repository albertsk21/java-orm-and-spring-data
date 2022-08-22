package softuni.exam.instagraphlite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.instagraphlite.models.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {


    @Query("FROM Post AS p "+
           "JOIN p.user AS u " +
           "GROUP BY u.id " +
           "ORDER BY COUNT(p)")
    Object[] getAllPostsByUser();




}
