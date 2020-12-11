package pers.bingo.datasource.util;

import org.springframework.core.NamedThreadLocal;
import org.springframework.util.StringUtils;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 数据源切换工具
 *
 * @Author GouBin
 * @Date 2020-10-21
 * @Version 1.0
 * @Modified_By
 */
public class DynamicDataSourceContextHolder {

    /**
     * 使用栈存储
     * <pre>
     * 支持嵌套切换
     * </pre>
     */
    private static final ThreadLocal<Deque<String>> DATASOURCE_CONTEXT_KEY = new NamedThreadLocal<Deque<String>>("dynamic-datasource") {
        @Override
        protected Deque<String> initialValue() {
            return new ArrayDeque<>();
        }
    };

    private DynamicDataSourceContextHolder() {
    }

    /**
     * 获取当前线程的数据源
     * @return
     */
    public static String peek() {
        return DATASOURCE_CONTEXT_KEY.get().peek();
    }

    /**
     * 设置当前线程数据源
     * @param ds
     */
    public static void push(String ds) {
        DATASOURCE_CONTEXT_KEY.get().push(StringUtils.isEmpty(ds) ? "" : ds);
    }

    /**
     * 清空当前线程数据源
     */
    public static void poll() {
        Deque<String> deque = DATASOURCE_CONTEXT_KEY.get();
        deque.poll();
        if (deque.isEmpty()) {
            DATASOURCE_CONTEXT_KEY.remove();
        }
    }

    /**
     * 强制清空本地线程
     * 如果手动调用了push，可调用该方法确保清除，防止内存泄漏
     */
    public static void clear() {
        DATASOURCE_CONTEXT_KEY.remove();
    }
}
