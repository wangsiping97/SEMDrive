package com;

import java.io.File;

import javax.swing.JFileChooser;

public class selectdirs {
		public static void main(String[] args) {
	        selectdirs hj = new selectdirs();
	        hj.openDic();
		}
		
		public String openDic(){
			JFileChooser fd = new JFileChooser();//实例化选择器
			fd.setMultiSelectionEnabled(true);
			//选择模式，可选择文件和文件夹 
			fd.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			//设置是否显示隐藏文件 
			fd.setFileHidingEnabled(false);
			fd.showOpenDialog(null);
			File f = fd.getSelectedFile();
			String path = f.getAbsolutePath();
			System.out.println(path);
			return path;
			}
	}
