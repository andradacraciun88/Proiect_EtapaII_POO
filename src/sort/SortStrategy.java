package sort;

import input.Input;
import input.ProducersInputData;

import java.util.List;

public interface SortStrategy {
    /**
     * @param input
     * @return
     */
    public List<ProducersInputData> sort(Input input);
}
