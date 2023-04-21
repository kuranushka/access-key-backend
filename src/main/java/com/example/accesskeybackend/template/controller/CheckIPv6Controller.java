package com.example.accesskeybackend.template.controller;

import com.example.accesskeybackend.template.dto.SupportIPv6Dto;
import com.example.accesskeybackend.template.service.CheckService;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/web/checkIpv6Support/")
public class CheckIPv6Controller {

  private final CheckService checkService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public SupportIPv6Dto checkSupportIPv6(
      @RequestParam final Map<String, String> params
  ) {
    return checkService.checkSupportIPv6(params);
  }
}
