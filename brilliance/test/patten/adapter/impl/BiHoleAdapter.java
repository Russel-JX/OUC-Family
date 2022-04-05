package patten.adapter.impl;

import patten.adapter.TransferAdapter;
import patten.adapter.model.Appliance;
import patten.adapter.model.Electric;
import patten.adapter.model.USBAppliance;
import patten.adapter.model.USBElectric;

public class BiHoleAdapter extends Adapter{
    public Electric toElectric(Appliance app){
        return new USBElectric();
    }

}
