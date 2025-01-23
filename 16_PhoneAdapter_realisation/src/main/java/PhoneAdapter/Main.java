package PhoneAdapter;

import itaphones.phone.*;

public class Main {
    public static void main(String[] args) {
        MobilePhone mobilePhone = new MobilePhone();
        Phone phoneAdapter = new PhoneAdapter(mobilePhone);

        // AutomaticDialer, работающий с интерфейсом Phone
        AutomaticDialer dialer = new AutomaticDialer(phoneAdapter);

        // Пытаемся набрать правильный номер
        Phone test = new MobilePhone();
        dialer.dial(test);

        // Пытаемся набрать неправильный номер
        dialer.dial("123456789");
    }
}
