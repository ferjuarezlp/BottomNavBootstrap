package com.ferjuarez.bottomnavigationbootstrap.models.account;

import com.google.gson.Gson;

public class Account {
    private int usuarioId;
    private String usuarioNombre;
    private String usuarioUserName;
    private String usuarioMail;
    private String usuarioIdCliente;
    private int clienteId;
    private String clienteNombre;
    private String clientePrefijo;
    private String clienteShippingMark;
    private String maxCodRelev;

    public Account(String usuarioMail, String usuarioUserName, String clientePrefijo){
        this.usuarioMail = usuarioMail;
        this.usuarioUserName = usuarioUserName;
        this.clientePrefijo = clientePrefijo;
    }

    private Account(Builder builder) {
        usuarioId = builder.usuarioId;
        usuarioNombre = builder.usuarioNombre;
        usuarioUserName = builder.usuarioUserName;
        usuarioMail = builder.usuarioMail;
        usuarioIdCliente = builder.usuarioMail;
        clienteId = builder.clienteId;
        clienteNombre = builder.clienteNombre;
        clientePrefijo = builder.clientePrefijo;
        clienteShippingMark = builder.clienteShippingMark;
        maxCodRelev = builder.maxCodRelev;
    }

    //region Getters
    public int getUsuarioId() {
        return usuarioId;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public String getUsuarioUserName() {
        return usuarioUserName;
    }

    public String getUsuarioMail() {
        return usuarioMail;
    }

    public String getUsuarioIdCliente() {
        return usuarioIdCliente;
    }

    public int getClienteId() {
        return clienteId;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public String getClientePrefijo() {
        return clientePrefijo;
    }

    public String getClienteShippingMark() {
        return clienteShippingMark;
    }

    public String getMaxCodRelev() {
        return maxCodRelev;
    }
    //endregion

    //region Setters

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public void setUsuarioUserName(String usuarioUserName) {
        this.usuarioUserName = usuarioUserName;
    }

    public void setUsuarioMail(String usuarioMail) {
        this.usuarioMail = usuarioMail;
    }

    public void setUsuarioIdCliente(String usuarioIdCliente) {
        this.usuarioIdCliente = usuarioIdCliente;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public void setClientePrefijo(String clientePrefijo) {
        this.clientePrefijo = clientePrefijo;
    }

    public void setClienteShippingMark(String clienteShippingMark) {
        this.clienteShippingMark = clienteShippingMark;
    }

    public void setMaxCodRelev(String maxCodRelev) {
        this.maxCodRelev = maxCodRelev;
    }

    //endregion

    public static final class Builder {
        private int usuarioId;
        private String usuarioNombre;
        private String usuarioUserName;
        private String usuarioMail;
        private String usuarioIdCliente;
        private int clienteId;
        private String clienteNombre;
        private String clientePrefijo;
        private String clienteShippingMark;
        private String maxCodRelev;

        public Builder() {
        }

        public Builder id(int val) {
            usuarioId = val;
            return this;
        }

        public Builder name(String val) {
            usuarioNombre = val;
            return this;
        }

        public Builder username(String val) {
            usuarioUserName = val;
            return this;
        }

        public Builder mail(String val) {
            usuarioMail = val;
            return this;
        }

        public Builder userClientID(String val) {
            usuarioIdCliente = val;
            return this;
        }

        public Builder clientID(int val) {
            clienteId = val;
            return this;
        }

        public Builder clientName(String val) {
            clienteNombre = val;
            return this;
        }

        public Builder clientPrefix(String val) {
            clientePrefijo = val;
            return this;
        }

        public Builder clientShippingMark(String val) {
            clienteShippingMark = val;
            return this;
        }

        public Builder maxCodeRel(String val) {
            maxCodRelev = val;
            return this;
        }

        public Account build() {
            return new Account(this);
        }
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}