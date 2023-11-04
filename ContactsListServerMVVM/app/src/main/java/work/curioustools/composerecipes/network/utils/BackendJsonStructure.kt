package work.curioustools.composerecipes.network.utils

// a helper class to provide additional common parameters to response.
// usually any api response has these fields as common so this class
// can be used as a wrapper of many response
data class BackendJsonStructure<T> (
     val data: T? = null,
     val page: Int = -1,
     val per_page: Int = -1,
     val total: Int = -1,
     val total_pages: Int = -1,
     val error:String? = null,
)

