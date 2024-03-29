package it.interno.luoghi.enumeration;

public enum TipoLuogo {

    NAZIONI("01"),
    REGIONI("02"),
    PROVINCE("03"),
    COMUNI("04");

    private String codTipoLuogo;

    TipoLuogo(String codTipoLuogo) {
        this.codTipoLuogo = codTipoLuogo;
    }

    public String getCodTipoLuogo() {
        return this.codTipoLuogo;
    }
}
