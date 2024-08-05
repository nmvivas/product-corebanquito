package com.banquito.core.product.util;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class UniqueIdGeneration {

	public String generateUniqueId() {
		String letters = generateRandomLetters(3);
		String numbers = generateRandomNumbers(5);
		String rawId = letters + "00" + numbers;
		return rawId;
	}

	private String generateRandomLetters(int length) {
		Random random = new Random();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			char letter = (char) ('A' + random.nextInt(26));
			sb.append(letter);
		}
		return sb.toString();
	}

	private String generateRandomNumbers(int length) {
		Random random = new Random();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(10);
			sb.append(number);
		}
		return sb.toString();
	}

	public String getUniqueId() {
		return generateUniqueId();
	}
}
