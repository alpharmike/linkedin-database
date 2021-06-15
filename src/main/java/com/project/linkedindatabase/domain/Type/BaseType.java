package com.project.linkedindatabase.domain.Type;

import com.project.linkedindatabase.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public abstract class BaseType extends BaseEntity {

    private String name;// name or description or id code or ...
}
