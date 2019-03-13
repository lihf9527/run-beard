package org.game.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.LocalDateTime;

public class JwtUtils {
    private static final String JWT_ID = "fcoinjwt";
    private static final String JWT_SECRET = "A53337C396C5A75D82E70DD7867A85B3";

    /**
     * 由字符串生成加密key
     */
    private static SecretKey generatedKey() {
        byte[] encodedKey = Base64.decodeBase64(JWT_SECRET);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    /**
     * 创建token
     *
     * @param subject 存入token中的字符串
     * @param seconds 有效时间, 单位 秒
     * @return
     */
    public static String createToken(String subject, long seconds) {
        LocalDateTime createTime = LocalDateTime.now();
        LocalDateTime expireTime = createTime.plusSeconds(seconds);
        return Jwts.builder()
                .setId(JWT_ID)
                .setSubject(subject)
                .setIssuedAt(DateUtils.toDate(createTime))
                .setExpiration(DateUtils.toDate(expireTime))
                .signWith(SignatureAlgorithm.HS256, generatedKey())
                .compact();
    }

    /**
     * 将token解析成Claims
     */
    public static Claims parseToken(String token) throws Exception {
        return Jwts.parser()
                .setSigningKey(generatedKey())
                .parseClaimsJws(token)
                .getBody();
    }

    public static void main(String[] args) throws Exception {
        String subject = "1";
        String token = createToken(subject, 1000 * 60);
        System.out.println("before ==> " + token);

        Claims claims = parseToken(token);
        System.out.println("after ==> " + claims.getSubject());
        System.out.println(claims.getExpiration());
    }
}
