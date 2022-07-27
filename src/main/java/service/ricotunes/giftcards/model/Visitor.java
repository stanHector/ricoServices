package service.ricotunes.giftcards.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "visitor")
public class Visitor {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    private String ip;
    private String method;
    private String url;
    private String page;
    private String userAgent;
    private LocalDateTime loggedTime;
    private boolean uniqueVisit;
    private int status;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public LocalDateTime getLoggedTime() {
        return loggedTime;
    }

    public void setLoggedTime(LocalDateTime loggedTime) {
        this.loggedTime = loggedTime;
    }

    public boolean isUniqueVisit() {
        return uniqueVisit;
    }

    public void setUniqueVisit(boolean uniqueVisit) {
        this.uniqueVisit = uniqueVisit;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}