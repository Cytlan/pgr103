// BuildCo Inc. Project Management System

import java.util.HashMap;

/**
 * Represents a worker
 */
public class BuildCoWorker implements BuildCoDataObject
{
	private String firstName;
	private String lastName;
	private BuildCoAddress address;
	private int id;
	private double hoursWorked;
	private double hourlyRate;

	public BuildCoWorker(String firstName, String lastName, BuildCoAddress address, int id, double hoursWorked, double hourlyRate)
	{
		setFirstName(firstName);
		setLastName(lastName);
		setAddress(address);
		setId(id);
		setHoursWorked(hoursWorked);
		setHourlyRate(hourlyRate);
	}

	/**
	 * Construct object from a hashmap
	 * @param map Hashmap to build object from
	 */
	public BuildCoWorker(HashMap<String,Object> map) throws Exception
	{
		deserialise(map);
	}

	/**
	 * Get first name
	 * @return First name
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * Set first name
	 * @param firstName First name
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * Get last name
	 * @return Last name
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * Set last name
	 * @param lastName Last name
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * Get address
	 * @return Address
	 */
	public BuildCoAddress getAddress()
	{
		return address;
	}

	/**
	 * Set address
	 * @param address Address
	 */
	public void setAddress(BuildCoAddress address)
	{
		this.address = address;
	}

	/**
	 * Get ID
	 * @return ID
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * Set ID
	 * @param id ID
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * Get hours worked
	 * @return Hours worked
	 */
	public double getHoursWorked()
	{
		return hoursWorked;
	}

	/**
	 * Set hours worked
	 * @param hoursWorked Hours worked
	 */
	public void setHoursWorked(double hoursWorked)
	{
		this.hoursWorked = hoursWorked;
	}

	/**
	 * Get hourly rate
	 * @return Hourly rate
	 */
	public double getHourlyRate()
	{
		return hourlyRate;
	}

	/**
	 * Set hourly rate
	 * @param hourlyRate Hourly rate
	 */
	public void setHourlyRate(double hourlyRate)
	{
		this.hourlyRate = hourlyRate;
	}

	/**
	 * Get type
	 * @return Worker type
	 */
	public String getType()
	{
		return "Worker";
	}

	/**
	 * Do work
	 * @return Work done
	 */
	public String doWork()
	{
		return "Played GameBoy";
	}

	/**
	 * Calculate worker pay
	 */
	public double calculatePay()
	{
		return (hourlyRate * hoursWorked);
	}

	/**
	 * Calculate total cost
	 */
	public double calculateCost()
	{
		return calculatePay();
	}

	/**
	 * Generate worker report
	 * @return Report as a string
	 */
	public String generateReport()
	{
		String report = getType()+": "+getFirstName()+" "+getLastName()+"\n";
		report += "Compensation: $"+calculatePay()+"\n";
		report += doWork()+"\n";
		return report;
	}

	/**
	 * Converts all data from this object into a hashmap of serialised data
	 * @return Hashmap of serialised objects
	 */
	public HashMap<String,Object> serialise()
	{
		HashMap<String,Object> h = new HashMap<>();
		h.put("id", getId());
		h.put("firstname", getFirstName());
		h.put("lastname", getLastName());
		h.put("address", getAddress().serialise());
		h.put("hoursworked", getHoursWorked());
		h.put("hourlyrate", getHourlyRate());
		h.put("type", getType());
		return h;
	}

	/**
	 * Loads the object with data from a hashmap of serialised data
	 * @param h Hashmap
	 * @return Hashmap
	 */
	@SuppressWarnings("unchecked") // We know what we are doing...
	public void deserialise(HashMap<String,Object> h) throws Exception
	{
		// Get id
		Object id = h.get("id");
		if(id instanceof Integer)
			setId((Integer)id);
		else
			throw new Exception("worker.id has incompatible type");

		// Get first name
		Object firstName = h.get("firstname");
		if(firstName instanceof String)
			setFirstName((String)firstName);
		else
			throw new Exception("worker.firstname has incompatible type");

		// Get last name
		Object lastName = h.get("lastname");
		if(lastName instanceof String)
			setLastName((String)lastName);
		else
			throw new Exception("worker.lastname has incompatible type");

		// Get address
		Object address = h.get("address");
		if(address instanceof HashMap)
			setAddress(new BuildCoAddress((HashMap<String,Object>)address));
		else
			throw new Exception("worker.address has incompatible type");

		// Get hours worked
		Object hoursWorked = h.get("hoursworked");
		if(hoursWorked instanceof Double)
			setHoursWorked((Double)hoursWorked);
		else if(hoursWorked instanceof Integer)
			setHoursWorked((Integer)hoursWorked);
		else
			throw new Exception("worker.hoursworked has incompatible type");

		// Get hourly rate
		Object hourlyRate = h.get("hoursworked");
		if(hourlyRate instanceof Double)
			setHourlyRate((Double)hourlyRate);
		else if(hourlyRate instanceof Integer)
			setHourlyRate((Integer)hourlyRate);
		else
			throw new Exception("worker.hoursworked has incompatible type");
	}

	/**
	 * Deserialise worker and create object of the appropriate type
	 * @param h Hashmap
	 * @return BuildCoWorker object
	 */
	public static BuildCoWorker deserialiseWorker(HashMap<String,Object> h) throws Exception
	{
		Object type = h.get("type");
		if(type instanceof String)
		{
			String t = ((String)type).toLowerCase();
			switch(t)
			{
				case "worker":
					return new BuildCoWorker(h);

				case "carpenter":
					return new BuildCoWorkerCarpenter(h);

				case "electrician":
					return new BuildCoWorkerElectrician(h);

				case "plumber":
					return new BuildCoWorkerPlumber(h);

				default:
					throw new Exception("Unknown worker type: "+t);
			}
		}
		else
			return new BuildCoWorker(h);
	}

	/**
	 * Get payroll
	 */
	public String getPayroll()
	{
		String payroll = "";
		payroll += "ID number: "+getId()+"\n";
		payroll += "Address: "+getAddress()+"\n";
		payroll += "Hourly Rate: "+getHourlyRate()+"\n";
		payroll += "Hours worked: "+getHoursWorked()+"\n";
		return payroll;
	}
}
