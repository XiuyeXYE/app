package com.ec.xy.app.algorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

import com.xiuye.sharp.X;
import com.xiuye.util.log.XLog;

public class CodeStatistics {

	static class FileTravel {

		private long fileCount = 0l;
		private long dirCount = 0l;

		private long lineCount = 0l;

		private long codeLine = 0l;

		public long getLineCount() {
			return lineCount;
		}

		public void setLineCount(long lineCount) {
			this.lineCount = lineCount;
		}

		public long getCodeLine() {
			return codeLine;
		}

		public void setCodeLine(long codeLine) {
			this.codeLine = codeLine;
		}

		public long getFileCount() {
			return fileCount;
		}

		public void setFileCount(long fileCount) {
			this.fileCount = fileCount;
		}

		public long getDirCount() {
			return dirCount;
		}

		public void setDirCount(long dirCount) {
			this.dirCount = dirCount;
		}

		private void scanFile(File f) {

			try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));) {

				String line;
				do {
					line = br.readLine();
//					XLog.ln(line);
					lineCount++;

					X.of(line).E(d -> {
//						if (!d.isEmpty()) {
//							codeLine++;
//						}
						d = d.trim();//去掉空白字符
						//不为空的就是代码
						X.of(d.isEmpty()).F(g->{
							codeLine++;
						});
					});
				} while (Objects.nonNull(line = br.readLine()));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void travel(String path) {
			File f = new File(path);
			X.of(f.exists()).T(d -> {
				X.beginS().IF(f.isDirectory()).THEN(i -> {
					dirCount++;
					XLog.ln("dir:", f);
					File[] fs = f.listFiles();
					for (File ff : fs) {
//						X.lnS(ff);
						travel(ff.getAbsolutePath());
					}
				}).ELSE(j -> {
					fileCount++;
					XLog.ln("file:", f);
					scanFile(f);
				}).end();

			});
		}

	}

	public static void main(String[] args) {

		FileTravel ft = new FileTravel();

//		String s = " 123 qwe ";
//		X.lnS(s.matches(".*\\S.*"));
		
		
//		ft.travel("G:\\software");
//		ft.travel("G:\\projects\\mybatis-plus\\mybatis-plus-core\\src\\main\\java");
		ft.travel("G:\\projects\\mybatis-plus");

		X.lnS("扫描文件夹数：",ft.getDirCount(),"\n", 
				"扫描文件数：",ft.getFileCount(), "\n",
				"扫描文件总行数：",ft.getLineCount(), "\n",
				"扫描文件总代码行数：",ft.getCodeLine());

	}

}
