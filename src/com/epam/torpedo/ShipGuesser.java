package com.epam.torpedo;

import java.awt.Point;
import java.util.Random;

public class ShipGuesser {

	private TorpedoMatrix matrix;
	int[][] shots = new int[30][30];
	int test = 0;

	public Point getUniqueRandomSpot() {
		int x, y;
		Random randomGenerator = new Random();
		do {
			x = randomGenerator.nextInt(30);
			y = randomGenerator.nextInt(30);
		} while (shots[x][y] == 1);
		return new Point(x, y);
	}

	public void checkIfThereIsAShip() {
		int hitCount = 0;
		int sunk = 0;
		int tryCount = 0;
		while (hitCount < 30) {
			tryCount++;
			Point spot = getUniqueRandomSpot();
			shots[spot.x][spot.y] = 1;
			HitType hit = matrix.isHit(spot.x, spot.y);
			if (hit == HitType.SINGLE) {
				// System.out.println("single hit");
			} else if (hit == HitType.SUNK) {
				sunk++;
				// System.out.println("ship has sunk: " + sunk);
				matrix.printTable();
			} else {
				// System.out.println("miss");
			}

			if (hit == HitType.SUNK || hit == HitType.SINGLE) {
				hitCount++;
				// System.out.println("Hitcount:" + hitCount);
			}
		}
		System.out.println("Tries: " + tryCount);
	}

	public ShipGuesser() {
		matrix = new TorpedoMatrix();
	}

	public static void main(String[] args) {
		ShipGuesser guesser = new ShipGuesser();
		guesser.checkIfThereIsAShip();

	}

}
