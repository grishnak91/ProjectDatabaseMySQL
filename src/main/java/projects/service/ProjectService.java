package projects.service;

import projects.dao.ProjectDao;
import projects.entity.Project;


public class ProjectService {
	
	private ProjectDao projectDao = new ProjectDao();
	
/*This method calls the DAO class to insert a row into the project.*/
	
	public Project addProject(Project project) {
		
		return projectDao.insertProject(project);
	}

}
