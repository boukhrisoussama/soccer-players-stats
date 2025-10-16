package com.soccer.stats.controller;

import com.soccer.stats.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired private UpdateService updateService;

    // Trigger server-side update
    @PostMapping("/update-data")
    public void triggerUpdate() {
        updateService.updateAll();
    }


}
