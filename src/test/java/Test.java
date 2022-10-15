

import com.example.security.SecurityApp;
import com.example.security.utils.RedisCache;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

public class Test {
@org.junit.Test
    public void test1(){
//        redisCache.setCacheObject("login:2",new User());
    RedisTemplate<String ,String> redisTemplate = new RedisTemplate<>();
    redisTemplate.afterPropertiesSet();
    ValueOperations<String,String> operation = redisTemplate.opsForValue();
    System.out.println(operation.get("dd"));
}
}
