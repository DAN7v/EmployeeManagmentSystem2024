
package Assign2;

import java.time.LocalDate;

public interface Approver {

    public abstract void approveLeave(Employee e, LocalDate d, int f);

    public abstract void markEmployeeAsWorking(Employee e);
}
