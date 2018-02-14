package com.smile.passionistar.ch0.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

public class RedisForPopularChat {
	int count;
	String s;
	private ZSetOperations<String, Object> ZsetOps;
	
	public RedisForPopularChat(int count, String s) {
		this.count = count;
		this.s = s.replaceAll("/", "").replace("websocket", "-56:8080");
	}
	
	public void sendCountForPopular() {
		@SuppressWarnings("unchecked")
		RedisTemplate<String, Object> redisTemplate = RedisCluster.ctx.getBean("redisTemplate", RedisTemplate.class);
		ZsetOps = redisTemplate.opsForZSet();
		ZsetOps.add("PopularRoom", s, count);
	}
}
