package br.com.henriquedev.CadastroServices.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {
    

    @Value(value="${spring.kafka.bootstrap-servers}")
    private String bootstrapServerAnddress;

    @Value(value="${users.kafka.TopicName}")
    private String topicName;
    
    @Bean
    public KafkaAdmin kafkaAdmin() {

        try {
            Map<String, Object> configs = new HashMap<>();
            configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServerAnddress);
            return new KafkaAdmin(configs);
        } catch (Exception e) {            
            e.printStackTrace();
        }
            return null;
        
    }


    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name(topicName)
                .partitions(1)
                .replicas(1)
                .build();
    }    
}
