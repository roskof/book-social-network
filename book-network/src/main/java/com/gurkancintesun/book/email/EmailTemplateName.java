package com.gurkancintesun.book.email;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmailTemplateName {
    ACTIVATE_ACCOUNT("activate_account")

    ;

    private final String name;
}
