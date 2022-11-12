package projects.service;

import java.util.List;
import java.util.NoSuchElementException;

import projects.dao.ProjectDao;
import projects.entity.Project;


public class ProjectService {
	
	private ProjectDao projectDao = new ProjectDao();
	
/*This method calls the DAO class to insert a row into the project.*/
	
	public Project addProject(Project project) {
		
		return projectDao.insertProject(project);
	}

/*This method calls the DAO class to get a list of all projects sorted by name.*/
	public List<Project> fetchAllProjects() {
		return projectDao.fetchAllProjects();
	}

/*This method calls the DAO class to get a single project designated by the project ID number.*/
	public Project fetchProjectById(Integer projectId) {
		return projectDao.fetchProjectById(projectId).orElseThrow(() -> 
		new NoSuchElementException("Project with ID = " + projectId + " does not exist."));
	}

}
