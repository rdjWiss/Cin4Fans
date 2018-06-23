package prj.mob1.prjmob1.retrofitUtil.models

import com.google.gson.annotations.SerializedName

/**
 * Created by sol on 19/06/2018.
 */

class PersonMovieCastCredits(@SerializedName("credit_id")val credit_id:String,
                    @SerializedName("id") val movieId:Int,
                    @SerializedName("title") val title:String,
                    @SerializedName("character") val character:String,
                    @SerializedName("release_date") val releaseDate: String,
                    @SerializedName("vote_average") val voteAverage:Double,
                    @SerializedName("overview") val overview:String,
                    @SerializedName("poster_path") val poster:String){
}


class PersonTVCastCredits(@SerializedName("credit_id")val credit_id:String,
                         @SerializedName("id") val showId:Int,
                         @SerializedName("name") val title:String,
                         @SerializedName("character") val character:String,
                         @SerializedName("first_air_date") val releaseDate: String,
                         @SerializedName("vote_average") val voteAverage:Double,
                         @SerializedName("overview") val overview:String,
                         @SerializedName("poster_path") val poster:String,
                        @SerializedName("episode_count") val epCount:String
                            ){
}

class PersonMovieCredits(@SerializedName("cast") val cast:List<PersonMovieCastCredits>)

class PersonTVCredits(@SerializedName("cast") val cast:List<PersonTVCastCredits>)