package patten.mediator.model;

public class Finance extends Colleague {
    public Finance(String name) {
        super.setName(name);
    }
    public void calculateSalary(Colleague colleague){
        System.out.println("给"+colleague.getName()+"计算工资");
    }
    
}
