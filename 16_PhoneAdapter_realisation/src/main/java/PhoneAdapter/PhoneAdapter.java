package PhoneAdapter;

import itaphones.phone.MobilePhone;
import itaphones.phone.Phone;

public class PhoneAdapter implements Phone {
    private final MobilePhone mobilePhone;

    public PhoneAdapter(MobilePhone mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
    private String response = "";

    @Override
    public void dial(String s) {
        response = mobilePhone.dial(s);
    }

    @Override
    public String getReponse() {
        return response;
    }
}
