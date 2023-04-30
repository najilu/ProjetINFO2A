package PoissonSampling;

public record Point(double x, double y, char type)
{

    public double distanceTo(Point other)
    {
        double dx = x - other.x;
        double dy = y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public String toString()
    {
        return type+"";
    }
}
