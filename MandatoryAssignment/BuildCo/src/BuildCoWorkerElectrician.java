// BuildCo Inc. Project Management System

import java.util.HashMap;

public class BuildCoWorkerElectrician extends BuildCoWorker
{
	private double wiringCost;

	public BuildCoWorkerElectrician(String firstName, String lastName, BuildCoAddress address, int id, double hoursWorked, double hourlyRate)
	{
		super(firstName, lastName, address, id, hoursWorked, hourlyRate);
	}

	/**
	 * Construct object from a hashmap
	 * @param map Hashmap to build object from
	 */
	public BuildCoWorkerElectrician(HashMap<String,Object> map) throws Exception
	{
		super(map);
		deserialise(map);
	}

	/**
	 * Set wiring cost
	 * @param wiringCost The cost of wires
	 */
	public void setWiringCost(double wiringCost)
	{
		this.wiringCost = wiringCost;
	}

	/**
	 * Get lumber cost
	 * @return Cost of lumber
	 */
	public double getWiringCost()
	{
		return wiringCost;
	}

	/**
	 * Get type
	 * @return Worker type
	 */
	public String getType()
	{
		return "Electrician";
	}

	/**
	 * Do work
	 * @return Work done
	 */
	public String doWork()
	{
		return "Zapped something";
	}

	/**
	 * Generate worker report
	 * @return Report as a string
	 */
	public String generateReport()
	{
		String report = super.generateReport();
		report += "Wiring cost: $"+getWiringCost()+"\n";
		return report;
	}

	/**
	 * Calculate total cost
	 */
	public double calculateCost()
	{
		return calculatePay() + getWiringCost();
	}

	/**
	 * Loads the object with data from a hashmap of serialised data
	 * @param h Hashmap
	 * @return Hashmap
	 */
	@SuppressWarnings("unchecked") // We know what we are doing...
	public void deserialise(HashMap<String,Object> h) throws Exception
	{
		// Let parent deserialise first
		super.deserialise(h);

		// Get wiring cost
		Object wiringCost = h.get("wiringcost");
		if(wiringCost instanceof Double)
			setWiringCost((Double)wiringCost);
		if(wiringCost instanceof Integer)
			setWiringCost((Integer)wiringCost);
		else
			throw new Exception("worker.wiringcost has incompatible type");
	}

	/**
	 * Converts all data from this object into a hashmap of serialised data
	 * @return Hashmap of serialised objects
	 */
	public HashMap<String,Object> serialise()
	{
		HashMap<String,Object> h = super.serialise();
		h.put("wiringcost", getWiringCost());
		return h;
	}
}
