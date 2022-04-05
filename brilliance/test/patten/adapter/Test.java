package patten.adapter;

import com.sun.tools.javac.util.List;
import patten.adapter.impl.Adapter;
import patten.adapter.model.Appliance;
import patten.adapter.model.BiHoleAppliance;
import patten.adapter.model.Electric;
import patten.adapter.model.USBAppliance;

public class Test {
    public static  void main(String[] args){
        List<Appliance> list = List.of(new USBAppliance(),new BiHoleAppliance());
        Adapter adapter = new Adapter();
        for(Appliance app : list){
            Adapter myAdapter = adapter.getAdapter(app);
            Electric elec = myAdapter.toElectric(app);
            System.out.println("app: "+app+" converted to: "+elec);
            //app: patten.adapter.model.USBAppliance@6e8cf4c6 converted to: patten.adapter.model.BiHoleElectric@12edcd21
            //app: patten.adapter.model.BiHoleAppliance@34c45dca converted to: patten.adapter.model.USBElectric@52cc8049
        }
    }
}
