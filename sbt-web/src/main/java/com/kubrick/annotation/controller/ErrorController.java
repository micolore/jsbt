package com.kubrick.annotation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author k
 */
@Controller
public class ErrorController {

    @RequestMapping("/403")
    public String toError403() {
        return "error/403";
    }
}
