package com.wolf.rxshopper.data.utils

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type


class NullOrEmptyConverterFactory: Converter.Factory() {
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        val delegate: Converter<ResponseBody, *> = retrofit.nextResponseBodyConverter<Any>(
            this, type, annotations
        )

        return Converter<ResponseBody, Any?> {
            return@Converter if (it.contentLength() == 0L) null else delegate.convert(it)
        }

    }
}