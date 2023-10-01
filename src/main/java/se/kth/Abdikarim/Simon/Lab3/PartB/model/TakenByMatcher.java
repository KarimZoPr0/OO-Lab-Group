package se.kth.Abdikarim.Simon.Lab3.PartB.model;

public class TakenByMatcher implements ItaskMatcher
{
    private final String takenBy;

    public TakenByMatcher(String takenBy)
    {
        this.takenBy = takenBy;
    }
    @Override
    public boolean match( Task task )
    {
        return takenBy.equals( task.getTakenBy() );
    }

    public String getTakenBy( )
    {
        return takenBy;
    }

    @Override
    public String toString( )
    {
        return "takenBy: " + takenBy;
    }
}
