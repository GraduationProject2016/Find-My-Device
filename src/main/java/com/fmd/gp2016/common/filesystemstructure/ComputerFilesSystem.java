package com.fmd.gp2016.common.filesystemstructure;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ComputerFilesSystem {
	public String path;
	public List<FMDFile> files;
	public List<FMDDirectory> directories;
	public int numOfFiles;
	public int numOfFolders;



	public ComputerFilesSystem() {
		files = new ArrayList<FMDFile>();
		directories = new ArrayList<FMDDirectory>();
	}

	public ComputerFilesSystem(String path_) throws IOException {
		path = path_;
		files = new ArrayList<FMDFile>();
		directories = new ArrayList<FMDDirectory>();

		ls();
	}

	public void ls() throws IOException {
		String fileName, fileType;
		long fileSize;

		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (!listOfFiles[i].isHidden()) {
				if (listOfFiles[i].isFile()) {
					fileName = listOfFiles[i].getName();
					fileSize = listOfFiles[i].length();
					fileType = Files.probeContentType(listOfFiles[i].toPath());
					files.add(new FMDFile(fileName, fileType, fileSize));
				} else if (listOfFiles[i].isDirectory()) {
					fileName = listOfFiles[i].getName();
					fileSize = listOfFiles[i].length();
					directories.add(new FMDDirectory(fileName, fileSize));
				}
			}
		}
	}

	public JSONObject toJson() throws JSONException {
		JSONObject object = new JSONObject();
		object.put("path", path);
		object.put("numoffiles", files.size());
		object.put("numoffolders", directories.size());

		JSONArray filesArray = new JSONArray();
		for (int i = 0; i < files.size(); i++) {
			filesArray.put(i, files.get(i).toJson());
		}
		object.put("files", filesArray);

		JSONArray foldersArray = new JSONArray();
		for (int i = 0; i < directories.size(); i++) {
			foldersArray.put(i, directories.get(i).toJson());
		}
		object.put("folders", foldersArray);
		return object;
	}



	public String getPath() {
		return path;
	}

	public List<FMDFile> getFiles() {
		return files;
	}

	public List<FMDDirectory> getDirectories() {
		return directories;
	}

	public int getNumOfFiles() {
		return numOfFiles;
	}

	public int getNumOfFolders() {
		return numOfFolders;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setFiles(List<FMDFile> files) {
		this.files = files;
	}

	public void setDirectories(List<FMDDirectory> directories) {
		this.directories = directories;
	}

	public void setNumOfFiles(int numOfFiles) {
		this.numOfFiles = numOfFiles;
	}

	public void setNumOfFolders(int numOfFolders) {
		this.numOfFolders = numOfFolders;
	}

	@Override
	public String toString() {
		return "ComputerFilesSystem [path=" + path + ", files=" + files + ", directories=" + directories
				+ ", numOfFiles=" + numOfFiles + ", numOfFolders=" + numOfFolders + "]";
	}

}
