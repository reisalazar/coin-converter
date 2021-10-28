package br.com.dio.coinconverter.data.repository

import br.com.dio.coinconverter.core.exceptions.RemoteException
import br.com.dio.coinconverter.data.model.ErrorResponse
import br.com.dio.coinconverter.data.services.AwesomeService
import com.google.gson.Gson
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class CoinRepositoryImpl(
    private val service: AwesomeService
) : CoinRepository {

    override suspend fun getExchageValue(coins: String) = flow {
        try {
            val exchangeValue = service.exchangeValue(coins)
            val exchange = exchangeValue.values.first()
            emit(exchange)
        }catch (e: HttpException) {
            val json = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(json, ErrorResponse::class.java)
            throw RemoteException(errorResponse.message)
        }
    }
}