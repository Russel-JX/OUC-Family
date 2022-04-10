package patten.mediator.model;

public class HR extends Colleague {
    public HR(String name) {
        super.setName(name);
    }

    public void doAttendance(Colleague colleague){
        System.out.println("给"+colleague.getName()+"统计考勤");
    }

}
