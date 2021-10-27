package backend;

import java.io.IOException;
import java.util.prefs.BackingStoreException;

/**
 *
 * @author Fego
 */
public class TestClass {

    public void test() {
       String value = "-50";
       
        System.out.println(Double.parseDouble(value));
    }

    public static void main(String[] args) throws BackingStoreException, IOException {
        new TestClass().test();
    }
}

