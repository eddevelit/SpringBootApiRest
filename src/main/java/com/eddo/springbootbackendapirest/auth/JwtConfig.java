package com.eddo.springbootbackendapirest.auth;

public class JwtConfig {

    public static final String LLAVE_SECRETA = "alguna.clave.secreta.12345678";

    public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIBOQIBAAJBANo72I2Lq6aYpy1PfQdo6nst4XIqsNMCEbMYUe19ksFB/ZLTSrWU\n" +
            "zHdceV3PamUiEangKDA9Rdn+GW886dG/1skCAwEAAQJAJO+EE1DUWHn1sYGGk756\n" +
            "EScOxRfcnZn4DOvM0fky4WpNzWI8Wd3PqkSjlF4lgjwJ9hl9Rg1s5CJbryRQUfvo\n" +
            "QQIhAPGGQvxtLzHMYo54E0zveFPtym+JEey6BAcPuBuBu9tlAiEA51A7hGEWouHm\n" +
            "HmNXQnh9PS13Ci5ao8ZaRarxej/awZUCIE2TbEyb3T7HgRh1pO1fNWzvwcfVZPZ+\n" +
            "QPDrsZzULLypAiB7zCn+YIPf6uzhCKPT9G56xpF2RnbGPktkC+gvWGt4vQIgCl2m\n" +
            "MfFMWfFN5sJrLd34KpvgBA8sDwLcpR9S6JBaW1c=\n" +
            "-----END RSA PRIVATE KEY-----";

    public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\n" +
            "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBANo72I2Lq6aYpy1PfQdo6nst4XIqsNMC\n" +
            "EbMYUe19ksFB/ZLTSrWUzHdceV3PamUiEangKDA9Rdn+GW886dG/1skCAwEAAQ==\n" +
            "-----END PUBLIC KEY-----";


}
