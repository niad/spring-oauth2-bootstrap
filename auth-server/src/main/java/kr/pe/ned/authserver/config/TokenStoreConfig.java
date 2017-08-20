package kr.pe.ned.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.cache.SpringCacheBasedUserCache;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
public class TokenStoreConfig {

    public static final String REDIS_CACHE_NAME="redis_cache_name";
    public static final String REDIS_PREFIX ="redis_cache_prefix";
    public static final Long EXPIRE =60*60L;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

//    @Bean
//    @Primary
//    public AuthorizationServerTokenServices tokenServices() {
//        DefaultTokenServices tokenServices = new DefaultTokenServices();
//        tokenServices.setSupportRefreshToken(true);
//        tokenServices.setAccessTokenValiditySeconds(60 * 60);	// access token validity seconds, default 12 hours
//        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24);  // access token validity seconds, default 30 days
//        tokenServices.setTokenStore(tokenStore());
//
//        // 사용자 조회 정보
//        //tokenServices.setClientDetailsService(clientDetailsService);
//        return tokenServices;
//    }

    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Bean
    RedisCache redisCache(RedisTemplate redisTemplate){
        RedisCache redisCache = new RedisCache(REDIS_CACHE_NAME, REDIS_PREFIX.getBytes(),redisTemplate,EXPIRE);
        return redisCache;
    }

    @Bean
    public UserCache userCache(RedisCache redisCache) throws Exception {
        UserCache userCache = new SpringCacheBasedUserCache(redisCache);
        return userCache;
    }

}
