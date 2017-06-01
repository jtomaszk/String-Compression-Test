import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jarematomaszkiewicz on 01.06.2017.
 */
public class StringCompression {

    private final String value;
    private final int length;

    public StringCompression(String value) {
        this.value = value;
        this.length = value.length();
    }


    public String compress() {
        List<CharBox> result = new LinkedList<>();

        value.chars()
                .mapToObj(CharBox::new)
                .reduce((a, b) -> {
                    if (a.equals(b)) {
                        a.incrementCount();
                        return a;
                    } else {
                        result.add(a);
                        return b;
                    }
                }).ifPresent(result::add);


        String compressed = result.stream()
                .map(CharBox::getString)
                .collect(Collectors.joining());

        return compressed.length() < length ? compressed : value;
    }

    private class CharBox {
        private final int value;
        private int count;

        public CharBox(int value) {
            this.value = value;
            count = 1;
        }

        public String getString() {
            return String.valueOf((char) value) + count;
        }

        public void incrementCount() {
            count++;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CharBox charBox = (CharBox) o;

            return value == charBox.value;
        }

        @Override
        public int hashCode() {
            return value;
        }

        @Override
        public String toString() {
            return "CharBox{" +
                    "value=" + value +
                    ", count=" + count +
                    '}';
        }
    }
}
