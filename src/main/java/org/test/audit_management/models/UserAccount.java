package org.test.audit_management.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.test.audit_management.dtos.NewUserDTO;

/**
 * Class that represents and stores the user information.
 * <p>
 * This class represents the entity user account, it has the information of the user and also an instance of the authentication object.
 * Each object of this class has a One to One relation to an object of the class UserAuth.
 * </p>
 * @see UserAuth
 * @version 1.0
 */

@Entity
@Table(name = "user_account")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserAccount {

    @Id
    @GeneratedValue
    private long userAccountId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "role")
    private String role;

    @OneToOne(mappedBy = "userAccount", orphanRemoval = true)
    @JsonIgnore
    private UserAuth userAuth;

    /** This constructor is used during the creation of a new user on the UserAuthService.
     *
     *
     * @param newUserDTO DTO received from the front-end with the user information defined during the register.
     * @param userAuth Auth object created on the UserAuthService.
     *
     * @see org.test.audit_management.services.UserAuthService
     */
    public UserAccount(NewUserDTO newUserDTO, UserAuth userAuth) {
        this.firstName = newUserDTO.firstName();
        this.lastName = newUserDTO.lastName();
        this.email = newUserDTO.email();
        this.role = newUserDTO.role();
        this.userAuth = userAuth;
    }
}
