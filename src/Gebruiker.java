public class Gebruiker {
    private String gebruikersnaam;

    private String wachtwoord;



    public Gebruiker(String gebruikersnaam, String wachtwoord){
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;

    }

    public String getGebruikersnaam(){
        return this.gebruikersnaam;
    }

    public String getWachtwoord(){
        return this.wachtwoord;
    }


    public void setGebruikersnaam(String gebruikersnaam){
        this.gebruikersnaam = gebruikersnaam;
    }

    public void setWachtwoord(String wachtwoord){
        this.wachtwoord = wachtwoord;
    }

}
