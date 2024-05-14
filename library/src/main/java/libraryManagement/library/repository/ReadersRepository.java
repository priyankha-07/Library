package libraryManagement.library.repository;

import libraryManagement.library.entity.Readers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadersRepository extends JpaRepository<Readers,Integer> {

Readers findByName(String name);
}
