import static org.junit.Assert.*;
import org.junit.*;
import org.hamcrest.*;

import java.io.StringReader;
import java.util.*;

class SChecker implements StringChecker{
    public boolean checkString(String str) {
        return str.contains("man");
    }
}

public class TestListExamples {
    @Test
    public void testMerge0() {
        List<String> input1 = Arrays.asList(new String[]{"a", "c", "e"});
        List<String> input2 = Arrays.asList(new String[]{"b", "d", "f"});
        List<String> output = ListExamples.merge(input1, input2);
        assertEquals(output.size(), 6);
        MatcherAssert.assertThat(output, CoreMatchers.hasItems("a", "b", "c", "d", "e", "f"));
    }

    @Test
    public void testFilter0() {
        List<String> input = Arrays.asList(new String[]{"man united", "man city", "liverpool"});
        StringChecker sc = new SChecker();
        List<String> output = ListExamples.filter(input, sc);
        assertEquals(output.size(), 2);
        MatcherAssert.assertThat(output, CoreMatchers.hasItems("man united", "man city"));
    }
}
