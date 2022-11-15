import static org.junit.Assert.*;
import org.junit.*;
import org.hamcrest.*;

import java.io.StringReader;
import java.util.*;

class SChecker implements StringChecker{
    public boolean checkString(String str) {
        return str.contains("united");
    }
}

public class TestListExamples {
    @Test(timeout = 100)
    public void testMerge0() {
        List<String> input1 = Arrays.asList(new String[]{"a", "c", "e"});
        List<String> input2 = Arrays.asList(new String[]{"b", "d", "f"});
        List<String> output = ListExamples.merge(input1, input2);
        List<String> expected = Arrays.asList(new String[]{"a", "b", "c", "d", "e", "f"});
        Assert.assertArrayEquals(expected.toArray(), output.toArray());
    }

    @Test(timeout = 100)
    public void testFilter0() {
        List<String> input = Arrays.asList(new String[]{"milan", "manchester united", "manchester city", "liverpool", "newcastle united", "west ham united"});
        List<String> expected = Arrays.asList(new String[]{"manchester united", "newcastle united", "west ham united"});
        StringChecker sc = new SChecker();
        List<String> output = ListExamples.filter(input, sc);
        Assert.assertArrayEquals(expected.toArray(), output.toArray());
    }

    @Test(timeout = 100)
    public void testFilter1() {
        List<String> input = Arrays.asList(new String[]{});
        List<String> expected = Arrays.asList(new String[]{});
        StringChecker sc = new SChecker();
        List<String> output = ListExamples.filter(input, sc);
        Assert.assertArrayEquals(expected.toArray(), output.toArray());
    }
}
