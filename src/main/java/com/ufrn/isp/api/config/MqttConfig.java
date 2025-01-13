package com.ufrn.isp.api.config;

import org.eclipse.paho.client.mqttv3.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttConfig {

    // Criação do cliente MQTT
    @Bean
    public MqttClient mqttClient() throws MqttException {
        // Configurações do cliente MQTT
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[] {"tcp://localhost:1883"});
        options.setUserName("user01");
        options.setPassword("123456789".toCharArray());
        options.setCleanSession(true);
        options.setKeepAliveInterval(30);

        // Criação do cliente com um ID único
        MqttClient mqttClient = new MqttClient("tcp://localhost:1883", MqttClient.generateClientId());



        mqttClient.connect(options);

        System.out.println("Conectado ao broker MQTT");
        mqttClient.subscribe("isp/requests", (topic, message) -> {
            System.out.println("Mensagem recebida no tópico: " + topic);
            System.out.println("Conteúdo da mensagem: " + new String(message.getPayload()));
        });

        // Definindo o callback para escutar as mensagens
        mqttClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                System.out.println("Conexão perdida: " + cause.getMessage());
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                System.out.println("Mensagem recebida no tópico " + topic + ": " + new String(message.getPayload()));
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                System.out.println("Mensagem entregue: " + token.getMessageId());
            }
        });

        return mqttClient;
    }
}
