package com.wsdev.maintenanceSystem.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressModel
{
    @NotBlank( message = "A rua é obrigatória." )
    @Column( nullable = false )
    @Size( max = 150 )
    private String street;

    @NotBlank( message = "O número é obrigatório." )
    @Column( nullable = false )
    @Size( max = 20 )
    private String number;

    @Size( max = 50 )
    private String complement;

    @NotBlank( message = "O bairro é obrigatório.")
    @Column( nullable = false )
    @Size( max = 50 )
    private String neighborhood;

    @NotBlank( message = "A cidade é obrigatória." )
    @Column( nullable = false )
    @Size( max = 50 )
    private String city;

    @NotBlank( message = "O estado é obrigatório." )
    @Column( nullable = false )
    @Size( max = 5 )
    @Pattern( regexp = "AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO",
            message = "Estado deve ser uma sigla válida (ex: SP, RJ)." )
    private String state;

    @NotBlank( message = "O CEP é obrigatório." )
    @Column( nullable = false )
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato 00000-000." )
    private String postalCode;
}

