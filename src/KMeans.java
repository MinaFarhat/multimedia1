import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KMeans {
    private int k;
    private int[] centroids;

    public KMeans(int k) {
        this.k = k;
    }

    public int[] cluster(int[] pixels) {
        int n = pixels.length;
        List<Integer>[] clusters = new List[k];
        for (int i = 0; i < k; i++) {
            clusters[i] = new ArrayList<Integer>();
        }
        centroids = new int[k];
        Random random = new Random();
        for (int i = 0; i < k; i++) {
            int index = random.nextInt(n);
            centroids[i] = pixels[index];
        }
        int[] clusterIndices = new int[n];
        boolean changed = true;
        while (changed) {
            changed = false;
            for (int i = 0; i < n; i++) {
                int pixel = pixels[i];
                int clusterIndex = getClosestCentroidIndex(pixel);
                if (clusterIndices[i] != clusterIndex) {
                    changed = true;
                    clusterIndices[i] = clusterIndex;
                }
                clusters[clusterIndex].add(pixel);
            }
            for (int i = 0; i < k; i++) {
                List<Integer> cluster = clusters[i];
                if (cluster.isEmpty()) {
                    continue;
                }
                int[] sum = new int[3];
                for (int pixel : cluster) {
                    sum[0] += (pixel >> 16) & 0xff;
                    sum[1] += (pixel >> 8) & 0xff;
                    sum[2] += pixel & 0xff;
                }
                centroids[i] = (sum[0] / cluster.size()) << 16 | (sum[1] / cluster.size()) << 8 | sum[2] / cluster.size();
                cluster.clear();
            }
        }
        return clusterIndices;
    }

    public int[] getCentroids() {
        return centroids;
    }

    private int getClosestCentroidIndex(int pixel) {
        int minDistance = Integer.MAX_VALUE;
        int closestCentroidIndex = -1;
        for (int i = 0; i < k; i++) {
            int distance = getDistance(pixel, centroids[i]);
            if (distance < minDistance) {
                minDistance = distance;
                closestCentroidIndex = i;
            }
        }
        return closestCentroidIndex;
    }

    private int getDistance(int pixel1, int pixel2) {
        int r1 = (pixel1 >> 16) & 0xff;
        int g1 = (pixel1 >> 8) & 0xff;
        int b1 = pixel1 & 0xff;
        int r2 = (pixel2 >> 16) & 0xff;
        int g2 = (pixel2 >> 8) & 0xff;
        int b2 = pixel2 & 0xff;
        int dr = r1 - r2;
        int dg = g1 - g2;
        int db = b1 - b2;
        return dr * dr + dg * dg + db * db;
    }
}

