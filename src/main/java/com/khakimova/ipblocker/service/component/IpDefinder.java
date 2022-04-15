package com.khakimova.ipblocker.service.component;

import java.util.Comparator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class IpDefinder {

    private final List<IpDefinderByRequestHeader> ipDefinderByRequestHeaderList;

    public IpDefinder(List<IpDefinderByRequestHeader> ipDefinderByRequestHeaderList) {
        ipDefinderByRequestHeaderList.sort(Comparator.comparing(IpDefinderByRequestHeader::priority));
        this.ipDefinderByRequestHeaderList = ipDefinderByRequestHeaderList;
    }

    public String defineIp(HttpServletRequest request) {
        for (IpDefinderByRequestHeader definder : ipDefinderByRequestHeaderList) {
            String ip = definder.defineIp(request);
            if (ip != null) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }
}
