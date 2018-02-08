package Airbnb;
import java.util.*;
/***
 * 给出一个ipv4的range，找出最少的cidr可以覆盖这个range内的所有ip
参考：
背景介绍https://en.wikipedia.org/wiki/Classless_Inter-Domain_Routing
这个是个online转化工具http://www.ipaddressguide.com/cidr
大概的思路是group as much IPs as you can.
 * code: http://stackoverflow.com/questions/33443914/how-to-convert-ip-address-range-to-cidr-in-java

解释： ——代表end-start能覆盖到的二进制位

start：xxxxxxx100000

end： xxxxxx——-这种情况下，先找出可以覆盖住xxxxxxx100000~xxxxxxx111111的cidr，start变为xxxxxxx100000 + 100000
end： xxxxxxxxx—-这种情况下，先找出可以覆盖住xxxxxxx100000~xxxxxxx101111的cidr，start变为xxxxxxx100000 + 10000
 * ***/
public class Ip2cidr {

	public static List<String> range2cidrlist( String startIp, String endIp ) {
        // check parameters
        if (startIp == null || startIp.length() < 8 ||
            endIp == null || endIp.length() < 8) return null;
        long start = ipToLong(startIp);
        long end = ipToLong(endIp);
        // check parameters
        if (start > end) return null;

        List<String> result = new ArrayList<String>();
        while (start <= end) {
            // identify the location of first 1's from lower bit to higher bit of start IP
            // e.g. 00000001.00000001.00000001.01101100, return 4 (100)
            long locOfFirstOne = start & (-start);
            int maxMask = 32 - (int) (Math.log(locOfFirstOne) / Math.log(2));

            // calculate how many IP addresses between the start and end
            // e.g. between 1.1.1.111 and 1.1.1.120, there are 10 IP address
            // 3 bits to represent 8 IPs, from 1.1.1.112 to 1.1.1.119 (119 - 112 + 1 = 8)
            double curRange = Math.log(end - start + 1) / Math.log(2);
            int maxDiff = 32 - (int) Math.floor(curRange);

            // why max?
            // if the maxDiff is larger than maxMask
            // which means the numbers of IPs from start to end is smaller than mask range
            // so we can't use as many as bits we want to mask the start IP to avoid exceed the end IP
            // Otherwise, if maxDiff is smaller than maxMask, which means number of IPs is larger than mask range
            // in this case we can use maxMask to mask as many as IPs from start we want.
            maxMask = Math.max(maxDiff, maxMask);

            // Add to results
            String ip = longToIP(start);
            result.add(ip + "/" + maxMask);
            // We have already included 2^(32 - maxMask) numbers of IP into result
            // So the next round start must add that number
            start += Math.pow(2, (32 - maxMask));
        }
        return result;
    }

    private static long ipToLong(String strIP) {
        String[] ipSegs = strIP.split("\\.");
        long res = 0;
        for (int i = 0; i < 4; i++) {
            res += Long.valueOf(ipSegs[i]) << (8 * (3 - i));
        }
        return res;
    }

    private static String longToIP(long longIP) {
        StringBuffer sb = new StringBuffer();
        sb.append(longIP >>> 24).append(".")
          .append((longIP & 0x00FFFFFF) >>> 16).append(".")
          .append(String.valueOf((longIP & 0x0000FFFF) >>> 8)).append(".")
          .append(String.valueOf(longIP & 0x000000FF));

        return sb.toString();
    }
}