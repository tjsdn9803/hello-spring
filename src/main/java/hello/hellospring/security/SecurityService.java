package hello.hellospring.security;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Service
public class SecurityService {
    private static final String SECRET_KEY = "askdjnaskjdkjsdasdfvfvhjtwrfbqkwjbekjqwbehjqwbejhqwbejhqwebjhwqebjhqwbejhqwbejhbqwehjbfjhbqjfhqaskdnsadnkjf";

    private final MemberRepository memberRepository;
    public SecurityService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public String createToken(String subject, long expTime){
        if(expTime <= 0){
            throw new RuntimeException("만료시간이 0보다 작습니다.");
        }

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());

        return Jwts.builder()
                .setSubject(subject)
                .signWith(signingKey, signatureAlgorithm)
                .setExpiration(new Date(System.currentTimeMillis() * expTime))
                .compact();
    }

    public Boolean getAuth(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();
        Optional<Member> optionalMember = memberRepository.findByEmail(claims.getSubject());
        return optionalMember.isPresent();
    }

    public Claims getClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
