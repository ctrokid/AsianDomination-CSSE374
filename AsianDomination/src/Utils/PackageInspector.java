package Utils;

import java.io.File;
import java.util.ArrayList;

public class PackageInspector {
//	Sample sourceDir = "C:\\Users\\yangr\\git\\AsianDomination-CSSE374\\AsianDomination\\src";
	
	public static ArrayList<String> toArrayHelper(String sourceDir, File dir) {
		ArrayList<String> classList = new ArrayList<String>();
		for (String name : dir.list()) {
			if(isJavaClass(name)){
				int index = dir.getPath().lastIndexOf(sourceDir)+sourceDir.length();
				classList.add(dir.getPath().substring(index).replace("\\", ".").substring(1)+"."+name.replaceAll(".java", ""));
				continue;
			}
			File tempFile = new File(dir.getPath() + "\\"+name);
			if (tempFile.isDirectory()) {
				classList.addAll(toArrayHelper(sourceDir, tempFile));
			}
		}
		return classList;
	}
	public static String[] getClasses(String sourceDir, File dir){
		ArrayList<String> classList = toArrayHelper(sourceDir, dir);
		String[] newArray =  new String[classList.size()];
		newArray = classList.toArray(newArray);
		return newArray;
	}
	
	public static Boolean isJavaClass(String name) {
		return name.endsWith(".java");
	}
}
