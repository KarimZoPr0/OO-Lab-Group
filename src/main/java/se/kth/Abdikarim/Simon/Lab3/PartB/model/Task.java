package se.kth.Abdikarim.Simon.Lab3.PartB.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Task to be used for projects. The user can create a task and store them in a project. API is provided
 */
public class Task implements Comparable<Task>, Serializable, Cloneable
{
    private final String description;
    private String takenBy;
    private final int id;
    private TaskState state;
    private LocalDate lastUpdate;
    private TaskPrio prio;

    /**
     * Package private visibility - only visible to other classes in package model
     * @param description
     * @param prio
     * @param takenBy
     * @param state
     * @param id
     */
    Task( String description, TaskPrio prio , String takenBy,TaskState state, int id)
    {
        this.description = description;
        this.prio = prio;
        this.id = id;
        this.takenBy = takenBy;
        this.state = state;
        this.lastUpdate = LocalDate.now();
    }

    // Package private visibility - only visible to other classes in package model
    Task()
    {
        this("New Task", TaskPrio.Low,null,TaskState.TO_DO, 0);
    }

    /**
     * Retrieves the description of the task
     *
     * @return the current task description
     */
    public String getDescription( )
    {
        return description;
    }

    /**
     * Retrieves the task owner of the task
     *
     * @return takenBy of the task
     */
    public String getTakenBy( )
    {
        return takenBy;
    }

    /**
     * Retrieves the current id of the task
     *
     * @return the current task id
     */
    public int getId( )
    {
        return id;
    }

    /**
     * Retrieves the current state of the task
     *
     * @return the current task state
     */
    public TaskState getState( )
    {
        return state;
    }

    /**
     * Retrieves the date of lastUpdate
     *
     * @return the date when task was last updated
     */
    public LocalDate getLastUpdate( )
    {
        return lastUpdate;
    }

    /**
     * Retrieves the priority of task
     *
     * @return the task priority
     */
    public TaskPrio getPrio( )
    {
        return prio;
    }

    /**
     * Sets task owner and updates lastUpdate to current date.
     *
     * @param takenBy the new task owner
     */
    public void setTakenBy(String takenBy)
    {
        if(this.takenBy != null) throw new IllegalStateException("Already Takenby");
        this.takenBy = takenBy;
        this.lastUpdate = LocalDate.now();
    }

    /**
     * Sets task state and updates lastUpdate to current date.
     *
     * @param state the new state for the task
     */
    public void setState(TaskState state)
    {
        this.state = state;
        this.lastUpdate = LocalDate.now();
    }

    /**
     * Sets task prio and updates lastUpdate to current date.
     *
     * @param prio the new prio for the task
     */
    public void setPrio( TaskPrio prio )
    {
        this.prio = prio;
        this.lastUpdate = LocalDate.now();
    }

    /**
     * @param obj
     * Compare if obj is a instance of Task
     * Considered equal if prio and description is the same
     * @return other equals 0 else return false
     */
    @Override
    public boolean equals( Object obj )
    {
        if( obj instanceof Task other )
        {
            return this.compareTo( other ) == 0;
        }
        return false;
    }

    /**
     * @param o the object to be compared.
     * Compare two priorities and sets an int value
     * If value equals 0 compare description
     * @return cmp
     */
    @Override
    public int compareTo( Task o )
    {
        int cmp = prio.ordinal() - o.prio.ordinal();
        if(cmp == 0)
        {
            cmp = description.compareTo( o.description );
        }
        return cmp;
    }

    /**
     * @return a String with information about the task
     */
    @Override
    public String toString( )
    {
        return "Description: " + description +
                ", id: " + id +
                ", takenBy: " + takenBy +
                ", state: " + state +
                ", lastUpdate: " + lastUpdate +
                ", prio: " + prio + "\n";
    }

    /**
     * Creates a new arraylist with new references.
     *
     * @return a deep copy of a arrayList
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone( ) throws CloneNotSupportedException
    {
        return super.clone( );
    }
}