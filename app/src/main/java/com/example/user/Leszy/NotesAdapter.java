package com.example.user.Leszy;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter <NotesAdapter.MyViewHolder> {

    public NotesAdapter() {}

    private List <NotesBuilder> notesList;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title, content;

        public MyViewHolder(View view) {
            super(view);
            itemView.setOnClickListener(this);
            title = (TextView) view.findViewById(R.id.title);
            content = (TextView) view.findViewById(R.id.content);
        }

    @Override
    public void onClick(View view) {
        for (int i = 0; i < 10; i++) {
            Intent intent = new Intent(view.getContext(), NotepadActivity.class);
            view.getContext().startActivity(intent);
        }
    }
}

    public NotesAdapter(List < NotesBuilder > notesList) {
        this.notesList = notesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NotesBuilder note = notesList.get(position);
        holder.title.setText(note.getTitle());
        holder.content.setText(note.getContent());
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }
}
