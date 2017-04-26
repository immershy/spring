package com.cmcc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zmcc on 17/4/26.
 */
@RestController
@RequestMapping("/")
public class HttpController {

    @RequestMapping("")
    public String returnByHttp2() {
        return "http2";
    }

}
