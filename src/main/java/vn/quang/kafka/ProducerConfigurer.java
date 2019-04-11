package vn.quang.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import vn.quang.entity.TheNumber;

/**
 * @author Quang N. Vien {@literal <vienngocquang@gmail.com>}
 */
@Configuration
@EnableKafka
public class ProducerConfigurer {
	
	@Value("${servers}")
	private String brokers;
	
	@Value("${acks}")
	private String acks;
	
	@Value("${retries}")
	private String retries;
	
	@Value("${max.in.flight.req.per.con}")
	private String maxFlightReqPerCon;

	@Value("${batch.size}")
	private String batchSize;

	@Value("${request.timeout}")
	private String requestTimeout;

	@Value("${linger.time}")
	private String lingerTime;

	@Value("${buffer.mem}")
	private String bufferMem;
	
	@Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);
        props.put(ProducerConfig.ACKS_CONFIG, acks);
        props.put(ProducerConfig.RETRIES_CONFIG, Integer.valueOf(retries));
        props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, Integer.valueOf(maxFlightReqPerCon));
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, Integer.valueOf(batchSize));
        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, Integer.valueOf(requestTimeout));
        props.put(ProducerConfig.LINGER_MS_CONFIG, Integer.valueOf(lingerTime));
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, Long.valueOf(bufferMem));
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        
        return props;
    }

    @Bean
    public ProducerFactory<String, TheNumber> producerFactory() {
        return new DefaultKafkaProducerFactory<String, TheNumber>(producerConfigs());
    }
    
    @Bean
    public KafkaTemplate<String, TheNumber> kafkaTemplate() {
        return new KafkaTemplate<String, TheNumber>(producerFactory());
    }
}