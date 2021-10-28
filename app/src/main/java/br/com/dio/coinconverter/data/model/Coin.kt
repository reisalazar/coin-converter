package br.com.dio.coinconverter.data.model

import java.util.*

enum class Coin( val locale: Locale) {

    USD(Locale.US),
    GBP(Locale.ENGLISH),
    CAD(Locale.CANADA),
    BRL(Locale("pt", "BR")),
    ARS(Locale("es", "AR"))

}