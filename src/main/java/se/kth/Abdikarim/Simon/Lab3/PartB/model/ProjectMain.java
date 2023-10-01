package se.kth.Abdikarim.Simon.Lab3.PartB.model;

import java.util.ArrayList;
import java.util.List;

- Simon
// TODO:
/*
1. Ta bort id parameter från Task klassen. Sätt id till newId. Tänk hur  getHighestId kan användas! - FRÅGAR ANDERS
2. Fortsätt lab3 -> UI, IO, etc. - DONE
3. Lägga till final - DONE
4. Javadoc
5. Testa och kombinera - DONE
6. Kontrollera att vi har följt instruktionerna korrekt.
 */

public class ProjectMain
{
    public static void main( String[] args )
    {

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
