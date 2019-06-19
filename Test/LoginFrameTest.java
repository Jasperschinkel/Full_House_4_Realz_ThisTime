import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginFrameTest {

   @Test
    public void loginTest(){
       LoginFrame loginFrame = new LoginFrame();
       loginFrame.setUsername("deAssistent");
       loginFrame.setPassword("Assistent2019");
       assertTrue(loginFrame.validateUser());
   }


}