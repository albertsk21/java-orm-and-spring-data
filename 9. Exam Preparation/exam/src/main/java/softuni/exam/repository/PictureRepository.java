package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.modelDB.Picture;

@Repository
public interface PictureRepository extends JpaRepository<Picture,Long> {



    @Query("FROM Picture WHERE url = ?1")
    Picture findByUrl(String url);

}
