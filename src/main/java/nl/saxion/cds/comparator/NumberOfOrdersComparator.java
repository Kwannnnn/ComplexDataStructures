package nl.saxion.cds.comparator;

import nl.saxion.cds.client.Client;
import nl.saxion.cds.parcel.Parcel;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class NumberOfOrdersComparator implements Comparator<Client> {
    private final HashMap<Long, List<Parcel>> parcelsPerCustomer;

    public NumberOfOrdersComparator(HashMap<Long, List<Parcel>> parcelsPerCustomer) {
        this.parcelsPerCustomer = parcelsPerCustomer;
    }

    @Override
    public int compare(Client o1, Client o2) {
        return getParcelsCountOfClient(o1)
                - getParcelsCountOfClient(o2);
    }

    private int getParcelsCountOfClient(Client client) {
        return this.parcelsPerCustomer.get(client.getId()).size();
    }
}
