package PoissonSampling;
import java.util.ArrayList;
import java.util.List;

public class PoissonAlgorithm {
    private final int k = 8; // nombre de tentatives pour trouver un nouveau point

    private final double cellSize = 1.0;
    //private final int gridSize;
    private final int width, height;
    private final List<Point> points = new ArrayList<>();
    private final List<Point> activeList = new ArrayList<>();
    public final Point[][] grid;

    public PoissonAlgorithm(int width, int height, Point[][] map){
        this.width = width;
        this.height = height;
        this.grid = map;
        //this.gridSize = (int) Math.ceil(Math.max(width, height) / cellSize);
    }

    public void generatePoints(char type, double r) { // distance minimale entre deux points
        // Ajouter un point aléatoire à la liste active
        Point firstPoint = new Point(Math.random() * width, Math.random() * height, type);
        activeList.add(firstPoint);
        points.add(firstPoint);
        grid[getIndex(firstPoint.x())][getIndex(firstPoint.y())] = firstPoint;

        while (!activeList.isEmpty()) {
            // Sélectionner un point aléatoire de la liste active
            int index = (int) (Math.random() * activeList.size());
            Point currentPoint = activeList.get(index);

            // Tenter de créer un nouveau point autour du point sélectionné
            boolean found = false;
            for (int i = 0; i < k; i++) {
                Point newPoint = generateRandomPointAround(currentPoint, type, r);
                if (isValid(newPoint, r)) {
                    activeList.add(newPoint);
                    points.add(newPoint);
                    grid[getIndex(newPoint.x())][getIndex(newPoint.y())] = newPoint;
                    found = true;
                }
            }

            // Si aucun nouveau point n'a été trouvé, retirer le point de la liste active
            if (!found) {
                activeList.remove(index);
            }
        }
    }

    private boolean isValid(Point point, double r) {
        if (point.x() < 0 || point.x() >= width || point.y() < 0 || point.y() >= height) {
            return false;
        }
        if(grid[getIndex(point.x())][getIndex(point.y())] != null) return false;

        int cellX = getIndex(point.x());
        int cellY = getIndex(point.y());
        int startX = Math.max(0, cellX - 5);
        int startY = Math.max(0, cellY - 5);
        int endX = Math.min(this.width - 1, cellX + 5);
        int endY = Math.min(this.height - 1, cellY + 5);
        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                Point p = grid[x][y];
                if (p != null && p.distanceTo(point) < r  && p.type() == point.type()) {// si le point n'est pas nul il ya des chances qu'on dise vraie
                    return false;
                }
            }
        }
        return true;
    }

    private Point generateRandomPointAround(Point point, char type, double r) {
        double angle = Math.random() * 2 * Math.PI;
        double distance = Math.sqrt(Math.random() * 3 + 1) * r;
        double x = point.x() + distance * Math.cos(angle);
        double y = point.y() + distance * Math.sin(angle);
        return new Point(x, y, type);
    }

    private int getIndex(double value) {
        return (int) Math.floor(value / cellSize);
    }
}