/*************************************************************************
 * Name: Nobel
 * Email: jwilove0049@163.com
 *
 * Compilation:  javac Fast.java
 * Execution: java Fast *.txt
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Arrays;
public class Fast {
    
    private void collinear(int size, Point[] points) {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenRadius(0.01);
        for (int i = 0; i < size; i++) {
            points[i].draw();
        }
        StdDraw.setPenRadius();   
        
        Arrays.sort(points);
        double slope1, slope2;
        double[] slopeOld = new double[size/4];
        Point[][] slopeRecord = new Point[size/4][size];
        int slopeNum = 0;
        int noAdd = 0;
        int cnt;
            
        for (int i = 0; i < size - 3; i++) {
            Point[] p = new Point[size-i-1]; 

            for (int j = 0; j < size-i-1; j++)
                p[j] = points[j+i+1];
                
            cnt = 1;
            Arrays.sort(p, points[i].SLOPE_ORDER);
            slope1 = points[i].slopeTo(p[0]);

            for (int j = 1; j < size-i-1; j++) {
                slope2 = points[i].slopeTo(p[j]);
                if (slope1 == slope2) { 
                    cnt++;

                } else if (cnt >= 3) {
                    
                    for (int k = 0; k < slopeNum; k++) {
                        if (slope1 == slopeOld[k]) {
                            for (int l = 0; l < size; l++) {
                                if (slopeRecord[k][l] == points[i]) {
                                    noAdd = 1;
                                    cnt = 1;
                                    break;
                                }
                            }
                        }
                    }
                    if (noAdd == 0) {
                        if (slopeNum == slopeOld.length) {
                            double[] slopeTmp = new double[slopeNum];
                            for (int l = 0; l < slopeNum; l++) 
                                slopeTmp[l] = slopeOld[l];
                            slopeOld = new double[slopeNum*2];
                            for (int l = 0; l < slopeNum; l++) 
                                slopeOld[l] = slopeTmp[l];
                            
                            Point[][] slopeRecordTmp = new Point[slopeNum][size];
                            for (int l = 0; l < slopeNum; l++) {
                                for (int m = 0; m < size; m++)
                                    slopeRecordTmp[l][m] = slopeRecord[l][m];
                            }
                            slopeRecord = new Point[slopeNum*2][size];
                            for (int l = 0; l < slopeNum; l++) {
                                for (int m = 0; m < size; m++)
                                    slopeRecord[l][m] = slopeRecordTmp[l][m];
                            }
                        }
                        StdOut.print(points[i]);
                        slopeRecord[slopeNum][0] = points[i];
                        
                        for (int k = 0; k < cnt -1; k++) {
                            StdOut.print(" -> " + p[j-cnt+k]);
                            slopeRecord[slopeNum][k+1] = p[j-cnt+k];
                        }   
                        StdOut.println(" -> " + p[j-1]);
                            
                        slopeRecord[slopeNum][cnt-1] = p[j-1];
                        points[i].drawTo(p[j-1]);
                        StdDraw.show(0);
                        slopeOld[slopeNum] = slope1;
                        slopeNum++;   
                    }
                    slope1 = slope2;
                    cnt = 1;
                    noAdd = 0;
                }
                else {
                    slope1 = slope2;
                    cnt = 1;
                    noAdd = 0;
                }
            }                
            if (cnt >= 3) {
                for (int k = 0; k < slopeNum; k++) {
                    if (slope1 == slopeOld[k]) {
                        for (int l = 0; l < size; l++) {
                            if (slopeRecord[k][l] == points[i]) {
                                noAdd = 1;
                                cnt = 1;
                                break;
                            }
                        }
                    }
                }
                if (noAdd == 0) {
                    if (slopeNum == slopeOld.length) {
                            double[] slopeTmp = new double[slopeNum];
                            for (int l = 0; l < slopeNum; l++) 
                                slopeTmp[l] = slopeOld[l];
                            slopeOld = new double[slopeNum*2];
                            for (int l = 0; l < slopeNum; l++) 
                                slopeOld[l] = slopeTmp[l];
                            
                            Point[][] slopeRecordTmp = new Point[slopeNum][size];
                            for (int l = 0; l < slopeNum; l++) {
                                for (int m = 0; m < size; m++)
                                    slopeRecordTmp[l][m] = slopeRecord[l][m];
                            }
                            slopeRecord = new Point[slopeNum*2][size];
                            for (int l = 0; l < slopeNum; l++) {
                                for (int m = 0; m < size; m++)
                                    slopeRecord[l][m] = slopeRecordTmp[l][m];
                            }
                        }
                    StdOut.print(points[i]);
                    slopeRecord[slopeNum][0] = points[i];
                    
                    for (int k = 0; k < cnt -1; k++) {
                        StdOut.print(" -> " + p[size-i-1-cnt+k]);
                        slopeRecord[slopeNum][k+1] = p[size-i-1-cnt+k];
                    }
                    StdOut.println(" -> " + p[size-i-2]);
                    slopeRecord[slopeNum][cnt-1] = p[size-i-2];
                    points[i].drawTo(p[size-i-2]);
                    StdDraw.show(0);
                    slopeOld[slopeNum] = slope1;
                    slopeNum++;
                } 
                cnt = 1; 
                noAdd = 0;
            }   
        }
    }
    
    public static void main(String[] args) {
        In in = new In(args[0]);      // input file
        int N = in.readInt();         // number of points N

        // repeatedly read in sites to open and draw resulting system
        Point[] points = new Point[N];
        Fast fast = new Fast();
        
        int x, y;            
        for (int i = 0; i < N; i++) {
            x = in.readInt();
            y = in.readInt();
            points[i] = new Point(x, y);
        }
        fast.collinear(N, points);
    }
}
