package prj.mob1.prjmob1.retrofitUtil

import android.content.Context
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import prj.mob1.prjmob1.Person.Person
import prj.mob1.prjmob1.retrofitUtil.models.CreditResponse
import prj.mob1.prjmob1.movie.MovieClass
import prj.mob1.prjmob1.season.Season
import prj.mob1.prjmob1.show.TVShow


//Created by sol on 14/06/2018.


interface RemoteApiService {

    @GET("movie/{movie_id}?api_key=$API_KEY&append_to_response=credits,similar,reviews")
    fun getMovieInfosById(@Path("movie_id") id: Int): Observable<MovieClass>

    @GET("person/{person_id}?api_key=$API_KEY&append_to_response=movie_credits,tv_credits")
    fun getPersonInfosById(@Path("person_id") id: Int): Observable<Person>

    @GET("tv/{tv_id}?api_key=$API_KEY&append_to_response=credits,similar,reviews")
    fun getShowInfosById(@Path("tv_id") id: Int): Observable<TVShow>

    @GET("tv/{tv_id}/season/{season_number}?api_key=$API_KEY&append_to_response=credits")
    fun getSeasonInfosById(@Path("tv_id") tvId: Int, @Path("season_number") seasonNum:Int): Observable<Season>

    // Companion object to create the RemoteApiService,
    //Singleton
    companion object Factory {
        val BASE_URL = "https://api.themoviedb.org/3/"
        val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
        const val API_KEY = "f8ecbc885ba42bd13eeceade9c8fcaf7"
        private var instance:RemoteApiService? = null

        fun create(): RemoteApiService? {
            if(instance == null){
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY

                val httpClient = OkHttpClient.Builder()
                httpClient.addInterceptor(logging)

                val gson = GsonBuilder()
                        .setLenient()
                        .create()

                val retrofit = Retrofit.Builder()
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .baseUrl(BASE_URL)
                        .client(httpClient.build())
                        .build()

                instance =  retrofit.create(RemoteApiService::class.java);
            }
            return instance
        }

        fun getRemoteImage(path:String, context: Context?): RequestBuilder<Drawable>?{
            val URL = BASE_IMAGE_URL + path
            return Glide.with(context).load(URL)
        }
    }
}
