package com.ricky.healthifier.service.auth;

import com.ricky.healthifier.datamodel.user.User;
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

    private static final String NAME = "name";
    private static final String ROLE = "role";
    private static final String WEIGHT = "weight";
    private static final long TEN_HOURS = 1000 * 60 * 60 * 10;
    private static final String SECRET_KEY = "Smoking is injurious to health";

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public Date extractExpirationDate(String token) {
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
        claims.put(ROLE, user.getRoleEnum().toString());
        claims.put(NAME, user.getUsername());
        claims.put(WEIGHT, user.getWeight());
        return createToken(claims, user.getEmail());
    }

    private String createToken(Map<String, Object> claims, String subject) {

        Date currentTime = new Date(System.currentTimeMillis());

        // token will expire after 10 hours of issue
        Date expirationTime = new Date(currentTime.getTime() + TEN_HOURS);

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(currentTime)
                .setExpiration(expirationTime).signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    @Override
    public boolean validateToken(String token, User user) {
        final String username = extractUsername(token);
        return (username.equals(user.getEmail()) && (!isTokenExpired(token)));
    }

    private boolean isTokenExpired(String token) {
        return extractExpirationDate(token).before(new Date());
    }

}
