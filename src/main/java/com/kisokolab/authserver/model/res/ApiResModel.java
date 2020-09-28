package com.kisokolab.authserver.model.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResModel {
    int code;
    boolean status;
    Object message;

    public ApiResModel(int i, boolean b, String s) {
    }
}

