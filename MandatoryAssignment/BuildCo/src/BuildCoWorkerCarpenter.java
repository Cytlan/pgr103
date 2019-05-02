// BuildCo Inc. Project Management System

import java.util.HashMap;

public class BuildCoWorkerCarpenter extends BuildCoWorker
{
	private double lumberCost;

	public BuildCoWorkerCarpenter(String firstName, String lastName, BuildCoAddress address, int id, double hoursWorked, double hourlyRate)
	{
		super(firstName, lastName, address, id, hoursWorked, hourlyRate);
	}

	/**
	 * Construct object from a hashmap
	 * @param map Hashmap to build object from
	 */
	public BuildCoWorkerCarpenter(HashMap<String,Object> map) throws Exception
	{
		super(map);
		deserialise(map);
	}

	/**
	 * Set lumber cost
	 * @param lumberCost The cost of lumber
	 */
	public void setLumberCost(double lumberCost)
	{
		this.lumberCost = lumberCost;
	}

	/**
	 * Get lumber cost
	 * @return Cost of lumber
	 */
	public double getLumberCost()
	{
		return lumberCost;
	}

	/**
	 * Get type
	 * @return Worker type
	 */
	public String getType()
	{
		return "Carpenter";
	}

	/**
	 * Do work
	 * @return Work done
	 */
	public String doWork()
	{
		return "Used a hammer";
	}

	/**
	 * Generate worker report
	 * @return Report as a string
	 */
	public String generateReport()
	{
		String report = super.generateReport();
		report += "Lumber cost: $"+getLumberCost()+"\n";
		return report;
	}

	/**
	 * Calculate total cost
	 */
	public double calculateCost()
	{
		return calculatePay() + getLumberCost();
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

		// Get lumber cost
		Object lumberCost = h.get("lumbercost");
		if(lumberCost  instanceof Double)
			setLumberCost((Double)lumberCost);
		if(lumberCost  instanceof Integer)
			setLumberCost((Integer)lumberCost);
		else
			throw new Exception("worker.lumbercost has incompatible type");
	}

	/**
	 * Converts all data from this object into a hashmap of serialised data
	 * @return Hashmap of serialised objects
	 */
	public HashMap<String,Object> serialise()
	{
		HashMap<String,Object> h = super.serialise();
		h.put("lumbercost", getLumberCost());
		return h;
	}
}
