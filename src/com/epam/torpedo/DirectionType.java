package com.epam.torpedo;

import java.util.Random;

public enum DirectionType {
	HORIZONTAL, VERTICAL;

	public static DirectionType getRandomDirection() {
		Random randomGenerator = new Random();
		int randomNumber = randomGenerator.nextInt(values().length);
		return values()[randomNumber];
	}

}
