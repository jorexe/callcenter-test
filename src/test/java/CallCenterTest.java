import org.junit.Test;

import java.util.stream.IntStream;

public class CallCenterTest {

    private static final int CALLS_AMOUNT = 10;
    private static final EmployeeType[] employees = new EmployeeType[]{
            EmployeeType.OPERATOR,
            EmployeeType.OPERATOR,
            EmployeeType.SUPERVISOR,
            EmployeeType.DIRECTOR
    };

    @Test
    public void testDispatcher() throws InterruptedException {
        Dispatcher dispatcher = new Dispatcher();
        int i = 0;
        for (EmployeeType employee : employees) {
            dispatcher.addEmployee(new Employee(++i, employee, dispatcher));
        }
        IntStream.range(0, CALLS_AMOUNT).parallel().forEach(c -> dispatcher.addCall(new Call(c)));
        dispatcher.waitUntilEnd();
    }

}
