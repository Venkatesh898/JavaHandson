package practice.immutableClass;

import java.util.ArrayList;
import java.util.List;

public class Driver {
    public static void main(String[] args) {
        List<Integer> listObj=new ArrayList<>();
        listObj.add(1);listObj.add(2);
        ImmutableClass immutableClass=new ImmutableClass(1,"ram",listObj);
        listObj.add(89);
        System.out.println(immutableClass);

    }
}
