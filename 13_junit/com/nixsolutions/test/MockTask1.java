import com.nixsolutions.Program;
import com.nixsolutions.Robot;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kozlovskij on 12/4/2015.
 */
public class MockTask1 {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void shouldReturnEqualStringArray () throws IOException {
        File resultFile = folder.newFile("result.txt");
        List<String> actualResults = new ArrayList<>();
        String [] expectedResults = {"0.1", "0.2", "1.2", "1.3", "1.2", "1.1", "1.0"};
        String line;
        Robot robot = new Robot(resultFile);
        Program program = new Program(robot);

        program.run("lffrflfrrfff");

        BufferedReader bufferedReader = new BufferedReader(new FileReader(resultFile));
        while ((line = bufferedReader.readLine()) != null) {
            actualResults.add(line);
        }

        Assert.assertArrayEquals(expectedResults,actualResults.toArray());
    }
}
