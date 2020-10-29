package com.aaron.design.solid;

import lombok.Data;

/**
 * The type Customer.
 */
@Data
public class Customer {

    String customerId;
    String customerFirstName;
    String CustomerLastName;
    String streetAddress1;
    String streetAddress2;
    String city;
    String postacode;
    String contryu,
    String homePhone;
    String mobilePhone;
    String primaryEmailAddress;


    /**
     * 不清楚這個 saveCustomer 的場景是什麼？
     * 方法接收的參數太多（大而全？）
     */
public Customer saveCustomer(
        String customerId,
        String customerFirstName,
        String CustomerLastName,
        String streetAddress1,
        String streetAddress2,
        String city,
        String postacode,
        String contryu,
        String homePhone,
        String mobilePhone,
        String primaryEmailAddress){

       if(customer == null ) {
           customer = new Customer();
           customer.setCustomerId(customerId);
       }

        customer.setCustomerFirstName(customerFirstName);
        customer.setCustomerLastName(customerLanstName);
        //.....略 setter method

        customeDao.saveCustomer(customer);

        return customer;
       }


    /**
     * 意圖不明顯
     * 方法實現本身添加複雜性
     * Customer 不是一個領域物件，他只是個資料載體
     */
    public Customer saveCustomerRevised(
            String customerId,
            String customerFirstName,
            String CustomerLastName,
            String streetAddress1,
            String streetAddress2,
            String city,
            String postacode,
            String contryu,
            String homePhone,
            String mobilePhone,
            String primaryEmailAddress){

    Customer customer = custimerDao.readCustomer(customerId);
    if(customer == null){
        customer = new Customer();
        customer.setCustomerId(customerId);
    }

    if(customer.getCustomerFirstName()!=null){
        customer.setCustomerFirstName(customerFirstName);
    }

    if(customer.getCustomerLastName()!=null){
        customer.setCustomerLastName(customerLastName);
    }

    //.....略 getter and setter method

    return customer;

    }

    /**
     * 實現方式-1
     */
    Patient.setShotType(ShotTypes.TYPE_FLU);
    Patient.setDose(dose); // 劑量
    Patient.setNurse(nurse);

    /**
     * 實現方式-2
     */
    Patient.giveFlushot();

    /**
     * 實現方式-3
     */
    Vaccine vaccine = Vaccines.standardAdultFluDose();
    nurse.administerFluVaccine(patient, vaccine);
}
