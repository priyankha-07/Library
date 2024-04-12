package libraryManagement.library.repository;

import libraryManagement.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Integer> {


    @Query(value = "SELECT u FROM User u WHERE u.name = :name", nativeQuery = true)
    User findByName(@Param("name") String name);
}
