package org.jeejeejango;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class TeamServiceApplicationTests {

    @BeforeClass
    public static void init() {
        org.apache.catalina.webresources.TomcatURLStreamHandlerFactory.getInstance();
    }


    @Test
    public void contextLoads() {
    }

}
