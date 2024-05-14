package libraryManagement.library.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Table(name = "usersinfo")
    public class UserInfo {
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private int id;
        private  String name;
        private String email;
        private String password;
        private String roles;
    }


