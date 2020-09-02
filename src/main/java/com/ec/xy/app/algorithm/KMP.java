package com.ec.xy.app.algorithm;

import com.xiuye.sharp.X;
import com.xiuye.util.cls.XType;
import com.xiuye.util.log.XLog;

public class KMP {
	
	
	private static int[] getNext(String p) {
		int [] next = XType.newInstance(int[]::new,p.length());
		
		next[0] = -1;
		int i=0,j=-1;
		
		if(i<p.length()) {
			if(j == -1|| p.charAt(i)==p.charAt(j)) {
				++i;
				++j;
				next[i] = j;
//				X.lnS(i,j,next[i]);
			}
			else {
				j = next[j];
//				X.lnS(j);
			}
		}
		
		
		XLog.logArray(next);
		
		return next;
		
	}
	
	
	private static int match(String str,String p) {
		
		
		int i=0;
		int j = 0;
		
		
		int []next = getNext(p);
		
		while(i<str.length()&&j<p.length()) {
			if(j==-1||str.charAt(i)==p.charAt(j)) {
				i++;
				j++;
				
			}else {
				j = next[j];
			}
		}
		
		if(j == p.length()) {
			return i-j;
		}else {
			return -1;
			
		}
	}
	
	public static void main(String[] args) {
		
		X.lnS(match("ABCAAACC", "AAACC"));
		
	}
	
}
