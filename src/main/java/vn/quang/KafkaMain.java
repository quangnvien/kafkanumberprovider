package vn.quang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import vn.quang.kafka.NumberProducer;

/**
 * @author Quang N. Vien {@literal <vienngocquang@gmail.com>}
 */
@SpringBootApplication
public class KafkaMain {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(KafkaMain.class, args);
		NumberProducer producer = new NumberProducer();
	    context.getAutowireCapableBeanFactory().autowireBean(producer);
	    Thread t = new Thread(producer);
	    t.start();
	}
}