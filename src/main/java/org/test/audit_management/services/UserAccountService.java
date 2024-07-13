package org.test.audit_management.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.audit_management.dtos.EditUserDTO;
import org.test.audit_management.models.UserAccount;
import org.test.audit_management.repositories.UserAccountRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserAccountService {

    @Autowired
    UserAccountRepository userAccountRepository;

    public UserAccount editUser(EditUserDTO editUserDTO) throws Exception {
        Optional<UserAccount> optionalUserAccount = userAccountRepository.findById(editUserDTO.userId());

        if (optionalUserAccount.isPresent()) {
            UserAccount userAccount = optionalUserAccount.get();

            userAccount.setFirstName(editUserDTO.firstName());
            userAccount.setLastName(editUserDTO.lastName());
            return userAccountRepository.save(userAccount);
        } else {
            throw new Exception("User not found");
        }
    }

    public boolean deleteUser(long userId) throws Exception {
        Optional<UserAccount> optionalUserAccount = userAccountRepository.findById(userId);

        if (optionalUserAccount.isPresent()) {
            userAccountRepository.delete(optionalUserAccount.get());
            return true;
        } else {
            throw new Exception("It was not possible to delete the user: user not found!");
        }
    }

    public UserAccount getUserAccount(long userId) throws Exception {
        Optional<UserAccount> userAccount = userAccountRepository.findById(userId);
        if (userAccount.isPresent()) {
            return userAccount.get();
        }
        throw new Exception("User not found");
    }

    public List<UserAccount> getAllUsers() {
        return userAccountRepository.findAll();
    }
}
