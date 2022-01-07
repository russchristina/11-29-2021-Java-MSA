package Controller;
import org.eclipse.jetty.util.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Driver {
    public static void main(String[] args) {
        Logger exceptions = LoggerFactory.getLogger("EXCEPTIONS");
        try{
            int[] a = {1,2,4};
            int b = 5;
            System.out.println(a[4]);
        }catch (Exception e){
            exceptions.debug(e.getMessage(),e);
        }
    }
}
