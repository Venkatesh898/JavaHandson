package practice.immutableClass;

import java.util.ArrayList;
import java.util.List;

public final class ImmutableClass {
    private  final int id;
    private  final  String name;
    private final List<Integer> list;
    public  ImmutableClass(int id, String name, List<Integer> list)
    {
        this.id=id;
        this.name=name;
        this.list=new ArrayList<>(list);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getList() {
        return new ArrayList<>(list);
    }

    @Override
    public String toString() {
        return "ImmutableClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", list=" + list +
                '}';
    }
}
