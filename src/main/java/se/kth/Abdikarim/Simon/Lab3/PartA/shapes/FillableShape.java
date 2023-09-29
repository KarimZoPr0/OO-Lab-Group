package se.kth.Abdikarim.Simon.Lab3.PartA.shapes;

import javafx.scene.paint.Color;

public abstract class FillableShape extends Shape
{
    private boolean filled;


    public FillableShape( double x, double y, Color color, boolean filled )
    {
        super(x,y,color);
        this.filled = filled;
    }

    public FillableShape()
    {
        super();
        filled = true;
    }

    public boolean isFilled()
    {
        return filled;
    }

    public void setFilled(boolean value)
    {
        filled = value;
    }
}
