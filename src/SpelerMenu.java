public class SpelerMenu extends SubMenu {

    public SpelerMenu(){
        renameButtons();
    }

    @Override
    public void renameButtons(){
        super.setTextLijst("Spelerlijst");
        super.setTextToevoegen("Speler wijzigen");
    }
}
