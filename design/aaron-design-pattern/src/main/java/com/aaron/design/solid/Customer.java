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
     * ������@�� saveCustomer �Ĉ�����ʲ�N��
     * �������յą���̫�ࣨ���ȫ����
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
        //.....�� setter method

        customeDao.saveCustomer(customer);

        return customer;
       }


    /**
     * ��D�����@
     * �������F��������}�s��
     * Customer ����һ���I���������ֻ�ǂ��Y���d�w
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

    //.....�� getter and setter method

    return customer;

    }

    /**
     * ���F��ʽ-1
     */
    Patient.setShotType(ShotTypes.TYPE_FLU);
    Patient.setDose(dose); // ����
    Patient.setNurse(nurse);

    /**
     * ���F��ʽ-2
     */
    Patient.giveFlushot();

    /**
     * ���F��ʽ-3
     */
    Vaccine vaccine = Vaccines.standardAdultFluDose();
    nurse.administerFluVaccine(patient, vaccine);
}
