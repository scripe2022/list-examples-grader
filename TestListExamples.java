import static org.junit.Assert.*;
import org.junit.*;
import org.hamcrest.*;
import org.hamcrest.CoreMatchers.*;

public class TestListExamples {
    @Test
    public void testMerge0() {
        List<String> input1 = Arrays.asList(new String[]{"a", "c", "e"});
        List<String> input2 = Arrays.asList(new String[]{"b", "d", "f"});
        List<String> output = ListExamples.merge(input1, input2);
        List<String> exp = Arrays.asList(new String[]{"a", "b", "c", "d", "e", "f"});
        assertThat(output, is(exp));
    }
}
