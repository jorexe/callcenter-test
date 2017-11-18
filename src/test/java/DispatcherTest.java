import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class DispatcherTest {

    @Test
    public void testEmployeeConcurrency() {
        Dispatcher dispatcher = new Dispatcher();
        int employees = 1000;
        IntStream.range(0, employees).parallel().forEach(i -> dispatcher.addEmployee(new Employee(i, EmployeeType.OPERATOR, dispatcher)));
        assertEquals(dispatcher.getEmployees().size(), employees);
    }

    @Test
    public void testPendingCallsConcurrency() {
        Dispatcher dispatcher = new Dispatcher();
        int calls = 1000;
        IntStream.range(0, calls).parallel().forEach(i -> dispatcher.addCall(new Call(i)));
        assertEquals(dispatcher.getPendingCalls().size(), calls);
    }

}
