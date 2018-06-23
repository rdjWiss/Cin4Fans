package prj.mob1.prjmob1.Settings
import com.google.gson.annotations.SerializedName


/**
 * Created by LE on 22/06/2018.
 */
data class ListeItemChoice
(
 @SerializedName("genres")
val choices: List<ItemChoice> = ArrayList<ItemChoice>()
)

