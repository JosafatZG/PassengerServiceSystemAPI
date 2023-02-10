package com.passengerservicesystemapi.entity;

import javax.persistence.*;

@Entity
@Table(name = "personas")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 30)
    private String primerNombre;
    @Column(length = 30)
    private String segundoNombre;
    @Column(length = 30)
    private String tercerNombre;
    @Column(nullable = false, length = 30)
    private String primerApellido;
    @Column(length = 30)
    private String segundoApellido;
    @Column(length = 30)
    private String apellidoDeCasada;
    @Column(length = 10)
    private String fechaNacimiento;
    public Persona(){

    }

    public Persona(Integer id, String primerNombre, String segundoNombre, String tercerNombre, String primerApellido, String segundoApellido, String apellidoDeCasada, String fechaNacimiento) {
        this.id = id;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.tercerNombre = tercerNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.apellidoDeCasada = apellidoDeCasada;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getTercerNombre() {
        return tercerNombre;
    }

    public void setTercerNombre(String tercerNombre) {
        this.tercerNombre = tercerNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getApellidoDeCasada() {
        return apellidoDeCasada;
    }

    public void setApellidoDeCasada(String apellidoDeCasada) {
        this.apellidoDeCasada = apellidoDeCasada;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Persona{");
        sb.append("id=").append(id);
        sb.append(", primerNombre='").append(primerNombre).append('\'');
        sb.append(", segundoNombre='").append(segundoNombre).append('\'');
        sb.append(", tercerNombre='").append(tercerNombre).append('\'');
        sb.append(", primerApellido='").append(primerApellido).append('\'');
        sb.append(", segundoApellido='").append(segundoApellido).append('\'');
        sb.append(", apellidoDeCasada='").append(apellidoDeCasada).append('\'');
        sb.append(", fechaNacimiento='").append(fechaNacimiento).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
