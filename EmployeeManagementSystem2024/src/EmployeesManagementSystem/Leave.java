
package Assign2;

import java.time.LocalDate;

public class Leave {

    private Employee employee;
    private LocalDate startDate;
    private int daysOfLeave;
    //-----------------------------------------------------------------------------------------------------------------------------

    public Leave(Employee employee, LocalDate startDate, int daysOfLeave) {
        this.employee = employee;
        this.startDate = startDate;
        this.daysOfLeave = daysOfLeave;
    }


    public String displayLeaveDetails() {   
    

        
        return startDate+ "\t" + daysOfLeave;
        
    }
 }

