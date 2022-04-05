package patten.adapter.impl;

import patten.adapter.TransferAdapter;
import patten.adapter.model.Appliance;
import patten.adapter.model.BiHoleAppliance;
import patten.adapter.model.Electric;
import patten.adapter.model.USBAppliance;

public class Adapter implements TransferAdapter{
    public Electric toElectric(Appliance app){
        return null;
    }

    //根据不同input,返回/创建不同对象。这里用简单工厂模式实现，
    // 后续可refactor改善成工厂方法模式或抽象工厂模式
    public Adapter getAdapter(Appliance app){
        if(app instanceof USBAppliance){
            return new USBAdapter();
        }else if(app instanceof BiHoleAppliance){
            return new BiHoleAdapter();
        }
        return null;
    }
}
