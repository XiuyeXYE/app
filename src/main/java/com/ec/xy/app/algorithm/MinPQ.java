package com.ec.xy.app.algorithm;

import java.util.Objects;
import java.util.Random;

import com.xiuye.sharp.X;
import com.xiuye.util.cls.XType;

//性能不好！
public class MinPQ<T extends Comparable<T>> {

	//基于链表的优先级队列
	private Node<T> head;
	private Node<T> tail;
	
	public MinPQ() {
		
	}
	
	public MinPQ<T> push(T value) {
		if(Objects.isNull(head)) {
			head = XType.newInstance(Node::new,value);
			tail = head;
		}
		else {
			Node<T> t = XType.newInstance(Node::new,value);
			Node<T> h = head;
			Node<T> p = h;
			while(Objects.nonNull(h) && h.value.compareTo(t.value)<0) {
				p = h;
				h = h.next;
			}
			
			//p = h = head
			//namely,head > t
			//push it before head
			if(p == h && h == head) {
				t.setNext(head);
				head = t; 
			}else {
				t.setNext(p.next);
				p.setNext(t);
			}
			
			
			
			//if tail,tail = t
			if(p == tail) {
				tail = t;
			}
			
			
		}
		return this;
	}
	
	public T pop(){
		if(Objects.isNull(head)) {
			return head.value;
		}
		else {
			Node<T> t = head;
			head = head.next;
			return t.value;
		}
	}
	
	
	

	@Override
	public String toString() {
		
		StringBuffer str = new StringBuffer();
		Node<T> next = head;
		while(Objects.nonNull(next)) {
			str.append(next.value+" ");
			next = next.next;
		}
		return str.toString();
	}




	private class Node<T>{
		private T value;
		private Node<T> next;
		private Node(T v) {
			value = v;
			next = null;
		}
		public T getValue() {
			return value;
		}
		public void setValue(T value) {
			this.value = value;
		}
		public Node<T> getNext() {
			return next;
		}
		public void setNext(Node<T> next) {
			this.next = next;
		}
		@Override
		public String toString() {
			return "Node [value=" + value /* + ", next=" + next */ + "]";
		}
		
		
	}
	
	
	
	public static void main(String[] args) {
		
		MinPQ<String> pq = new MinPQ<>();
		
		pq.push("abc");
		pq.push("hij");
		pq.push("def");
		
		X.lnS(pq);
		
		MinPQ<Integer> pq2 = new MinPQ<Integer>();
		pq2.push(5);
		pq2.push(2);
		pq2.push(5);
		pq2.push(6);
		pq2.push(3);
		pq2.push(1);
		pq2.push(3);
		pq2.push(0);
		X.lnS(pq2);
		X.lnS(pq2.pop());
		X.lnS(pq2.pop());
		X.lnS(pq2.pop());
		
		Random rand = new Random();
		MinPQ<Integer> pq3 = new MinPQ<Integer>();
		int seed = 100000;
		for(int i=0;i<seed;i++) {
			pq3.push(rand.nextInt(seed));
		}
		
		X.lnS(pq3);
		
	}

}
