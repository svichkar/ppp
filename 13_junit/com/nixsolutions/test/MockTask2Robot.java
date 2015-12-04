import com.nixsolutions.Program;
import com.nixsolutions.Robot;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by kozlovskij on 12/4/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class MockTask2Robot {
    @Mock
    private Robot program;
    @InjectMocks
    private Program robot;


}
