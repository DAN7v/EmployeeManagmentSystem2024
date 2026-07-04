
package Assign2;

import java.util.ArrayList;

public abstract class Employee {

    private String name;
    private int id;
    private double salary;
    private String state;
    private ArrayList<Leave> leaveRecordS = new ArrayList<>();

    //-----------------------------------------------------------------------------------------------------------------------------
    public Employee(String name, int id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
        this.leaveRecordS = new ArrayList<>();
        state = "Working";
    }

    //-----------------------------------------------------------------------------------------------------------------------------
    public abstract double calculateBonus();

    //-----------------------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {

        String s = String.format("%-8s  %9s  %10.2f %10.2f ", name, id, salary, calculateBonus());
        return (s);

    }
    
    //-----------------------------------------------------------------------------------------------------------------------------
    public void setStateOnLeave(String state){
       this.state =state;
    }

    //-----------------------------------------------------------------------------------------------------------------------------
    public void setStateWorking(String state) {
        this.state = state;
    }

    //-----------------------------------------------------------------------------------------------------------------------------
    public void addLeaveRecord(Leave leave) {
        leaveRecordS.add(leave);
    }
    
    //-----------------------------------------------------------------------------------------------------------------------------
    public ArrayList getLeaveRecords() {
        return leaveRecordS;
    }
    
    //-----------------------------------------------------------------------------------------------------------------------------
    public String getName() {
        return name;
    }
    
    //-----------------------------------------------------------------------------------------------------------------------------
    public int getId() {
        return id;
    }
    
    //-----------------------------------------------------------------------------------------------------------------------------
    public String getState() {
        return state;
    }
    
    //-----------------------------------------------------------------------------------------------------------------------------
    public double getSalary() {
        return salary;
    }
    

}
