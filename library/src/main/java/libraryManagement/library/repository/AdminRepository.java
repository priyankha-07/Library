package libraryManagement.library.repository;

import libraryManagement.library.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Admin findByName(String name);
}
