package com.smile.passionistar.ch0.redis;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

public class ChatroomMessageListener implements MessageListener {

	@Override
	public void onMessage(Message msg, byte[] channel) {
		System.out.println("Message Received at Listener: " + msg.toString() + " from Channel [" + new String(channel) +"]");
		
	}	

}
