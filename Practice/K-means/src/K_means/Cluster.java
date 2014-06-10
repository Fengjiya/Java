package K_means;
import java.util.*;
public class Cluster {  
    private int id;//��ʶ  
    private Point center;//����  
    private List<Point> members = new ArrayList<Point>();//��Ա  
  
    public Cluster(int id, Point center) {  
        this.id = id;  
        this.center = center;  
        
    }  
  
    public Cluster(int id, Point center, List<Point> members) {  
        this.id = id;  
        this.center = center;  
        this.members = members;  
    }  
  
    public void addPoint(Point newPoint) {  
        if (!members.contains(newPoint))  
            members.add(newPoint);  
        else  
            throw new IllegalStateException("��ͼ����ͬһ����������!");  
    }  
  
    public int getId() {  
        return id;  
    }  
  
    public Point getCenter() {  
        return center;  
    }  
  
    public void setCenter(Point center) {  
        this.center = center;  
    }  
  
    public List<Point> getMembers() {  
        return members;  
    }  
  
    @Override  
    public String toString() {  
        return "Cluster{" +  
                "id=" + id +  
                ", center=" + center +  
                ", members=" + members +  
                "}";  
    }  
}