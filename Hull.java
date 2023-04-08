
// name: Roya Parsa
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


class Pair {
    int one;
    int two;
    public Pair(int one, int two) {
        this.one = one; this.two = two;
    }
}

public class Hull {

    public static void main(String[] args) {
        Scanner scanner =  new Scanner(System.in);
        int N = scanner.nextInt();
        float[] x = new float[N];
        float[] y = new float[N];
        for (int i = 0; i < N; i += 1) {
            x[i] = scanner.nextFloat();
            y[i] = scanner.nextFloat();
        }

        List<Pair> hullSegments = new ArrayList<Pair>();

        
        //TO DO!!
        for(int i= 0; i< N-1; i += 1){
            for (int j = i+1; j < N; j++){

                // computing a, b, c
                float a = y[j] - y[i];
                float b = x[i] - x[j];
                float c = x[i]*y[j] - y[i]*x[j];

                // determines if ax + by of a point is greater than c or less than c
                int greaterThan = 0;
                int lowerThan = 0;
                
                // determines if edge will be added or not
                boolean isEdge = true;

                for (int k = 0; k<N; k++){
                    if (a*x[k] + b*y[k] > c){
                        greaterThan += 1;
                    }
                    else if (a*x[k] + b*y[k] < c){
                        lowerThan += 1;
                    }
                    else{

                        // computing distances from: i-j, i-k, j-k
                        float distance_i_j = distance(x[i],y[i],x[j],y[j]);
                        float distance_i_k = distance(x[i],y[i],x[k],y[k]);
                        float distance_j_k = distance(x[j],y[j],x[k],y[k]);

                        // comparing distances along the line 
                        if (distance_i_j < distance_i_k || distance_i_j < distance_j_k){
                            // not an edge
                            isEdge = false;
                        }
                    }  
                }
                if ((lowerThan == 0 || greaterThan == 0) && isEdge == true){
                    // it is along the line therefore we add it
                    hullSegments.add(new Pair(i,j));
                }
            }
        }

        System.out.println(hullSegments.size());
        for (Pair p : hullSegments) {
            System.out.printf("%d %d\n", p.one, p.two);
        }
    }

    private static float distance(float x1, float y1, float x2, float y2) {
        float dx = x1 - x2;
        float dy = y1 - y2;
        return dx*dx + dy*dy;
    }
}

