package com.khakimova.ipblocker.service.component;

import java.util.Comparator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class IpDefiner {

    private final List<IpDefinerByRequestHeader> ipDefinerByRequestHeaderList;

    public IpDefiner(List<IpDefinerByRequestHeader> ipDefinerByRequestHeaderList) {
        ipDefinerByRequestHeaderList.sort(Comparator.comparing(IpDefinerByRequestHeader::priority));
        this.ipDefinerByRequestHeaderList = ipDefinerByRequestHeaderList;
    }

    public String defineIp(HttpServletRequest request) {
        if(request == null) {
            return null;
        }
        for (IpDefinerByRequestHeader definer : ipDefinerByRequestHeaderList) {
            String ip = definer.defineIp(request);
            if (ip != null) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }
}
