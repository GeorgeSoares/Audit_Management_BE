package org.test.audit_management.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.test.audit_management.dtos.NewUserDTO;

/**
 * Class that is used to store the authentication data of the user, as well as it is used for this purpose.
 *
 * <p>
 * This class stores the authentication information of the user on its parameters, the idea to create such structure was to implement safety
 * good practices. Each object of this class has a One to One relation with an object of the UserAccount class.
 * </p>
 *
 * @see UserAccount
 * @version 1.0
 */

@Table(name = "users")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserAuth {

    @Id
    @GeneratedValue
    private long userId;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_account_id", referencedColumnName = "userAccountId")
    @JsonIgnore
    private UserAccount userAccount;

    /**
     * UserAuth constructor used during the creation of a new user.
     * <p>
     * This constructor is used during the creation of a new user on the Service, it takes an DTO as parameter and
     * should store the e-mail and the encrypted password.
     * </p>
     *
     * @see org.test.audit_management.services.UserAuthService
     * @param newUserDTO
     */
    public UserAuth(NewUserDTO newUserDTO) {
        this.email = newUserDTO.email();
        this.password = newUserDTO.password();
    }
}
