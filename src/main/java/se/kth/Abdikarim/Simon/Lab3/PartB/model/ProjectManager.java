package se.kth.Abdikarim.Simon.Lab3.PartB.model;

import java.util.ArrayList;
import java.util.List;


/**
 * Manages a collection of projects and provides an API to modify the data.
 */
public class ProjectManager
{
    private final List< Project > projects;

    private int nextProjectId;


    /**
     * Public visibility - Visible to other classes and packages
     */
    public ProjectManager(  )
    {
        projects = new ArrayList<>(  );
        this.nextProjectId = 0;
    }

    /**
     * Clears the current project list, adds the new projects
     *
     * @param incomingProjects the new project list to be added
     */
    public void setProjects( List< Project > incomingProjects )
    {
        projects.clear();
        projects.addAll( incomingProjects );
        nextProjectId = getHighestId() + 1;
    }

    /**
     * Checks if specified title is unique
     *
     * @param title the title to be checked
     * @return true if title is unique, else false
     */
    public boolean isTitleUnique( String title )
    {
        return projects.stream( ).noneMatch( p -> p.getTitle( ).equals( title ) );
    }

    /**
     * Check if project title is unique
     * Creates a new project with title, desc and id
     *
     * @param title the new title for the project
     * @param descr the new description
     * @return the new project that was added
     */
    public Project addProject( String title, String descr )
    {
        if ( !isTitleUnique( title ) ) throw new TitleNotUniqueException( " Title not Unique" );

        Project project = new Project(new ArrayList<>(  ), title, descr, nextProjectId++ );
        projects.add( project );
        return project;
    }

    /**
     * Remove Project
     *
     * @param project
     */
    public void removeProject( Project project )
    {
        projects.remove( project );
    }


    /**
     * Retrieves project by id
     *
     * @param id
     * @return project by id
     */
    public Project getProjectById( int id )
    {
        return ( Project ) projects.get( id ).clone();
    }

    /**
     * Retrieves a list of projects where title contains the specified titleStr
     *
     * @param titleStr The string to check against project title
     * @return A list of the projects where title contains titleStr
     */
    public List<Project> findProjects( String titleStr )
    {
        return projects.stream().filter( p -> p.getTitle().contains( titleStr )).toList();
    }

    /**
     * Retrieves the nextProjectId
     *
     * @return nextProjectId
     */
    public int getNextProjectId( )
    {
        return nextProjectId;
    }

    /**
     * Retrieves the ID of the project with the highest order.
     *
     * @return the id of the highest project id or return -1 if empty
     */
    private int getHighestId( )
    {
        int max = -1;
        for ( Project project : projects )
        {
            if(project.getId() > max)
            {
                max = project.getId();
            }
        }
        return max;
    }

    /**
     * Create a variable ArrayList
     * Copy the task using clone method for deep copy
     *
     * @return a deep copy of a list
     * @throws CloneNotSupportedException if not found
     */
    public List< Project > getProjects( )
    {
        ArrayList<Project> copiedProjects = new ArrayList<>(  );
        for ( Project project : projects )
        {
            Project newProject = new Project( project.getTasks(), project.getTitle(), project.getDescription(), project.getId() );
            copiedProjects.add(newProject);
        }
        return copiedProjects;
    }

    /**
     * @return a String with information about the project
     */
    @Override
    public String toString( )
    {
        return  "projects: " + projects.toString() +
                ", nextProjectId: " + nextProjectId;
    }
}