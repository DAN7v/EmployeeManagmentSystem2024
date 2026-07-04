
package Assign2;

public class Developer extends Employee {

    public Developer(String name, int id, double salary) {
        super(name, id, salary);

    }

    @Override
    public double calculateBonus() {
        double salary = super.getSalary();
        double bouns = 0.12;
        return ( salary * bouns) ;
    }

}
