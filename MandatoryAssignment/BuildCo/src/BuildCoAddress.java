// BuildCo Inc. Project Management System

import java.util.HashMap;

public class BuildCoAddress implements BuildCoDataObject
{
	private String street;
	private String city;
	private String postcode;
	private String state;

	/**
	 * Default constructor
	 */
	public BuildCoAddress()
	{
	}

	/**
	 * Construct object from arguments
	 */
	public BuildCoAddress(String street, String city, String postcode, String state)
	{
		setStreet(street);
		setCity(city);
		setPostcode(postcode);
		setState(state);
	}

	/**
	 * Construct object from a hashmap
	 */
	public BuildCoAddress(HashMap<String,Object> map) throws Exception
	{
		deserialise(map);
	}

	/**
	 * Get street
	 * @return Street name
	 */
	public String getStreet()
	{
		return street;
	}

	/**
	 * Set street
	 * @param street Street name
	 */
	public void setStreet(String street)
	{
		this.street = street;
	}

	/**
	 * Get city
	 * @return City name
	 */
	public String getCity()
	{
		return city;
	}

	/**
	 * Set city
	 * @param city City name
	 */
	public void setCity(String city)
	{
		this.city = city;
	}

	/**
	 * Get postcode
	 * @return Postcode
	 */
	public String getPostcode()
	{
		return postcode;
	}

	/**
	 * Set postcode
	 * @param postcode Postcode
	 */
	public void setPostcode(String postcode)
	{
		this.postcode = postcode;
	}

	/**
	 * Get state
	 * @return State name
	 */
	public String getState()
	{
		return state;
	}

	/**
	 * Set state
	 * @param state State name
	 */
	public void setState(String state)
	{
		this.state = state;
	}

	/**
	 * Converts all data from this object into a hashmap of strings
	 * @return HashMap of all the serialised data
	 */
	public HashMap<String,Object> serialise()
	{
		HashMap<String,Object> h = new HashMap<>();
		h.put("street", getStreet());
		h.put("city", getCity());
		h.put("postcode", getPostcode());
		h.put("state", getState());
		return h;
	}

	/**
	 * Loads the object with data from a hashmap of strings
	 * @param h HashMap with serialised data to deserialise
	 */
	public void deserialise(HashMap<String,Object> h) throws Exception
	{
		// Get street
		Object street = h.get("street");
		if(street instanceof String)
			setStreet((String)street);
		else
			throw new Exception("address.street has incompatible type");

		// Get city
		Object city = h.get("city");
		if(city instanceof String)
			setCity((String)city);
		else
			throw new Exception("address.city has incompatible type");

		// Get postcode
		Object postcode = h.get("postcode");
		if(postcode instanceof String)
			setPostcode((String)postcode);
		else
			throw new Exception("address.postcode has incompatible type");

		// Get name
		Object state = h.get("state");
		if(state instanceof String)
			setState((String)state);
		else
			throw new Exception("address.state has incompatible type");
	}

	/**
	 * String representation of the address
	 * @return Entire address on 1 line, separated by comma
	 */
	public String toString()
	{
		return getStreet()+", "+getPostcode()+" "+getCity()+", "+getState();
	}
}
