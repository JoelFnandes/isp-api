package com.ufrn.isp.api.controller;

import com.ufrn.isp.api.domain.Lighting;
import com.ufrn.isp.api.service.LightingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lighting")
public class LightingController {

    @Autowired
    private LightingService lightingService;

    @GetMapping
    public List<Lighting> getAllLightings() {
        return lightingService.getAllLightings();
    }

    @PostMapping
    public Lighting createLighting(@RequestBody Lighting lighting) {
        return lightingService.createLighting(lighting);
    }

    @PutMapping("/{id}")
    public Lighting updateLighting(@PathVariable Long id, @RequestBody Lighting lighting) {
        return lightingService.updateLighting(id, lighting);
    }

    @DeleteMapping("/{id}")
    public void deleteLighting(@PathVariable Long id) {
        lightingService.deleteLighting(id);
    }
}
