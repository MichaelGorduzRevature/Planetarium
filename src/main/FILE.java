import java.util.ArrayList;
import java.util.List;

public class FILE {
    
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Billy");
        names.add("Bob");
        names.forEach(name -> System.out.println(name));
    }
}
