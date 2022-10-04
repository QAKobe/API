package pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
// we use two annotation above that will take care of getters and setters
public class PokemonPojo {

    private int count;

    private String next;

    private String previous;

    private List<ResultPojo> results;

//    public int getCount() {
//        return count;
//    }
//
//    public void setCount(int count) {
//        this.count = count;
//    }
//
//    public String getNext() {
//        return next;
//    }
//
//    public void setNext(String next) {
//        this.next = next;
//    }
//
//    public String getPrevious() {
//        return previous;
//    }
//
//    public void setPrevious(String previous) {
//        this.previous = previous;
//    }
//
//    public List<ResultPojo> getResults() {
//        return results;
//    }
//
//    public void setResults(List<ResultPojo> results) {
//        this.results = results;
//    }
}
