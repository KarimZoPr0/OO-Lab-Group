package se.kth.Abdikarim.Simon.Lab3.PartB.model;

public class TakenByMatcher implements ItaskMatcher
{
    private String takenby;

    public TakenByMatcher(String takenby)
    {
        this.takenby = takenby;
    }
    @Override
    public boolean match( Task task )
    {
        return takenby.equals( task.getTakenBy() );
    }

    public String getTakenby( )
    {
        return takenby;
    }

    @Override
    public String toString( )
    {
        return "takenBy: " + takenby;
    }
}
