package com.fmd.gp2016.common.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fmd.gp2016.common.filesystemstructure.ComputerFilesSystem;
import com.fmd.gp2016.common.filesystemstructure.FMDDirectory;
import com.fmd.gp2016.common.filesystemstructure.FMDFile;
import com.fmd.gp2016.common.filesystemstructure.FMDPartion;

public class JSONDecoding {

	public static ComputerFilesSystem decodeJsonOFPathContent(JSONObject object) throws JSONException {
		ComputerFilesSystem fs = new ComputerFilesSystem();

		fs.path = (String) object.get("path");
		fs.numOfFiles = (int) object.get("numoffiles");
		fs.numOfFolders = (int) object.get("numoffolders");

		JSONArray fileArray = (JSONArray) object.get("files");
		for (int i = 0; i < fs.numOfFiles; i++) {
			JSONObject obj = (JSONObject) fileArray.get(i);
			fs.files.add(new FMDFile((String) obj.getString("name"), (String) obj.getString("type"),
					(int) obj.get("size")));
		}

		JSONArray folderArray = (JSONArray) object.get("folders");
		for (int i = 0; i < fs.numOfFolders; i++) {
			JSONObject obj = (JSONObject) folderArray.get(i);
			fs.directories.add(new FMDDirectory((String) obj.getString("name"), (int) obj.get("size")));
		}
		return fs;
	}

	public static List<FMDPartion> decodeJsonOFPartions(JSONObject object) throws JSONException {
		List<FMDPartion> partions = new ArrayList<FMDPartion>();

		int numOfPartions = (int) object.get("numOfPartions");
		JSONArray folderArray = (JSONArray) object.get("partions");

		for (int i = 0; i < numOfPartions; i++) {
			JSONObject obj = (JSONObject) folderArray.get(i);
			partions.add(new FMDPartion((String) obj.getString("name"), (String) obj.getString("path"),
					obj.getLong("totalSpace"), (long) obj.getLong("usableSpace")));
		}

		return partions;
	}
}
