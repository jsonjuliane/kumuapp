package wanda.weiss.kumuchallenge.model.pojo

//Wrapper class to be able to handle throwable return and casting to error class
data class ItunesWrapper(val result: ItunesData?, val t: Throwable?)