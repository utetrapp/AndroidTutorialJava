package de.h_da.fbi.demofirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Message> messages;
    private String userId;
    private Context context;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.");
    private final int VIEW_TYPE_MESSAGE_SENT = 1;
    private final int VIEW_TYPE_MESSAGE_RECEIVED = 0;

    public MessageAdapter(Context context, List<Message> messages, String userId) {
        this.context = context;
        this.messages = messages;
        this.userId = userId;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //default received
        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.viewholder_message_sent, parent, false);
            return new SentMessageViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.viewholder_message_received, parent, false);
            return new ReceivedMessageViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messages.get(position);
        if (message.getUserId().equals(userId)) {
            return VIEW_TYPE_MESSAGE_SENT;
        }
        return VIEW_TYPE_MESSAGE_RECEIVED;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Message message = messages.get(position);
        //default received
        if (holder.getItemViewType() == VIEW_TYPE_MESSAGE_SENT) {
            ((SentMessageViewHolder) holder).bind(message);
        }
        else {
            ((ReceivedMessageViewHolder) holder).bind(message);
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    class SentMessageViewHolder extends RecyclerView.ViewHolder {

        TextView txtContent, txtDate;

        public SentMessageViewHolder(View itemView) {
            super(itemView);

            txtContent = itemView.findViewById(R.id.txtContent);
            txtDate = itemView.findViewById(R.id.txtDate);
        }

        void bind(Message message) {
            txtDate.setText(dateFormat.format(message.getTimestamp()));
            txtContent.setText(message.getContent());
        }
    }

    class ReceivedMessageViewHolder extends RecyclerView.ViewHolder {

        TextView txtContent, txtDate;

        public ReceivedMessageViewHolder(View itemView) {
            super(itemView);

            txtContent = itemView.findViewById(R.id.txtContent);
            txtDate = itemView.findViewById(R.id.txtDate);
        }

        void bind(Message message) {
            txtDate.setText(dateFormat.format(message.getTimestamp()));
            txtContent.setText(String.format("%s: %s", message.getNickname(), message.getContent()));
        }
    }
}


