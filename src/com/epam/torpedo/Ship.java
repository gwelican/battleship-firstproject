package com.epam.torpedo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Ship {
	private int shipLength;
	private int shipX;
	private int shipY;
	private DirectionType shipDirection;
	private int shipId;

	private List<Point> shipPoints = new ArrayList<Point>();

	public int getId() {
		return shipId;
	}

	private List<Point> shotTaken = new ArrayList<>();

	public Ship(int x, int y, int length, DirectionType direction, int id) {
		if (direction == DirectionType.HORIZONTAL) {
			for (int i = 0; i < length; i++) {
				shipPoints.add(new Point(x + i, y));
			}
		} else {
			for (int i = 0; i < length; i++) {
				shipPoints.add(new Point(x, y + i));
			}
		}
		this.shipX = x;
		this.shipY = y;
		this.shipLength = length;
		this.shipDirection = direction;
		this.shipId = id;

	}

	public boolean isInTheShip(int x, int y) {
		Point point = new Point(x, y);
		if (shipPoints.contains(point)) {
			return true;
		}
		return false;
	}

	private boolean isSunk() {
		return shipPoints.size() > shotTaken.size() ? false : true;
	}

	@Override
	public String toString() {
		return "Ship [shipLength=" + shipLength + ", shipX=" + shipX + ", shipY=" + shipY + ", shipDirection="
				+ shipDirection + ", shipId=" + shipId + ", shotTaken=" + shotTaken + "]";
	}

	public HitType isHit(int x, int y) {
		if (isInTheShip(x, y)) {
			Point point = new Point(x, y);
			if (!shotTaken.contains(point))
				shotTaken.add(new Point(x, y));
			if (isSunk()) {
				return HitType.SUNK;
			}
			return HitType.SINGLE;
		} else {
			return HitType.MISS;
		}
	}

	public HitType getCoordinate(int x, int y) {
		Point point = new Point(x, y);
		if (shipPoints.contains(point)) {
			if (isSunk()) {
				return HitType.SUNK;
			}
			return HitType.SINGLE;
		}
		return HitType.MISS;
	}
}
