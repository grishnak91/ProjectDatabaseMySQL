package projects;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import projects.entity.Project;
import projects.exception.DbException;
import projects.service.ProjectService;


public class Projects {

/*This class is the actual application that takes user input to build a project with CRUD 
 *operations.*/
	
	private Scanner scanner = new Scanner(System.in);
	private ProjectService projectService = new ProjectService();

/*This List is needed in order to display the menu options to the user.*/
	
	
	//@formatter: off
	private List<String> operations = List.of(
			"1) Add a project"
			);
	//@formatter: on

/*Start of menu application*/

	public static void main(String[] args) {
		new Projects().processUserSelection();

	}
	
/*This method takes user input and performs options that the user selects. It will
*loop until user requests to exit application.*/
	
	private void processUserSelection() {
		boolean done = false;
		
		while(!done) {
			try {
			int selection = getUserSelection();
			
					
			switch(selection) {
			case -1:
				done = exitMenu();
				break;
				
			case 1:
				createProject();
				break;
				
			default:
				System.out.println("\n" + selection + " is not valid. Try again.");
				break;
				}
			}
			catch(Exception e) {
				System.out.println("\nError: " + e.toString() + " Try again.");
			}
		}
		
	}

/*This method takes the user input and creates the parameters of a new project with
 *the entered data. Each entry by the user will be represented by a row in the database in mysql.*/
	
	private void createProject() {
		String projectName = getStringInput("Enter the project name");
		BigDecimal estimatedHours = getDecimalInput("Enter the estimated hours");
		BigDecimal actualHours = getDecimalInput("Enter the actual hours");
		Integer difficulty = getIntInput("Enter the project difficulty (1-5)");
		String notes = getStringInput("Enter the project notes");
		
		Project project = new Project();
		
		project.setProjectName(projectName);
		project.setEstimatedHours(estimatedHours);
		project.setActualHours(actualHours);
		project.setDifficulty(difficulty);
		project.setNotes(notes);
		
		Project dbProject = projectService.addProject(project);
		System.out.println("You have successfully created project: " + dbProject);
	
}
	
/*This method takes the user input and returns a BigDecimal.*/
	
	private BigDecimal getDecimalInput(String prompt) {
		String input = getStringInput(prompt);
		
		if(Objects.isNull(input)) {
			return null;
		}
		try {
			return new BigDecimal(input).setScale(2);
		}catch(NumberFormatException e) {
			throw new DbException(input + " is not a valid decimal number.");
		}
	}

/*This method is called when the user wants to exit the menu.*/
	
	private boolean exitMenu() {
		System.out.println("\nExiting the menu. Goodbye.");
		return true;
	}
	
/*This method prints the options available to the user for creating a project.*/
	
	private int getUserSelection() {
		printOperations();
		Integer input = getIntInput("Enter a menu selection");
		
		return Objects.isNull(input) ? -1 : input;
	}

/*This method prints a prompt and returns an integer from the user's input.*/

	private Integer getIntInput(String prompt) {
		String input = getStringInput(prompt);
		
		if(Objects.isNull(input)) {
			return null;
		}
		try {
			return Integer.valueOf(input);
		}catch(NumberFormatException e) {
			throw new DbException(input + " is not a valid number.");
		}
	}

/*This method gets input from the user and returns a string.*/
	
	private String getStringInput(String prompt) {
		System.out.print(prompt + ": ");
		String input = scanner.nextLine();
		
		return input.isBlank() ? null : input.trim();
	}

/*This method prints the menu selections.*/
	
	private void printOperations() {
		
		System.out.println("\nAvailable Selection. Press Enter to quit.");
		
		operations.forEach(line -> System.out.println("   " + line));
		
	}

}



