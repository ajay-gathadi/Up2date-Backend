package com.up2date;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpaController {
    private static final Logger logger = LoggerFactory.getLogger(SpaController.class);
//    @RequestMapping(value = {
//            "/",
//            "/{path:[^\\.]*}",
//    }
//    )

    @GetMapping(value = {"/", "/{path:[^\\.]*}"})
    public String forwardToIndex() {
        logger.info("Forwarding to index.html");
        return "forward:/index.html";
    }
}
