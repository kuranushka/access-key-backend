package com.example.accesskeybackend.template.service;

import com.example.accesskeybackend.template.dto.SupportIPv6Dto;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.xbill.DNS.Address;

@Service
@AllArgsConstructor
public class CheckService {

  private static final String URL_KEY = "siteUrl";

  public SupportIPv6Dto checkSupportIPv6(Map<String, String> map) {
    if (!map.isEmpty() && map.get(URL_KEY) != null) {
      try {
        final String host = formatURL(map.get(URL_KEY));
        final InetAddress[] addresses = Address.getAllByName(host);
        final boolean isIPv6 = Arrays.stream(addresses).anyMatch(ip -> ip instanceof Inet6Address);
        return SupportIPv6Dto.builder().success(isIPv6).build();
      } catch (UnknownHostException | URISyntaxException ex) {
        throw new RuntimeException("Unrecognized host", ex);
      }
    }
    throw new RuntimeException("Query parameter must contain 'siteUrl'");
  }

  private String formatURL(String stringURI) throws URISyntaxException {
    if (stringURI.contains("//")) {
      final URI uri = new URI(stringURI);
      final String host = uri.getHost();
      if (host == null) {
        throw new RuntimeException("Unrecognized host");
      }
      if (host.startsWith("www.")) {
        return host.substring(4);
      }
      return host;
    } else {
      if (stringURI.endsWith("/")) {
        return stringURI.substring(0, stringURI.lastIndexOf("/"));
      }
      return stringURI;
    }
  }
}
