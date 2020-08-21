package xyz.idiosoul.fair.order.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class RequestHeaderUtil {
    /**
     * 获取买家id
     *
     * @return
     */
    public static int getSellerId() {
        String dataSpace = getHeaderValue("merchantShopId");
        if (Objects.isNull(dataSpace)) {
            throw new RuntimeException("客户端异常，请重新打开");
        }
        return Integer.valueOf(dataSpace);
    }

    /**
     * 获取卖家id
     *
     * @return
     */
    public static int getCustomerId() {
        return 1;
//        String buyerId = getHeaderValue("userId");
//        if (Objects.isNull(buyerId)) {
//            throw new RuntimeException("请重新登录");
//        }
//        return Integer.valueOf(buyerId);
    }

    /**
     * 获取管理员id
     *
     * @return
     */
    public static int getAdminId() {
        return 2;
//        String buyerId = getHeaderValue("userId");
//        if (Objects.isNull(buyerId)) {
//            throw new RuntimeException("请重新登录");
//        }
//        return Integer.valueOf(buyerId);
    }

    /**
     * 获取客户端渠道
     *
     * @return
     */
    public static String getClientChannel() {
        String clientChannel = getHeaderValue("clientType");
        if (Objects.isNull(clientChannel)) {
            throw new RuntimeException("客户端异常，请重新打开");
        }
        return clientChannel;
    }

    /**
     * 获取请求参数值
     *
     * @param parameterName 请求参数名
     * @return
     */
    private static String getParameterValue(String parameterName) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        return request.getParameter(parameterName);
    }

    /**
     * 获取请求头参数值
     *
     * @param parameterName 请求参数名
     * @return
     */
    private static String getHeaderValue(String parameterName) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        return request.getHeader(parameterName);
    }

}
