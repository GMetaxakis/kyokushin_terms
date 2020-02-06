package geo.metax.kyokushinterminology

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Filterable
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import geo.metax.kyokushinterminology.data.allData
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv.adapter = WordAdapter(data = allData.sortedBy { it.japanese })
        rv.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        search_input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                (rv.adapter as? Filterable)?.filter?.filter(charSequence.toString())
            }
        })

        search_input.threshold = 1
        search_input.setAdapter(
            ArrayAdapter<String>(
                this,
                android.R.layout.select_dialog_item,
                mutableListOf(allData.map { it.japanese }, allData.map { it.english }).flatten()
            )
        )
    }
}
