package com.android.example.helloapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.example.helloapp.domain.InputTopicBean
import com.android.example.helloapp.domain.OutputTopicBean
import timber.log.Timber

class TopicAdapter(private val viewModel :HomeViewModel): ListAdapter<OutputTopicBean, TextItemViewHolder>(TopicDiffCallback()) {

    // RecyclerView は、新しい ViewHolder を作成する必要があるたびにこのメソッドを呼び出します。
    // このメソッドは、ViewHolder とそれに関連する View を作成して初期化しますが、
    // ビューのコンテンツは埋めません（ViewHolder はこの時点で特定のデータにバインドされていません）。
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        return TextItemViewHolder.from(parent)
    }

    // RecyclerView はこのメソッドを呼び出して、ViewHolder をデータに関連付けます。
    // このメソッドは適切なデータを取得し、そのデータを使用してビューホルダーのレイアウトを埋めます。
    // たとえば、RecyclerView が名前のリストを表示する場合、
    // このメソッドはリストの中から適切な名前を見つけて、ビューホルダーの TextView ウィジェットを埋めます。
    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, viewModel)

        Timber.i("★item.toString() = ${item.toString()}")
    }
}

class TextItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    private var topicId: Long? = null
    private val note: TextView = itemView.findViewById(R.id.note)
    private val created: TextView = itemView.findViewById(R.id.created)

    fun bind(item: OutputTopicBean, viewModel :HomeViewModel) {
        topicId = item.topicId
        note.text = item.note
        created.text = formatMilliToDateString(item.created)

        // 削除ボタンクリック時の処理
        itemView.findViewById<Button>(R.id.delete_button).setOnClickListener { view ->
            // TODO 動作するがこれでいいかわからない。
            // この処理の実装のためにTopicAdapterのコンストラクタにviewModelを追加が、問題ないか？
            viewModel.removeTopic(topicId!!)
        }
    }

    companion object {
        fun from(parent: ViewGroup): TextItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater
                .inflate(R.layout.text_item_view, parent, false)
            return TextItemViewHolder(view)
        }
    }
}


class TopicDiffCallback : DiffUtil.ItemCallback<OutputTopicBean>() {
    override fun areItemsTheSame(oldItem: OutputTopicBean, newItem: OutputTopicBean): Boolean {
        return oldItem.topicId == newItem.topicId
    }

    override fun areContentsTheSame(oldItem: OutputTopicBean, newItem: OutputTopicBean): Boolean {
        return oldItem == newItem
    }
}

