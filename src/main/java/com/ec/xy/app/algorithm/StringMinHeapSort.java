package com.ec.xy.app.algorithm;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.xiuye.util.cls.XType;
import com.xiuye.util.log.XLog;

public class StringMinHeapSort {

	// 堆排序内部是使用数组保存节点值
	// 逻辑上是一颗完美二叉树，保存在数组这个线性空间中
	private String[] nodes;
	private int currIdx;

	@SuppressWarnings("unchecked")
	public StringMinHeapSort(int len) {
		currIdx = -1;
		nodes = XType.newInstance(String[]::new, len);

	}

	
	//在插入的时候上浮，保证堆有序，小顶堆
	//每次插入新的元素时候，都是有序的，所以在此插入的时候，
	//对只需要调整一次就好
	//但对于乱序的数组，则需要调整该数组大小（通用）次数，当然其中有冗余的操作
	//但是能够保证全部有序。
	public void insert(String node) {
		if (currIdx + 1 >= nodes.length) {
			resize(nodes.length + 10);
		}
		nodes[++currIdx] = node;
		swim(nodes, currIdx);
		// 下面是错误的思想
		// 交换首节点会使排序变成乱序，不是一次下沉操作就能解决的
		// 每次都在尾部添加元素，应该使用上浮操作
//		while(currIdx/2)
//		exch(nodes, 0, currIdx);// 最后加入的节点和首节点，也就是顶点交换
//		sink(nodes, 0);// 然后下沉维持堆得结构
		// 这样所有节点调整，就不用上浮了swim，用sink下沉就好！

	}

	//交换节点
	private void exch(String[] ns, int i, int j) {
		String tmp = ns[i];
		ns[i] = ns[j];
		ns[j] = tmp;
	}

	//父节点下沉操作，保证父节点总是小于子节点，子节点的大小不管
	private void sink(String[] ns, int i/* 父节点 */) {
//		2*i+1;
//		2*(i+1);

		int left = 2 * i + 1;// 左子节点
		int right = 2 * (i + 1);// 右子节点

		// 小顶堆

//		error code
		if (left < ns.length && Objects.nonNull(ns[left]) && ns[left].compareTo(ns[i]) < 0) {// 左子节点小于父节点，交换
			exch(ns, left, i);
			sink(ns, left);// 继续比较后面的left的子节点,下沉
		}
		if (right < ns.length && Objects.nonNull(ns[right]) && ns[right].compareTo(ns[i]) < 0) {// 右子节点小于父节点，交换
			exch(ns, right, i);
			sink(ns, right);// 继续比较后面的right的子节点,下沉
		}
	}

	// core
	//子节点小于父节点，就上浮，也就是交换父节点和该子节点
	private void swim(String[] ns, int k) {
//				k%2 == 0;//右节点
		int fIdx = -1;
		if (k % 2 == 0) {
			fIdx = k / 2 - 1;// 父节点索引
		} else {// 左节点
			fIdx = k / 2;// 父节点索引
		}
		// 如果父节点大于子节点就上浮子节点，namely，与父节点交换位置
		if (fIdx > -1 && ns[fIdx].compareTo(ns[k]) > 0) {
			exch(ns, fIdx, k);
			swim(ns, fIdx);
		}
	}

	// 排序就是不断弹出根节点，根节点输出就是堆排序的结果；不断调整节点位置，如此反复
	private String pop() {
		String ret = nodes[0];
		nodes[0] = nodes[currIdx];
		nodes[currIdx--] = null;//弹出一个当前位置减一
		sink(nodes, 0);// 根（父）节点下沉，调整堆得顺序

		return ret;

	}

	@Deprecated
	private int minNodeIndex(String[] ns, int left, int right) {
		return ns[left].compareTo(ns[right]) > 0 ? right : left;
	}

	@SuppressWarnings("unchecked")
	private void resize(int length) {
		Object[] temp = nodes;
		nodes = XType.newInstance(String[]::new, length);
		System.arraycopy(temp, 0, nodes, 0, temp.length);
	}
	
	//为了sort 另起一个复制体，保证和原来的不冲突！
	public StringMinHeapSort clone() {
		StringMinHeapSort temp = XType.newInstance(StringMinHeapSort::new, nodes.length);
		System.arraycopy(nodes, 0, temp.nodes, 0, nodes.length);
		temp.currIdx = this.currIdx;
		return temp;
	}
	
	
	//打印堆的存储空间数组的值
	private void printNodes() {
		for (int i = 0; i < nodes.length; i++) {
			XLog.print(nodes[i], ' ');
		}
		XLog.println();
	}

	// 堆排序
	public List<String> sort() {
//		

		List<String> ret = XType.list();

		StringMinHeapSort smhs = this.clone();
		int size = smhs.currIdx;
		for (int i = 0; i < size; i++) {
			ret.add(this.pop());
		}

		return ret;

	}

	public static void main(String[] args) {

		StringMinHeapSort strMHS = XType.newInstance(StringMinHeapSort::new, 5);
		strMHS.insert("A");
		strMHS.insert("C");
		strMHS.insert("D");
		strMHS.insert("F");
		strMHS.insert("E");
		strMHS.insert("E");
		strMHS.insert("E");
		strMHS.insert("A");
		strMHS.insert("A");
		strMHS.insert("A");
		strMHS.insert("B");
		strMHS.insert("J");
		strMHS.insert("B");

		strMHS.printNodes();

		XLog.ln(strMHS.sort());

	}

}
