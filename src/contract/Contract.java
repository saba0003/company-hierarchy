package contract;

import enums.ContractType;

public class Contract {

    private String contractId;
    private Client client;
    private double value;
    private final ContractType contractType;

    public Contract(String contractId, Client client, double value, ContractType contractType) {
        this.contractId = contractId;
        this.client = client;
        this.value = value;
        this.contractType = contractType;
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

    public ContractType getContractType() {
        return contractType;
    }
}
