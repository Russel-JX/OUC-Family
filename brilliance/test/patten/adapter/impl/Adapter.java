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

    /**
     * 根据不同input,返回/创建不同对象。这里用简单工厂模式实现。缺点：每次增加新的适配器，要改这里代码。
     * 如果这里改成工厂方法模式或抽象工厂模式，虽然每次增加新的适配器，不用要改这里代码。但还是要在一个地方
     * 维护"各种电器->各种适配器"的映射关系，每次增加新适配器，还是要改映射关系的代码。
     * 所以索新就在这里维护好了，用简单方法模式，不用工厂方法或抽象工厂模式。
     */
    public Adapter getAdapter(Appliance app){
        if(app instanceof USBAppliance){
            return new USBAdapter();
        }else if(app instanceof BiHoleAppliance){
            return new BiHoleAdapter();
        }
        return null;
    }
}
