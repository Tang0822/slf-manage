package com.slf.manage.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jftang3
 */
@Slf4j
public class MyFilter extends ZuulFilter {

    @Override
    public String filterType() {
        //定义filter的类型，有pre、route、post、error四种
        return "pre";
    }

    @Override
    public int filterOrder() {
        //定义filter的顺序，数字越小表示顺序越高，越先执行
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //表示是否需要执行该filter，true表示执行，false表示不执行
        return true;
    }

    @Override
    public Object run() {
        //filter需要执行的具体操作
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info("--->>> MyFilter {},{}", request.getMethod(), request.getRequestURL().toString());
        //获取请求的参数
        String token = request.getParameter("name");
        if (StringUtils.isNotBlank(token)) {
            //对请求进行路由
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);
            return null;
        } else {
            //不对其进行路由
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(400);
            ctx.setResponseBody("name is empty");
            ctx.set("isSuccess", false);
            return null;
        }
    }
}
