package libraryManagement.library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "readers")

public class Readers {
    @Id
    @GeneratedValue
    int id;
    String name;
    String address;
    String bookName;
    String password;
    String roles;
}
