package se.kth.Abdikarim.Simon.Lab3.PartB.model;

import com.sun.prism.shader.AlphaOne_Color_AlphaTest_Loader;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Task implements Comparable<Task>, Serializable
{
    private final String description;
    private String takenBy;
    private final int id;
    private TaskState state;
    private LocalDate lastUpdate;
    private TaskPrio prio;

    Task( String description, TaskPrio prio , String takenBy,TaskState state, int id)
    {
        this.description = description;
        this.prio = prio;
        this.id = id;
        this.takenBy = takenBy;
        this.state = state;
        this.lastUpdate = LocalDate.now();
    }

    Task()
    {
        this("New Task", TaskPrio.Low,null,TaskState.TO_DO, 0);
    }

    public String getDescription( )
    {
        return description;
    }

    public String getTakenBy( )
    {
        return takenBy;
    }

    public int getId( )
    {
        return id;
    }

    public TaskState getState( )
    {
        return state;
    }

    public LocalDate getLastUpdate( )
    {
        return lastUpdate;
    }

    public TaskPrio getPrio( )
    {
        return prio;
    }

    public void setTakenby(String takenBy)
    {
        if(this.takenBy != null) throw new IllegalStateException("Already Takenby");
        this.takenBy = takenBy;
        this.lastUpdate = LocalDate.now();
    }

    public void setState(TaskState state)
    {
        this.state = state;
        this.lastUpdate = LocalDate.now();
    }

    public void setPrio( TaskPrio prio )
    {
        this.prio = prio;
        this.lastUpdate = LocalDate.now();
    }

    @Override
    public boolean equals( Object obj )
    {
        if( obj instanceof Task other )
        {
            return this.compareTo( other ) == 0;
        }
        return false;
    }

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

    @Override
    public String toString( )
    {
        return "Description: " + description +
                ", id: " + id +
                ", takenBy: " + takenBy +
                ", state: " + state +
                ", lastUpdate: " + lastUpdate +
                ", prio: " + prio;
    }
}
