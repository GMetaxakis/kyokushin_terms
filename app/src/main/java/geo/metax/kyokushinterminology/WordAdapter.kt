package geo.metax.kyokushinterminology

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import geo.metax.kyokushinterminology.data.Word
import kotlinx.android.synthetic.main.item_word.view.*

class WordAdapter(private val data: List<Word>) : RecyclerView.Adapter<WordAdapter.ViewHolder>(),
    Filterable {
    private var filterableData: MutableList<Word> = data.toMutableList()

    override fun getFilter(): Filter = object : Filter() {
        override fun performFiltering(charSequence: CharSequence?): FilterResults {
            filterableData.clear()

            val results = FilterResults()
            if (charSequence.isNullOrEmpty()) {
                filterableData.addAll(data)
            } else {
                val filterPattern = charSequence.toString().toLowerCase().trim { it <= ' ' }
                data.forEach {
                    if (it.contains(filterPattern))
                        filterableData.add(it)
                }
            }
            results.values = filterableData
            results.count = filterableData.size
            return results
        }

        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
            this@WordAdapter.notifyDataSetChanged()
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_word,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = filterableData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filterableData[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(word: Word) {
            itemView.tv_type.text = word.wordType.name
            itemView.tv_j.text = word.japanese
            itemView.tv_e.text = word.english
        }
    }
}