package com.smile.passionistar.ch0.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@ComponentScan("com.smile.passionistar.ch0.redis")
public class ChatRoomConfig {
	
	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		JedisConnectionFactory redisConnectionFactory =new JedisConnectionFactory();
		redisConnectionFactory.setHostName("127.0.0.1");
		redisConnectionFactory.setPort(6379);
		redisConnectionFactory.setUsePool(true);
		return redisConnectionFactory;
	}
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		redisTemplate.setDefaultSerializer(stringSerializer());
		return redisTemplate;
	}
	
	@Bean
	public StringRedisSerializer stringSerializer() {
		return new StringRedisSerializer();
	}
	
	@Bean
	public MessageListenerAdapter delegateMessageListener() {
		return new MessageListenerAdapter(new ChatroomMessageDelegate());
	}
	
	@Bean
	public MessageListenerAdapter messageListener() {
		return new MessageListenerAdapter(new ChatroomMessageListener());
	}
	
	@Bean
	public RedisMessageListenerContainer redisContainer() {
		RedisMessageListenerContainer redisContainer = new RedisMessageListenerContainer();
		redisContainer.setConnectionFactory(redisConnectionFactory());
//		redisContainer.addMessageListener(delegateMessageListener(), new ChannelTopic("chatroom.patrick"));
		redisContainer.addMessageListener(messageListener(), new PatternTopic("c.*"));
		return redisContainer;
	}

	

	
	

}
