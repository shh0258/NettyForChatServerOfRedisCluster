package com.smile.passionistar.ch0;

import java.util.UUID;

public class RandomNickname {
	private String userId = "익명-";
	
	public String MakeNickName() {
		UUID uuid = UUID.randomUUID();
		userId= userId+uuid.toString().replaceAll("-", "");
		return userId;
	}
}