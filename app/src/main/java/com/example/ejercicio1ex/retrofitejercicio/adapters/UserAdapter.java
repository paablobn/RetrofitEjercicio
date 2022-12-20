package com.example.ejercicio1ex.retrofitejercicio.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejercicio1ex.retrofitejercicio.R;
import com.example.ejercicio1ex.retrofitejercicio.modelos.DataItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserVH> {
    private List<DataItem> objects;
    private int resource;
    private Context context;

    public UserAdapter(List<DataItem> objects, int resource, Context context) {
        this.objects = objects;
        this.resource = resource;
        this.context = context;
    }

    public class UserVH extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView lbNombre;
        TextView lbApellidos;
        TextView lbEmail;

        public UserVH(@NonNull View itemView) {
            super(itemView);

            imgPhoto = itemView.findViewById(R.id.imgUser);
            lbNombre = itemView.findViewById(R.id.lbNombreUser);
            lbApellidos = itemView.findViewById(R.id.lbApellidosUser);
            lbEmail = itemView.findViewById(R.id.lbEmailUser);

        }
    }

    @NonNull
    @Override
    public UserVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View userView = LayoutInflater.from(context).inflate(resource, null);
        userView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new UserVH(userView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserVH holder, int position) {
        DataItem d = objects.get(position);
        holder.lbEmail.setText(d.getEmail());
        holder.lbNombre.setText(d.getFirstName());
        holder.lbApellidos.setText(d.getLastName());
        Picasso.get()
                .load(d.getAvatar())// url para la descarga de la imagen
                .error(R.drawable.ic_launcher_foreground)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }
}
