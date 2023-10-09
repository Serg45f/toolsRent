package com.sgsavch.toolsrent.resource;

import com.sgsavch.toolsrent.domain.HttpResponse;
import com.sgsavch.toolsrent.domain.User;
import com.sgsavch.toolsrent.dto.UserDTO;
import com.sgsavch.toolsrent.exception.ApiException;
import com.sgsavch.toolsrent.form.LoginForm;
import com.sgsavch.toolsrent.service.UserService;
import com.sgsavch.toolsrent.service.implementation.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;

import static com.sgsavch.toolsrent.utils.ExceptionsUtils.processError;
import static com.sgsavch.toolsrent.utils.UserUtils.getLoggedInUser;
import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.security.authentication.UsernamePasswordAuthenticationToken.unauthenticated;

@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UserResource {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final ApplicationEventPublisher publisher;

    @PostMapping("/login")
    public ResponseEntity<HttpResponse> login(@RequestBody @Valid LoginForm loginForm) {
        UserDTO user = authenticate(loginForm.getEmail(), loginForm.getPassword());
        return user.isUsingMfa() ? sendVerificationCode(user) : sendResponse(user);
    }

//    @PostMapping("/login")
//    public ResponseEntity<HttpResponse> login(@RequestBody @Valid LoginForm loginForm) {
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword());
//        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//        UserDTO user = userService.getUserByEmail(loginForm.getEmail());
//        return user.isUsingMfa() ? sendVerificationCode(user) : sendResponse(user);
//    }


    @PostMapping("/register")
    public ResponseEntity<HttpResponse> saveUser(@RequestBody @Valid User user) {
        UserDTO userDTO = userService.createUser(user);
        return ResponseEntity.created(getUri()).body(
               HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("user", userDTO))
                        .message("User created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    private URI getUri() {
        return URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/get/<userId>").toUriString());
    }

    private ResponseEntity<HttpResponse> sendResponse(UserDTO user) {
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("user", user))
                        .message("Login successful")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    private ResponseEntity<HttpResponse> sendVerificationCode(UserDTO user) {
        userService.sendVerificationCode(user);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("user", user))
                        .message("Verification Code Sent")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
    private UserDTO authenticate(String email, String password) {
        UserDTO userByEmail = userService.getUserByEmail(email);
        try {
//            if(null != userByEmail) {
//                publisher.publishEvent(new NewUserEvent(email, LOGIN_ATTEMPT));
//            }
            Authentication authentication = authenticationManager.authenticate(unauthenticated(email, password));
            UserDTO loggedInUser = getLoggedInUser(authentication);
//            if(!loggedInUser.isUsingMfa()) {
//                publisher.publishEvent(new NewUserEvent(email, LOGIN_ATTEMPT_SUCCESS));
//            }
            return loggedInUser;
        } catch (Exception exception) {
//            if(null != userByEmail) {
//                publisher.publishEvent(new NewUserEvent(email, LOGIN_ATTEMPT_FAILURE));
//            }
            processError(request, response, exception);
            throw new ApiException(exception.getMessage());
        }
    }
}
