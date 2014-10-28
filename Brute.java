import java.util.Arrays;

public class Brute {
    public static void main(String[] args) { 
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        int x;
        int y;
        Point[]  point = new Point[N];
        double slope1, slope2, slope3;
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenRadius(0.01);
        for (int i = 0; i < N; i++) {
            x = in.readInt();
            y = in.readInt();
            point[i] = new Point(x, y);
            point[i].draw();
        }
        StdDraw.setPenRadius();
        Arrays.sort(point, 0, N);
        

        for (int i = 0; i < N-3; i++) {      
            
            for (int j = i+1; j < N-2; j++) {
                slope1 = point[i].slopeTo(point[j]);
                
                for (int k = j+1; k < N-1; k++) {
                    slope2 = point[i].slopeTo(point[k]);
                    
                    for (int l = k+1; l < N; l++) {
                        slope3 = point[i].slopeTo(point[l]);
                        
                        if ((slope1 == slope2) && (slope1 == slope3)) {
                            StdOut.println(point[i] 
                                               + " -> " + point[j]
                                               + " -> " + point[k] 
                                               +" -> " + point[l]);
                            
                            point[i].drawTo(point[l]);
                            StdDraw.show(0);
                            
                        }
                    }
                }
            }
        }
    }
}