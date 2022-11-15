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
    // Merge: simple
    @Test(timeout = 100)
    public void testMerge0() {
        List<String> input1 = Arrays.asList(new String[]{"a", "c", "e"});
        List<String> input2 = Arrays.asList(new String[]{"b", "d", "f"});
        List<String> output = ListExamples.merge(input1, input2);
        List<String> expected = Arrays.asList(new String[]{"a", "b", "c", "d", "e", "f"});
        Assert.assertArrayEquals(expected.toArray(), output.toArray());
    }
    // Merge: two empty lists
    @Test(timeout = 100)
    public void testMerge1() {
        List<String> input1 = Arrays.asList(new String[]{});
        List<String> input2 = Arrays.asList(new String[]{});
        List<String> output = ListExamples.merge(input1, input2);
        List<String> expected = Arrays.asList(new String[]{});
        Assert.assertArrayEquals(expected.toArray(), output.toArray());
    }
    // Merge: one empty lists
    @Test(timeout = 100)
    public void testMerge2() {
        List<String> input1 = Arrays.asList(new String[]{});
        List<String> input2 = Arrays.asList(new String[]{"a", "b"});
        List<String> output = ListExamples.merge(input1, input2);
        List<String> expected = Arrays.asList(new String[]{"a", "b"});
        Assert.assertArrayEquals(expected.toArray(), output.toArray());
    }
    // Merge: one empty lists
    @Test(timeout = 100)
    public void testMerge3() {
        List<String> input1 = Arrays.asList(new String[]{"a", "b"});
        List<String> input2 = Arrays.asList(new String[]{});
        List<String> output = ListExamples.merge(input1, input2);
        List<String> expected = Arrays.asList(new String[]{"a", "b"});
        Assert.assertArrayEquals(expected.toArray(), output.toArray());
    }
    // Merge: duplicate elements
    @Test(timeout = 100)
    public void testMerge4() {
        List<String> input1 = Arrays.asList(new String[]{"a", "b", "c"});
        List<String> input2 = Arrays.asList(new String[]{"a", "b", "c"});
        List<String> output = ListExamples.merge(input1, input2);
        List<String> expected = Arrays.asList(new String[]{"a", "a", "b", "b", "c", "c"});
        Assert.assertArrayEquals(expected.toArray(), output.toArray());
    }
    // Merge: one is strictly less than the other
    @Test(timeout = 100)
    public void testMerge5() {
        List<String> input1 = Arrays.asList(new String[]{"a", "b"});
        List<String> input2 = Arrays.asList(new String[]{"y", "z"});
        List<String> output = ListExamples.merge(input1, input2);
        List<String> expected = Arrays.asList(new String[]{"a", "b", "y", "z"});
        Assert.assertArrayEquals(expected.toArray(), output.toArray());
    }
    // Filter: simple
    @Test(timeout = 100)
    public void testFilter0() {
        List<String> input = Arrays.asList(new String[]{"milan", "manchester united", "manchester city", "liverpool", "newcastle united", "west ham united"});
        List<String> expected = Arrays.asList(new String[]{"manchester united", "newcastle united", "west ham united"});
        StringChecker sc = new SChecker();
        List<String> output = ListExamples.filter(input, sc);
        Assert.assertArrayEquals(expected.toArray(), output.toArray());
    }
    // Filter: empty lists
    @Test(timeout = 100)
    public void testFilter1() {
        List<String> input = Arrays.asList(new String[]{});
        List<String> expected = Arrays.asList(new String[]{});
        StringChecker sc = new SChecker();
        List<String> output = ListExamples.filter(input, sc);
        Assert.assertArrayEquals(expected.toArray(), output.toArray());
    }
    // Filter: no match
    @Test(timeout = 100)
    public void testFilter2() {
        List<String> input = Arrays.asList(new String[]{"real madrid", "barcelona", "bayern munich"});
        List<String> expected = Arrays.asList(new String[]{});
        StringChecker sc = new SChecker();
        List<String> output = ListExamples.filter(input, sc);
        Assert.assertArrayEquals(expected.toArray(), output.toArray());
    }
    // Filter: duplicate
    @Test(timeout = 100)
    public void testFilter3() {
        List<String> input = Arrays.asList(new String[]{"united states", "united states"});
        List<String> expected = Arrays.asList(new String[]{"united states", "united states"});
        StringChecker sc = new SChecker();
        List<String> output = ListExamples.filter(input, sc);
        Assert.assertArrayEquals(expected.toArray(), output.toArray());
    }
}
