package se.kth.Abdikarim.Simon.Lab3.PartB.model;

import java.util.ArrayList;
import java.util.List;

public class ProjectManager
{
    private final List< Project > projects;

    private int nextProjectId;


    public ProjectManager(  )
    {
        projects = new ArrayList<>(  );
        this.nextProjectId = 0;
    }

    public void setProjects( List< Project > incomingProjects )
    {
        projects.clear();
        projects.addAll( incomingProjects );
    }

    public boolean isTitleUnique( String title )
    {
        return projects.stream( ).noneMatch( p -> p.getTitle( ).equals( title ) );
    }

    public Project addProject( String title, String descr )
    {
        if ( !isTitleUnique( title ) ) throw new TitleNotUniqueException( " Title not Unique" );

        nextProjectId = getHighestId() + 1;
        Project project = new Project(new ArrayList<>(  ), title, descr, nextProjectId );
        projects.add( project );
        return project;
    }

    public void removeProject( Project project )
    {
        projects.remove( project );
    }


    public Project getProjectById( int id )
    {
        return projects.get( id );
    }


    public List<Project> findProjects( String titleStr )
    {
        return projects.stream().filter( p -> p.getTitle().equals( titleStr )).toList();
    }

    public int getNextProjectId( )
    {
        return nextProjectId;
    }

    private int getHighestId( )
    {
        return projects.stream( ).max( Project::compareTo ).map( Project::getId ).orElse( -1 );
    }

    public List< Project > getProjects( ) throws CloneNotSupportedException
    {
        ArrayList<Project> copiedProjects = new ArrayList<>(  );
        for ( Project project : projects )
        {
            Project newProject = (Project ) project.clone();
            copiedProjects.add(newProject);
        }
        return copiedProjects;
    }

    @Override
    public String toString( )
    {
        return  "projects: " + projects.toString() +
                ", nextProjectId: " + nextProjectId;
    }
}

