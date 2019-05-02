// BuildCo Inc. Project Management System

/**
 * Interface for a BuildCo data source
 * This is responsible for loading and saving projects on demand
 * The data source can be anything: A database, plain text files, or JSON, depending on the implementation!
 */
public interface BuildCoData
{
	/**
	 * Get all projects
	 * @return Array of projects
	 */
	BuildCoProject[] getProjects();

	/**
	 * Get a single project based on its name
	 * @param projectName Name of the project
	 * @return Project if found, null otherwise
	 */
	BuildCoProject getProject(String projectName);

	/**
	 * Save a single project - Will add a new project if no project with that ID exists, or overwrite the existing one
	 * @param project Project to save
	 * @return True if successful, false otherwise
	 */
	boolean saveProject(BuildCoProject project);
}
