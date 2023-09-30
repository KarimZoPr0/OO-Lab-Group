package se.kth.Abdikarim.Simon.Lab3.PartA.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line extends Shape
{
    private double x2;
    private double y2;

    private double width;

    public Line( double x, double y, double x2, double y2, Color color )
    {
        super( x, y, color );
        this.x2 = x2;
        this.y2 = y2;
    }

    public Line( )
    {
        super( );
        x2 = y2 = 200;
    }


    public double getX2( )
    {
        return x2;
    }

    public void setX2( double x2 )
    {
        this.x2 = x2;
    }

    public double getY2( )
    {
        return y2;
    }

    public void setY2( double y2 )
    {
        this.y2 = y2;
    }

    @Override
    protected void move( long elapsedTimeNs )
    {
        super.move( elapsedTimeNs );
        x2 += getDx() * elapsedTimeNs / BILLION;
        y2 += getDy() * elapsedTimeNs / BILLION;
    }


    @Override
    public void paint( GraphicsContext gc )
    {
        gc.setStroke( getColor( ) );
        gc.strokeLine( getX( ), getY( ), x2, y2);
    }

    @Override
    public void constrain( double boxX, double boxY, double boxWidth, double boxHeight )
    {
        super.constrain( boxX, boxY, boxWidth,boxHeight );
        // If outside the box - calculate new dx and dy
        if (x2 < boxX) {
            setVelocity( Math.abs(getDx()), getDy() );
        } else if (x2 > boxWidth) {
            setVelocity( -Math.abs(getDx()), getDy() );
        }
        if (y2 < boxY) {
            setVelocity( getDx(),Math.abs(getDy()) );
        } else if (y2 > boxHeight) {
            setVelocity( getDx(),-Math.abs(getDy()) );
        }
    }

    @Override
    public String toString( )
    {
        return super.toString() +
                ", x2=" + x2 +
                ", y2=" + y2;
    }
}



