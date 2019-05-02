// BuildCo Inc. Project Management System

import java.util.HashMap;

public class BuildCoWorkerPlumber extends BuildCoWorker
{
	private double plumbingCost;

	public BuildCoWorkerPlumber(String firstName, String lastName, BuildCoAddress address, int id, double hoursWorked, double hourlyRate)
	{
		super(firstName, lastName, address, id, hoursWorked, hourlyRate);
	}

	/**
	 * Construct object from a hashmap
	 * @param map Hashmap to build object from
	 */
	public BuildCoWorkerPlumber(HashMap<String,Object> map) throws Exception
	{
		super(map);
		deserialise(map);
	}

	/**
	 * Set wiring cost
	 * @param plumbingCost The cost of wires
	 */
	public void setPlumbingCost(double plumbingCost)
	{
		this.plumbingCost = plumbingCost;
	}

	/**
	 * Get plumbing cost
	 * @return Cost of plumbing supplies
	 */
	public double getPlumbingCost()
	{
		return plumbingCost;
	}

	/**
	 * Get type
	 * @return Worker type
	 */
	public String getType()
	{
		return "Plumber";
	}

	/**
	 * Do work
	 * @return Work done
	 */
	public String doWork()
	{
		return "Had a leak";
	}

	/**
	 * Generate worker report
	 * @return Report as a string
	 */
	public String generateReport()
	{
		String report = super.generateReport();
		report += "Plumbing cost: $"+getPlumbingCost()+"\n";
		return report;
	}

	/**
	 * Calculate total cost
	 */
	public double calculateCost()
	{
		return calculatePay() + getPlumbingCost();
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

		// Get plumbing cost
		Object plumbingCost = h.get("plumbingcost");
		if(plumbingCost instanceof Double)
			setPlumbingCost((Double)plumbingCost);
		if(plumbingCost instanceof Integer)
			setPlumbingCost((Integer)plumbingCost);
		else
			throw new Exception("worker.plumbingcost has incompatible type");
	}


	/**
	 * Converts all data from this object into a hashmap of serialised data
	 * @return Hashmap of serialised objects
	 */
	public HashMap<String,Object> serialise()
	{
		HashMap<String,Object> h = super.serialise();
		h.put("plumbingcost", getPlumbingCost());
		return h;
	}
}
