package com.wsdev.maintenanceSystem.Models;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressModel
{
    @NotBlank( message = "A rua é obrigatória" )
    private String street;

    @NotBlank( message = "O número é obrigatório" )
    private String number;

    private String complement;

    @NotBlank( message = "O bairro é obrigatório")
    private String neighborhood;

    @NotBlank( message = "A cidade é obrigatória" )
    private String city;

    @NotBlank
    @Pattern( regexp = "AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO",
            message = "Estado deve ser uma sigla válida (ex: SP, RJ)" )
    private String state;

    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato 00000-000")
    private String postalCode;
}

