package patten.mediator;

import patten.mediator.model.Coder;
import patten.mediator.model.Colleague;
import patten.mediator.model.Finance;
import patten.mediator.model.HR;

public class MyMediator extends Mediator {

    public MyMediator(Colleague hr, Colleague finance,Colleague coder,Colleague manager) {
        super.setHr(hr);
        super.setFinance(finance);
        super.setCoder(coder);
        super.setManager(manager);
    }

    @Override
    public void doAttendance(Colleague colleague) {
        ((HR)hr).doAttendance(colleague);
    }

    @Override
    public void calculateSalary(Colleague colleague) {
        ((Finance)finance).calculateSalary(colleague);
    }

    @Override
    public void developOA(Colleague colleague) {
        ((Coder)coder).developOA(colleague);
    }
}
