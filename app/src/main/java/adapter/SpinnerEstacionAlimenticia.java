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

import Entidad.EstacionAlimenticia;
import frgp.utn.edu.ar.appmobile.R;


public class SpinnerEstacionAlimenticia extends ArrayAdapter<EstacionAlimenticia> {
    private final List<EstacionAlimenticia> items;
    public SpinnerEstacionAlimenticia(@NonNull Context context, @NonNull List<EstacionAlimenticia> items) {
        super(context, 0, items);
        this.items = items;
    }

    @Override
    public int getCount(){
        return items.size();
    }

    @Nullable
    @Override
    public EstacionAlimenticia getItem(int position){
        return items.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Nullable
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(getContext())
                    .inflate(R.layout.sp_it_estacion_alimenticia, parent, false);
        }

        TextView estacionAlimenticia = view.findViewById(R.id.textEstacionAlimenticia);

        EstacionAlimenticia item = items.get(position);

        estacionAlimenticia.setText(item.getEstacionAlimenticia());

        return view;
    }
}
