package patten.mediator.model;

public class Coder extends Colleague {
    public Coder(String name) {
        super.setName(name);
    }
    public void developOA(Colleague colleague){
        System.out.println("给"+colleague.getName()+"开发OA系统");
    }
    
}
