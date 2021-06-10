package com.android.example.helloapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.example.helloapp.domain.OutputTopicBean
import timber.log.Timber

class TopicAdapter: RecyclerView.Adapter<TextItemViewHolder>() {
    var data =  listOf<OutputTopicBean>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = data.size

    // RecyclerView は、新しい ViewHolder を作成する必要があるたびにこのメソッドを呼び出します。
    // このメソッドは、ViewHolder とそれに関連する View を作成して初期化しますが、
    // ビューのコンテンツは埋めません（ViewHolder はこの時点で特定のデータにバインドされていません）。
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.text_item_view, parent, false)
        return TextItemViewHolder(view)
    }

    // RecyclerView はこのメソッドを呼び出して、ViewHolder をデータに関連付けます。
    // このメソッドは適切なデータを取得し、そのデータを使用してビューホルダーのレイアウトを埋めます。
    // たとえば、RecyclerView が名前のリストを表示する場合、
    // このメソッドはリストの中から適切な名前を見つけて、ビューホルダーの TextView ウィジェットを埋めます。
    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)

        Timber.i("★item.toString() = ${item.toString()}")
    }
}

class TextItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    private val note: TextView = itemView.findViewById(R.id.note)
    private val created: TextView = itemView.findViewById(R.id.created)

    fun bind(item: OutputTopicBean) {
        note.text = item.note
        created.text = formatMilliToDateString(item.created)
    }
}

