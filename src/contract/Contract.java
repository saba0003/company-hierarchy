package contract;

import company.Company;

public class Contract {

    private String contractId;
    private Company company;
    private Client client;
    private double value;

    public Contract(String contractId, Company company, Client client, double value) {
        this.contractId = contractId;
        this.company = company;
        this.client = client;
        this.value =value;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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
