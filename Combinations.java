import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combinations {
    public static void main(String[] args) {
        Combinations c = new Combinations();
        c.go();
    }

    private void go() {

        ArrayList<String> al = new ArrayList<String>(Arrays.asList(new String[]{"1","2","3","4", "5"}));
        System.out.println(al.toString());

        List<List<String>> alCombo = combine(al);
        int i = 1;
        for (List<String> strings : alCombo) {
            System.out.println(""+i++ +": " + strings.toString());
        }
    }


    private List<List<String>> combine(List<String> arr) {
        if (arr.size() == 0) {
            //must put one empty List in List
            List<List<String>> emptyList =  new ArrayList<List<String>> ();
            emptyList.add(new ArrayList<String>());
            return emptyList;
        }

        List<List<String>> restCombinations = combine(arr.subList(1, arr.size()));

        List<List<String>> withFirst = new ArrayList<List<String>>();
        for (List<String> comb : restCombinations) {
            //must clone comb
            List<String> comb2 = new ArrayList<>(comb);
            comb2.add(0, arr.get(0));
            withFirst.add(comb2);
        }

        withFirst.addAll(restCombinations);
        return withFirst;
    }

    /*
    jmf python,
    
    def combine(arr):
    if len(arr) == 0:
        return [[]]

    #arr[1:] returns new array (mao försvinner 1, arr - head)
    restCombinations = combine(arr[1:]) #all after 1

    with_first = []
    #loop and add all combs to first
    for comb in restCombinations:
        with_first.append(arr[:1] + comb)

    return with_first + restCombinations
    */

}
