package com.luluShop.customerservice.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {

    @GetMapping("/google-map")
    public String map(){
        return "map";
    }
}
