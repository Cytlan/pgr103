// BuildCo Inc. Project Management System

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONTokener;
import org.json.JSONObject;

/**
 * JSON implementation of the BuildCoData interface
 *
 * This isn't an optimal implementation - It's a bit inefficient and reads the entire file on every call.
 * This was done for simplicity, and to make sure it will always serve up-to-date data.
 * A potential improvement would be to check if the file has been updated since last time, and reload if it has been modified.
 */
public class BuildCoDataJson implements BuildCoData
{
	private boolean exists_ = false;
	private String sourceFile;

	public BuildCoDataJson()
	{

	}

	/**
	 * Returns true if the chosen data source exists
	 * @return True if data source exists, false otherwise
	 */
	public boolean exists()
	{
		File f = new File(sourceFile);
		return f.exists() && !f.isDirectory();
	}

	/**
	 * Set source file
	 */
	public void setSource(String sourceFile)
	{
		this.sourceFile = sourceFile;
	}

	/**
	 * Load data from file
	 * @return ArrayList on success, null of failure
	 */
	private ArrayList<BuildCoProject> load()
	{
		// Do we have a source file?
		if(sourceFile == null)
			return null;

		// Read entire file
		String file = readFile(sourceFile);
		if(file == null)
			return null;

		// Parse
		JSONTokener tokener = new JSONTokener(file);
		JSONObject root = new JSONObject(tokener);

		// Get all project objects
		JSONArray jsonProjectsArray = root.getJSONArray("projects");
		int length = jsonProjectsArray.length();

		ArrayList<BuildCoProject> projects = new ArrayList<BuildCoProject>();

		// Loop through all and attempt to deserialise
		for(int i = 0; i < length; i++)
		{
			try
			{
				JSONObject jsonProject= jsonProjectsArray .getJSONObject(i);
				HashMap<String,Object> projectMap = jsonObjectToMap(jsonProject);
				BuildCoProject project = new BuildCoProject(projectMap);
				projects.add(project);
			}
			catch(Exception e)
			{
				System.out.println("Failed to load project "+i+": "+e.getMessage());
			}
		}

		return projects;
	}

	/**
	 * Convert a JSON array into a map
	 */
	private ArrayList<Object> jsonArrayToMap(JSONArray arrObj)
	{
		ArrayList<Object> arr = new ArrayList<>();
		for(int i = 0; i < arrObj.length(); i++)
		{
			JSONObject subObject = arrObj.optJSONObject(i);
			JSONArray subArray = arrObj.optJSONArray(i);

			// Handle nested objects
			if(subObject != null)
			{
				HashMap<String,Object> subMap = jsonObjectToMap(subObject);
				arr.add(subMap);
			}
			// Handle arrays
			else if(subArray != null)
			{
				ArrayList<Object> subArr= jsonArrayToMap(subArray);
				arr.add(subArr);
			}
			// Other data
			else
			{
				Object value = arrObj.get(i);
				arr.add(value);
			}
		}
		return arr;
	}

	/**
	 * Convert a JSON object into a map
	 */
	private HashMap<String,Object> jsonObjectToMap(JSONObject obj)
	{
		HashMap<String,Object> map = new HashMap<>();
		Iterator<?> keys = obj.keys();
		while(keys.hasNext())
		{
			String key = (String)keys.next();
			try
			{
				JSONObject subObject = obj.optJSONObject(key);
				JSONArray subArray = obj.optJSONArray(key);

				// Handle nested objects
				if(subObject != null)
				{
					HashMap<String,Object> subMap = jsonObjectToMap(subObject);
					map.put(key, subMap);
				}
				// Handle arrays
				else if(subArray != null)
				{
					ArrayList<Object> subArr = jsonArrayToMap(subArray);
					map.put(key, subArr);
				}
				// Other data
				else
				{
					Object value = obj.get(key);
					map.put(key, value);
				}
			}
			catch(Exception e)
			{
				// Ignore
			}
		}
		return map;
	}

	/**
	 * Read entire file from disk
	 * @param path Path of file to read
	 * @return Contents fo file as string
	 */
	private String readFile(String path)
	{
		try
		{
			// Read entire file
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file);
			byte[] data = new byte[(int) file.length()];
			fis.read(data);
			fis.close();

			// Convert to UTF-8 string
			String str = new String(data, "UTF-8");
			return str;
		}
		catch(IOException e)
		{
			// Oops!
			return null;
		}
	}

	/**
	 * Write file to disk
	 * @param data Contents of file as string
	 * @param path Path of file to read
	 */
	private boolean writeFile(String data, String path)
	{
		try
		{
			File file= new File(path);
			FileWriter fw = new FileWriter(file);
			fw.write(data);
			fw.close();
		}
		catch (IOException iox)
		{
			return false;
		}
		return true;
	}

	/**
	 * Get all projects from the JSON object
	 * @return Array of projects
	 */
	public BuildCoProject[] getProjects()
	{
		if(!exists())
			return null;

		ArrayList<BuildCoProject> projects = load();
		BuildCoProject[] arr = new BuildCoProject[projects.size()];
		for(int i = 0; i < projects.size(); i++)
		{
			arr[i] = projects.get(i);
		}
		return arr;
	}

	/**
	 * Get project by name
	 * @param projectName Name of the project to search for
	 * @return Single project or null if not found
	 */
	public BuildCoProject getProject(String projectName)
	{
		if(!exists())
			return null;

		ArrayList<BuildCoProject> projects = load();
		for(int i = 0; i < projects.size(); i++)
		{
			if(projects.get(i).getName().equals(projectName))
				return projects.get(i);
		}
		return null;
	}

	/**
	 * Create JSON array from an ArrayList
	 * @param arr ArrayList to convert
	 * @return JSONArray representation of the ArrayList
	 */
	@SuppressWarnings("unchecked") // We know what we are doing...
	private JSONArray arrayToJsonArray(ArrayList<Object> arr)
	{
		JSONArray json = new JSONArray();
		for(int i = 0; i < arr.size(); i++)
		{
			Object value = arr.get(i);

			if(value instanceof HashMap)
				json.put(mapToJsonObject((HashMap<String,Object>)value));
			else if(value instanceof ArrayList)
				json.put(arrayToJsonArray((ArrayList<Object>)value));
			else
				json.put(value);
		}
		return json;
	}

	/**
	 * Create JSON object from a hashmap
	 * @param map hashmap to convert
	 * @return JSONObject representation of the hashamp
	 */
	@SuppressWarnings("unchecked") // We know what we are doing...
	private JSONObject mapToJsonObject(HashMap<String,Object> map)
	{
		JSONObject json = new JSONObject();
		for(Map.Entry<String, Object> entry : map.entrySet())
		{
			String key = entry.getKey();
			Object value = entry.getValue();

			if(value instanceof HashMap)
				json.put(key, mapToJsonObject((HashMap<String,Object>)value));
			else if(value instanceof ArrayList)
				json.put(key, arrayToJsonArray((ArrayList<Object>)value));
			else
				json.put(key, value);
		}
		return json;
	}

	/**
	 * Save a single project to file
	 * @param project BuildCoProject object to save
	 * @return True if success, false on failure
	 */
	public boolean saveProject(BuildCoProject project)
	{
		// Because we're working with a single JSON file, we actually have to save all projects...
		// If this was an actual database, we would only need to save 1 project
		// However, we don't want to save any object unless we're explicitly told so.
		// Therefore, we must read the project file again, and only add/replace the project given.
		ArrayList<BuildCoProject> projects;
		if(exists())
		{
			projects = load();
			for(int i = 0; i < projects.size(); i++)
			{
				if(projects.get(i).getId() == project.getId())
				{
					projects.remove(i);
					break;
				}
			}
		}
		else
		{
			projects = new ArrayList<>();
		}

		// Add new project
		projects.add(project);

		// Create JSON object of the projects
		JSONArray jsonProjectsArray = new JSONArray();
		for(int i = 0; i < projects.size(); i++)
		{
			JSONObject jsonProject = mapToJsonObject(project.serialise());
			jsonProjectsArray.put(jsonProject);
		}

		JSONObject json = new JSONObject();
		json.put("projects", jsonProjectsArray);

		return writeFile(json.toString(), sourceFile);
	}
}
