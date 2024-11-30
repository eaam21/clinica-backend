package com.clinica.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	private static final String SECRET_KEY = "08a55ad23bdc4c4d4d782e41ac5b5a5328a04838f17275803d4ecbd594cb20a8572681913eb2fc5933f6bfb08aa141c35348549e846ced8f236bcb2ebe93b41d67ca6783d7c1bfbda5a56c309a9e51ebc680aabe177ffa1ac659d474d35046aaea665fb95f55bde9424d511c6e8433e9fc1b922a9d50b3abc9d304353c017022579efe16a5b463398724ad6d8ce49d10aa6ac8a013404f0d5ead746a2d204fa8f67e8967e3987b177314fe08bfbc4588eaf67326016e0e68318fc034a108797acaf2f3cb05c1acf65a143523c8d9468750954c3e1e3c8c7713d7166d859ab20979ebbcb4a70fb454403b8b602febc2fcbb2329d511258926820dd7708d37d97085c31b286754f290f68f9f9036ce3a8fa53473c020d30b9eeaa2ef0d527723c41349a5febf3538b3d0318e6eac7901b7852e841f3d6c8ddeb7da20f2d985fca16636dd9b3cf386420dc746019a30e974624ba17cfa742027e21e062d7ef6690537d486e4d1d9fda545fe29bfb84dba0e641875642ea08ff24eff18607ea5e1cc7c926b966256f2e361424e21fbe791f3efb48552a4906be4e93297cd0789529e342712dac4266e78430337386454a7e3b21f9c5d1acdfa6e5893fd0d97d1d630283c4a5b55c01720b5a6a7deb9d6157e9514feae7e6c824eee9926b072226e093a1d3994b20c52952ef3dc82a72224d31b62c17de2a8a9c42950905461857cd6";
	private static final long EXPIRATION_TIME = 3600000;
	
	public String generateToken(String username, String rol) {
		return Jwts.builder()
				.subject(username)
				.claim("rol", rol)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
				.signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY)))
				.compact();
	}
	
	public Claims extractionClaims(String token) {
		return Jwts.parser()
				.verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY)))
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
	
	public boolean validateToken(String token) {
		try {
			extractionClaims(token);
			return true;
		} catch (JwtException e) {
			return false;
		}
	}
}
