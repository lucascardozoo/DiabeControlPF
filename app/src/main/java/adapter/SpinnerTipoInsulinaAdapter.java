package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import Entidad.TipoInsulina;
import frgp.utn.edu.ar.appmobile.R;

public class SpinnerTipoInsulinaAdapter extends ArrayAdapter<TipoInsulina> {
    private final List<TipoInsulina> items;
    public SpinnerTipoInsulinaAdapter(@NonNull Context context, @NonNull List<TipoInsulina> items){
        super(context, 0, items);
        this.items = items;
    }
    @Override
    public int getCount(){
        return items.size();
    }
    @Nullable
    @Override
    public TipoInsulina getItem(int position){
        return items.get(position);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        return createItemView(position, convertView, parent);
    }
    @NonNull
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(getContext())
                    .inflate(R.layout.sp_it_tipo_insulina, parent, false);
        }
        TextView txtInsulina = view.findViewById(R.id.txtInsulina);

        TipoInsulina item = items.get(position);
        txtInsulina.setText(item.getNomInsulina());

        return view;
    }
}