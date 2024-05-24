    package com.example.creditcard2.Service

    import com.example.creditcard2.Model.CreditCard
    import retrofit2.http.GET

    interface CreditCardService {
        @GET("2")
        suspend fun getCreditCard(): List<CreditCard>
    }