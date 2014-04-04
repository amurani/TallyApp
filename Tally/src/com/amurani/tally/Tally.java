package com.amurani.tally;

import java.util.ArrayList;
import java.util.List;

public class Tally {
	
	private List<Integer> mList;
	private List<Integer> mPositions;
	private int lastChange;
	
	public Tally(Object[] array) {
		this.mList = new ArrayList<Integer>();
		this.mPositions = new ArrayList<Integer>();
		
		for (int i = 0; i < array.length; i++)
			mList.add(0);
	}
	
	private void add() {
		lastChange = this.mPositions.size() - 1;
		int mPosition = this.mPositions.get(lastChange);
		int mValue = this.mList.get(mPosition);
		this.mList.set(mPosition, ++mValue);
	}
	
	public int getLastChange() {
		return this.mList.get(this.lastChange);
	}
	
	public int getLastPosition() {
		return this.lastChange;
	}
	
	public int getValueAt(int mPosition) {
		return this.mList.get(mPosition);
	}
	
	public void increment(int mPosition) {
		this.mPositions.add(mPosition);
		this.add();
	}
	
	public void undolast() {
		if (this.mPositions.size() > 0) {
			lastChange = this.mPositions.size() - 1;
			int mPosition = this.mPositions.get(lastChange);
			int mValue = this.mList.get(mPosition);
			this.mList.set(mPosition, --mValue);
			this.mPositions.remove(lastChange);
			lastChange = mPosition;
		}
	}
	
}