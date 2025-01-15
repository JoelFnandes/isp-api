package com.ufrn.isp.api.service;

import com.ufrn.isp.api.domain.Request;
import com.ufrn.isp.api.service.RequestService;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;

@Service
public class MqttService {

    @Autowired
    private RequestService requestService;

    private final MqttClient mqttClient;

    // Construtor para injeção do cliente MQTT
    @Autowired
    public MqttService(MqttClient mqttClient) {
        this.mqttClient = mqttClient;
        startListening();
    }

    // Iniciar a escuta no tópico
    public void startListening() {
        try {
            mqttClient.subscribe("isp/requests", (topic, message) -> {
                System.out.println("Inscrito no tópico: " + topic);
                String payload = new String(message.getPayload(), StandardCharsets.UTF_8);
                System.out.println("Mensagem recebida: " + payload);
                processMessage(payload);
            });
        } catch (MqttException e) {
            throw new RuntimeException("Erro ao escutar o tópico MQTT", e);
        }
    }

    // Processa a mensagem recebida
    public void processMessage(String payload) {
        Request request = new Request();
        request.setTipoSolicitacao("Solicitação recebida via MQTT");
        request.setDescricao(payload);
        request.setStatus("Pendente");
        request.setDataCriacao(new Timestamp(System.currentTimeMillis()));

        requestService.save(request);
    }

    // Método para publicar mensagem via MQTT
    public void publishMessage(String topic, String message) {
        try {
            MqttMessage mqttMessage = new MqttMessage();
            mqttMessage.setPayload(message.getBytes());
            mqttClient.publish(topic, mqttMessage);  // Publica a mensagem no tópico
            System.out.println("Mensagem publicada no tópico " + topic + ": " + message);
        } catch (Exception e) {
            System.err.println("Erro ao publicar mensagem no MQTT: " + e.getMessage());
        }
    }
}
