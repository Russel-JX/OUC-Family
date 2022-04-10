package patten.mediator.model;

public class Manager extends Colleague {
    public Manager(String name) {
        super.setName(name);
    }
    public String manageProject(){
        return "manager给自己做事";
    }
    
}
