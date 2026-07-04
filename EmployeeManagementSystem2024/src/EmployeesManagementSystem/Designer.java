
package Assign2;


public class Designer extends Employee {

    public Designer(String name, int id, double salary) {
        super(name, id, salary);
    }

    @Override
    public double calculateBonus() {

        return (super.getSalary() * .1);
    }

}

