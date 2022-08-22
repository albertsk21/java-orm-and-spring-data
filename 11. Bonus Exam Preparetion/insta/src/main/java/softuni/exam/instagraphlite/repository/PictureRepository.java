package softuni.exam.instagraphlite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.instagraphlite.models.entity.Picture;

@Repository
public interface PictureRepository extends JpaRepository<Picture,Long> {


    @Query("From Picture WHERE path = ?1")
   Picture getPictureByUrl(String url);


    @Query("FROM Picture AS p "+
           "WHERE p.size > 30000 "+
           "ORDER BY p.size ASC")
    Picture[] getAllPicture();


}
