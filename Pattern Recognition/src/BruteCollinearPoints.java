import java.util.ArrayList;
import java.util.Arrays;

/*************
 *  Examines 4 points at a time and checks whether they all lie on the same line segment,
 *  returning all such line segments. To check whether the 4 points p, q, r, and s are collinear,
 *  check whether the three slopes between p and q, between p and r, and between p and s are all equal.
 */

public class BruteCollinearPoints {

    private LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        // check if the argument to the constructor is null
        if (points == null)
        throw new IllegalArgumentException("Given argument is null.");

        // check if argument to the constructor contains a repeated point
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException("One of the entities is null.");
            }
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("Duplicated entries.");
                }
            }
        }

        ArrayList<LineSegment> collinearPoints = new ArrayList<>();

        Point[] sortedPoints =  Arrays.copyOf(points, points.length);
        Arrays.sort(sortedPoints);

        for (int p = 0; p < sortedPoints.length - 3; p++) {
            for (int q = p + 1; q < sortedPoints.length - 2; q++) {
                for (int r = q + 1; r < sortedPoints.length - 1; r++) {
                    for (int s = r + 1; s < sortedPoints.length; s++) {
                        if (sortedPoints[p].slopeTo(sortedPoints[q]) == sortedPoints[p].slopeTo(sortedPoints[r]) &&
                                sortedPoints[p].slopeTo(sortedPoints[q]) == sortedPoints[p].slopeTo(sortedPoints[s])) {
                            collinearPoints.add(new LineSegment(sortedPoints[p], sortedPoints[s]));
                        }
                    }
                }
            }
        }

        segments = collinearPoints.toArray(new LineSegment[collinearPoints.size()]);

    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return Arrays.copyOf(segments, numberOfSegments());
    }

}
