package br.com.app.decora.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.io.IOUtils;

public class ServiceUtil {

	public static String cripto() {
	    
	    String token = null;
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128, new SecureRandom());
			Key key = keyGenerator.generateKey();
		
			token = IOUtils.toString(key.getEncoded());
		} catch (IOException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return token;
	}
	
	public static String createJWT(String id, String password, long ttlMillis) {

		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.ES256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		SecretKey secretKey = null;
		try {
			secretKey = KeyGenerator.getInstance("AES").generateKey();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
		
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(encodedKey);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		JwtBuilder builder = Jwts.builder()
		                                .setIssuedAt(now)
		                                .setSubject(password)
		                                .signWith(signatureAlgorithm, signingKey);
		 //time to expiration
		if (ttlMillis >= 0) {
		    long expMillis = nowMillis + ttlMillis;
		    Date exp = new Date(expMillis);
		    builder.setExpiration(exp);
		}
		return builder.compact();
	}
	
	public static String parseJWT(String jwt) {
		Claims claims = Jwts.parser()         
		   .setSigningKey(DatatypeConverter.parseBase64Binary("ASD"))
		   .parseClaimsJws(jwt).getBody();
		return claims.getSubject();
	}
}
