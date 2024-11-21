package pl.put.poznan.sorting.logic;

import java.util.Arrays;

public class SortingMadness {

    private final String[] transforms;

    public SortingMadness(String[] transforms) {
        this.transforms = transforms;
    }

    public String transform(String text) {
        String result = text;

        for (String transform : transforms) {
            switch (transform.toLowerCase()) {
                case "upper":
                    result = result.toUpperCase();
                    break;
                case "lower":
                    result = result.toLowerCase();
                    break;
                case "reverse":
                    result = new StringBuilder(result).reverse().toString();
                    break;
                case "capitalize":
                    result = capitalize(result);
                    break;
                case "escape":
                    result = escapeHtml(result);
                    break;
                default:
                    // If an unknown transform is encountered, you can log or skip it
                    System.err.println("Unknown transform: " + transform);
            }
        }
        return result;
    }

    private String capitalize(String text) {
        if (text == null || text.isEmpty()) return text;
        return Arrays.stream(text.split(" "))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                .reduce((a, b) -> a + " " + b)
                .orElse("");
    }

    private String escapeHtml(String text) {
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#x27;");
    }
}
