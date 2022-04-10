package patten.mediator.model;

import patten.mediator.Mediator;

public class Colleague {
    //员工公共属性
    private String Name;
    //中介
    Mediator mediator;
    public void setMediator(Mediator mediator){
        this.mediator = mediator;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
