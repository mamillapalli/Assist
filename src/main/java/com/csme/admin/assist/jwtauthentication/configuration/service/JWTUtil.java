package com.csme.admin.assist.jwtauthentication.configuration.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@Profile("JWT")
@Slf4j
public class JWTUtil {

    @Autowired
    RSAUtil rsaUtil;

    private final String jwtExpiryInMinutues;

    public JWTUtil(@Value("${jwt.expiry.inMinutes}")  String jwtExpiryInMinutues) {
        this.jwtExpiryInMinutues = jwtExpiryInMinutues;

    }



    public String extractUsername(String token, PublicKey publicKey) {
        return extractClaim(token, Claims::getSubject, publicKey);
    }

    public Date extractExpiration(String token,PublicKey publicKey) {
        return extractClaim(token, Claims::getExpiration, publicKey);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver, PublicKey publicKey) {
        final Claims claims = extractAllClaims(token, publicKey);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token, PublicKey publicKey) {
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token,PublicKey publicKey) {
        return extractExpiration(token,publicKey).before(new Date());
    }

    public String generateToken(UserDetails userDetails, PrivateKey privateKey) {
//        Map<String, Object> claims = new HashMap<>();
//        return createToken(claims, userDetails.getUsername(),privateKey);

        Map<String, Object> claims = new HashMap<>();
        StringBuffer userRoles= new StringBuffer();
        userDetails.getAuthorities().stream().forEach(grantedAuthority -> {
            log.info("authority of the user " + grantedAuthority.getAuthority());
            userRoles.append(grantedAuthority.getAuthority()).append(",");
        });
        if(userRoles.length()>0) userRoles.deleteCharAt(userRoles.length()-1);
        claims.put("roles",userRoles);
        return createToken(claims, userDetails.getUsername(),privateKey);
    }

    private String createToken(Map<String, Object> claims, String subject, PrivateKey privateKey) {

        log.info("JWT expiry configured in minutes is " + jwtExpiryInMinutues);
        log.info("JWT iat time " + new Date(System.currentTimeMillis()));
        log.info("JWT expiry time " + new Date(System.currentTimeMillis() + 1000 * 60 *  Integer.valueOf(jwtExpiryInMinutues)));

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 *  Integer.valueOf(jwtExpiryInMinutues)))
                .signWith(SignatureAlgorithm.RS512, privateKey).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails, PublicKey publicKey) {
        final String username = extractUsername(token, publicKey);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token,publicKey));
    }

    public String extractUsernameFromRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        try {
            String authorizationHeader = request.getHeader("Authorization");
            String jwtToken =  authorizationHeader.substring(7);
            return extractClaim(jwtToken, Claims::getSubject, rsaUtil.getPublicKey());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

//    public static void main(String a[])
//    {
//        System.out.println("issued at " + new Date(System.currentTimeMillis()));
//        System.out.println("expiry at " + new Date(System.currentTimeMillis()+ 1000 * 60 * 60 ));
//    }

}
