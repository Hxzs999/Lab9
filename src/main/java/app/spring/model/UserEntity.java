package app.spring.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user", schema = "users")
@Getter
@Setter
public class UserEntity {

    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;

    @Basic
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Basic
    @Column(name = "password", nullable = false)
    private String password;

    @Basic
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Basic
    @Column(name = "role", nullable = false)
    private String role;

    // Конструкторы, геттеры и сеттеры уже предоставлены Lombok
    // Методы equals и hashCode также могут быть предоставлены Lombok
}