
package Assign2;

import java.time.LocalDate;

public class Manager extends Employee implements Approver{
    
     public Manager(String name, int id, double salary) {
        super(name, id, salary);
       
    }
     
     public double calculateBonus(){
       double salary = super.getSalary();
       double bouns =0.15;
       return salary*bouns;
    }
     
     @Override
    public void approveLeave(Employee employee, LocalDate date, int id) {
        Leave leave = new Leave(employee,date,id);
        employee.setStateOnLeave("Leave");
        employee.addLeaveRecord(leave);
    }
    
     @Override
     public void markEmployeeAsWorking(Employee employee) {
       
        employee.setStateWorking("Working");
    }
     
}
