package se.kth.Abdikarim.Simon.Lab3.PartB.model;

public class MainTask
{

    public static void main( String[] args )
    {
        Task task = new Task( "test", TaskPrio.Low,"Simon",TaskState.TO_DO, 1 );

        System.out.println(task);

        task.setPrio( TaskPrio.High );
        System.out.println(task );

        Task task2 = new Task("test", TaskPrio.Low, "Abdikarim",TaskState.TO_DO, 2);

        System.out.println(task2 );

        System.out.println(task.compareTo( task2 ));
    }
}
