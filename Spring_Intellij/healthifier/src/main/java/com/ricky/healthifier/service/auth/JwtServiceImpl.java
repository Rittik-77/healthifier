package com.ricky.healthifier.service.auth;

import com.ricky.healthifier.datamodel.user.User;
import com.ricky.healthifier.utils.exception.AppException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {

    private static final String TOKEN_EXPIRED = "Token Expired. Please Login again to continue";
    private static final long ONE_HOUR = 1000 * 60 * 60;
    private static final String SECRET_KEY = "Smoking is injurious to health";

    @Override
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Date extractExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    @Override
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, user.getEmail());
    }

    private String createToken(Map<String, Object> claims, String subject) {

        Date currentTime = new Date(System.currentTimeMillis());

        // token will expire after 10 hours of issue
        Date expirationTime = new Date(currentTime.getTime() + ONE_HOUR);

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(currentTime)
                .setExpiration(expirationTime).signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    @Override
    public void checkIsTokenExpired(String token) throws AppException {
        if (extractExpirationDate(token).before(new Date()))
            throw new AppException(TOKEN_EXPIRED);
    }

}
