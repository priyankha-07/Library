package libraryManagement.library.repository;

import libraryManagement.library.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository  extends JpaRepository<Books , Integer> {
    Books findByName(String name);
}
