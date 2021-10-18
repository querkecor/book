package aug.filter;

import aug.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @description:
 * @author: 86134
 * @time: 2021/10/16 14:38
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    
    }
    
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
            JdbcUtils.CAC();
        }catch (Exception e){
            JdbcUtils.RAC();
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void destroy() {
    
    }
}
