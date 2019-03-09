package com.me.nio;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Java Path实例表示文件系统中的路径。一个路径可以指向一个文件或一个目录。
 * 路径可以是绝对路径，也可以是相对路径。绝对路径包含从文件系统的根目录到它
 * 指向的文件或目录的完整路径。相对路径包含相对于其他路径的文件或目录的路径。
 * @author 清明
 *
 */
public class PathDemo {
    public static void main(String[] args) {
        
        //创建绝对路径
        Path path = Paths.get("d:\\data\\myfile.txt");
        System.out.println(path.toString());
        
        //相对路径
        Path file = Paths.get("d:\\data", "myfile.txt");
        
        //代码.表示“当前目录”
        Path currentDir = Paths.get(".");
        System.out.println(currentDir.toAbsolutePath());
        System.out.println(currentDir.toString());
        
        //..表示“父目录”或者“上一级目录”
        Path parentDir = Paths.get("..");
        System.out.println(parentDir.toAbsolutePath());
        System.out.println(parentDir.normalize());
        
        
    }
}
