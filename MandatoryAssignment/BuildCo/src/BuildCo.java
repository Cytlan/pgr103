// BuildCo Inc. Project Management System

import java.time.LocalDate;

/**
 * BuildCo Inc. Project Management System
 * Main entry class
 */
public class BuildCo
{
	/**
	 * BuildCo Application class
	 */
	static BuildCoApp app;

	/**
	 * Data manager
	 */
	static BuildCoData dataManager;

	/**
	 * Create a default JSON data source
	 */
	public static void createJsonSource(BuildCoDataJson jsonSource)
	{
		System.out.println("JSON data source does not exist. Creating the default one");

		// Make project
		BuildCoProject project1 = new BuildCoProject(
				1,
				"House",
				"Shire Gotshalk",
				new BuildCoAddress("123 Main Street", "Anywhere", "19001", "PA"),
				LocalDate.parse("2019-11-03"),
				LocalDate.parse("2020-05-29"),
				0.18
		);

		// Make workers
		BuildCoWorkerElectrician electrician = new BuildCoWorkerElectrician(
				"Peg",
				"Fisher",
				new BuildCoAddress("467 Seminole Avenue", "Jenkintown", "19446", "PA"),
				1234,
				20,
				20
		);

		BuildCoWorkerCarpenter carpenter = new BuildCoWorkerCarpenter(
				"Yusef",
				"Eberly",
				new BuildCoAddress("88 Stallion Circle", "Horsham", "19022", "PA"),
				2456,
				30,
				17.5
		);

		BuildCoWorkerPlumber plumber = new BuildCoWorkerPlumber(
				"Harley",
				"Davidson",
				new BuildCoAddress("9821 Apt B", "Siglerville", "19345", "PA"),
				3214,
				20,
				25
		);

		// Set custom costs
		electrician.setWiringCost(3200);
		carpenter.setLumberCost(2000);
		plumber.setPlumbingCost(2750);

		// Add workers to project
		project1.addWorker(electrician);
		project1.addWorker(carpenter);
		project1.addWorker(plumber);

		// Save default project
		jsonSource.saveProject(project1);
	}

	/**
	 * Main
	 * <p>
	 * Creates a new app class and hands over control
	 * </p>
	 */
	public static void main(String[] args)
	{
		System.out.println("Loading BuildCo Project Manager");

		// Create JSON data handler
		BuildCoDataJson jsonSource = new BuildCoDataJson();
		dataManager = jsonSource;

		// Set the data source
		jsonSource.setSource("data.json");

		// If the source was not found, create a new one
		if(!jsonSource.exists())
		{
			createJsonSource(jsonSource);
		}

		// Create the app and load data
		app = new BuildCoApp(dataManager);

		// Let the app do its thing
		app.run();
	}
}
