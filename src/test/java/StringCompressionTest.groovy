import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by jarematomaszkiewicz on 01.06.2017.
 */
class StringCompressionTest extends Specification {

    @Unroll
    def "basic string #given compression"(String given, String compressed) {
        expect:
            new StringCompression(given).compress() == compressed

        where:
        given            | compressed
        "aabcccccaaa"    | "a2b1c5a3"
        "aabcccccaaaaab" | "a2b1c5a5b1"
        "aabcdefgh"      | "aabcdefgh"
        "aaabcdefgh"     | "aaabcdefgh"
        "aaaaabc"        | "a5b1c1"
        "a"              | "a"
        ""               | ""
        "aaaaa"          | "a5"

    }

}
