package org.test.audit_management.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.audit_management.dtos.EditUserDTO;
import org.test.audit_management.models.User;
import org.test.audit_management.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User editUser(EditUserDTO editUserDTO) throws Exception {
        Optional<User> optionalUserAccount = userRepository.findById(editUserDTO.userId());

        if (optionalUserAccount.isPresent()) {
            User userAccount = optionalUserAccount.get();

            userAccount.setFirstName(editUserDTO.firstName());
            userAccount.setLastName(editUserDTO.lastName());
            return userRepository.save(userAccount);
        } else {
            throw new Exception("User not found");
        }
    }

    public boolean deleteUser(UUID userId) throws Exception {
        Optional<User> optionalUserAccount = userRepository.findById(userId);

        if (optionalUserAccount.isPresent()) {
            userRepository.delete(optionalUserAccount.get());
            return true;
        } else {
            throw new Exception("It was not possible to delete the user: user not found!");
        }
    }

    public User getUserAccount(UUID userId) throws Exception {
        Optional<User> userAccount = userRepository.findById(userId);
        if (userAccount.isPresent()) {
            return userAccount.get();
        }
        throw new Exception("User not found");
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
