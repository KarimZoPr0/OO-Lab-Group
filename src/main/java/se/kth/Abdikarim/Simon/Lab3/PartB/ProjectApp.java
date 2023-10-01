package se.kth.Abdikarim.Simon.Lab3.PartB;

import se.kth.Abdikarim.Simon.Lab3.PartB.io.ProjectsFileIO;
import se.kth.Abdikarim.Simon.Lab3.PartB.model.Project;
import se.kth.Abdikarim.Simon.Lab3.PartB.model.ProjectManager;
import se.kth.Abdikarim.Simon.Lab3.PartB.ui.MainUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;


public class ProjectApp {

    private static final String FILE_NAME = "projects.ser";

    public void run() throws Exception { // we do not catch all exceptions

        File projectsFile = new File(FILE_NAME);
        ProjectManager projectsManager = new ProjectManager();
        boolean couldReadFile = false;

        try {

            if (projectsFile.exists()) {
                List< Project > projects = ProjectsFileIO.deSerializeFromFile(projectsFile);
                projectsManager.setProjects(projects);
                couldReadFile = true;
            }

            MainUI ui = new MainUI(projectsManager);
            ui.mainLoop();

        } catch (FileNotFoundException | ClassNotFoundException e) {
            System.out.println("Could not load projects from file, please check the data file.");
            System.out.println("Continuing with empty manager.");
        }

        // run method about to exit - save data
        if(couldReadFile || !projectsFile.exists()) {
            List<Project> projectsToSave = projectsManager.getProjects();
            ProjectsFileIO.serializeToFile(projectsFile, projectsToSave);
        }
        System.out.println("Application exits");
    }

    public static void main(String[] args) throws Exception {

        ProjectApp app = new ProjectApp();
        app.run();
    }
}

