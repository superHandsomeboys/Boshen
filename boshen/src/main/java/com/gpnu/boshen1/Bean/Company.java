package com.gpnu.boshen1.Bean;

public class Company {
    private Integer company_id;
    private String introduce;
    private Integer customer_quantity;
    private Integer member_quantity;
    private Integer project_quantity;
    private Integer to_company_quantity;
    private Integer consult_index_id;
    private Integer train_index_id;

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Integer getCustomer_quantity() {
        return customer_quantity;
    }

    public void setCustomer_quantity(Integer customer_quantity) {
        this.customer_quantity = customer_quantity;
    }

    public Integer getMember_quantity() {
        return member_quantity;
    }

    public void setMember_quantity(Integer member_quantity) {
        this.member_quantity = member_quantity;
    }

    public Integer getProject_quantity() {
        return project_quantity;
    }

    public void setProject_quantity(Integer project_quantity) {
        this.project_quantity = project_quantity;
    }

    public Integer getTo_company_quantity() {
        return to_company_quantity;
    }

    public void setTo_company_quantity(Integer to_company_quantity) {
        this.to_company_quantity = to_company_quantity;
    }

    public Integer getConsult_index_id() {
        return consult_index_id;
    }

    public void setConsult_index_id(Integer consult_index_id) {
        this.consult_index_id = consult_index_id;
    }

    public Integer getTrain_index_id() {
        return train_index_id;
    }

    public void setTrain_index_id(Integer train_index_id) {
        this.train_index_id = train_index_id;
    }

    @Override
    public String toString() {
        return "Company{" +
                "company_id=" + company_id +
                ", introduce='" + introduce + '\'' +
                ", customer_quantity=" + customer_quantity +
                ", member_quantity=" + member_quantity +
                ", project_quantity=" + project_quantity +
                ", to_company_quantity=" + to_company_quantity +
                ", consult_index_id=" + consult_index_id +
                ", train_index_id=" + train_index_id +
                '}';
    }
}
