package PhoneAdapter;

import itaphones.phone.*;

public class Main {
    public static void main(String[] args) {
        MobilePhone mobilePhone = new MobilePhone();
        Phone phoneAdapter = new PhoneAdapter(mobilePhone);

        phoneAdapter.dial("+37060013245");
        System.out.println(phoneAdapter.getReponse());
    }
}
