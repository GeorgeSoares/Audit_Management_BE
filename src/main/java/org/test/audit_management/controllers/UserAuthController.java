package org.test.audit_management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.audit_management.dtos.NewUserDTO;
import org.test.audit_management.services.UserAccountService;
import org.test.audit_management.services.UserAuthService;


@RestController
@RequestMapping("/api/v1/auth")
public class UserAuthController {

    @Autowired
    UserAuthService userAuthService;

    @Autowired
    UserAccountService userAccountService;

    @PostMapping("/newuser")
    public ResponseEntity<?> newUser(@RequestBody NewUserDTO newUserDTO) {

        // Still missing the setup of Spring Security!
        try {
            return ResponseEntity.ok(userAuthService.createUser(newUserDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    // Change Password endpoint!
}
