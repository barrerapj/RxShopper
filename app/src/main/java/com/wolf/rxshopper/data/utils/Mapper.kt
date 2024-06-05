package com.wolf.rxshopper.data.utils

interface Mapper<I, O> {
    fun map(input: I): O
}

// Non-nullable to Non-nullable
interface IListMapper<I, O> : Mapper<List<I>, List<O>>

class ListMapper<I, O>(
    private val mapper: Mapper<I, O>
) : IListMapper<I, O> {
    override fun map(input: List<I>): List<O> {
        return input.map { mapper.map(it) }
    }
}

// Nullable to Non-nullable
interface INullableInputListMapper<I, O> : Mapper<List<I>?, List<O>>

class NullableInputListMapper<I, O>(
    private val mapper: Mapper<I, O>
) : INullableInputListMapper<I, O> {
    override fun map(input: List<I>?): List<O> {
        return input?.map { mapper.map(it) }.orEmpty()
    }
}

// Non-nullable to Nullable
interface NullableOutputListMapper<I, O> : Mapper<List<I>, List<O>?>

class NullableOutputListMapperImpl<I, O>(
    private val mapper: Mapper<I, O>
) : NullableOutputListMapper<I, O> {
    override fun map(input: List<I>): List<O>? {
        return if (input.isEmpty()) null else input.map { mapper.map(it) }
    }
}