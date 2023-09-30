package se.kth.Abdikarim.Simon.Lab3.PartB.model;

public class TitleNotUniqueException extends RuntimeException
{

    public TitleNotUniqueException( String message )
    {
        super( message );
    }

    public TitleNotUniqueException()
    {
        super();
    }
}
