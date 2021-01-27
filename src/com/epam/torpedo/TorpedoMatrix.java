package com.epam.torpedo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TorpedoMatrix {
	private static final int MAX = 30;
	private List<Ship> ships = new ArrayList<Ship>();
	int shipNum = 1;

	public TorpedoMatrix() {
		addBatchShip(2, 4);
		addBatchShip(3, 3);
		addBatchShip(4, 2);
		addBatchShip(5, 1);
	}

	public void addBatchShip(int amount, int length) {
		for (int i = 0; i < amount; i++) {
			addShip(length);
		}
	}

	public HitType isHit(int x, int y) {
		for (Ship ship : ships) {
			HitType hit = ship.isHit(x, y);
			if (hit != HitType.MISS) {
				return hit;
			}
		}
		return HitType.MISS;
	}

	public Ship generateRandomShip(int length) {
		Random randomGenerator = new Random();
		boolean shipValidPosition = true;
		int x, y;
		DirectionType direction;

		do {
			direction = DirectionType.getRandomDirection();
			x = randomGenerator.nextInt(MAX);
			y = randomGenerator.nextInt(MAX);
			shipValidPosition = isValidPositionForShip(x, y, direction, length);
		} while (shipValidPosition != true);
		return new Ship(x, y, length, direction, shipNum++);
	}

	public void addShip(int length) {
		Ship ship = generateRandomShip(length);
		ships.add(ship);
	}

	public HitType getField(int x, int y) {
		for (Ship ship : ships) {
			HitType hit = ship.getCoordinate(x, y);
			if (hit != HitType.MISS) {
				return hit;
			}
		}
		return HitType.MISS;
	}

	public void printTable() {
		for (int x = 0; x < 30; x++) {
			for (int y = 0; y < 30; y++) {
				HitType hit = getField(x, y);
				if (hit == HitType.MISS) {
					System.out.print(" .");
				} else if (hit == HitType.SINGLE) {
					System.out.print(" 0");
				} else if (hit == HitType.SUNK) {
					System.out.print(" x");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	private boolean isValidPositionForShip(int x, int y, DirectionType direction, int length) {
		if (!areCoordsAreInBoundaries(x, y, direction, length)) {
			return false;
		}
		for (Ship ship : ships) {
			if (ship.isInTheShip(x, y)) {
				return false;
			}
		}
		return true;
	}

	public boolean checkIfValueFitsOnTheTable(int value, int length) {
		return value + length > MAX ? false : true;
	}

	private boolean areCoordsAreInBoundaries(int x, int y, DirectionType direction, int length) {
		return direction == DirectionType.HORIZONTAL ? checkIfValueFitsOnTheTable(x, length)
				: checkIfValueFitsOnTheTable(y, length);
	}

}
