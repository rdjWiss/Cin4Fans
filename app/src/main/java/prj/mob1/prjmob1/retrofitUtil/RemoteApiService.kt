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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import prj.mob1.prjmob1.ListItem.ListMovies
import prj.mob1.prjmob1.ListItem.ListShow
import prj.mob1.prjmob1.episode.Episode
import prj.mob1.prjmob1.season.Season
import retrofit2.Response
import prj.mob1.prjmob1.Person.Person
import prj.mob1.prjmob1.show.TVShow
import prj.mob1.prjmob1.movie.MovieClass


//Created by sol on 14/06/2018.


interface RemoteApiService {

    //Get movie infos
    @GET("movie/{movie_id}?api_key=$API_KEY&append_to_response=credits,similar,reviews,videos")
    fun getMovieInfosById(@Path("movie_id") id: Int): Observable<MovieClass>

    //Get person infos
    @GET("person/{person_id}?api_key=$API_KEY&append_to_response=movie_credits,tv_credits")
    fun getPersonInfosById(@Path("person_id") id: Int): Observable<Person>

    //Get tv show infos
    @GET("tv/{tv_id}?api_key=$API_KEY&append_to_response=credits,similar,reviews,videos")
    fun getShowInfosById(@Path("tv_id") id: Int): Observable<TVShow>

    //Get tv show season infos
    @GET("tv/{tv_id}/season/{season_number}?api_key=$API_KEY&append_to_response=credits,videos")
    fun getSeasonInfosById(@Path("tv_id") tvId: Int,
                           @Path("season_number") seasonNum:Int): Observable<Season>

    //Get episode infos
    @GET("tv/{tv_id}/season/{season_number}/episode/{episode_number}?api_key=$API_KEY&append_to_response=")
    fun getEpisodeInfosById(@Path("tv_id") tvId: Int,
                            @Path("season_number") seasonNum:Int,
                            @Path("episode_number") episodeNum:Int): Observable<Episode>

    //Get list of films airing
    @GET("movie/now_playing?api_key=$API_KEY")
    fun getLatesMovies(): Observable<Response<ListMovies>>

    //Get all list of films airing
    @GET("discover/movie?api_key=$API_KEY&include_adult=false")
    fun getAllMovies(): Observable<Response<ListMovies>>

    //Get all list of TV SHOW airing
    @GET("tv/airing_today?api_key=$API_KEY")
    fun getTVShow_now(): Observable<Response<ListShow>>

    //Get all list of TV SHOW airing
    @GET("discover/tv?api_key=$API_KEY")
    fun getAllShow(): Observable<Response<ListShow>>


    // Companion object to create the RemoteApiService,
    //Singleton
    companion object Factory {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
        const val API_KEY = "f8ecbc885ba42bd13eeceade9c8fcaf7"
        const val BASE_YOUTUBE_URL = "http://www.youtube.com/watch?v="

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

                instance =  retrofit.create(RemoteApiService::class.java)
            }
            return instance
        }




        fun getRemoteImage(path:String, context: Context?): RequestBuilder<Drawable>?{
            val URL = BASE_IMAGE_URL + path
            return Glide.with(context).load(URL)
        }


        fun getYoutubeURL(path:String):String {
            return BASE_YOUTUBE_URL + path

        }

        fun <T> sendRequest(observable:Observable<T>,success:(T)->Unit,failure:(Throwable)->Unit)
        {
            observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(success,failure)

        }
    }
}
