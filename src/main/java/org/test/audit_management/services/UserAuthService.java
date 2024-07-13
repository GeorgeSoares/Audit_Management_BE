package org.test.audit_management.services;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.audit_management.dtos.ChangeUserPasswordDTO;
import org.test.audit_management.dtos.NewUserDTO;
import org.test.audit_management.models.UserAccount;
import org.test.audit_management.models.UserAuth;
import org.test.audit_management.repositories.UserAccountRepository;
import org.test.audit_management.repositories.UserAuthRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserAuthService {

    @Autowired
    UserAuthRepository userAuthRepository;

    @Autowired
    UserAccountRepository userAccountRepository;

    //
    public UserAccount createUser(@NotNull NewUserDTO newUserDTO) throws Exception {
        Optional<UserAuth> optionalUserAuth = userAuthRepository.findByEmail(newUserDTO.email());

        if (optionalUserAuth.isEmpty()) {
            UserAuth newUserAuth = new UserAuth(newUserDTO);
            UserAccount newUserAccount = new UserAccount(newUserDTO, newUserAuth);

            newUserAuth.setUserAccount(newUserAccount);
            userAuthRepository.save(newUserAuth);

            return userAccountRepository.save(newUserAccount);
        } else {
            throw new Exception("The e-mail is already registered!");
        }
    }

    public void changeUserPassword(@NotNull long userId, @NotNull ChangeUserPasswordDTO changeUserPasswordDTO) {
        // Implement logic and methods from BCrypt.
    }
}
