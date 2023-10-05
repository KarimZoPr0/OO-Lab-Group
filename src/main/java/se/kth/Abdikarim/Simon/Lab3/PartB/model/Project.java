package se.kth.Abdikarim.Simon.Lab3.PartB.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Project contains a collections of tasks and provides an API to modify the date
 */
public class Project implements Comparable<Project>, Serializable, Cloneable
{
    private List<Task> tasks;
    private final String title;
    private final String description;
    private final int id;
    private int nextTaskId;
    private final LocalDate localDate;

    /**
     * Package private visibility - only visible to other classes in package model
     * @param tasks
     * @param title
     * @param description
     * @param id
     */
    Project( List<Task> tasks , String title, String description, int id )
    {
        this.tasks = tasks;
        this.title = title;
        this.description = description;
        this.id = id;
        this.nextTaskId = 0;
        localDate = LocalDate.now();
    }

    /**
     * Package private visibility - only visible to other classes in package model
     */
    Project()
    {
        this(new ArrayList<>(  ),"My Project", "Some tasks", 0);
    }

    /**
     * Finds tasks that matches the strategy matcher
     *
     * @param matcher the strategy used to filter
     * @return The tasks that was filtered based on matcher
     */
    public List<Task> findTasks(ItaskMatcher matcher)
    {
        return tasks.stream().filter( matcher::match ).toList();
    }

    /**
     * Adds a new task with description and priority
     *
     * @param descr the task description
     * @param prio the priority of the task
     * @return the new task
     */
    public Task addTask(String descr, TaskPrio prio)
    {
        Task task = new Task( descr, prio,null, TaskState.TO_DO, nextTaskId++ );
        tasks.add( task );
        return task;
    }

    /**
     * Removes given task from the task list
     *
     *
     * @param task the task to remove
     * @return true if removed else false
     */
    public boolean removeTask(Task task)
    {
        return tasks.remove( task );
    }

    /**
     * Retrieves the project state based on task state
     *
     * @return Empty if task list is empty, Completed if all is DONE, else ONGOING
     */
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

    /**
     * The date when task was last updated
     *
     * @return The localDate if task is empty, else date of latest task last update
     */
    public LocalDate getLastUpdated()
    {
        if(tasks.isEmpty()) return localDate;
        return tasks.get( nextTaskId ).getLastUpdate();
    }

    /**
     * Create a variable ArrayList
     * Copy the task using clone method for deep copy
     *
     * @return a deep copy of a list
     */
    public List< Task > getTasks( )
    {
        var copiedTasks = new ArrayList<Task>(  );
        for ( Task task : tasks )
        {
            copiedTasks.add( new Task( task.getDescription( ), task.getPrio( ), task.getTakenBy( ), task.getState( ), task.getId( ) ) );
        }
        return copiedTasks;
    }

    public String getTitle( )
    {
        return title;
    }

    /**
     * Retrieves a project description
     *
     * @return description
     */
    public String getDescription( )
    {
        return description;
    }

    /**
     * Retrieves the project id
     *
     * @return id
     */
    public int getId( )
    {
        return id;
    }

    /**
     * Retrieves the nextTaskId
     *
     * @return nextTaskId
     */
    public int getNextTaskId( )
    {
        return nextTaskId;
    }

    public LocalDate getLocalDate( )
    {
        return localDate;
    }
    /**
     * @param obj the object to be compared.
     * Compare if obj is a subclass for Project
     * If value equals 0 compare project
     * @return cmp
     */
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

    /**
     * @return a String with information about the task
     */

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

    /**
     * Retrieves the task by id
     *
     * @param id
     * @return the task with the id
     */
    public Task getTaskById( int id )
    {
        return tasks.get( id );
    }

    /**
     * Create a arrayList and new references for task
     * Add it to a new arrayList
     *
     * @return a deep copy of a list
     */
    @Override
    public Object clone( )
    {
        try
        {
            Project cloned = (Project ) super.clone( );
            List<Task> clonedTasks = new ArrayList<>(  );

            for ( Task task : tasks )
            {
                clonedTasks.add( (Task) task.clone() );
            }

            cloned.tasks = clonedTasks;
            return cloned;
        } catch ( CloneNotSupportedException e )
        {
            throw new RuntimeException( e );
        }
    }
}
