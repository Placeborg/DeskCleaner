package main;

import java.io.*;
import java.nio.*;
import java.nio.file.*;

import javax.swing.filechooser.FileSystemView;

public class Main {
	
	static Path moveFolder = FileSystemView.getFileSystemView().getHomeDirectory().toPath().getParent();
	static Path homeFolder = FileSystemView.getFileSystemView().getHomeDirectory().toPath();
	
	public static void main (String[] args) throws IOException {
		if(args.length < 1 ) {
			showCommands();
		}

		ARGSLOOP: for(String s : args) {
			switch(s) {
			case "-path":
				if (args.length >= 2) {
					setPath(args[1]);
				} else {
					showCommands();
				}
				break ARGSLOOP;
			case "-move":
				moveToFolder();
				break;
			case "-moveback":
				moveBack();
				break;
			default:
				showCommands();
				break;
			}
		}
	}

	static void showCommands() {
		System.out.println("Use one of these commands to use deskcleaner:");
		System.out.println("   -path <path>   set folder to move files into");
		System.out.println("   -move          moves files from the desktop to path");
		System.out.println("   -moveback      moves files from the desktop to path");
	}
	
	static void setPath(String newPath) {
		//newPath needs validation for Path
		moveFolder = Paths.get(newPath);
		System.out.println("Default path set as: " + moveFolder);
	}
	
	static void moveToFolder() throws IOException {
		String fileName = "/file.txt";
		//Path source = Paths.get(homeFolder + fileName);
		Path source = Paths.get("WRITEANYTHING");
		System.out.println("Source exists: " + Files.exists(moveFolder, LinkOption.NOFOLLOW_LINKS));
		Path target = Paths.get(moveFolder + fileName);
		System.out.println("Target exists: " + Files.exists(homeFolder, LinkOption.NOFOLLOW_LINKS));
		
		//Files.move(source, target, StandardCopyOption.ATOMIC_MOVE);
		System.out.println("Moved files from: " + homeFolder);
		System.out.println("              to: " + moveFolder);
	}
	
	static void moveBack() {
		System.out.println("Moved files back to desktop");
	}
	
}
