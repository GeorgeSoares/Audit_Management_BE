package org.test.audit_management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.audit_management.dtos.EditUserDTO;
import org.test.audit_management.models.UserAccount;
import org.test.audit_management.services.UserAccountService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserAccountController {

    @Autowired
    UserAccountService userAccountService;

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable long userId, @RequestBody EditUserDTO editUserDTO) throws Exception {
        try {
            return ResponseEntity.ok(userAccountService.editUser(editUserDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable long userId) {
        try {
            userAccountService.deleteUser(userId);
            return ResponseEntity.ok("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/")
    public List<UserAccount> getAllUsers() {
        return userAccountService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable long userId) {
        try {
            return ResponseEntity.ok(userAccountService.getUserAccount(userId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}
