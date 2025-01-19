import java.util.List;

public class ElementUtils {
    public static <T> T lastElement(List<T> list){
        if (list == null || list.isEmpty()){
            return null;
        }
        return list.getLast();
    }
    public static <T> T lastElement(T[] array){
        if (array == null || array.length == 0){
            return null;
        }
        return array[array.length - 1];
    }
}
