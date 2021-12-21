package nl.saxion.cds.client;

import nl.saxion.cds.comparator.NumberOfOrdersComparator;
import nl.saxion.cds.parcel.Parcel;
import nl.saxion.cds.util.Searcher;

import java.util.HashMap;
import java.util.List;

public class ClientService {
    private final ClientDAO clientDAO;

    public ClientService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public Client getClient(Long id) {
        return this.clientDAO.get(id);
    }

    public List<String> getTop10Recipients(HashMap<Long, List<Parcel>> parcelsPerCustomer) {
        var top10 = Searcher.findTopK(this.clientDAO.getAll(), 10, new NumberOfOrdersComparator(parcelsPerCustomer));
        return top10.stream().map(Client::toString).toList();
    }
}
