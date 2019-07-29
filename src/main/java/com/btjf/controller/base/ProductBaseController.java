package com.btjf.controller.base;

import com.btjf.business.common.exception.BusinessException;
import com.btjf.constant.SysConstant;
import com.btjf.model.sys.SysUser;
import com.btjf.util.RdStringUtil;
import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by liuyq on 2019/7/28.
 */
public abstract class ProductBaseController {
    private static final Logger LOGGER = Logger
            .getLogger(ProductBaseController.class);


    /**
     * 获取当前登录的角色ID
     *
     * @return
     */
    public SysUser getLoginUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        SysUser sysUser = (SysUser) request.getAttribute(SysConstant.LOGINUSER);
        if (null == sysUser) {
            throw new BusinessException("请登录之后重试");
        } else {
            return sysUser;
        }
    }

    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public String getRequestParams() {
        String params = "";
        try {
            // 同样的参数顺序请求，在request取值时可能出现顺序不一致的情况，在slb转发 订单重复时，检验无法判断，因此此处需要进行排序
            Enumeration<String> e = getRequest().getParameterNames();
            List<String> list = Collections.list(e);
            Collections.sort(list);
            for (String name : list) {
                String value = paramString(name);
                params += name + "=" + value + "&";
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return params;
    }

    protected String paramString(String str) {
        return RdStringUtil.isNull(getRequest().getParameter(str));
    }
}
