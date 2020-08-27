package com.ec.xy.app.algorithm;

import java.util.Comparator;

import com.xiuye.util.cls.XType;

public class IndexMinPQ<D extends Number> {

	private class Node<K, V extends Number> {
		private K key;
		private V value;

		public Node(K k, V v) {
			key = k;
			value = v;
		}

		public K getKey() {
			return key;
		}

		public void setKey(K key) {
			this.key = key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return "Node [key=" + key + ", value=" + value + "]";
		}

	}

	private Node<Integer, Integer>[] nodes;
	private int capacity;
	private int tail;

	@SuppressWarnings("unchecked")
	public IndexMinPQ(int len) {
		nodes = XType.newInstance(Node[]::new, len);
		capacity = len;
		tail = -1;

	}

	private Node<Integer, D> createNode(Integer idx, D d) {
		return XType.newInstance(Node::new, idx, d);
	}

	public void push(int idx, D d) {
		Node<Integer, D> node = createNode(idx, d);
		nodes[++tail] = node;

	}

	private void sort() {
		Node<Integer, D> tmp = nodes[0];
		nodes[0] = nodes[tail];
		nodes[tail] = tmp;

		int i = tail;
		int j = -1;
		while ((j = i / 2) > -1 && nodes[i].value < nodes[j].value) {
			exch(nodes, i, j);
		}

	}

	private void exch(Node<Integer, D>[] nodes, int i, int j) {
		Node<Integer, D> tmp = nodes[i];
		nodes[i] = nodes[j];
		nodes[j] = tmp;
	}

	public D pop() {

	}

	public D delMin() {

	}

}
