// BuildCo Inc. Project Management System

import java.util.HashMap;

public interface BuildCoDataObject
{
	/**
	 * Create a HashMap consisting of serialised data
	 * @return HashMap
	 */
	HashMap<String,Object> serialise();

	/**
	 * Reconstruct the object from a hashmap consisting of serialised data
	 * @param hashMap The hashmap to parse
	 */
	void deserialise(HashMap<String,Object> hashMap) throws Exception;
}
