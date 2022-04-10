package patten.mediator;

import patten.mediator.model.*;

public class Test {
    public static void main(String[] args){
        Colleague hr = new HR("Cathy");
        Colleague finance = new Finance("Lili");
        Colleague coder = new Coder("Russell");
        Colleague manager = new Manager("Bob");
        Mediator mediator = new MyMediator(hr,finance,coder,manager);
        hr.setMediator(mediator);
        finance.setMediator(mediator);
        coder.setMediator(mediator);
        manager.setMediator(mediator);

        mediator.doAttendance(finance);//财务让HR统计自己考勤
        mediator.doAttendance(coder);//码农让HR统计自己考勤
        mediator.developOA(hr);//HR让码农给自己开发OA
        mediator.developOA(finance);//财务让码农给自己开发OA

    }
}
