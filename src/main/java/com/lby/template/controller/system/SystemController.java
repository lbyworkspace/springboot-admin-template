package com.lby.template.controller.system;

import com.lby.template.utils.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system")
public class SystemController {

    @GetMapping("/getInfo")
    public ResponseEntity getInfo(@RequestParam String token){
        return ResponseEntity.ok(JwtUtils.parser(token));
    }
}
