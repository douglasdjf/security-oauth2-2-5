package com.sprincsecurity.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("securityapi")
public class SecurityProperties {
    private String origenPermitida;

    private final Seguranca seguranca  = new Seguranca();

    public Seguranca getSeguranca() {
        return seguranca;
    }

    public String getOrigenPermitida() {
        return origenPermitida;
    }

    public void setOrigenPermitida(String origenPermitida) {
        this.origenPermitida = origenPermitida;
    }

    public static class Seguranca{

        private boolean enableHttps;

        public boolean isEnableHttps() {
            return enableHttps;
        }

        public void setEnableHttps(boolean enableHttps) {
            this.enableHttps = enableHttps;
        }

    }
}
