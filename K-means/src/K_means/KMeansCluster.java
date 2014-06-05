package K_means;

import java.io.FileNotFoundException;
import java.util.*;

public class KMeansCluster {  
    private int k;//�صĸ���  
    private int num = 100000;//��������  
    private List<Double> datas;//ԭʼ������  
    private String address;//������·��  
    private List<Point> data = new ArrayList<Point>(); 
    
    private AbstractDistance distance = new AbstractDistance() 
    {  
        @Override  
        public double getDis(Point p1, Point p2) 
        {  
            //ŷ����¾���  
            return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));  
        }  
    };  
  
    public KMeansCluster(int k, int num, String address) 
    {  
        this.k = k;  
        this.num = num;  
        this.address = address;  
    }  
  
    public KMeansCluster(int k, String address) 
    {  
        this.k = k;  
        this.address = address;  
    }  
  
    public KMeansCluster(int k, List<Double> datas) {  
        this.k = k;  
        this.datas = datas;  
    }  
  
    public KMeansCluster(int k, int num, List<Double> datas) {  
        this.k = k;  
        this.num = num;  
        this.datas = datas;  
    }  
  
    private void check() {  
        if (k == 0)  
            throw new IllegalArgumentException("k must be the number > 0");  
  
        if (address == null && datas == null)  
            throw new IllegalArgumentException("program can't get real data");  
    }  
  
    //��ʼ������ 
    public void init() throws FileNotFoundException {  
        check();  
        //��ȡ�ļ���init data  
        //����ԭʼ����  
        for (int i = 0, j = datas.size(); i < j; i++)  
            data.add(new Point(i, datas.get(i), 0));  
    }  
  
    // ��һ�����ѡȡ���ĵ� 
    public Set<Point> chooseCenter() {  
        Set<Point> center = new HashSet<Point>();  
        Random ran = new Random();  
        int roll = 0;  
        while (center.size() < k) {  
            roll = ran.nextInt(data.size());  
            center.add(data.get(roll));  
        }  
        return center;  
    }  
  
    
    public List<Cluster> prepare(Set<Point> center) {  
        List<Cluster> cluster = new ArrayList<Cluster>();  
        Iterator<Point> it = center.iterator();  
        int id = 0;  
        while (it.hasNext()) {  
            Point p = it.next();  
            if (p.isBeyond()) {  
                Cluster c = new Cluster(id++, p);  
                c.addPoint(p);  
                cluster.add(c);  
            } else  
                cluster.add(new Cluster(id++, p));  
        }  
        return cluster;  
    }  
  
    //��һ�����㣬���ĵ�Ϊ����ֵ
    public List<Cluster> clustering(Set<Point> center, List<Cluster> cluster) {  
        Point[] p = center.toArray(new Point[0]);  
        TreeSet<Distence> distence = new TreeSet<Distence>();//��ž�����Ϣ  
        Point source;  
        Point dest;  
        boolean flag = false;  
        for (int i = 0, n = data.size(); i < n; i++) {  
            distence.clear();  
            for (int j = 0; j < center.size(); j++) {  
                if (center.contains(data.get(i)))  
                    break;  
  
                flag = true;  
                // �������  
                source = data.get(i);  
                dest = p[j];  
                distence.add(new Distence(source, dest, distance));  
            }  
            if (flag == true) {  
                Distence min = distence.first();  
                for (int m = 0, k = cluster.size(); m < k; m++) {  
                    if (cluster.get(m).getCenter().equals(min.getDest()))  
                        cluster.get(m).addPoint(min.getSource());  
  
                }  
            }  
            flag = false;  
        }  
  
        return cluster;  
    }  
  
    //�������㣬���ĵ�Ϊ����������ֵ 
    public List<Cluster> cluster(List<Cluster> cluster) {  
//        double error;  
        Set<Point> lastCenter = new HashSet<Point>();  
        for (int m = 0; m < num; m++) {  
//            error = 0;  
            Set<Point> center = new HashSet<Point>();  
            // ���¼����������  
            for (int j = 0; j < k; j++) {  
                List<Point> ps = cluster.get(j).getMembers();  
                int size = ps.size();  
                if (size < 3) {  
                    center.add(cluster.get(j).getCenter());  
                    continue;  
                }  
                // �������  
                double x = 0.0, y = 0.0;  
                for (int k1 = 0; k1 < size; k1++) {  
                    x += ps.get(k1).getX();  
                    y += ps.get(k1).getY();  
                }  
                //�õ��µ����ĵ�  
                Point nc = new Point(-1, x / size, y / size, false);  
                center.add(nc);  
            }  
            if (lastCenter.containsAll(center))//���ĵ㲻�ڱ仯���˳�����  
                break;  
            lastCenter = center;  
            // ��������  
            cluster = clustering(center, prepare(center));  
//            for (int nz = 0; nz < k; nz++) {  
//                error += cluster.get(nz).getError();//�������  
//            }  
        }  
        return cluster;  
    }  
  
    //���������Ϣ������̨ 
    public void out2console(List<Cluster> cs) {  
        for (int i = 0; i < cs.size(); i++) {  
            System.out.println("No." + (i + 1) + " cluster:");  
            Cluster c = cs.get(i);  
            List<Point> p = c.getMembers();  
            for (int j = 0; j < p.size(); j++) {  
                System.out.println("\t" + p.get(j).getX() + " ");  
            }  
            System.out.println();  
        }  
    }  
}