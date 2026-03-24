package dk.mike.springbootaidemo2026.openai.jedis;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPooled;


@Configuration
@RequiredArgsConstructor
public class JedisVectorStoreConfiguration {
    @Bean
    public JedisPooled jedisPooled() {
        return new JedisPooled("localhost", 6379);
    }

}
