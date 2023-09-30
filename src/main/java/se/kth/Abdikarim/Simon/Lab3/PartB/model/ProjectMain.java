package se.kth.Abdikarim.Simon.Lab3.PartB.model;

import java.util.ArrayList;

public class ProjectMain
{
    public static void main( String[] args )
    {
        Task task = new Task( "Got A", TaskPrio.High,"Player", 0 );
        task.setState( TaskState.TO_DO );
        task.setPrio( TaskPrio.Low );

        Task task1 = new Task( "Got B", TaskPrio.High, "Simon", 0 );
        task1.setState( TaskState.TO_DO );
        task1.setPrio( TaskPrio.Medium );

        Task task2 = new Task( "Got C", TaskPrio.High, "Abdikarim", 0 );
        task2.setState( TaskState.TO_DO );
        task2.setPrio( TaskPrio.Low );

        var tasks = new ArrayList<Task>(  );
        tasks.add( task );
        tasks.add( task1 );
        tasks.add( task2 );

        var p1 = new Project(tasks, "My Title", "My Description", 0);

        var projects = new ArrayList<Project>(  );
        projects.add( p1 );

        ProjectManager pm = new ProjectManager();
        pm.setProjects( projects );

       // System.out.println( pm );

        for ( Task myPTask : p1.getTasks() )
        {
            System.out.println( myPTask );
        }

    }
}
