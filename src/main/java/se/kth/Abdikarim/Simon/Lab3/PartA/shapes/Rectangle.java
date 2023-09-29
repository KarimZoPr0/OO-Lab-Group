package se.kth.Abdikarim.Simon.Lab3.PartA.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends FillableShape
{
    private double height, width;

    public Rectangle( int x, int y, double height, double width, Color color, boolean filled )
    {
        super(x,y,color,filled);
        this.height = height;
        this.width = width;
    }

    public Rectangle( )
    {
        super();
        height = width = 100;
    }

    public double getHeight( )
    {
        return height;
    }

    public void setHeight( double height )
    {
        this.height = height;
    }

    public double getWidth( )
    {
        return width;
    }

    public void setWidth( double width )
    {
        this.width = width;
    }

    @Override
    public void paint ( GraphicsContext gc )
    {
        if ( isFilled() )
        {
            gc.setFill( getColor() );
            gc.fillRect(getX(), getY(),width, height  );
        }
        gc.setStroke( getColor() );
        gc.strokeRect(getX(), getY(),width, height );
    }

    @Override
    public void constrain( double boxX, double boxY, double boxWidth, double boxHeight )
    {
        // Punkt (0,0)
        super.constrain( boxX, boxY, boxWidth, boxHeight );

        // Punkt (1,0)
         if ((getX() + width) > boxWidth) {
            setVelocity( -Math.abs(getDx()), getDy() );
        }
        
        // Punkt (0, 1)
        if ((getY() + width) > boxHeight) {
            setVelocity( getDx(),-Math.abs(getDy()) );
        }
    }

    @Override
    public String toString( )
    {
        return super.toString() +
                ", height=" + height +
                ", width=" + width;
    }
}
