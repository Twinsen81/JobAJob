package jobajob.library.uicomponents.widget

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import jobajob.library.uicomponents.R

class Toolbar @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        @StyleRes defStyle: Int = 0,
        @AttrRes defStyleAttr: Int = androidx.appcompat.R.attr.toolbarStyle) : Toolbar(context, attrs, defStyleAttr) {

    private var viewTitle: TextView? = null

    private var titleTextAppearance: Int = 0
    private var titleTextColor: Int = 0

    init {
        context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.Toolbar,
                defStyleAttr,
                defStyle).apply {
            try {
                val iconColor = getColor(R.styleable.Toolbar_navigationIconColor, ContextCompat.getColor(context, R.color.icons))
                setNavigationIconColor(iconColor)
                titleTextAppearance = getResourceId(androidx.appcompat.R.styleable.Toolbar_titleTextAppearance, 0)
                setTitleTextAppearance(context, titleTextAppearance)
                if (hasValue(androidx.appcompat.R.styleable.Toolbar_titleTextColor)) {
                    setTitleTextColor(getColor(androidx.appcompat.R.styleable.Toolbar_titleTextColor, 0))
                }
                val title = getText(androidx.appcompat.R.styleable.Toolbar_title)
                setTitle(title)
            } finally {
                recycle()
            }
        }

        setNavigationOnClickListener { (context as Activity).finish() }
    }

    fun setNavigationIconColor(color: Int) {
        navigationIcon?.let { DrawableCompat.setTint(it, color) }
    }

    override fun setTitle(@StringRes resId: Int) {
        title = resources.getString(resId)
    }

    override fun setTitle(title: CharSequence?) {
        if (!title.isNullOrEmpty() && !title.isNullOrBlank()) {
            getViewTitle().text = title
        }
    }

    override fun setTitleTextColor(@ColorInt color: Int) {
        titleTextColor = color
        viewTitle?.setTextColor(color)
    }

    override fun setTitleTextAppearance(context: Context, @StyleRes resId: Int) {
        titleTextAppearance = resId
        viewTitle?.setTextAppearance(context, resId)
        //CalligraphyUtils.applyFontToTextView(context, viewTitle, context.getString(R.string.font_medium))
    }

    private fun getViewTitle(): TextView {
        if (viewTitle == null) {
            viewTitle = TextView(context)
            viewTitle?.maxLines = 1
            viewTitle?.ellipsize = TextUtils.TruncateAt.END
            viewTitle?.gravity = Gravity.CENTER
            if (titleTextAppearance != 0) {
                viewTitle?.setTextAppearance(context, titleTextAppearance)
            }
            if (titleTextColor != 0) {
                viewTitle?.setTextColor(titleTextColor)
            }
            val lp = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            lp.gravity = Gravity.CENTER
            viewTitle?.layoutParams = lp
            addView(viewTitle)
            //CalligraphyUtils.applyFontToTextView(context, viewTitle, context.getString(R.string.font_medium))
        }
        return viewTitle as TextView
    }
}