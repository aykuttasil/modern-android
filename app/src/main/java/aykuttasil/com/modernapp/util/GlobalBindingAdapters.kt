package aykuttasil.com.modernapp.util

import androidx.databinding.BindingAdapter
import android.view.View


/**
 * Created by aykutasil on 19.12.2017.
 */
class GlobalBindingAdapters {

    companion object {
        /**
         * Bind Glide with an ImageView.
         *
         * @param view        the ImageView to bind to Glide.
         * @param src         The URL of the image to load.
         * @param placeholder The placeholder icon.
         * @param error       The error icon.
         * @param blurValue   The blur radius value between 1 and 25.
         * @param cropCircle  Crop the image in a circle of not.
         */
        /*
        @JvmStatic
        @SuppressWarnings("unchecked")
        @BindingAdapter(value = arrayOf("src", "placeholder", "error", "blur", "cropCircle"), requireAll = false)
        fun setGlideAdapter(view: ImageView?, src: String, placeholder: Drawable?, error: Drawable?, blurValue: Int, cropCircle: Boolean) {
            if (view != null) {
                val ctx = view.context
                val glideBuilder = GlideApp.with(ctx).load(src)

                //val filters = arrayListOf<Transformation<Bitmap>>()

                if (placeholder != null) {
                    glideBuilder.placeholder(placeholder)
                }
                if (error != null) {
                    glideBuilder.error(error);
                }

                if (blurValue > 0) {
                    //glideBuilder.transform(BlurTransformation (ctx, blurValue))
                }

                if (cropCircle) {
                    //glideBuilder.transform(CropCircleTransformation (ctx))
                }
                glideBuilder.into(view)
            }
        }
        */

        @JvmStatic
        @BindingAdapter("visibleGone")
        fun showHide(view: View, show: Boolean) = if (show) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }
}


/*
enum class Shape {
    SQUARE, CIRCLE
}

@BindingAdapter("imageUrl", "placeHolder", "shape", requireAll = false)
fun load(view: ImageView, url: String, placeHolder: Drawable?, shape: Shape?) {
    val requestCreator = Picasso.with(view.context)
            .load(url)
            .placeholder(placeHolder)

    requestCreator.apply {
        when (shape) {
            Shape.CIRCLE -> transform(CircleTransform())
            Shape.SQUARE -> TODO()
        }
    }
    requestCreator.into(view)
}
*/