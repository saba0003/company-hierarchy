package contract;

public class Contract {

    private String contractId;
    private Client client;
    private double value;

    public Contract(String contractId, Client client, double value) {
        this.contractId = contractId;
        this.client = client;
        this.value = value;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
