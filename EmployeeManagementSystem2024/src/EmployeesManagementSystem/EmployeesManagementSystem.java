package Assign2;

import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.util.ArrayList;


//-----------------------------------------------------------------------------------------------------------------------------
public class EmployeesManagementSystem {

    private static ArrayList<Employee> ALemployees = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {

        PrintWriter pw;
        Scanner in;

        File input = new File("input.txt");
        File output = new File("output.txt");

        if (!input.exists()) {
            System.out.println("input file dosen't exists");
            System.exit(0);
        }
        // Scaning
        in = new Scanner(input);
        // print writer 
        pw = new PrintWriter(output);
        

   
        ALemployees  = new ArrayList<>();
        String line;
        String[] command = new String[5];
        String[] firstW = new String[3];

        while (in.hasNext()) {
            line = in.nextLine();
            command = line.split("[,]");
            firstW = command[0].split("[_]");

            switch (firstW[0]) {
                case "Add":
                    command[0] = firstW[1];
                    pw.println(addEmployee(command));
                    break;
                case "Give":
                    int IDmanager = Integer.parseInt(command[1].trim());
                    int IDemployee = Integer.parseInt(command[2].trim());
                    LocalDate start = LocalDate.parse(command[3].trim());
                    int days = Integer.parseInt(command[4].trim());
                    if (findEmployeeById(IDmanager) != null && findEmployeeById(IDemployee) != null) {
                        pw.println(approveLeave(IDmanager, IDemployee, start, days));
                    } else {
                        pw.println("Leave approval failed. Either manager ID is incorrect or employee ID does not exist.");
                    }
                    break;
                case "Change":
                    IDmanager = Integer.parseInt(command[1].trim());
                    IDemployee = Integer.parseInt(command[2].trim());
                    pw.println(markEmployeeAsWorking(IDmanager, IDemployee));
                    break;
                case "del":
                    if (firstW[1].equals("Employee")) {
                        int iD = Integer.parseInt(command[1].trim());
                        pw.println(removeEmployee(iD));
                    }
                    break;
                case "printAllEmployees":
                    pw.print(printAllEmployees());
                    break;
                case "print":
                    if (firstW[1].equals("Leave")) {
                        pw.println(printEmployeeLeaveRecords(Integer.parseInt(command[1].trim())));
                    }
                    break;
            }
        }

        pw.close();
        pw.flush();

    }

    public static String addEmployee(String details[]) {
        // crate object to add employees
        Employee Emplo = null;
        if (details[0].trim().equalsIgnoreCase("Manager")) {
            Emplo = new Manager(details[1].trim(), Integer.parseInt(details[2].trim()), Double.parseDouble(details[3].trim()));
        } else if (details[0].trim().equalsIgnoreCase("Developer")) {
            Emplo = new Developer(details[1].trim(), Integer.parseInt(details[2].trim()), Double.parseDouble(details[3].trim()));
        } else if (details[0].trim().equalsIgnoreCase("Designer")) {
            Emplo = new Designer(details[1].trim(), Integer.parseInt(details[2].trim()), Double.parseDouble(details[3].trim()));
        }

        // emoloyee info are in the array   
        ALemployees.add(Emplo);

        // use the instanceOf for checking the rank of the employee
        if (Emplo instanceof Manager) {
            return "Manager " + Emplo.getName() + "  added.";
        } else if (Emplo instanceof Developer) {
            return "Developer " + Emplo.getName() + "  added.";
        } else if (Emplo instanceof Designer) {
            return "Designer " + Emplo.getName() + "  added.";
        }

        
        return "Employee adding failed";

    }

    public static String removeEmployee(int id) {
       Employee employee = findEmployeeById(id);
    if (employee != null) {
        ALemployees.remove(employee);
        return "Employee removed successfully.";
    }
    return "Employee with ID " + id + " not found.";

    }

    public static String printAllEmployees() {
        StringBuilder sbuild = new StringBuilder();
        
        sbuild.append(String.format("%-15s %-6s %-10s %-10s", "Name", "ID", "Salary", "Bonus")).append("\n");
        // ittrate to print all employees
        for (int i = 0; i < ALemployees.size(); i++) {
            Employee Emplo = (Employee) ALemployees.get(i);
            sbuild.append(Emplo.toString());
            sbuild.append("\n");
        }
      //all employees saved in string builder printed
        return sbuild.toString();
    }

    public static String approveLeave(int managerId, int employeeId, LocalDate startDate, int days) {
      
        
        //to  find employee
        Employee Emplo = findEmployeeById(employeeId);          
        //to find Manager
        Manager Man = (Manager)findEmployeeById(managerId);     
        
        //check if working or not
  
        if (Emplo.getState().equals("Working")){
            Man.approveLeave(Emplo, startDate, days);
            return "Leave approved for " + Emplo.getName();
        }
        
        // if the state not working
        return "Cannot approve leave for  " + Emplo.getName() + " as they are already on leave.";

    }

    public static String markEmployeeAsWorking(int managerId, int employeeId) {
        //look for the Manager
        Manager Mange = (Manager) findEmployeeById(managerId);
        
        //look for the employee
        Employee Emplo = findEmployeeById(employeeId);
        
        //if both exists
        if (Mange != null && Emplo != null){
            Mange.markEmployeeAsWorking(Emplo);
            return "Employee " + Emplo.getName() + " is now marked as working.";
        }
        
        // if the employee dosent exits
        return " Either manager ID is incorrect or employee ID does not exist"; 
    }

    public static String printEmployeeLeaveRecords(int id) {
       StringBuilder sb = new StringBuilder();
        Employee Emplo = findEmployeeById(id);
        sb.append("Leave Records for ").append(Emplo.getName()).append(":\n"); 
        sb.append("Start Date\tDays of Leave\n");
     for ( int i = 0 ; i< Emplo.getLeaveRecords().size(); i++ ) {
        Leave leave = (Leave) Emplo.getLeaveRecords().get(i);
   
   
      
        sb.append(leave.displayLeaveDetails());
        sb.append("\n");
       
    
   }
    return sb.toString();
    }     
    

    private static Employee findEmployeeById(int id) {
    // check the employee id 
    for (Employee emplo : ALemployees) {
        if (emplo.getId() == id) {
            return emplo; // Return the employee if the id matches
        }
    }// Return null if no employee with the given id is found
    return null; 
    }
}
    
    

