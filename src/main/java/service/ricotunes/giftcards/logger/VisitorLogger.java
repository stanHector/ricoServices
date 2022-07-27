package service.ricotunes.giftcards.logger;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import service.ricotunes.giftcards.model.Visitor;
import service.ricotunes.giftcards.service.VisitorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;


@Component
public class VisitorLogger implements HandlerInterceptor {

    private VisitorService visitorService;

    public VisitorLogger(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        final String ip = HttpRequestResponseUtils.getClientIpAddress();
        final String url = HttpRequestResponseUtils.getRequestUrl();
        final String page = HttpRequestResponseUtils.getRequestUri();
        final String userAgent = HttpRequestResponseUtils.getUserAgent();
        final String requestMethod = HttpRequestResponseUtils.getRequestMethod();
        final LocalDateTime timestamp = LocalDateTime.now();

        Visitor visitor = new Visitor();
        visitor.setIp(ip);
        visitor.setMethod(requestMethod);
        visitor.setUrl(url);
        visitor.setPage(page);
        visitor.setUserAgent(userAgent);
        visitor.setLoggedTime(timestamp);
        visitor.setStatus(response.getStatus());
        visitor.setUniqueVisit(true);

        visitorService.saveVisitorInfo(visitor);
        return true;
    }
}