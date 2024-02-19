package it.epicode.Device.Management.services;

import it.epicode.Device.Management.entities.User;
import it.epicode.Device.Management.payloads.UserLoginDTO;
import it.epicode.Device.Management.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private JWTTools jwtTools;

    public String authUserAndGenerateToken(UserLoginDTO payload) {
        User user = userService.findByEmail(payload.email());
        if (user.getPassword().equals(payload.password())) {
            return jwtTools.createToken(user);
        } else {
            throw new RuntimeException("Credenziali sbagliate");
        }
    }
}
