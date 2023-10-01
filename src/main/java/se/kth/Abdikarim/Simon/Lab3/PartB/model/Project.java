package se.kth.Abdikarim.Simon.Lab3.PartB.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Project implements Comparable<Project>, Serializable, Cloneable
{
    private List<Task> tasks;
    private final String title;
    private final String description;
    private final int id;
    private int nextTaskId;
    private final LocalDate localDate;

    Project( List<Task> tasks , String title, String description, int id )
    {
        this.tasks = tasks;
        this.title = title;
        this.description = description;
        this.id = id;
        this.nextTaskId = 0;
        localDate = LocalDate.now();
    }

    Project()
    {
        this(new ArrayList<>(  ),"My Project", "Some tasks", 0);
    }

    public List<Task> findTasks(ItaskMatcher matcher)
    {
        return tasks.stream().filter( matcher::match ).toList();
    }

    public Task addTask(String descr, TaskPrio prio)
    {
        Task task = new Task( descr, prio,null, TaskState.TO_DO, nextTaskId++ );
        tasks.add( task );
        return task;
    }
    
    public boolean removeTask(Task task)
    {
        return tasks.remove( task );
    }

    public ProjectState getState()
    {
        if(tasks.isEmpty())
        {
            return ProjectState.EMPTY;
        }

        if(tasks.stream().allMatch( task -> task.getState() == TaskState.DONE ))
        {
            return ProjectState.COMPLETED;
        }

        return ProjectState.ONGOING;
    }
    
    public LocalDate getLastUpdated()
    {
        if(tasks.isEmpty()) return localDate;
        return tasks.get( nextTaskId ).getLastUpdate();
    }

    public List< Task > getTasks( ) throws CloneNotSupportedException
    {
        var copiedTasks = new ArrayList<Task>(  );
        for ( Task task : tasks )
        {
            copiedTasks.add( (Task) task.clone() );
        }
        return copiedTasks;
    }

    public String getTitle( )
    {
        return title;
    }

    public String getDescription( )
    {
        return description;
    }

    public int getId( )
    {
        return id;
    }

    public int getNextTaskId( )
    {
        return nextTaskId;
    }

    public LocalDate getLocalDate( )
    {
        return localDate;
    }

    @Override
    public boolean equals( Object obj )
    {
        if(obj instanceof Project)
        {
            Project other = (Project ) obj;
            return this.compareTo( other ) == 0;
        }
        return false;
    }

    @Override
    public int compareTo( Project o )
    {
        return this.title.compareTo( o.title );
    }

    @Override
    public String toString( )
    {
        return  "tasks=" + tasks +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", nextTaskId=" + nextTaskId +
                ", LocalDate=" + localDate + "\n";
    }

    public Task getTaskById( int id )
    {
        return tasks.get( id );
    }

    @Override
    public Object clone( ) throws CloneNotSupportedException
    {
        Project cloned = (Project ) super.clone( );
        List<Task> clonedTasks = new ArrayList<>(  );

        for ( Task task : tasks )
        {
            clonedTasks.add( (Task) task.clone() );
        }

        cloned.tasks = clonedTasks;
        return cloned;
    }
}
