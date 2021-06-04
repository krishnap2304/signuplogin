package com.bootcamp.signuplogin.controller;

import com.bootcamp.signuplogin.model.Role;
import com.bootcamp.signuplogin.model.RoleEnum;
import com.bootcamp.signuplogin.model.User;
import com.bootcamp.signuplogin.model.UsersList;
import com.bootcamp.signuplogin.repository.RoleRepository;
import com.bootcamp.signuplogin.repository.UserRepository;
import com.bootcamp.signuplogin.request.SignupRequest;
import com.bootcamp.signuplogin.response.MessageResponse;
import com.bootcamp.signuplogin.service.CaptchaValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class will be used for doing the resitration process.
 */
@CrossOrigin(origins ="*",maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class RegistrationController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    CaptchaValidatorService captchaValidatorService;

    /**
     * This method is used for registration process, where user has to provide username, email and password details.
     * This service checks to see if the username already exists or email already exists, if any of them already exists,
     * then it sends out the appropriate message stating that "username is not available" or "email id already registered".
     * After successful validation of username and email it encodes the password and create a user document in user repository.
     * @param signupRequest
     * @return
     */
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {
        boolean isvalidCaptcha = captchaValidatorService.validateCaptcha(signupRequest.getCaptchaResp(),null);
        if (!isvalidCaptcha){
            return ResponseEntity.
                    badRequest().
                    body(new MessageResponse("Error: Invalid Captcha not validated"));
        }
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.
                    badRequest().
                    body(new MessageResponse("Error: Username is not available"));
        }
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already registered"));
        }
        User user = new User(signupRequest.getUsername()
                ,signupRequest.getEmail()
                ,passwordEncoder.encode(signupRequest.getPassword()));
        Set<String> strRoles = signupRequest.getRoles();
        Set<Role> roles = new HashSet();
        if (strRoles == null){
            throw new RuntimeException("Error: Roles  not found");
        }else{
            strRoles.forEach(role->{
                switch(role){
                    case "admin":
                        Role adminRole = roleRepository.findByName(RoleEnum.ROLE_ADMIN)
                                .orElseThrow(()-> new RuntimeException("Error: Role is not found"));
                        roles.add(adminRole);
                        break;
                     case "moderator":
                        Role moderatorRole = roleRepository.findByName(RoleEnum.ROLE_MODERATOR)
                                .orElseThrow(()-> new RuntimeException("Error: Role is not found"));
                        roles.add(moderatorRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
                                .orElseThrow(()-> new RuntimeException("Error: Role is not found"));
                        roles.add(userRole);
                }
            });

        }
        user.setRoles(roles);
        user.setResetPasswordToken(null);
        user.setNewPassword(null);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User Registered Successfully"));
    }

    @GetMapping("roles")
    public List<Role>getAllRoles() {
        return roleRepository.findAll();
    }

    @GetMapping("users")
    public List<User>getUser() {
        return userRepository.findAll();
    }
    /**
     * This method un-resiters the user, by sending out an unregistered event,
     * sending out an email to the user stating the user unregistered successfully
     * and finally that user gets deleted from the user repository.
     * @param username
     * @return
     */

    @DeleteMapping("/de-register")
    public ResponseEntity<MessageResponse> deRegisterUser(@RequestParam String username){
        //TODO: Need to send out an email that user success un-registered.
        // Send out an event message to Email Listener
        logger.info("User De-Reistered Successfully");
        userRepository.findByUsername(username).ifPresent(user_to_remove -> userRepository.delete(user_to_remove));

        return ResponseEntity.ok(new MessageResponse("User De-Registered Successfully"));
    }

}


