package aug.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * @description:
 * @author: 86134
 * @time: 2021/9/19 20:01
 */
public class WebUtils {
    public static <T> T paramToBean(Map value, T bean) {
        try {
            BeanUtils.populate(bean, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 将字符串转换成int类型的数据
     * @param strId
     * @param defaultValue
     * @return 转换的int类型值，有异常则返回默认的int类型的值
     */
    public static int parseInt(String strId,int defaultValue){
        try {
            return Integer.parseInt(strId);
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return defaultValue;
    }
}
