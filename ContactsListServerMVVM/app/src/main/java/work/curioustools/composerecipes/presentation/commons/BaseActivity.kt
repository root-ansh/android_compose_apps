package work.curioustools.composerecipes.presentation.commons

import android.util.Log
import androidx.activity.ComponentActivity

open class BaseActivity:ComponentActivity()


fun log(s:String,tag:String = "curioustools>>"){
    Log.e(tag, s)
}