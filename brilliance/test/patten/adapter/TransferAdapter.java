package patten.adapter;

import patten.adapter.model.Appliance;
import patten.adapter.model.Electric;

public interface TransferAdapter {
    Electric toElectric(Appliance app);
}
