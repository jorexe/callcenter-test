import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Dispatcher {

    private final Queue<Employee> employees = new PriorityQueue<>(Employee.employeeComparator());
    private final Queue<Call> pendingCalls = new LinkedList<>();

    public void addEmployee(Employee employee) {
        synchronized (employees) {
            this.employees.add(employee);
        }
    }

    public void addCall(Call call) {
        synchronized (employees) {
            if (!employees.isEmpty()) {
                Employee employee = employees.remove();
                employee.pickUpCall(call);
            } else {
                synchronized (pendingCalls) {
                    pendingCalls.add(call);
                }
            }
        }
    }

    public void endCall(Employee employee) {
        synchronized (pendingCalls) {
            if (pendingCalls.isEmpty()) {
                synchronized (employees) {
                    employees.add(employee);
                }
            } else {
                employee.pickUpCall(pendingCalls.remove());
            }
        }
    }

    public void waitUntilEnd() throws InterruptedException {
        while (!pendingCalls.isEmpty()) {
            TimeUnit.SECONDS.sleep(1);
        }
    }

    public Queue<Employee> getEmployees() {
        return employees;
    }

    public Queue<Call> getPendingCalls() {
        return pendingCalls;
    }
}
