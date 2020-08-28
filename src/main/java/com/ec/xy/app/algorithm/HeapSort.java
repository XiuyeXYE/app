package com.ec.xy.app.algorithm;

public class HeapSort {
	
	private int []nodes;
	private int currIdx;
	
	public HeapSort(int len) {
		currIdx = -1;
		nodes = new int[len];
	}
	
	public void insert(int node) {
		nodes[++currIdx] = node;
//		while(currIdx/2)
	}
	

	public static void main(String[] args) {

	}

}
