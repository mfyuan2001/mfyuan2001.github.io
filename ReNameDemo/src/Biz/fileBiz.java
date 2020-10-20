package Biz;

import java.io.File;

public class fileBiz {
	
	public static File[] getAllFile(String path) {
		return new File(path).listFiles();	
	}
	
	public static int getFindChar(String template,char c) {
		int size = 0;
		for (int i = 0; i < template.length(); i++) {
			if(template.charAt(i) == c) size++;
		}
		return size;
	}
	
	public static int getIndexNoChar(StringBuffer template,char c) {
		
		for (int i = 0; i < template.length(); i++) {
			if(template.charAt(i) != c) return i;
		}
		return -1;
	}
	public static String deleteCharToString(StringBuffer template,char c) {
		String str = "";
		for (int i = 0; i < template.length(); i++) {
			if(template.charAt(i) == c) {
			
			}else {
				str +=template.charAt(i);
			}
		}
		return str;
	}
}
