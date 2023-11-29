package com.example.duan1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

import com.example.duan1.Model.Invoice;
import com.example.duan1.R;
import com.example.duan1.databinding.HistoryItemBinding;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.ViewHolder> {

    private Context context;
    private List<Invoice> historyList;

    public AdapterHistory(Context context, List<Invoice> historyList) {
        this.context = context;
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public AdapterHistory.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_item, parent, false);
        return new AdapterHistory.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHistory.ViewHolder holder, int position) {
        Invoice invoice = historyList.get(position);
        holder.binding.txtEmail.setText(invoice.getEmail());
        Long date = Long.parseLong(invoice.getDate());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm a");
        holder.binding.txtDate.setText(simpleDateFormat.format(date));
        holder.binding.txtTotal.setText("$"+invoice.getTotal());
        holder.binding.txtStatus.setText("Đã giao hàng");
        holder.binding.txtAddress.setText(invoice.getAddress());

    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        HistoryItemBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = HistoryItemBinding.bind(itemView);
        }
    }
}

