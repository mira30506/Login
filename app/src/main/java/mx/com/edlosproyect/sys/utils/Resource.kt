package mx.com.edlosproyect.sys.utils

data class Resource<out T>(
    val status: Status,
    val data: T? = null,
    val message: ErrorHttp? = null){
    companion object{
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data)
        }
        fun <T> error(msg: ErrorHttp? = null): Resource<T> {
            return Resource(Status.ERROR, message =msg)
        }
        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}


data class ErrorHttp(val code:String, val message:String)