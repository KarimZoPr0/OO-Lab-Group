package se.kth.Abdikarim.Simon.Lab3.PartA.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends FillableShape
{
    private double diameter;

    public Circle( double x, double y, Color color, boolean filled, double diameter )
    {
        super(x,y,color, filled);
        this.diameter = diameter;
    }

    public Circle( )
    {
        super();
        this.diameter = 20;
    }

    public double getDiameter( )
    {
        return diameter;
    }

    public void setDiameter( double diameter )
    {
        this.diameter = diameter;
    }

    @Override
    public void constrain( double boxX, double boxY, double boxWidth, double boxHeight )
    {
        if (getX() < boxX)
        {
            setVelocity( Math.abs(getDx()), getDy() );
        }
        else if ((getX()) > boxWidth - diameter) {
            setVelocity( -Math.abs(getDx()), getDy() );
        }

        if (getY() < boxY) {
            setVelocity( Math.abs(getDy()), getDx() );
        }
        else if ((getY()) > boxHeight - diameter) {
            setVelocity( -Math.abs(getDy()), getDx() );
        }

    }

    @Override
    public void paint( GraphicsContext gc )
    {
        gc.strokeOval(getX(),getY(),diameter, diameter);
        if ( isFilled() )
        {
            gc.setFill( getColor());
            gc.fillOval( getX(),getY(),diameter,diameter );
        }
    }

    @Override
    public String toString( )
    {
        return super.toString() +
                "diameter" + diameter;
    }
}
