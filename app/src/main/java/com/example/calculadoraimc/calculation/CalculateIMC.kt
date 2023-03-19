package com.example.calculadoraimc.calculation

import java.text.DecimalFormat

class CalculateIMC {

    fun calculateIMC(weight: String, height: String) : String{
        try{
            val result = weight.toDouble() / (height.toDouble() * height.toDouble())
            val decimalFormat = DecimalFormat("0.00")

            return if(result < 18.5)
                "Peso Baixo \nIMC: ${decimalFormat.format(result)}"
            else if(result <= 24.9)
                "Peso Normal \nIMC: ${decimalFormat.format(result)}"
            else if(result <= 29.9)
                "Sobrepeso \nIMC: ${decimalFormat.format(result)}"
            else if(result <= 34.9)
                "Obesidade (Grau 1) \nIMC: ${decimalFormat.format(result)}"
            else if( result <= 39.9)
                "Obsesidade Severa (Grau 2) \nIMC: ${decimalFormat.format(result)}"
            else "Obesidade Mórbida (Grau 3) \nIMC: ${decimalFormat.format(result)}t"
        }catch (ex: Exception){
            return "Falha ao tentar calcular, verifique se todos os campos foram preenchidos corretamente"
        }

    }
}