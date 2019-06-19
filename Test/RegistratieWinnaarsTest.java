import org.testng.annotations.Test;

import java.sql.*;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistratieWinnaarsTest {

    @Test
    public void getAantalTafelsFromDB() {
        RegistratieWinnaars registratieWinnaars = new RegistratieWinnaars(2,3);

        try{
            int toernooiCode = registratieWinnaars.getToernooiCode();
            Connection conn =
                    DriverManager.getConnection("jdbc:mysql://meru.hhs.nl/18095240", "18095240",
                            "Ene3shaise");
            PreparedStatement ps = conn.prepareStatement("Select aantal_tafels From Toernooi WHERE TC = ?;");
            ps.setInt(1, toernooiCode);
            ResultSet rst;
            rst = ps.executeQuery();
            if(rst.next()) {
                int aantalTafels = rst.getInt("aantal_tafels");
                registratieWinnaars.setAantelTafels(aantalTafels);

            }
        }catch (Exception e) {
            System.out.println(e);}

            assertEquals(14,registratieWinnaars.getAantaltafels());
    }


}