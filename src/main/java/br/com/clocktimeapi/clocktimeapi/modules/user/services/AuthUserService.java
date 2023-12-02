// package br.com.clocktimeapi.clocktimeapi.modules.user.services;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.stereotype.Service;

// import br.com.clocktimeapi.clocktimeapi.modules.user.dto.UserRequest;
// import br.com.clocktimeapi.clocktimeapi.modules.user.entities.UserEntity;

// @Service
// public class AuthUserService {

//     @Autowired
//     private AuthenticationManager authenticationManager;

//     @Autowired
//     private JwtTokenProvider jwtTokenProvider;

//     @Autowired
//     private UserService userService;

//     public String authenticateUser(UserRequest userRequest) {
//         // Autenticar o usuário
//         Authentication authentication = authenticationManager.authenticate(
//             new UsernamePasswordAuthenticationToken(
//                 userRequest.getLogin(),
//                 userRequest.getPassword()
//             )
//         );

//         // Gerar token JWT
//         UserEntity user = (UserEntity) authentication.getPrincipal();
//         return jwtTokenProvider.generateToken(user);
//     }
// }
