package com.immortal.market2hand.interceptor.home;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.immortal.market2hand.config.SiteConfig;
import com.immortal.market2hand.constant.SessionConstant;
import com.immortal.market2hand.entity.common.SiteSetting;
import com.immortal.market2hand.entity.common.Student;
import com.immortal.market2hand.service.common.FriendLinkService;
import com.immortal.market2hand.service.common.GoodsCategoryService;
import com.immortal.market2hand.service.common.NewsService;
import com.immortal.market2hand.service.common.SiteSettingService;
import com.immortal.market2hand.util.SessionUtil;
import com.immortal.market2hand.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


/**
 * 前台全局拦截器
 *
 * @author Administrator
 */
@Component
public class HomeGlobalInterceptor implements HandlerInterceptor {

    @Autowired
    private GoodsCategoryService goodsCategoryService;
    @Autowired
    private SiteConfig siteConfig;
    @Autowired
    private FriendLinkService friendLinkService;
    @Autowired
    private SiteSettingService siteSettingService;

    @Autowired
    private NewsService newsService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Student s = (Student) SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
        if (!StringUtil.isAjax(request)) {
            if (null != s) {
                request.setAttribute("NewsCount", newsService.findNewsCount(s.getId()));
            }
            //若不是ajax请求，则将菜单信息放入页面模板变量
            request.setAttribute("goodsCategorys", goodsCategoryService.findAll());
            request.setAttribute("friendLinkList", friendLinkService.findList(8));

            SiteSetting siteSetting = siteSettingService.find();
            if (siteSetting != null) {
                request.setAttribute("siteName", siteSetting.getSiteName());
                request.setAttribute("siteSetting", siteSetting);
            }
        }
        return true;
    }
}
