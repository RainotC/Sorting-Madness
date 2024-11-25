package pl.put.poznan.sorting.doc;

/**
 * Class description
 *
 * @author Me
 * @version 1.0
 */
public class DocumentationTemplate {
    /**
     * Method description
     *
     * @param a param a description
     * @param b param b description
     * @return return value description
     */
    public int sum(int a, int b) {
        return a + b;
    }

    /**
     * Second method description
     *
     * @param a param a description
     * @return return value description
     * @throws IllegalArgumentException exception description
     */
    public int reciprocal(int a) throws IllegalArgumentException {
        if (a != 0) {
            return 1 / a;
        }
        else {
            throw new IllegalArgumentException();
        }
    }
}
