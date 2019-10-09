package jobajob.library.uicomponents.widget

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import jobajob.library.uicomponents.R
import kotlinx.android.synthetic.main.searchview.view.*

class SearchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {


    var hint: CharSequence?
        get() = searchViewEditText?.hint
        set(value) {
            searchViewEditText?.hint = value ?: ""
        }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            onTextChangeListener?.invoke(s?.toString() ?: "")
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
    }

    private var onTextChangeListener: ((String) -> Unit)? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.searchview, this, true)
        setBackgroundResource(R.drawable.searchview_background)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        searchViewEditText?.addTextChangedListener(textWatcher)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        searchViewEditText?.removeTextChangedListener(textWatcher)

    }

    fun setOnTextChangeListener(onTextChangeListener: ((String) -> Unit)?) {
        this.onTextChangeListener = onTextChangeListener
    }
}
