import javax.swing.*;

public class ToernooiMenu extends SubMenu {

    public ToernooiMenu(){
        renameButtons();
    }

    @Override
    public void renameButtons(){
        setTextLijst("Toernooienlijst");
        setTextToevoegen("Toernooi wijzigen");
    }

}
