package com.example.bicycleshop.security.model;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class FullAccountSecurityDataId implements Serializable {
    private long accountId;
    private long groupId;
    private long authorityId;
}
