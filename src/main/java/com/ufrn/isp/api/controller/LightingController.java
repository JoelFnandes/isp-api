package com.ufrn.isp.api.controller;

import com.ufrn.isp.api.domain.Lighting;
import com.ufrn.isp.api.dto.LigarLuzesRequest;
import com.ufrn.isp.api.service.LightingService;
import com.ufrn.isp.api.service.MqttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LightingController {

    @Autowired
    private LightingService lightingService;
    private MqttService mqttService;

    @PostMapping("/ligar-luzes")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> ligarLuzes(@RequestBody LigarLuzesRequest request) {
        String mensagem = "Luzes " + request.getStatus() + " no dispositivo: " + request.getDispositivo();

        mqttService.publishMessage("isp/luzes", mensagem);

        // Retorna uma resposta de sucesso com status HTTP 200
        return ResponseEntity.ok("Luzes " + request.getStatus() + " com sucesso.");
    }

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
