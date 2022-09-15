package com.example.retotecnico

const val REGEX_PASSWORD =
    "^" + "(?=.*[0-9])" + "(?=.*[a-z])" + "(?=.*[A-Z])" + "(?=.*[-!@#$%^&*()_=+{};:,<.>])" +
            "(?=\\S+$)" + ".{8,}" + "$"