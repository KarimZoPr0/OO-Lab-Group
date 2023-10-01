package se.kth.Abdikarim.Simon.Lab3.PartB.model;

public class NotDoneMatcher implements ItaskMatcher
{
    @Override
    public boolean match( Task task )
    {
        return task.getState() != TaskState.DONE;
    }
}
