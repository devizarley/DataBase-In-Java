package br.com.flap.listoftasks.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.flap.listoftasks.R;
import br.com.flap.listoftasks.model.ListTask;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    List<ListTask> listaTarefas;

    public Adapter(List<ListTask> list) {
        listaTarefas = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tarefas_adapter, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ListTask tarefa = listaTarefas.get(position);
        holder.tarefa.setText(tarefa.getTask());

    }

    @Override
    public int getItemCount() {
        return listaTarefas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tarefa;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tarefa = itemView.findViewById(R.id.tarefaText);

        }
    }
}
