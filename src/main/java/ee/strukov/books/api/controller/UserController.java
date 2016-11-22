package ee.strukov.books.api.controller;

import ee.strukov.books.api.model.User;
import ee.strukov.books.api.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by strukov on 20.11.16.
 */
@RestController
@RequestMapping(value = "api/v1")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private DefaultTokenServices defaultTokenServices;

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public ResponseEntity<User> getLoggedInUser(@AuthenticationPrincipal User user) {
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public @ResponseBody void logout(@RequestParam("token") String value) throws InvalidClientException {
        defaultTokenServices.revokeToken(value);
    }

}
