// BuildCo Inc. Project Management System

// -----------------------------------------------------------------------------
//
// BuildCo Inc. Project Management System
//
// File: BuildCoApp.java
// Desc: App class file
//
// Handles all app related functions
//
// -----------------------------------------------------------------------------

public class BuildCoApp
{
	/**
	 * Data manager - Allows us to read and write data objects
	 */
	private BuildCoData dataManager;

	/**
	 * Cached list of projects
	 */
	private BuildCoProject[] projects = null;

	/**
	 * Creates a new BuildCo app
	 * @param dataManager Data manager
	 */
	public BuildCoApp(BuildCoData dataManager)
	{
		this.dataManager = dataManager;
	}

	/**
	 * Get all projects
	 * @return Array of all projects
	 */
	public BuildCoProject[] getProjects()
	{
		// If we have a cached project list, return that
		if(projects != null)
			return projects;

		// Otherwise get projects from the data manager
		projects = dataManager.getProjects();
		return projects;
	}

	/**
	 * Run the main application
	 */
	public void run()
	{
		// Load projects
		if(projects == null)
			getProjects();

		// Print the list of all projects
		for(int i = 0; i < projects.length; i++)
		{
			System.out.println("*************************************");
			System.out.println(projects[i].generateProjectInfo());
			System.out.println("=====================================");
			System.out.print(projects[i].generateProjectReport());

			double totalCost = projects[i].calculateCost();
			double overhead = projects[i].calculateOverhead();
			System.out.println("Project Total: $"+totalCost);
			System.out.println("Contractor Overhead: $"+overhead);
			System.out.println("*************************************");
		}
	}
}
