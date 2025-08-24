package com.wsdev.maintenanceSystem.Database.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private String name;

    public enum Values
    {
        ADMIN( 1L ),
        BASIC( 2L );

        private final long roleId;

        Values( long roleId )
        {
            this.roleId = roleId;
        }

        public long getRoleId()
        {
            return roleId;
        }
    }
}
