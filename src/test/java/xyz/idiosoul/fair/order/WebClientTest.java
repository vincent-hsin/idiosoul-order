package xyz.idiosoul.fair.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import xyz.idiosoul.fair.order.dto.CreditInfo;

/**
 * @author xinw
 */
@SpringBootTest
public class WebClientTest {
    @Autowired
    private WebClient webClient;
    @Test
    public void testWebClient(){
        CreditInfo creditInfo = webClient.get().uri("https://public.creditchina.gov.cn/private-api/getTyshxydmDetailsContent?keyword=上海跃橙文化传播有限公司").retrieve().bodyToMono(CreditInfo.class).block();
        System.out.println(creditInfo);
    }
}
