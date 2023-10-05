package se.kth.Abdikarim.Simon.Lab3.PartB.model;

import se.kth.Abdikarim.Simon.Lab3.PartB.io.ProjectsFileIO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProjectMain
{
    public static void main( String[] args )
    {

        var tasks = new ArrayList<Task>(  );

        var p1 = new Project(tasks, "Project 1", "My Description", 0);
        p1.addTask( "Tja", TaskPrio.High );
        p1.addTask( "Yoo", TaskPrio.Medium );
        p1.addTask( "Hej", TaskPrio.Low );
        p1.addTask( "Hola", TaskPrio.Low );
        p1.addTask( "Bonjour", TaskPrio.Low );
        p1.addTask( "Konichiwa", TaskPrio.Low );



        var projects = new ArrayList<Project>(  );
        projects.add( p1 );

        var pm = new ProjectManager();
        pm.setProjects( projects );

        List< Project > getList = null;
        getList = pm.getProjects();

        Project p1Cloned = null;
        p1Cloned = pm.getProjectById( 0 );
        p1Cloned.addTask( "New Task", TaskPrio.High );
        System.out.println( p1 );


        try
        {
            ProjectsFileIO.serializeToFile( new File( "projects.ser" ),  projects );
        } catch ( IOException e )
        {
            throw new RuntimeException( e );
        }
    }
}
