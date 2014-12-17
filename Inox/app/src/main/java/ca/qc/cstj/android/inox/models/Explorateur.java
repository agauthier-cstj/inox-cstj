package ca.qc.cstj.android.inox.models;

/**
 * Created by anthony on 12/11/14.
 */
public class Explorateur {
    private int idExplorateur;
    private String usager;
    private String href;

    public int getIdExplorateur() {
        return idExplorateur;
    }

    public void setIdExplorateur(int idExplorateur) {
        this.idExplorateur = idExplorateur;
    }

    public String getUsager() {
        return usager;
    }

    public void setUsager(String usager) {
        this.usager = usager;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
