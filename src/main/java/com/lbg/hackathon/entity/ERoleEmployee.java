package com.lbg.hackathon.entity;

public enum ERoleEmployee {
	
	ROLE_DEVELOPER,
	ROLE_DEVOPS;

	public static ERoleEmployee findByName(String name) {
		ERoleEmployee result = null;
		for (ERoleEmployee direction : values()) {
			if (direction.name().equalsIgnoreCase(name)) {
				result = direction;
				break;
			}
		}
		return result;
	}

}
