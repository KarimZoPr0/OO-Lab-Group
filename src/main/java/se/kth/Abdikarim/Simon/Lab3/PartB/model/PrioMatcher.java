package se.kth.Abdikarim.Simon.Lab3.PartB.model;

public class PrioMatcher implements ItaskMatcher
{
    private final TaskPrio prio;

    public PrioMatcher (TaskPrio prio)
    {
        this.prio = prio;
    }


    @Override
    public boolean match( Task task )
    {
        return prio.equals( task.getPrio() );
    }

    public TaskPrio getPrio( )
    {
        return prio;
    }

    @Override
    public String toString( )
    {
        return "prio: " + prio;
    }
}
