import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Employee {

    private final static Random random = new Random();

    private int id;

    private Dispatcher dispatcher;

    private EmployeeType employeeType;

    private Call call;

    Employee(int id, EmployeeType employeeType, Dispatcher dispatcher) {
        this.id = id;
        this.dispatcher = dispatcher;
        this.employeeType = employeeType;
    }

    public void pickUpCall(Call call) {
        int callSeconds = random.nextInt(6) + 5;
        System.out.println(String.format("%s %d picking up call %d - %d seconds", employeeType, id, call.getId(), callSeconds));
        this.call = call;
        CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(callSeconds);
                hangUpCall();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private void hangUpCall() {
        System.out.println(String.format("%s %d hanging up call %d", employeeType, id, call.getId()));
        dispatcher.endCall(this);
    }

    public static Comparator<Employee> employeeComparator() {
        return Comparator.comparingInt(a -> a.employeeType.ordinal());
    }
}
