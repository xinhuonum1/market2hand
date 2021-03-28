package com.immortal.market2hand;

import com.immortal.market2hand.util.MailUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@SpringBootTest
class Market2handApplicationTests {


    @Test
    void mailTest() throws MessagingException {
        MailUtils.sendMail("876332171@qq.com");
    }

}
