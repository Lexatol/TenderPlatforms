package ru.lexp00.platform.tpusers.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "user_id")
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private UUID userId;

    @Column(name = "user_name")
    @Size(min = 1, message = "User name must be more than 4 characters")
    private String userName;

    @Column(name = "user_password")
    @Size(min = 6, message = "Password must be more than 6 characters")
    private String userPassword;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ToString.Exclude
    private Collection<Role> userRoles;

}
