// package br.com.clocktimeapi.clocktimeapi.providers;

// import java.security.SignatureException;
// import java.time.Duration;
// import java.time.Instant;
// import java.util.Date;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.core.Authentication;
// import org.springframework.stereotype.Component;

// import com.auth0.jwt.JWT;
// import com.auth0.jwt.algorithms.Algorithm;
// import com.auth0.jwt.exceptions.JWTVerificationException;

// import br.com.clocktimeapi.clocktimeapi.modules.user.entities.UserEntity;

// @Component
// public class JWTUserProvider {
    
//     @Value("${jwt.secret.user.key}")
//     private String secretUserKey;

//     public String createJWTTokenUser(Authentication authentication) {
//         UserEntity user = (UserEntity) authentication.getPrincipal();

//         var expiresIn = Instant.now().plus(Duration.ofHours(24));

//         Algorithm algorithm = Algorithm.HMAC256(secretUserKey);


//         var token = JWT.create()
//                 .withSubject(Integer.toString(user.getId()))
//                 .withIssuedAt(new Date())
//                 .withExpiresAt(expiresIn)
//                 .withClaim("roles", user.getEmployee().getJob_id())
//                 .sign(algorithm);

//     }

//     public Integer getUserIdFromToken(String token) {
//         Claims claims = Jwts.parser()
//                 .setSigningKey(jwtSecret)
//                 .parseClaimsJws(token)
//                 .getBody();

//         return Integer.parseInt(claims.getSubject());
//     }

//     public boolean validateToken(String authToken) {
//         try {
//             Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
//             return true;
//         } catch (JWTVerificationException ex) {
//             return null;
//         }
//         return false;
//     }

// }
