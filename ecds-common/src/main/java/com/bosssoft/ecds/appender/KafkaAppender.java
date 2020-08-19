package com.bosssoft.ecds.appender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * 将日志放入kafka中
 * @author wuliming
 * @date 2020-07-22 13:49
 */
@Data
@Slf4j
public class KafkaAppender extends AppenderBase<ILoggingEvent> {
    /**
     * 设置kafka服务器地址
     */
    private String bootstrapServers;
    /**
     * kafka 生产者
     */
    private Producer<String, String> producer;
    /**
     * 日志消息前缀
     */
    private String logPrefix;
    /**
     * kafka topic
     */
    private String logTopic;

    @Override
    public void start() {
        super.start();
        if (producer == null) {
            Properties props = new Properties();
            props.put("bootstrap.servers", bootstrapServers);
            /**
             * acks指定必须要有多少个partition副本收到消息，生产者才会认为消息的写入是成功的。
             *       acks=0，生产者不需要等待服务器的响应，以网络能支持的最大速度发送消息，吞吐量高，但是如果broker没有收到消息，生产者是不知道的
             *       acks=1，leader partition收到消息，生产者就会收到一个来自服务器的成功响应
             *       acks=all，所有的partition都收到消息，生产者才会收到一个服务器的成功响应
             */
            props.put("acks", "all");
            //  生产者从服务器收到临时性错误时，生产者重发消息的次数
            props.put("retries", 2);
            //  batch.size，发送到同一个partition的消息会被先存储在batch中，该参数指定一个batch可以使用的内存大小，单位是byte。不一定需要等到batch被填满才能发送
            props.put("batch.size", 0);
            //延迟1s，1s内数据会缓存进行发送
            props.put("linger.ms", 1);
            //buffer.memory，设置生产者内缓存区域的大小，生产者用它缓冲要发送到服务器的消息。
            props.put("buffer.memory", 33554432);
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            producer = new KafkaProducer<>(props);
        }
    }

    @Override
    protected void append(ILoggingEvent eventObject) {
        String msg = eventObject.getFormattedMessage();
        log.debug("向kafka推送日志开始:" + msg);
        ProducerRecord<String, String> record = new ProducerRecord<>(logTopic, msg);
        producer.send(record);
    }
}
