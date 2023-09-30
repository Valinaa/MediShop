package tech.valinaa.medishop.utils.config;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author Valinaa
 * @Date 2023/9/28 16:04
 * @Description Kafka消息队列集群配置
 */
@Configuration
public class KafkaClusterConfiguration {
    @Bean
    public KafkaProducer<String,String> kafkaProducer() {
        var props = new Properties();
        props.put("bootstrap.servers", "master:9092,slave1:9092,slave2:9092");
        //客户端发送服务端失败的重试次数
        props.put("retries", 2);
        //多个记录被发送到同一个分区时,生产者将尝试将记录一起批处理成更少的请求.
        //此设置有助于提高客户端和服务器的性能,配置控制默认批量大小(以字节为单位)
        props.put("batch.size", 16384);
        //生产者可用于缓冲等待发送到服务器的记录的总内存字节数(以字节为单位)
        props.put("buffer-memory", 33554432);
        //生产者producer要求leader节点在考虑完成请求之前收到的确认数,用于控制发送记录在服务端的持久化
        //acks=0,设置为0,则生产者producer将不会等待来自服务器的任何确认.该记录将立即添加到套接字(socket)缓冲区并视为已发送.在这种情况下,无法保证服务器已收到记录,并且重试配置(retries)将不会生效(因为客户端通常不会知道任何故障),每条记录返回的偏移量始终设置为-1.
        //acks=1,设置为1,leader节点会把记录写入本地日志,不需要等待所有follower节点完全确认就会立即应答producer.在这种情况下,在follower节点复制前,leader节点确认记录后立即失败的话,记录将会丢失.
        //acks=all,acks=-1,leader节点将等待所有同步复制副本完成再确认记录,这保证了只要至少有一个同步复制副本存活,记录就不会丢失.
        props.put("acks", "all");
        //指定key使用的序列化类
        Serializer<String> keySerializer = new StringSerializer();
        //指定value使用的序列化类
        Serializer<String> valueSerializer = new StringSerializer();
        //创建Kafka生产者
        return new KafkaProducer<>(props, keySerializer, valueSerializer);
    }
    @Bean
    public KafkaConsumer<String,String> kafkaConsumer() {
        var props = new Properties();
        props.put("bootstrap.servers", "master:9092,slave1:9092,slave2:9092");
        //开启consumer的偏移量(offset)自动提交到Kafka
        props.put("enable.auto.commit", "true");
        //consumer的偏移量(offset) 自动提交的时间间隔,单位毫秒
        props.put("auto.commit.interval", 5000);
        //在Kafka中没有初始化偏移量或者当前偏移量不存在情况
        //earliest, 在偏移量无效的情况下, 自动重置为最早的偏移量
        //latest, 在偏移量无效的情况下, 自动重置为最新的偏移量
        //none, 在偏移量无效的情况下, 抛出异常.
        props.put("auto.offset.reset", "latest");
        //请求阻塞的最大时间(毫秒)
        props.put("fetch.max.wait", 500);
        //请求应答的最小字节数
        props.put("fetch.min.size", 1);
        //心跳间隔时间(毫秒)
        props.put("heartbeat-interval", 3000);
        //一次调用poll返回的最大记录条数
        props.put("max.poll.records", 500);
        //指定消费组
        props.put("group.id", "my-group");
        //指定key使用的反序列化类
        Deserializer<String> keyDeserializer = new StringDeserializer();
        //指定value使用的反序列化类
        Deserializer<String> valueDeserializer = new StringDeserializer();
        //创建Kafka消费者
        return new KafkaConsumer<>(props, keyDeserializer, valueDeserializer);
    }
}
