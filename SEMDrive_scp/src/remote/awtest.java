package remote;
import java.io.File;
import java.io.FileFilter;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class awtest{
	
	public static void main(String[] args) {
        awtest hj = new awtest();
        hj.openDic();
	}
	
	public String openDic(){
		JFileChooser fd = new JFileChooser();//实例化选择器
		fd.setMultiSelectionEnabled(true);
		//选择模式，可选择文件和文件夹 
		fd.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		//设置是否显示隐藏文件 
		fd.setFileHidingEnabled(true);
		fd.setAcceptAllFileFilterUsed(true);
		fd.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("pdf","pdf"));
		fd.showOpenDialog(null);
		File f = fd.getSelectedFile();
		//System.out.println("==========="+f.getName()+"============"+f.getAbsolutePath()+"===="+f.getParent()+"==="+f.getPath());
		String path = f.getAbsolutePath();
		return path;
		}
}

