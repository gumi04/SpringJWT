package com.example.config.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

/**
 * The class Jwt utils.
 */
@Component
@Slf4j
public class JwtUtils {

	@Value("${jwt.secret.key}")
	private String secretKey;
	@Value("${jwt.time.expiration}")
	private String timeExpiration;

	/**
	 * Generate acces token string.
	 *
	 * @param username the username
	 * @return the string
	 */
	public String generateAccesToken(String username) {
		return Jwts
				.builder()
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration)))
				.signWith(getSignatureKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	/**
	 * Is token valid boolean.
	 *
	 * @param token the token
	 * @return the boolean
	 */
	public boolean isTokenValid(String token) {
		try {
			Jwts.parserBuilder()
					.setSigningKey(getSignatureKey())
					.build()
					.parseClaimsJws(token)
					.getBody();

			return true;
		} catch (Exception e) {
			log.info("Token invalido ".concat(e.getMessage()));
			return false;
		}
	}

	/**
	 * Gets username from token.
	 *
	 * @param token the token
	 * @return the username from token
	 */
	public String getUsernameFromToken(String token) {
		return getClaim(token, Claims::getSubject);
	}

	/**
	 * Gets claim.
	 *
	 * @param <T>             the type parameter
	 * @param token           the token
	 * @param claimsTFunction the claims t function
	 * @return the claim
	 */
	public <T> T getClaim(String token, Function<Claims, T> claimsTFunction) {
		Claims claims = extractAllClaims(token);
		return claimsTFunction.apply(claims);
	}

	/**
	 * Extract all claims claims.
	 *
	 * @param token the token
	 * @return the claims
	 */
	public Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSignatureKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}


	/**
	 * Gets signature key.
	 *
	 * @return the signature key
	 */
	public Key getSignatureKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

}
