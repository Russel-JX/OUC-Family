package patten.mediator;

import patten.mediator.model.Colleague;

public abstract class Mediator {
    Colleague hr;
    Colleague finance;
    Colleague coder;
    Colleague manager;

    public abstract void doAttendance(Colleague colleague);
    public abstract void calculateSalary(Colleague colleague);
    public abstract void developOA(Colleague colleague);

    public Colleague getHr() {
        return hr;
    }

    public void setHr(Colleague hr) {
        this.hr = hr;
    }

    public Colleague getFinance() {
        return finance;
    }

    public void setFinance(Colleague finance) {
        this.finance = finance;
    }

    public Colleague getCoder() {
        return coder;
    }

    public void setCoder(Colleague coder) {
        this.coder = coder;
    }

    public Colleague getManager() {
        return manager;
    }

    public void setManager(Colleague manager) {
        this.manager = manager;
    }
}
