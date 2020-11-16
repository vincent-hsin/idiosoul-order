package xyz.idiosoul.fair.order;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author xinw
 */
@Slf4j
public class LoggingTest {
    @Test
    public void test1(){
        try {
            int i = 1;
            int result = i/0;
        }catch (Exception e){
            log.error("这是个异常");
        }
    }

    @Test
    public void test2(){
        try {
            int i = 1;
            int result = i/0;
        }catch (Exception e){
            log.error("这是个异常",e);
        }
    }

    @Test
    public void test3(){
        int i1 = 1;
        int i2 = 0;
        try {
            int result = i1 / i2;
        } catch (Exception e) {
            log.error("除数：{}，被除数：{}，这是个异常", i1, i2, e);
        }
    }
}
