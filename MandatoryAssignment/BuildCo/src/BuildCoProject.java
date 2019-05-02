// BuildCo Inc. Project Management System

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a project, consisting of work and workers
 */
public class BuildCoProject implements BuildCoDataObject
{
	private int id;
	private String name;
	private String customer;
	private BuildCoAddress address;
	private double overheadPercent;
	private LocalDate startDate;
	private LocalDate endDate;

	private ArrayList<BuildCoWorker> workers = new ArrayList<>();

	/**
	 * Construct object from arguments
	 */
	public BuildCoProject(int id, String name, String customer, BuildCoAddress address, LocalDate startDate, LocalDate endDate, double overheadPercent)
	{
		setId(id);
		setName(name);
		setCustomer(customer);
		setAddress(address);
		setStartDate(startDate);
		setEndDate(endDate);
		setOverhead(overheadPercent);
	}

	/**
	 * Construct object from a hashmap
	 * @param map Hashmap to build object from
	 */
	public BuildCoProject(HashMap<String,Object> map) throws Exception
	{
		deserialise(map);
	}

	/**
	 * Add a worker to the project
	 */
	public void addWorker(BuildCoWorker worker)
	{
		workers.add(worker);
	}

	/**
	 * Set project ID
	 * @param id Project ID
	 */
	public void setId(int id)
	{
		this.id= id;
	}

	/**
	 * Get project ID
	 * @return Project name
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * Set project name
	 * @param name Project name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Get project name
	 * @return Project name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Set customer
	 * @param customer Customer's name
	 */
	public void setCustomer(String customer)
	{
		this.customer = customer;
	}

	/**
	 * Get customer
	 * @return Customer's name
	 */
	public String getCustomer()
	{
		return customer;
	}

	/**
	 * Set start date
	 * @param startDate Start date
	 */
	public void setStartDate(LocalDate startDate)
	{
		this.startDate = startDate;
	}

	/**
	 * Get start date
	 * @return Start date
	 */
	public LocalDate getStartDate()
	{
		return startDate;
	}

	/**
	 * Set end date
	 * @param endDate End date
	 */
	public void setEndDate(LocalDate endDate)
	{
		this.endDate = endDate;
	}

	/**
	 * Get end date
	 * @return End date
	 */
	public LocalDate getEndDate()
	{
		return endDate;
	}

	/**
	 * Get project overhead
	 * @return Overhead
	 */
	public double getOverhead()
	{
		return overheadPercent;
	}

	/**
	 * Set project overhead
	 */
	public void setOverhead(double overheadPercent)
	{
		this.overheadPercent = overheadPercent;
	}

	/**
	 * Get project address
	 * @return Address
	 */
	public BuildCoAddress getAddress()
	{
		return address;
	}

	/**
	 * Set project address
	 */
	public void setAddress(BuildCoAddress address)
	{
		this.address = address;
	}

	/**
	 * Generate a project info string
	 * @return String
	 */
	public String generateProjectInfo()
	{
		String info = "";
		info += "Project ID: "+getId()+"\n";
		info += "Project name: "+getName()+"\n";
		info += "Start Date: "+getStartDate().format(DateTimeFormatter.ISO_LOCAL_DATE)+"\n";
		info += "Customer: "+getCustomer()+"\n";
		info += "Address: "+getAddress();
		return info;
	}

	/**
	 * Generate a project report of all the associated costs
	 */
	public String generateProjectReport()
	{
		String report = "";
		for(int i = 0; i < workers.size(); i++)
		{
			report += workers.get(i).generateReport()+"\n";
		}
		return report;
	}

	/**
	 * Calculate total cost of the project
	 * @return Total cost as a double
	 */
	public double calculateCost()
	{
		double cost = 0;
		for(int i = 0; i < workers.size(); i++)
		{
			cost += workers.get(i).calculateCost();
		}
		return cost;
	}

	/**
	 * Calculate total contractor overhead of the project
	 * @return Total overhead as a double
	 */
	public double calculateOverhead()
	{
		return calculateCost() * getOverhead();
	}

	/**
	 * Converts all data from this object into a hashmap of serialised data
	 * @return Hashmap of serialised objects
	 */
	public HashMap<String,Object> serialise()
	{
		HashMap<String,Object> h = new HashMap<>();
		h.put("id", getId());
		h.put("name", getName());
		h.put("customer", getCustomer());
		h.put("startdate", getStartDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
		h.put("enddate", getEndDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
		h.put("overhead", String.valueOf(getOverhead()));
		h.put("address", getAddress().serialise());

		// Workers
		ArrayList<Object> serialisedWorkers = new ArrayList<>();
		for(int i = 0; i < workers.size(); i++)
		{
			serialisedWorkers.add(workers.get(i).serialise());
		}
		h.put("workers", serialisedWorkers);

		return h;
	}

	/**
	 * Loads the object with data from a hashmap of serialised data
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
			throw new Exception("project.id has incompatible type");

		// Get name
		Object name = h.get("name");
		if(name instanceof String)
			setName((String)name);
		else
			throw new Exception("project.name has incompatible type");

		// Get customer
		Object customer = h.get("customer");
		if(customer instanceof String)
			setCustomer((String)customer);
		else
			throw new Exception("project.customer has incompatible type");

		// Get start date
		Object startDate = h.get("startdate");
		if(startDate instanceof String)
			setStartDate(LocalDate.parse((String)startDate));
		else
			throw new Exception("project.startdate has incompatible type");

		// Get end date
		Object endDate = h.get("enddate");
		if(endDate  instanceof String)
			setEndDate(LocalDate.parse((String)endDate));
		else
			throw new Exception("project.enddate has incompatible type");

		// Get overhead
		Object overhead = h.get("overhead");
		if(overhead instanceof String)
			setOverhead(Double.parseDouble((String)overhead));
		else
			throw new Exception("project.overhead has incompatible type");

		// Get address
		Object address = h.get("address");
		if(address instanceof HashMap)
			setAddress(new BuildCoAddress((HashMap<String,Object>)address));
		else
			throw new Exception("project.address has incompatible type");

		// Workers
		Object workers = h.get("workers");
		if(workers instanceof ArrayList)
		{
			ArrayList<Object> workersArr = (ArrayList<Object>)workers;
			for(int i = 0; i < workersArr.size(); i++)
			{
				Object worker = workersArr.get(i);
				if(worker instanceof HashMap)
					addWorker(BuildCoWorker.deserialiseWorker((HashMap<String,Object>)worker));
				else
					throw new Exception("project.worker["+i+"] has incompatible type");
			}
		}
		else
			throw new Exception("project.workers has incompatible type");

	}
}
