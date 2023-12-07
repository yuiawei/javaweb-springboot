package fit.iseeyou.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * JWT 工具类
 */
public class JWTUtils {
    private static final String SIGN = "0LvsOGxEOUqLXGveZZnWV9OTYnZti2iGLmsoo8I6Wb6CSFHxnyYFRm5fOoxml";

    /**
     * 生成token
     */
    public static String getToken(Map<String, String> payloadMap) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 7);
        JWTCreator.Builder jwtBuilder = JWT.create();
        for (Map.Entry<String, String> payload : payloadMap.entrySet()) {
            String key = payload.getKey();
            String value = payload.getValue();
            jwtBuilder.withClaim(key, value);
        }
        return jwtBuilder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SIGN));
    }

    /**
     * 验证token
     */
    public static DecodedJWT verifyToken(String token) {
        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }
}
