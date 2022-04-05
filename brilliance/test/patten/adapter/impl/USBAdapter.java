package patten.adapter.impl;

import patten.adapter.TransferAdapter;
import patten.adapter.model.Appliance;
import patten.adapter.model.BiHoleElectric;
import patten.adapter.model.Electric;
import patten.adapter.model.USBAppliance;

public class USBAdapter extends Adapter{
    public Electric toElectric(Appliance app){
        return new BiHoleElectric();
    }

}
