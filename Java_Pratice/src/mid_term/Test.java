package mid_term;

import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<String> a = RandomString.randomList("A", 11);
        
        RandomString.divideNumByForEach(3, a);
        // RandomString.divideNumByResult(3, a);

    }
}
