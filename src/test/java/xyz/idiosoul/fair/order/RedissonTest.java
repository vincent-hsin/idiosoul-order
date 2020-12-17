package xyz.idiosoul.fair.order;

import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;

/**
 * @author xinw
 */
public class RedissonTest {
    @Test
    public void testMap() {
        RedissonClient redissonClient = Redisson.create();
        RMap<String, Long> foo = redissonClient.getMap("foo", StringCodec.INSTANCE);
        foo.putIfAbsent("count", 9L);
    }
}
