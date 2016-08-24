package zaza.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import zaza.api.jsonmodel.Credentials;
import zaza.model.User;
import zaza.repository.UserRepository;

import javax.servlet.http.HttpSession;

@RestController()
@RequestMapping("/api/session")
public class AuthenticationResource {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @Autowired
    public AuthenticationResource(AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public User login(@RequestBody Credentials credentials, HttpSession httpSession) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
//        Set<Role> roles = (new ArrayList<GrantedAuthority>(authentication.getAuthorities())).stream().map((GrantedAuthority ga) -> Role.valueOf(ga.getAuthority())).collect(Collectors.toSet());
        User user = userRepository.findByUsername(credentials.getUsername());
//        User user = new User(credentials.getUsername(), roles);
        httpSession.setAttribute("user", user);
        return user;
    }

    @RequestMapping(method = RequestMethod.GET)
    public User session(HttpSession session) {
        return (User) session.getAttribute("user");
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void logout(HttpSession session) {
        session.invalidate();
    }


}
