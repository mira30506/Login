package mx.com.edlosproyect.sys.utils

interface Mapper<I, O> {
    suspend fun map(input: I): O
}