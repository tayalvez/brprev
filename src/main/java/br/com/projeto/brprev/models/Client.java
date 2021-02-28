package br.com.projeto.brprev.models;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "tb_clients")
public class Client extends AbstractEntity{
    @NotEmpty
    private String name;
    @NotEmpty
    private String cpf;
    @NotEmpty
    @Email
    private String email;
    private String adress;
    private String cep;


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
